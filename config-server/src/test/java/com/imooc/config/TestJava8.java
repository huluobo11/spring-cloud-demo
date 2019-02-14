package com.imooc.config;

import java.util.ArrayList;
import java.util.List;

public class TestJava8 {

    public static void main(String[] args) {

        Runnable runnable7 = new Runnable(){
            @Override
            public void run() {
                System.out.println("java7的写法");
            }
        };

        Runnable runnable8 = ()-> System.out.println("java8的写法");

        List<String> list = new ArrayList<>();
        list.forEach(item -> {
            System.out.println("--------------");
            System.out.println(item);
        });

    }
}
