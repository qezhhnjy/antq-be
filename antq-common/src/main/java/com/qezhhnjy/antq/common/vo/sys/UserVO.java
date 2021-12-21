package com.qezhhnjy.antq.common.vo.sys;

import com.qezhhnjy.antq.entity.sys.Notice;
import com.qezhhnjy.antq.entity.sys.Role;
import com.qezhhnjy.antq.entity.sys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-16:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserVO {

    private User user;

    private List<Role> roleList;

    private Map<String, Boolean> menuMap;

    private List<Notice> noticeList;

    public UserVO(User user, List<Role> roleList) {
        this.user = user;
        this.roleList = roleList;
    }

    public UserVO(User user, List<Role> roleList, Map<String, Boolean> menuMap) {
        this.user = user;
        this.roleList = roleList;
        this.menuMap = menuMap;
    }
}
