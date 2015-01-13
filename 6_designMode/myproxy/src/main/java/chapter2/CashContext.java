package chapter2;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-22
 * Time: ����4:08
 * To change this template use File | Settings | File Templates.
 */
public class CashContext {
    private CashSuper cashSuper;

    public CashContext(String type) {
        if ("����".equals(type)) {
            cashSuper = new CashNormal();
        } else if ("8��".equals(type)) {
            cashSuper = new CashRebate("0.8");
        } else if ("7��".equals(type)) {
            cashSuper = new CashRebate("0.7");
        } else if ("��300��100".equals(type)) {
            cashSuper = new CashReturn("300", "100");
        } else {
            cashSuper = null;
        }
    }

    public double acceptCash(double money) {
        return cashSuper.acceptCash(money);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
