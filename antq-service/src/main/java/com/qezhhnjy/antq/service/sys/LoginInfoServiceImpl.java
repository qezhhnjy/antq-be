package com.qezhhnjy.antq.service.sys;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.entity.sys.LoginInfo;
import com.qezhhnjy.antq.mapper.sys.LoginInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/1/1-19:14
 */
@Service
@Slf4j
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo>
        implements LoginInfoService {

    @Override
    public PageInfo<LoginInfo> query(Query query) {
        String search = query.getSearch();
        List<LoginInfo> result;

        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        if (StrUtil.isBlank(search)) {
            result = lambdaQuery().orderByDesc(LoginInfo::getId).list();
        } else {
            result = lambdaQuery()
                    .like(LoginInfo::getBrowser, search)
                    .or().like(LoginInfo::getClient, search)
                    .or().like(LoginInfo::getEngine, search)
                    .or().like(LoginInfo::getIp, search)
                    .or().like(LoginInfo::getOs, search)
                    .or().like(LoginInfo::getUsername, search)
                    .or().like(LoginInfo::getPlatform, search)
                    .orderByDesc(LoginInfo::getId)
                    .list();
        }
        return new PageInfo<>(result);
    }
}
