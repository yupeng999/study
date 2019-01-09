package com.my.ann;

import com.my.controller.UserController;
import com.my.service.UserService;
import com.my.serviceImpl.UserServiceByDBImpl;

public class Test {

    public void show(UserService srcObj) {
        srcObj = ProxyBase.getInstance(srcObj);
        srcObj.createUser("123412423");
    }

    @org.junit.Test
    public void test() {
        UserService db=new UserServiceByDBImpl();
        show(db);
    }



}
