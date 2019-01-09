package com.my.ann;

import java.lang.annotation.*;

@Target(value= ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NewProxy {

    //被代理类类型
    public  Class<?> value();

}
