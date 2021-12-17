package com.qezhhnjy.antq.common.vo.sys;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2021/12/16-11:27
 */
@Data
@Accessors(chain = true)
public class Tree {

    private String title;

    private Long value;

    private Long key;

    private List<Tree> children;
}
