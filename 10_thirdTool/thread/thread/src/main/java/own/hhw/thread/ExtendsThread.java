package own.hhw.thread;

public class ExtendsThread extends Thread {
    @Override
    public void run() {
        // TODO Auto-generated method stub
        /*System.out.println("yes,执行了我！");*/
        super.run();
    }

    public static void main(String[] args) {
//	    ExtendsThread extendsThread=new ExtendsThread();
//	    extendsThread.start();
        System.out.println(1);
        for (int i = 0; i < 1000000000; i++) {
            for (int j = 0; j < 5; j++) {
            }
        }
        System.out.println(2);
    }
}
