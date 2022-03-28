package com.qezhhnjy.antq.oauth.controller;

import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.MenuVO;
import com.qezhhnjy.antq.common.vo.sys.Tree;
import com.qezhhnjy.antq.service.sys.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-17:39
 */
@RestController
@RequestMapping("/menu")
@Slf4j
@CacheConfig(cacheNames = "menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @PostMapping("/add")
    @CacheEvict(beforeInvocation = true, allEntries = true)
    public BaseResult<Void> add(@RequestBody MenuVO vo) {
        menuService.add(vo);
        return BaseResult.success();
    }

    @DeleteMapping("/delete")
    @CacheEvict(beforeInvocation = true, allEntries = true)
    public BaseResult<Void> delete(Long id) {
        menuService.remove(id);
        return BaseResult.success();
    }

    @PutMapping("/update")
    @CacheEvict(beforeInvocation = true, allEntries = true)
    public BaseResult<MenuVO> update(@RequestBody MenuVO vo) {
        menuService.update(vo);
        return BaseResult.success(vo);
    }

    @GetMapping("/detail")
    @Cacheable(key = "#id")
    public BaseResult<MenuVO> detail(Long id) {
        MenuVO vo = menuService.detail(id);
        return BaseResult.success(vo);
    }

    @PostMapping("/list")
    @Cacheable
    public BaseResult<List<MenuVO>> list(@RequestBody Query query) {
        List<MenuVO> list = menuService.list(query);
        return BaseResult.success(list);
    }

    @PostMapping("/query")
    @Cacheable
    public BaseResult<PageInfo<MenuVO>> query(@RequestBody Query query) {
        PageInfo<MenuVO> pageInfo = menuService.query(query);
        return BaseResult.success(pageInfo);
    }

    @GetMapping("/tree")
    @Cacheable
    public BaseResult<Tree> tree(boolean menu) {
        Tree tree = menuService.tree(menu);
        return BaseResult.success(tree);
    }

}
