package own.hhw.util;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-12
 * Time: обнГ4:07
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
        new File("F:\\sdf\\sss\\123.txt").getParentFile().mkdirs();
        new File("F:\\sdf\\sss\\123.txt").createNewFile();
    }
}
