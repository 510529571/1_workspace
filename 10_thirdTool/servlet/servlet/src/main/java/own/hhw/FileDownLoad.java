package own.hhw;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * User: hanwei
 * Date: 16-1-11
 * Time: ионГ10:46
 */
public class FileDownLoad {
    private List<String> getFileNames(String path) {
        List<String> fileNames = new ArrayList<String>();
        File file = new File(path);

        String[] files = file.list();
        Arrays.sort(file.list());
        for (int i = files.length - 1; i >= 0; i--) {
            fileNames.add(files[i]);
        }
        return fileNames;
    }

    public static void main(String[] args) {
        // This is the path where the file's name you want to take.
        String path = "F:\\test";
        new FileDownLoad().getFileNames(path);
    }

}
