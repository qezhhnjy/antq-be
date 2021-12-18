package com.qezhhnjy.antq.entity.finance;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhaoyangfu
 * @date 2021/12/19-0:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("finance_stock")
public class Stock extends BaseEntity {
    private Long   id;
    private String code;
    private String name;
}
