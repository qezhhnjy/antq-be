package com.qezhhnjy.antq.entity.finance;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author zhaoyangfu
 * @date 2022/1/3-21:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("finance_optional")
public class FinanceOptional extends BaseEntity {

    private long   id;
    private String username;

    @NotBlank(message = "股票代码不能为空")
    private String symbol;

    private String type;

    private String market;
}
