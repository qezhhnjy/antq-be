package com.qezhhnjy.antq.entity.im;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-14:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("im_group")
public class Group extends BaseEntity {

    private String name;
}
