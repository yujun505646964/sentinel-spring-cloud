package com.example.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spring")
@Slf4j
public class ConsumerController {

    /*
     * blockHandler 方法必须public ,切返回值相同，参数相同，异常
     * */
    @SentinelResource(value = "/spring/hello", blockHandler = "exceptionHandler")
    @GetMapping("/hello")
    public String hello() {
        return "你好,hello";
    }

    @SentinelResource(value = "/spring/helloName/{name}")
    @GetMapping("/helloName/{name}")
    public String helloName(@PathVariable String name) {
        return "你好," + name;
    }


    @GetMapping("/post")
    public String post() {
        return "POST 请求";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "DELETE 请求";
    }


    public String exceptionHandler(BlockException ex) {
        System.out.println("blockHandler");
        return "blockHandler";
    }

    public void fallbackHandler(String str) {
        log.error("fallbackHandler：" + str);
    }
}