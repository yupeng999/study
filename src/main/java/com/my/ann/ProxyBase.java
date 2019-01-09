package com.my.ann;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBase implements InvocationHandler {

    private Object srcObj;

    static class ProxyHelp {
        private static final Class<NewProxy> newProxy = NewProxy.class;

        // 获取代理对象，或是原生对象
        public static <T> T getInstance(T srcObj) {//srcObj被代理对象
            Class<?> tclass = srcObj.getClass();//被代理类的class
            if (tclass.getAnnotation(ProxyHelp.newProxy) != null) {//判断被代理类上是否有JsProxy注解
                if (srcObj instanceof ProxyBase) {
                    ProxyBase proxyBase = (ProxyBase) srcObj;//强转  用于invoke
                    proxyBase.srcObj = srcObj;
                    Object instance = Proxy.newProxyInstance(srcObj.getClass().getClassLoader(), srcObj.getClass().getInterfaces(), proxyBase);
                    if (instance == null) {
                        return null;
                    }
                    return (T) instance;
                }
            }
            return srcObj;
        }
    }

    public static <T> T getInstance(T t) {
        return ProxyHelp.getInstance(t);
    }

    // 代理
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke...................");
        return method.invoke(srcObj, args);
    }
}

