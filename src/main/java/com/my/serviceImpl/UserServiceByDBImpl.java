package com.my.serviceImpl;

import com.my.ann.NewProxy;
import com.my.ann.ProxyBase;
import com.my.service.UserService;
import org.springframework.stereotype.Service;

@NewProxy(UserServiceByDBImpl.class)
@Service
public class UserServiceByDBImpl extends ProxyBase implements UserService {

    public String createUser(String user) {
        System.out.println("user was created by db!!");
        return user+"was created by db!!!";
    }
}
