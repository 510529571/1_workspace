package own.hhw.lang.io;

import java.io.*;
import java.util.Date;

import org.junit.Test;

import own.hhw.util.PublicTool;

public class MyFile {
    @Test
    public void testFileMethod() throws IOException {
        File f = new File("src/file");
        if (!f.exists())
            if (!f.mkdirs())
                System.out.println("创建文件失败！");

        File f1 = File.createTempFile("abcde", ".txt", f);
        java.lang.String[] files = f.list();

        for (int i = 0; i < files.length; i++) {
            System.out.println("文件"+i+":"+files[i].toString());
        }

        System.out.println("path:"+f1.getPath());
        System.out.println("AbsolutePath:"+f1.getAbsolutePath());
        System.out.println("CanonicalFile:"+f1.getCanonicalFile().getPath());
        System.out.println("FreeSpace:"+f1.getFreeSpace());
        System.out.println("Name:"+f1.getName());
        System.out.println("TotalSpace:"+f1.getTotalSpace());
        System.out.println(PublicTool.dateToString(new Date("path:"+f1.lastModified()), "yyyy-MM-dd hh:mm:ss"));

        f.delete();
    }

    @Test
    public void test1() {
        try {

//            byte[] b = new byte[1024];
//			int a = System.in.read(b);
//            String s=new String(b,0,a);
//            FileOutputStream fos=new FileOutputStream("src/file.txt");

            PrintStream pf = new PrintStream("src/file.txt");
            pf.println("23");
            pf.close();

            FileWriter fw = new FileWriter("src/file1.txt");
            fw.write("123");
            fw.flush();
            fw.close();
            // System.out.println(s);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
