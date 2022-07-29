package com.qezhhnjy.antq.service.pic;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qezhhnjy.antq.entity.pic.Album;
import com.qezhhnjy.antq.mapper.pic.AlbumMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyangfu
 * @since 2022/7/27-23:02
 */
@Service
@Slf4j
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {
}
