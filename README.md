# study
 service 注入动态代理，实现运行时动态调用不同实现类
 ``` java
 // 注解类   用于接口的实现类上，此实现类必须继承ProxyBase
 
@Target(value= ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NewProxy {

    //被代理类类型
    public  Class<?> value();

}


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
                    Object instance = 
                    Proxy.newProxyInstance(srcObj.getClass().getClassLoader(), srcObj.getClass().getInterfaces(), proxyBase);
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

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke...................");
        return method.invoke(srcObj, args);
    }
}

//测试类
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
 
 ```
