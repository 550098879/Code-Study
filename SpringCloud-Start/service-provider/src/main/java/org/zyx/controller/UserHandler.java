package org.zyx.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zyx.entity.User;
import org.zyx.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private int port;

    @GetMapping("/findAll")
    public List<User> findAll(){
        System.out.println(port);
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") int id){
        System.out.println(port);
        return userService.findOne(id);
    }


}
