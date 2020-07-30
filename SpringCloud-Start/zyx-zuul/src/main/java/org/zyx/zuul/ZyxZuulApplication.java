package org.zyx.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy  //启用Zuul网关组件
@EnableDiscoveryClient
public class ZyxZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZyxZuulApplication.class, args);
    }

}
