package com.java.myspringbootdemo02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.java.myspringbootdemo02.Persistence")
public class MySpringbootRedisMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringbootRedisMqApplication.class, args);
        System.out.println("启动成功");
    }
}
