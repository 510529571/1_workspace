package own.hhw.thread.synchronized_.demo3;

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
            while(num==2){
                wait(100);
            }
            if (num == 0) {
                System.out.println("û�г��ˣ�");
                return false;
            } else {
                System.out.println("��ʼ�ó�...");
                Thread.sleep(2000);
                System.out.println("ȡ��һ������ʣ" + (--num));
                return true;
            }
        }
    }

    /**
     * hhw:task ΪʲôgetCar��returnCarû����סͬһ��Դ�أ�
     * @throws InterruptedException
     */
    public void returnCar() throws InterruptedException {
        synchronized (num) {
            System.out.println("��ʼ����...");
            Thread.sleep(500);
            System.out.println("����һ������ʣ" + (++num));
        }
    }
}
