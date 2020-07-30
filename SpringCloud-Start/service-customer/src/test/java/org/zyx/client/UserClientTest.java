package org.zyx.client;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserClientTest {

    @Resource
    private UserClient userClient;

    @Test
    void clientTest(){
        System.out.println(userClient.findById(1));
    }

}