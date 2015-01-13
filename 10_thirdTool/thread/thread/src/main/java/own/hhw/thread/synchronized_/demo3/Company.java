package own.hhw.thread.synchronized_.demo3;


/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-22
 * Time: 上午11:23
 * To change this template use File | Settings | File Templates.
 */
public class Company {
    public static void main(String[] args) {
        Carport carport = new Carport(2);
        new Thread(new Courier(carport), "1").start();
        new Thread(new Courier(carport), "2").start();
        new Thread(new Courier(carport), "3").start();
        new Thread(new Courier(carport), "4").start();
        new Thread(new Courier(carport), "5").start();
    }
}


