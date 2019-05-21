package com.yjc.find;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.yjc.find.dao")
public class FindApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindApplication.class, args);
    }

}
