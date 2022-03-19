package com.qezhhnjy.antq.algorithm.study.tree;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoyangfu
 * @date 2022/3/10-9:41
 */
@Data
@NoArgsConstructor
public class TreeNode<T> {

    private T           value;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T value, TreeNode<T> left, TreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
