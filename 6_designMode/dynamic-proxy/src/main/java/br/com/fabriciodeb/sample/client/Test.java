package br.com.fabriciodeb.sample.client;

import br.com.fabriciodeb.sample.Foo;
import br.com.fabriciodeb.sample.impl.FooImpl;
import br.com.fabriciodeb.sample.proxy.ProxyFoo;

/**
 * User: hanwei
 * Date: 15-7-3
 * Time: обнГ5:05
 */
public class Test {
    public static void main(String[] args) {
        FooImpl foo=new FooImpl();
        Foo fooProxy= ProxyFoo.createProxy(foo);

        fooProxy.foo();
        fooProxy.foo();

        fooProxy.fooInt(1);
        fooProxy.fooInt(1);
        fooProxy.fooInt(2);

        fooProxy.fooString(new String("123"));
        fooProxy.fooString(new String("123"));

        Class l=Test.class;
    }
}
