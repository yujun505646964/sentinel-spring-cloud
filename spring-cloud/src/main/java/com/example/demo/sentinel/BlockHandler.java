package com.example.demo.sentinel;
/*
 * blockHandler
    1.方法必须public,不在同一个类中，还必须为static
    2.返回值相同，参数列表相同，可以在参数最后多带一个参数，类型为异常BlockException
    * (com.alibaba.csp.sentinel.slots.system.SystemBlockException系统规则也会被捕获)
    3.SystemBlockException：只对sentinel resource资源起作用，但是当非资源打满系统负载，就可能导致重要资源接口被系统规则限流
 */

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class BlockHandler {

    public static String helloBlockHandler(BlockException ex) {
        System.out.println("blockHandler：" + ex);
        return "blockHandler";
    }
}
