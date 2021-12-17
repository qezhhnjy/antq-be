package com.qezhhnjy.antq.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.common.vo.sys.MenuVO;
import com.qezhhnjy.antq.common.vo.sys.Tree;
import com.qezhhnjy.antq.entity.sys.Menu;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:13
 */
public interface MenuService extends IService<Menu> {

    void add(MenuVO vo);

    void update(MenuVO vo);

    MenuVO detail(Long id);

    List<MenuVO> list(Query query);

    void remove(Long id);

    PageInfo<MenuVO> query(Query query);

    Tree tree(boolean menu);

    void loadResourceRolesMap();
}
