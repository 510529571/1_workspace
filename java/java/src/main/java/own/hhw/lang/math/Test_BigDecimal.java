package own.hhw.lang.math;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-2-19
 * Time: 上午9:41
 * hhw:task 熟悉了解 精准数据处理对象
 * <p/>
 * 快速输入
 * psvm+Tab键  main方法
 * sout+Tab键  System.out.println();
 */
public class Test_BigDecimal {
    @Test
    public void test_operator() {

        BigDecimal bigd = new BigDecimal("0");

        bigd = bigd.add(BigDecimal.valueOf(1));//add

        System.out.println(bigd.longValue());
    }

    @Test
    public void test_decimalFormat() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(100.01d));
    }

}
