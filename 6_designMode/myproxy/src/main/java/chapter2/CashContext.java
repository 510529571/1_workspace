package chapter2;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-22
 * Time: ÏÂÎç4:08
 * To change this template use File | Settings | File Templates.
 */
public class CashContext {
    private CashSuper cashSuper;

    public CashContext(String type) {
        if ("Õý³£".equals(type)) {
            cashSuper = new CashNormal();
        } else if ("8ÕÛ".equals(type)) {
            cashSuper = new CashRebate("0.8");
        } else if ("7ÕÛ".equals(type)) {
            cashSuper = new CashRebate("0.7");
        } else if ("Âú300·µ100".equals(type)) {
            cashSuper = new CashReturn("300", "100");
        } else {
            cashSuper = null;
        }
    }

    public double acceptCash(double money) {
        return cashSuper.acceptCash(money);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
