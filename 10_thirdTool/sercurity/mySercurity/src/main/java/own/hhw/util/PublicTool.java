package own.hhw.util;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-12
 * Time: 下午4:07
 * To change this template use File | Settings | File Templates.
 */
public class PublicTool {
    public static File makeFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        return file;
    }

    public static void main(String[] args) throws IOException {
       /* new File("F:\\sdf\\sss\\123.txt").getParentFile().mkdirs();
        new File("F:\\sdf\\sss\\123.txt").createNewFile();*/

        //hhw:task 和java项目，LearnEncoding类，testByte方法输出的字节码不一样，很奇怪
        byte[] bs = "汉".getBytes("gbk");
        StringBuilder sb = new StringBuilder("");
        System.out.println(bs.length);
        for(byte b:bs)
        {
            sb.append(Integer.toHexString(b >>> 4 & 0xF));
            sb.append(Integer.toHexString(b & 0xF));
        }
        System.out.println(sb.toString());
    }
}
