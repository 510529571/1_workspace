package java.lang;

import own.hhw.lang.classloader.Test;

/**
 * User: hanwei
 * Date: 16-3-14
 * Time: ����2:03
 */
public class Test1 implements Test {
    public void say(){
        System.out.println("����Test" );
        new Test2().say();
    }
}
