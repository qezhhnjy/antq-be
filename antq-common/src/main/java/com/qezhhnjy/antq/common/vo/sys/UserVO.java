package com.qezhhnjy.antq.common.vo.sys;

import com.qezhhnjy.antq.entity.sys.Role;
import com.qezhhnjy.antq.entity.sys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-16:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    private User user;

    private List<Role> roleList;

}
