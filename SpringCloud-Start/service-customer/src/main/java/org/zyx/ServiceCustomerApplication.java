package org.zyx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
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
