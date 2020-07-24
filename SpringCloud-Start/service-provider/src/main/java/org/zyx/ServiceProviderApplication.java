package org.zyx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("org.zyx.mapper")  //使用tk.mapper的包扫描
@SpringBootApplication
@EnableDiscoveryClient //启用Eureka客户端(SpringCloud提供)    ---@EnableEurekaClient(netflix提供) 都可以启动
public class ServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderApplication.class, args);
    }


    @Bean //注入RestTemplate
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
