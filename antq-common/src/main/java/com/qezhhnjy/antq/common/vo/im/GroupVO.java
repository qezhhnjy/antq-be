package com.qezhhnjy.antq.common.vo.im;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-15:09
 */
@Data
public class GroupVO {

    private Long       groupId;
    @NotNull
    private String       groupName;
    @NotEmpty
    private List<String> userList;
}
