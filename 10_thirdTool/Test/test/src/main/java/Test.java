import sun.io.ByteToCharBig5;
import sun.io.ByteToCharCp037;

import java.io.File;
import java.io.IOException;
import java.lang.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 15-2-9
* Time: ÏÂÎç6:04
        * To change this template use File | Settings | File Templates.
        */
public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println(11);

        ByteToCharCp037 sdf = new ByteToCharCp037();
        ByteToCharBig5 obj1 = new ByteToCharBig5();

        String cmdStr = "cmd /c copy f:\\ser.txt f:\\ser1.txt" ;
        new File("").createNewFile();
        Runtime.getRuntime().exec(cmdStr);
    }
}
