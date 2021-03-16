package com.example.demo.controller;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.demo.sentinel.BlockHandler;
import com.example.demo.sentinel.FallbackHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spring")
@Slf4j
public class ConsumerController {

    private static int count = 0;

    /*
     * blockHandler 方法必须public ,切返回值相同，参数相同，异常必须为BlockException
     * 最好设置entryType = EntryType.IN（spring.cloud.sentinel.filter=true起作用的时候，也是IN类型，为什么@SentinelResource默认的是OUT）
     *
     * */
    @SentinelResource(value = "/spring/hello",entryType = EntryType.IN)
    @GetMapping("/block")
    public String block(@RequestParam String name) {
        throw new RuntimeException("发生异常");
    }

    @SentinelResource(value = "/spring/helloName/{name}", fallbackClass = FallbackHandler.class,fallback = "fallbackHandler")
    @GetMapping("/fallback")
    public String fallback() {

        if (++count%2 == 0) {
            throw new RuntimeException("发生异常");
        }
        return "success";
    }


    @GetMapping("/hello")
    @SentinelResource(value = "/hello", blockHandlerClass = BlockHandler.class, blockHandler = "helloBlockHandler",entryType = EntryType.IN)
    public String hello() {
        return "hello world";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "hello world----------2";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "DELETE 请求";
    }

}