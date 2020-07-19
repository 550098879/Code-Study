package org.zyx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 引导类:SpringBoot应用入口
 */
//@EnableAutoConfiguration :启用SpringBoot应用的自动配置
//@ComponentScan :类似于<context:component-scan base-package="">,自动扫描该类所在的包以及所有子包
//@SpringBootConfiguration : 实质是@Configuration注解,声明一个Java配置类,只允许包含一个
@SpringBootApplication //组合注解:包含了上面的注解,
public class SpringBoot_StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot_StartApplication.class,args);
    }
}
