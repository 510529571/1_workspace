package own.hhw.thread.synchronized_.demo4;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-22
 * Time: ����11:01
 * To change this template use File | Settings | File Templates.
 */
public class Carport {
    private Integer num;

    public Carport(Integer num) {
        this.num = num;
    }

    public boolean getCar() throws InterruptedException {
        synchronized (num) {
                if (num == 0) {
                    System.out.println("û�г��ˣ�");
                    return false;
                } else {
                    System.out.println("��ʼ�ó�...");
                    Thread.sleep(10000);
                    System.out.println("ȡ��һ������ʣ" + (--num));
                    return true;
                }
        }
    }

    public boolean getCar2() throws InterruptedException {
        synchronized (num) {
            if (num == 0) {
                System.out.println("û�г��ˣ�");
                return false;
            } else {
                System.out.println("��ʼ�ó�...");
                Thread.sleep(1500);
                --num;
                --num;
                System.out.println("ȡ����������ʣ" + num);
                return true;
            }
        }
    }
}
