package com.qezhhnjy.antq.common.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhaoyangfu
 * @since 2022/7/30-1:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlbumQuery extends Query {

    private Long albumId;
}
