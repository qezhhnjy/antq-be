package com.qezhhnjy.antq.oauth.service;

import cn.hutool.core.collection.CollUtil;
import com.qezhhnjy.antq.common.consts.RedisConstant;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.MenuVO;
import com.qezhhnjy.antq.entity.sys.Role;
import com.qezhhnjy.antq.service.sys.MenuService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 资源与角色匹配关系管理业务类
 * Created by macro on 2020/6/19.
 */
@Service
public class ResourceService {

    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;
    @Resource
    private MenuService                         menuService;

    @PostConstruct
    public void initData() {
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/hello", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/oauth/user/list", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/oauth/user/add", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/web/hello/user", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/api/user/currentUser", CollUtil.toList("ADMIN", "TEST"));

        List<MenuVO> menuList = menuService.list(new Query());
        if (CollUtil.isEmpty(menuList)) return;
        menuList.forEach(menu -> resourceRolesMap.put(menu.getMenu().getPath(),
                menu.getRoleList().stream().map(Role::getRoleName).collect(Collectors.toList())));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
