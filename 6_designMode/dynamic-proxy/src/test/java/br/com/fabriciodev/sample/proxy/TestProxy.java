package br.com.fabriciodev.sample.proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.junit.Test;

import br.com.fabriciodeb.sample.Address;
import br.com.fabriciodeb.sample.Foo;
import br.com.fabriciodeb.sample.Person;
import br.com.fabriciodeb.sample.impl.FooImpl;
import br.com.fabriciodeb.sample.proxy.DynamicProxyEntityInterceptor;
import br.com.fabriciodeb.sample.proxy.EntityInterceptor;
import br.com.fabriciodeb.sample.proxy.LoggerInterceptor;
import br.com.fabriciodeb.sample.proxy.ProxyFoo;
import sun.misc.PostVMInitHook;

public class TestProxy {

    /**
     * 使用jdk的reflect提供动态代理
     */
	@Test
	public void testProxy() {
        Foo foo = new FooImpl();

		Foo proxy = (Foo) ProxyFoo.createProxy(foo);

		proxy.foo();
	}

    /**
     *使用cglib包的功能提供动态代理
     */
	@Test
	public void proxyPerson() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Person.class);
		enhancer.setCallback(new MethodInterceptor() {

			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("before: " + method);

				proxy.invokeSuper(obj, args);

				System.out.println("after: " + method);

				return null;
			}
		});

		Person proxy = (Person) enhancer.create();

		proxy.getId();

		proxy.getName();

		proxy.setName("Fabricio");
	}

    /**
     * 目的同上，只是做了封装
     */
	@Test
	public void testLoggerProxy() {
		Person person = LoggerInterceptor.createProxy(Person.class);

		person.setName("Fabricio");

		System.out.println(person.getName());
	}


    /**
     * 看到了吗，定义的代理类可以代理任何对象
     */
    @Test
    public void testLoggerProxyFoo() {
        FooImpl foo = LoggerInterceptor.createProxy(FooImpl.class);

        foo.foo();

    }

    public static void main(String[] args) {
        assertEquals(0, 1);
    }

    @SuppressWarnings("unchecked")
	@Test
	public void dynamicProxy() {
		Person person = new Person();

		Person proxy = DynamicProxyEntityInterceptor.createProxy(person);

		proxy.setName("Fabricio");

		assertNull("name is not null", person.getName());
		assertEquals("Fabricio", proxy.getName());

		EntityInterceptor<Person> interceptor = (EntityInterceptor<Person>) proxy;

		Person applied = interceptor.applyChanges();

		assertNotNull("Not apply changes", applied);
		assertEquals("Fabricio", applied.getName());

		proxy.getAddresses().add(new Address());

		assertEquals(0, person.getAddresses().size());
		assertEquals(1, proxy.getAddresses().size());

		proxy.getAddresses().get(0).setAddress("Avenue Brasil");

		assertEquals("Avenue Brasil", proxy.getAddresses().get(0).getAddress());

		person = interceptor.applyChanges();

		assertEquals(1, person.getAddresses().size());
		assertEquals("Avenue Brasil", person.getAddresses().get(0).getAddress());

		proxy.getAddresses().get(0).setAddress("Changed");
		person = interceptor.applyChanges();
		assertEquals(1, person.getAddresses().size());
		assertEquals("Changed", person.getAddresses().get(0).getAddress());

		proxy.getAddresses().remove(0);
		person = interceptor.applyChanges();
		assertEquals(0, person.getAddresses().size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void dynamicProxy2() {
		Person person = new Person();
		person.getAddresses().add(new Address("address"));
		
		Person proxy = DynamicProxyEntityInterceptor.createProxy(person);
		
		proxy.getAddresses().get(0).setAddress("new");
		
		EntityInterceptor<Person> interceptor = (EntityInterceptor<Person>) proxy;
		
		assertEquals("address", person.getAddresses().get(0).getAddress());
		assertEquals("new", proxy.getAddresses().get(0).getAddress());
		
		interceptor.applyChanges();
		
		assertEquals("new", person.getAddresses().get(0).getAddress());
}
}