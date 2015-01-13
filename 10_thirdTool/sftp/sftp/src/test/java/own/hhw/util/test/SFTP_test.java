package own.hhw.util.test;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import own.hhw.util.SftpUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-3-7
 * Time: ����10:34
 * To change this template use File | Settings | File Templates.
 */
public class SFTP_test {
    //sftp����
    public static final String HOST = "211.157.145.4";
    public static final int PORT = 2229;
    public static final String USERNAME = "fund_gf";
    public static final String PASSWORD = "xurqe3czeh";
    public static final String DOWNLOAD_DIRECTORY = "/cgb/download/";
    public static final String UPLOAD_DIRECTORY = "/cgb/upload/";

    /**
     * sftp �����ļ����õ������ļ���
     *
     * @return
     */
    public static InputStream downloadFileAsStream(String directory, String downloadFile) {
        InputStream in = null;

        SftpUtil sf = new SftpUtil();

        try {
            sf.connect(HOST, PORT, USERNAME, PASSWORD);
            in = sf.download(directory, downloadFile);
        } catch (JSchException e) {
        } catch (SftpException e) {
        } catch (FileNotFoundException e) {
        }
        return in;
    }

    /**
     * sftp �����ļ�
     *
     * @return
     */
    public static void download(String directory, String downloadFile, String saveFile) {

        SftpUtil sf = new SftpUtil();

        try {
            sf.connect(HOST, PORT, USERNAME, PASSWORD);

            sf.download(directory, downloadFile, saveFile);

            sf.disconnect();
        } catch (JSchException e) {
        } catch (SftpException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public static void main(String[] args) {
        SFTP_test.download("/cgb/download/", "ALLSCORE_CGBFUND_PURCHASE_20140307.txt", "f:/gg/12345.txt");
    }
}
