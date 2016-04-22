package own.hhw;

import java.io.File;

/**
 * User: hanwei
 * Date: 16-1-11
 * Time: 上午10:37
 */
public class TakeFilePathAndName {

    public static void main(String[] args) {
        // This is the path where the file's name you want to take.
        String path = "F:\\work\\2_工作资料\\2_广发项目\\1_支付渠道\\19_民生银行\\对账模板文件\\生产";
        getFile(path);
    }

    private static void getFile(String path){
        // get file list where the path has
        File file = new File(path);
        // get the folder list
        File[] array = file.listFiles();

        for(int i=0;i<array.length;i++){
            if(array[i].isFile()){

                // only take file name
                System.out.println("^^^^^" + array[i].getName());
                // take file path and name
                System.out.println("#####" + array[i]);
                // take file path and name
                System.out.println("*****" + array[i].getPath());
            }else if(array[i].isDirectory()){
                getFile(array[i].getPath());
            }
        }

       String[] files= file.list();
        for (String s:files){
            System.out.println(s);
        }


    }
}
