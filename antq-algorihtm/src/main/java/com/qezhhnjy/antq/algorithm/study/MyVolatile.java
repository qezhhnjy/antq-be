package com.qezhhnjy.antq.algorithm.study;

import cn.hutool.core.thread.ThreadUtil;

/**
 * @author zhaoyangfu
 * @date 2022/2/22-11:23
 */
public class MyVolatile {

    private static volatile boolean mark = false;

    public static void main(String[] args) {
        new Thread(() -> {
            ThreadUtil.sleep(1000);
            mark = true;
            System.out.println(mark);
        }).start();

        // 1. 不使用volatile修饰的情况下，如果while中什么都做，则程序一直无法结束并退出，但只需要在while中添加以下任意一个处理则会立刻退出。
        //    考虑，可能是空循环线程一直工作，没有时间获得主内存的数据更新，一旦内部有执行语句则有空闲刷新数据。
        // 2. 使用volatile则可有避免这种无法及时更新的问题。
        new Thread(() -> {
            while (!mark) {
                // System.out.println("false");
                // ThreadUtil.sleep(500);
            }
            System.out.println("count is true");
        }).start();
    }
}
