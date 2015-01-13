package own.hhw.lang.exception;

import org.junit.Test;

//hhw:tag return和throw都有跳出方法的功能，而且return和throw都应该是一个分支的最后一条语句
public class WhereThrow {
    /**
     * 出现这种异常，异常模块后面的程序还会被执行
     */
    @Test
    public void specilThread1() {
        WhereThrow wt = new WhereThrow();
        try {
            wt.function(1);
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            System.out.println("这里一定会执行！");
        }
        System.out.println("这里会被执行");
    }

    /**
     * 出现这种异常，异常块后面的程序将不会被执行
     */
    @Test
    public void specilThread2() throws MyException {
        WhereThrow wt = new WhereThrow();
        try {
            wt.function(1);
        } catch (MyException e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("这里一定会执行！");
        }
        System.out.println("这里会被执行!");    //当catch中有throw或return语句时不执行这里
    }

    public void function(int s) throws MyException {
        if (s == 1)
            throw new MyException("不能为1");
        Integer.parseInt("123");
    }

    @Test
    public void testNull() {
        WhereThrow wt = null;
        int i = 0;
        boolean tag = true;
        while (tag)
            try {
                wt.function(2);
                tag = false;
            } catch (MyException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } finally {
                i++;
                if (i == 10) {
                    wt = new WhereThrow();
                }
            }
    }

    @Test
    public void testRunTimeExcpetion() {
        try {

            testRunTimeExcpetion1();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("这里还会执行吗？2");
    }

    public void testRunTimeExcpetion1(){
        WhereThrow wt = new WhereThrow();
        try {
            wt.function(1);
        } catch (MyException e) {
//            e.printStackTrace();
            throw new MyRuntimeException("这里不能为1",e);
        }
        System.out.println("这里还会执行吗？1");
    }

    @Test
    public void testArrayOut() {
        Integer[] a = {1, 2};
        try {
            try {
                int m = a[2];
            } catch (ArrayIndexOutOfBoundsException e) {
                //hhw:tag 异常的这个构造方法让我们可以对异常进行二次处理了
                throw new MyException("数组的大小为" + a.length, e);
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
