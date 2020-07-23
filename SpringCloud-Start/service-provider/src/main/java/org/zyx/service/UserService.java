package org.zyx.service;

import org.springframework.stereotype.Service;
import org.zyx.entity.User;
import org.zyx.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 查找全部
     * @return
     */
    public List<User> findAll(){
        return userMapper.selectAll();
    }

    public User findOne(int id){
        return userMapper.selectByPrimaryKey(id);
    }


}
