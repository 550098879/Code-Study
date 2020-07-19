package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping("/")
@EnableAutoConfiguration //启用自动配置(自动配置相关的文件,类似SpringBoot启动器)
public class HelloController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/show")
    public String hello(){
        return "Hello SpringBoot!!!";
    }

    /**
     * 仅限当前类使用
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(HelloController.class,args);
    }


}
