package com.qezhhnjy.antq.oauth.controller;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.MenuVO;
import com.qezhhnjy.antq.service.sys.MenuService;
import lombok.extern.slf4j.Slf4j;
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
public class MenuController {

    @Resource
    private MenuService menuService;

    @PostMapping("/add")
    public BaseResult<Void> add(@RequestBody MenuVO vo) {
        menuService.add(vo);
        return BaseResult.success();
    }

    @DeleteMapping("/delete")
    public BaseResult<Void> delete(Long id) {
        menuService.remove(id);
        return BaseResult.success();
    }

    @PutMapping("/update")
    public BaseResult<MenuVO> update(@RequestBody MenuVO vo) {
        menuService.update(vo);
        return BaseResult.success(vo);
    }

    @GetMapping("/detail")
    public BaseResult<MenuVO> detail(Long id) {
        MenuVO vo = menuService.detail(id);
        return BaseResult.success(vo);
    }

    @PostMapping("/list")
    public BaseResult<List<MenuVO>> list(@RequestBody Query query) {
        List<MenuVO> list = menuService.list(query);
        return BaseResult.success(list);
    }

}
