package own.hhw.util;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.Properties;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-3-7
 * Time: 上午11:08
 * To change this template use File | Settings | File Templates.
 */
public class SftpUtil {
    private ChannelSftp sftp;
    private Session sshSession;

    /**
     * 连接sftp服务器
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return ChannelSftp
     * @throws com.jcraft.jsch.JSchException 抛出JSchException
     */
    public boolean connect(String host, int port, String username, String password) throws JSchException {
        JSch jsch = new JSch();
        sshSession = jsch.getSession(username, host, port);
        sshSession.setPassword(password);
//        sshSession.setTimeout(10);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();
        sftp = (ChannelSftp) channel;
        return true;
    }

    /**
     * 断开连接
     */
    public void disconnect() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            } else if (sftp.isClosed()) {
            }
        }
        if (sshSession != null) {
            if (sshSession.isConnected()) {
                sshSession.disconnect();
            }
        }
    }

    /**
     * 上传文件
     *
     * @param directory  sftp的目录
     * @param uploadFile 要上传的文件
     * @throws com.jcraft.jsch.SftpException e
     * @throws java.io.FileNotFoundException e
     */
    public void upload(String directory, String uploadFile) throws SftpException, FileNotFoundException {
        createDir(directory);
        File file = new File(uploadFile);
        sftp.put(new FileInputStream(file), file.getName());
    }


    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     * @throws com.jcraft.jsch.SftpException e
     * @throws java.io.FileNotFoundException e
     */
    public void download(String directory, String downloadFile, String saveFile) throws SftpException, IOException {
        sftp.cd(directory);
        File file = PublicTool.makeFile(saveFile);
        sftp.get(downloadFile, new FileOutputStream(file));
    }

    public InputStream download(String directory, String downloadFile) throws SftpException, FileNotFoundException {
        sftp.cd(directory);
        return sftp.get(downloadFile);
    }


    /**
     * 创建目录
     *
     * @param filepath
     */
    private void createDir(String filepath) {
        boolean bcreated = false;
        boolean bparent = false;
        File file = new File(filepath);
        String ppath = file.getParent();
        try {
            sftp.cd(ppath);
            bparent = true;
        } catch (SftpException e1) {
            bparent = false;
        }
        try {
            if (bparent) {
                try {
                    sftp.cd(filepath);
                    bcreated = true;
                } catch (Exception e) {
                    bcreated = false;
                }
                if (!bcreated) {
                    sftp.mkdir(filepath);
                    bcreated = true;
                }
            } else {
                createDir(ppath);
                sftp.cd(ppath);
                sftp.mkdir(filepath);
            }
        } catch (SftpException e) {
            e.printStackTrace();
        }

        try {
            sftp.cd(filepath);
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @throws com.jcraft.jsch.SftpException e
     */
    public void delete(String directory, String deleteFile) throws SftpException {
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     * @return vector
     * @throws SftpException e
     */
    public Vector listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

}
