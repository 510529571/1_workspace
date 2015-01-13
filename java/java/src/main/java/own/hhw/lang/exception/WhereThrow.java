package own.hhw.lang.exception;

import org.junit.Test;

//hhw:tag return��throw�������������Ĺ��ܣ�����return��throw��Ӧ����һ����֧�����һ�����
public class WhereThrow {
    /**
     * ���������쳣���쳣ģ�����ĳ��򻹻ᱻִ��
     */
    @Test
    public void specilThread1() {
        WhereThrow wt = new WhereThrow();
        try {
            wt.function(1);
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            System.out.println("����һ����ִ�У�");
        }
        System.out.println("����ᱻִ��");
    }

    /**
     * ���������쳣���쳣�����ĳ��򽫲��ᱻִ��
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
            System.out.println("����һ����ִ�У�");
        }
        System.out.println("����ᱻִ��!");    //��catch����throw��return���ʱ��ִ������
    }

    public void function(int s) throws MyException {
        if (s == 1)
            throw new MyException("����Ϊ1");
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
        System.out.println("���ﻹ��ִ����2");
    }

    public void testRunTimeExcpetion1(){
        WhereThrow wt = new WhereThrow();
        try {
            wt.function(1);
        } catch (MyException e) {
//            e.printStackTrace();
            throw new MyRuntimeException("���ﲻ��Ϊ1",e);
        }
        System.out.println("���ﻹ��ִ����1");
    }

    @Test
    public void testArrayOut() {
        Integer[] a = {1, 2};
        try {
            try {
                int m = a[2];
            } catch (ArrayIndexOutOfBoundsException e) {
                //hhw:tag �쳣��������췽�������ǿ��Զ��쳣���ж��δ�����
                throw new MyException("����Ĵ�СΪ" + a.length, e);
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
