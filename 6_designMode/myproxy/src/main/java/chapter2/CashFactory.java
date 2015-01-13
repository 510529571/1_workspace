package chapter2;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-22
 * Time: ÏÂÎç3:24
 * To change this template use File | Settings | File Templates.
 */
public class CashFactory {
    public static CashSuper getCash(String type) {
        if ("Õı³£".equals(type)) {
            return new CashNormal();
        } else if ("8ÕÛ".equals(type)) {
            return new CashRebate("0.8");
        } else if ("7ÕÛ".equals(type)) {
            return new CashRebate("0.7");
        } else if ("Âú300·µ100".equals(type)) {
            return new CashReturn("300", "100");
        } else {
            return null;
        }
    }
}
