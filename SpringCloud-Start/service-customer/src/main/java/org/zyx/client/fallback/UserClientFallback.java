package org.zyx.client.fallback;

import org.springframework.stereotype.Component;
import org.zyx.client.UserClient;
import org.zyx.entity.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        User user = new User();
        user.setUsername("服务器正忙");
        return user;
    }
}
