package com.qezhhnjy.antq.algorithm.study.tree;

import cn.hutool.core.collection.ListUtil;

import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/3/10-9:41
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class Rebuild {

    public static void main(String[] args) {
        List<Integer> pre = ListUtil.of(1, 2, 4, 7, 3, 5, 6, 8);
        List<Integer> tin = ListUtil.of(4, 7, 2, 1, 5, 3, 8, 6);

        TreeNode<Integer> root = rebuild(pre, tin);
        System.out.println(root);
    }

    public static TreeNode<Integer> rebuild(List<Integer> pre, List<Integer> tin) {
        if (pre.size() == 0 || tin.size() == 0) return null;
        TreeNode<Integer> root = new TreeNode<>();
        root.setValue(pre.get(0));
        int tinIndex = tin.indexOf(pre.get(0));
        root.setLeft(rebuild(pre.subList(1, tinIndex + 1), tin.subList(0, tinIndex)));
        root.setRight(rebuild(pre.subList(tinIndex + 1, pre.size()), tin.subList(tinIndex + 1, tin.size())));
        return root;
    }
}
