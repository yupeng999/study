package com.my.serviceImpl;

import com.my.ann.NewProxy;
import com.my.ann.ProxyBase;
import com.my.service.UserService;
import org.springframework.stereotype.Service;

@NewProxy(UserServiceByFileImpl.class)
@Service
public class UserServiceByFileImpl extends ProxyBase implements UserService {

    public String createUser(String user) {
        System.out.println("user was created by file!!!------");
        return user+"was created by file!!!";
    }
}
