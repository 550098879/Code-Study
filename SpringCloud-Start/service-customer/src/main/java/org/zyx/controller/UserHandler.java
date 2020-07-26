package org.zyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.zyx.entity.User;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customer/user")
public class UserHandler {

    @Autowired
    private RestTemplate restTemplate;

//    @Resource
//    private DiscoveryClient discoveryClient; //包含了拉取的所有服务信息

    @GetMapping("/findAll")
    public List<User> findAll(){
        //获取相应的服务信息(原生手法)
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
//        ServiceInstance instance = instances.get(0);
//        String prefix_Url = "http://"+instance.getHost()+":"+instance.getPort();
//        return restTemplate.getForObject(prefix_Url+"/user/findAll",List.class);

        //使用Ribbon实现负载均衡
        return restTemplate.getForObject("http://service-provider/user/findAll",List.class);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") int id){
        //原生手法
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
//        ServiceInstance instance = instances.get(0);
//        String prefix_Url = "http://"+instance.getHost()+":"+instance.getPort();
//        return restTemplate.getForObject(prefix_Url+"/user/"+id,User.class);

        return restTemplate.getForObject("http://service-provider/user/"+id,User.class);
    }


}
