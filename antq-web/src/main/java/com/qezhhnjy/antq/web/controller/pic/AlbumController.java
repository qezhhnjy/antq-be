package com.qezhhnjy.antq.web.controller.pic;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.enums.ResultCode;
import com.qezhhnjy.antq.common.exception.BizException;
import com.qezhhnjy.antq.common.query.AlbumQuery;
import com.qezhhnjy.antq.entity.pic.Album;
import com.qezhhnjy.antq.entity.pic.PicInfo;
import com.qezhhnjy.antq.service.pic.AlbumService;
import com.qezhhnjy.antq.service.pic.PicInfoService;
import com.qezhhnjy.antq.web.util.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhaoyangfu
 * @since 2022/7/27-23:08
 */
@RestController
@RequestMapping("/album")
@Slf4j
public class AlbumController {

    @Resource
    private MinioUtil      minioUtil;
    @Resource
    private AlbumService   albumService;
    @Resource
    private PicInfoService picInfoService;

    @PostMapping("/upload")
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<?> upload(MultipartFile file, Album album) throws Exception {
        log.info("album=>{}", album);
        String station = album.getStation();
        String title = album.getTitle();
        albumService.lambdaQuery()
                .eq(Album::getStation, station)
                .eq(Album::getTitle, title)
                .oneOpt()
                .ifPresent(exist -> {
                    throw new BizException(ResultCode.DATA_EXIST);
                });

        String tmpDirPath = FileUtil.getTmpDirPath();
        String originalFilename = file.getOriginalFilename();
        String prefix = FileUtil.getPrefix(originalFilename);
        String tmpFile = tmpDirPath + originalFilename;
        String tmpDir = tmpDirPath + prefix;
        FileUtil.del(tmpFile);
        FileUtil.del(tmpDir);
        List<String> urls = new ArrayList<>();
        try {
            file.transferTo(Paths.get(tmpFile));
            ZipUtil.unzip(tmpFile, Charset.forName(System.getProperty("sun.jnu.encoding")));
            FileUtil.del(tmpFile);
            for (String name : FileUtil.listFileNames(tmpDir)) {
                log.info("name=>{}", tmpDir + name);
                String url = minioUtil.upload(tmpDir, name, album);
                urls.add(url);
                if (StrUtil.isBlank(album.getCover())) album.setCover(url);
            }
            album.setCount(urls.size());
            albumService.save(album);
            Long id = album.getId();
            for (int i = 0; i < urls.size(); i++) {
                String url = urls.get(i);
                PicInfo picInfo = new PicInfo().setAlbumId(id).setSequence(i).setUrl(url);
                picInfoService.save(picInfo);
            }
            return BaseResult.success(id);
        } finally {
            FileUtil.del(tmpFile);
            FileUtil.del(tmpDir);
        }
    }

    @PostMapping("/add-pic")
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<PicInfo> addPic(MultipartFile file, Long albumId) throws Exception {
        Album album = albumService.getById(albumId);
        String url = minioUtil.upload(file, album);
        PicInfo info = new PicInfo().setAlbumId(albumId).setUrl(url);
        picInfoService.lambdaQuery().eq(PicInfo::getAlbumId, albumId)
                .orderByDesc(PicInfo::getSequence)
                .last("LIMIT 1")
                .oneOpt()
                .ifPresent(max -> info.setSequence(max.getSequence() + 1));
        picInfoService.save(info);
        albumService.lambdaUpdate()
                .eq(Album::getId, albumId)
                .set(Album::getCount, album.getCount() + 1)
                .update();

        return BaseResult.success(info);
    }

    @DeleteMapping("/delete")
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<?> delete(Long id) throws Exception {
        Album album = albumService.getById(id);
        Objects.requireNonNull(album, "图集不存在");
        minioUtil.remove(album.getCover());
        albumService.removeById(id);

        List<PicInfo> list = picInfoService.lambdaQuery().eq(PicInfo::getAlbumId, id).list();
        minioUtil.removeObjects(list.stream().map(PicInfo::getUrl).collect(Collectors.toList()));
        picInfoService.lambdaUpdate()
                .eq(PicInfo::getAlbumId, id)
                .remove();
        return BaseResult.success(id);
    }

    @DeleteMapping("/delete-pic")
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<?> deletePic(Long id) throws Exception {
        PicInfo pic = picInfoService.getById(id);
        minioUtil.remove(pic.getUrl());
        picInfoService.removeById(id);
        Album album = albumService.getById(pic.getAlbumId());
        albumService.lambdaUpdate()
                .eq(Album::getId, album.getId())
                .set(Album::getCount, album.getCount() - 1)
                .update();
        return BaseResult.success(id);
    }

    @PostMapping("/album-page")
    public BaseResult<PageInfo<Album>> albumPage(@RequestBody AlbumQuery query) {
        String search = query.getSearch();
        boolean notBlank = StrUtil.isNotBlank(search);
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<Album> list = albumService.lambdaQuery()
                .and(notBlank, i -> i.like(Album::getTitle, search)
                        .or().like(Album::getAuthor, search)
                        .or().like(Album::getModel, search)
                        .or().like(Album::getStation, search)
                        .or().like(Album::getTag, search)
                )
                .orderByDesc(Album::getId)
                .list();
        return BaseResult.success(PageInfo.of(list));
    }

    @PostMapping("/info-page")
    public BaseResult<PageInfo<PicInfo>> infoPage(@RequestBody AlbumQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<PicInfo> list = picInfoService.lambdaQuery()
                .eq(PicInfo::getAlbumId, query.getAlbumId())
                .list();
        return BaseResult.success(PageInfo.of(list));
    }
}
