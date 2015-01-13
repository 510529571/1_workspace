package own.hhw.util.test;

import org.junit.Test;
import own.hhw.util.PublicTool;

import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-23
 * Time: обнГ3:19
 * To change this template use File | Settings | File Templates.
 */
public class Test_PublicTool {

    @Test
    public void test1(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(PublicTool.getDate("24")));
    }
}
