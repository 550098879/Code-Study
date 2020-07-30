package org.zyx.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.zyx.client.UserClient;

import javax.annotation.Resource;

@RestController
@RequestMapping("/customer/user")
@DefaultProperties(defaultFallback = "fallbackMethod")  //指定默认的全局熔断方法(控制器中的所有方法返回值需要一致)
public class UserHandler {

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private UserClient userClient;

//    @Resource
//    private DiscoveryClient discoveryClient; //包含了拉取的所有服务信息

    @GetMapping("/findAll")
    @HystrixCommand
    public String findAll(){
        //获取相应的服务信息(原生手法)
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
//        ServiceInstance instance = instances.get(0);
//        String prefix_Url = "http://"+instance.getHost()+":"+instance.getPort();
//        return restTemplate.getForObject(prefix_Url+"/user/findAll",List.class);

        //使用Ribbon实现负载均衡
        //return restTemplate.getForObject("http://service-provider/user/findAll",String.class);

        //使用Feign接口获取数据
        return userClient.findAll().toString();
    }

    @GetMapping("/{id}")
//    @HystrixCommand
    @HystrixCommand(fallbackMethod = "findByIdFallback") //服务降级触发应急事件
    public String findById(@PathVariable("id") int id){
        //原生手法
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
//        ServiceInstance instance = instances.get(0);
//        String prefix_Url = "http://"+instance.getHost()+":"+instance.getPort();
//        return restTemplate.getForObject(prefix_Url+"/user/"+id,User.class);

        if(id < 1){//手动引发请求异常,实现熔断机制
            throw new RuntimeException(); //抛出异常会被服务降级
        }
        //开启Ribbon负载均衡,直接通过服务名发送请求
       // return restTemplate.getForObject("http://service-provider/user/"+id,String.class);

        return userClient.findById(2).toString();
    }

    /**
     * 方法级:熔断降级方法 (findById方法对应的熔断方法)
     * @param id 参数需要和对应的请求方法一致
     * @return
     */
    public String findByIdFallback(int id){
        return "服务器正忙,请稍后再试";
    }

    /**
     * 全局熔断方法
     * @return 返回值需要统一
     */
    public String  fallbackMethod(){
        return "服务器正忙,请稍后再试(全局熔断方法)";
    }


}
