package chapter2;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-22
 * Time: ����3:23
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        CashSuper cashSuper=CashFactory.getCash("8��");
        System.out.println(cashSuper.acceptCash(300));

        CashContext cashContext=new CashContext("8��");
        System.out.println(cashContext.acceptCash(300));
    }
}
