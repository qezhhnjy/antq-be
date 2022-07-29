package com.qezhhnjy.antq.web.controller.pic;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.AlbumQuery;
import com.qezhhnjy.antq.entity.pic.Album;
import com.qezhhnjy.antq.entity.pic.PicInfo;
import com.qezhhnjy.antq.service.pic.AlbumService;
import com.qezhhnjy.antq.service.pic.PicInfoService;
import com.qezhhnjy.antq.web.util.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        String tmpDirPath = FileUtil.getTmpDirPath();
        String originalFilename = file.getOriginalFilename();
        String prefix = FileUtil.getPrefix(originalFilename);
        String tmpFile = tmpDirPath + originalFilename;
        String tmpDir = tmpDirPath + prefix;
        FileUtil.del(tmpFile);
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

    @PostMapping("/info-page")
    public BaseResult<PageInfo<PicInfo>> infoPage(@RequestBody AlbumQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<PicInfo> list = picInfoService.lambdaQuery().eq(PicInfo::getAlbumId, query.getAlbumId())
                .list();
        return BaseResult.success(PageInfo.of(list));
    }
}
