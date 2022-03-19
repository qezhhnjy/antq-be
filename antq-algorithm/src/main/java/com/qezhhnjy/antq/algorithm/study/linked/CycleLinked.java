package com.qezhhnjy.antq.algorithm.study.linked;

/**
 * @author zhaoyangfu
 * @date 2022/3/10-8:55
 * 寻找循环链表的入口节点
 * 定义：两个 指针，一个是慢指针，一个是快指针
 * <p>
 * 假设 slow 走了 L 步，那么 fast 就走了 2L 步。
 * <p>
 * 我们 链表的头部 到 链表的环的入口结点处 的距离是 S
 * <p>
 * 那么 从入口结点 到 我们 快慢指针相遇的地点 的距离 为 d。
 * <p>
 * 链表的环中，慢指针走过的距离是d，那么没走过的距离是M。
 * <p>
 * 我们不确定的是快指针在链表的环里走过了多少圈来与慢指针相遇，因此 将这个参数设置为n。
 * <p>
 * 那么 L = s + d
 * 2L = 2(s+d) = n*(m + d) + d + s
 * 由上面公式 推导出 n(m+d) = s + d
 * 得到：s = n(m+d) -d；
 * s = nm + (n-1)(d)
 * s = m + (n-1)(m+d)
 */
public class CycleLinked {
}
