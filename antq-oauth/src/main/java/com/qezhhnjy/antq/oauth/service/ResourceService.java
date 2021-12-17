package com.qezhhnjy.antq.oauth.service;

import com.qezhhnjy.antq.service.sys.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 资源与角色匹配关系管理业务类
 * Created by macro on 2020/6/19.
 */
@Service
@Slf4j
public class ResourceService {

    @Resource
    private MenuService                         menuService;

    @PostConstruct
    public void initData() {
        menuService.loadResourceRolesMap();
    }
}
