package com.qezhhnjy.antq.common.query;

import lombok.Data;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-18:50
 */
@Data
public class Query {

    private String search;

    private int pageNum;

    private int pageSize;
}
