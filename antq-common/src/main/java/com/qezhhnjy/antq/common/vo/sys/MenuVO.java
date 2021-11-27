package com.qezhhnjy.antq.common.vo.sys;

import com.qezhhnjy.antq.entity.sys.Menu;
import com.qezhhnjy.antq.entity.sys.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-16:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO {

    private Menu menu;

    private List<Role> roleList;
}
