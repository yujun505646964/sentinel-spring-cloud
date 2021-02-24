package com.example.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.demo.sentinel.BlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spring")
@Slf4j
public class ConsumerController {

    /*
     * blockHandler 方法必须public ,切返回值相同，参数相同，异常必须为BlockException
     * */
    @SentinelResource(value = "/spring/hello", blockHandler = "exceptionHandler")
    @GetMapping("/block")
    public String block(@RequestParam String name) {
        throw new RuntimeException("发生异常");
    }

    @SentinelResource(value = "/spring/helloName/{name}", fallback = "fallbackHandler")
    @GetMapping("/fallback")
    public String fallback() {
        throw new RuntimeException("发生异常");
    }


    @GetMapping("/hello")
    @SentinelResource(value = "/hello",blockHandlerClass = BlockHandler.class,blockHandler = "helloBlockHandler")
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


    public String fallbackHandler() {
        System.out.println("fallbackHandler");
        return "fallbackHandler";
    }
}