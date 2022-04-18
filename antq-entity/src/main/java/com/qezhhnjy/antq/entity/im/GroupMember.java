package com.qezhhnjy.antq.entity.im;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-14:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("im_group_member")
public class GroupMember extends BaseEntity {

    private Long   id;
    private String groupName;
    private Long   groupId;
    private String username;
}
