package org.zyx.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zyx.client.fallback.UserClientFallback;
import org.zyx.entity.User;

import java.util.List;

/**
 * 基于Feign组件,模拟SpringMVC发送请求
 */
@FeignClient(value = "service-provider",fallback = UserClientFallback.class)
public interface UserClient {

    @GetMapping("/user/findAll")
    List<User> findAll();

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") int id);


}
