package helloworld;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.logging.Logger;

public class ClientProxy implements MethodInterceptor {
    private Object target;
    private static final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());

    public ClientProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        logger.info("cglib拦截器执行前-------------------------------");
        logger.info("obj = " + obj.getClass());
        logger.info("method = " + method);
        logger.info("proxy = " + proxy);

        Object object = proxy.invoke(target, args);
        logger.info("cglib拦截器执行后-------------------------------");
        return object;
    }

    public <T> T getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

}
