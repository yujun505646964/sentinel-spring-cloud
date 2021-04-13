package com.example.demo.sentinel;

public class FallbackHandler {

    public  static String fallbackHandler() {
        System.out.println("fallbackHandler：");
        return "fallbackHandler";
    }
}
