package chapter2;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-22
 * Time: ÏÂÎç3:04
 * To change this template use File | Settings | File Templates.
 */
public class CashReturn implements CashSuper {
    private Double moneyCondition = 0d;
    private Double moneyReturn = 0d;

    public CashReturn(String moneyCondition, String moneyReturn) {
        this.moneyCondition = Double.parseDouble(moneyCondition);
        this.moneyReturn = Double.parseDouble(moneyReturn);
    }

    @Override
    public double acceptCash(double money) {
        if(money>moneyCondition)
            money=money-Math.floor(money/moneyCondition)*moneyReturn;
        return money;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
