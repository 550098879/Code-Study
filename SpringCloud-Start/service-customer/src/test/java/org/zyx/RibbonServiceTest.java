package org.zyx;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;

import javax.annotation.Resource;

@SpringBootTest
public class RibbonServiceTest {

    @Resource
    private RibbonLoadBalancerClient client;

    @Test
    public void ribbonTest(){

        for (int i = 0; i < 50; i++) {
            ServiceInstance instance = this.client.choose("service-provider");
            System.out.println(instance.getHost()+":"+instance.getPort());
        }
    }




}
