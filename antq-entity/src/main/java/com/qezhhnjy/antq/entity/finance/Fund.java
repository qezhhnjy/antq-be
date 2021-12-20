package com.qezhhnjy.antq.entity.finance;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhaoyangfu
 * @date 2021/12/19-15:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("finance_fund")
public class Fund extends BaseEntity {
    private Long   id;
    private String code;
    private String name;
    private String type;
}
