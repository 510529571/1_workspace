package own.hhw.util;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-12
 * Time: ����4:07
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

        //hhw:task ��java��Ŀ��LearnEncoding�࣬testByte����������ֽ��벻һ���������
        byte[] bs = "��".getBytes("gbk");
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