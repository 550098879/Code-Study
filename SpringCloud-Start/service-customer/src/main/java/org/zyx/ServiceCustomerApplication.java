package org.zyx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})   //排除数据源自动配置类
@EnableDiscoveryClient   //启用Eureka客户端发现
@EnableCircuitBreaker  //开启熔断器
//@SpringCloudApplication  //组合注解,包含上面三种注解
@EnableFeignClients //启用Feign组件
public class ServiceCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCustomerApplication.class, args);
    }

    @Bean
    @LoadBalanced   //开启Ribbon负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
