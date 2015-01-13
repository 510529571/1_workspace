package br.com.fabriciodeb.sample.proxy;

import java.lang.reflect.Method;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class LoggerInterceptor<T> implements MethodInterceptor {


	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before method: " + method);

		Object result = proxy.invokeSuper(obj, args);

        System.out.println("after method: " + method);
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T createProxy(Class<T> clazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(new LoggerInterceptor<T>());

		return (T) enhancer.create();
	}

}
