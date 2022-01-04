package com.qezhhnjy.antq.common.enums;

/**
 * @author zhaoyangfu
 * @date 2022/1/4-16:14
 */
public enum MarketEnum {
    CN("￥"),
    HK("HK$"),
    USA("$"),
    ;

    public final String symbol;

    MarketEnum(String symbol) {
        this.symbol = symbol;
    }
}
