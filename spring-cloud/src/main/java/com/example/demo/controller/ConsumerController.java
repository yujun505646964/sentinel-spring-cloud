package com.example.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spring")
public class ConsumerController {

    @SentinelResource(value = "/spring/hello")
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

}