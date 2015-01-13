package chapter2;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-22
 * Time: обнГ3:02
 * To change this template use File | Settings | File Templates.
 */
public class CashRebate implements CashSuper {
    private Double moneyRebate;

    public CashRebate(String moneyRebate) {
        this.moneyRebate = Double.parseDouble(moneyRebate);
    }

    @Override
    public double acceptCash(double money) {
        return money*moneyRebate;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
