package own.hhw.app.MySocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: hanwei
 * Date: 15-9-9
 * Time: 下午5:46
 */
public class MyServerSocket {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(2222);
        Socket s = ss.accept();
        DataInputStream ds=new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        int len = ds.readInt();
        byte[] recv = new byte[len];
        ds.readFully(recv);
        //字符数组长度
        int bufferlen = 3;
        //声明数组
        byte[] buff= new byte[bufferlen];
        int receivedToLen;
        ByteArrayOutputStream tos=new ByteArrayOutputStream();
        while ((receivedToLen = ds.read(buff,0,bufferlen)) ==  bufferlen) {
            tos.write(buff,0,receivedToLen);
            if(ds.available() == 0){
                break;
            }
        }
        if(receivedToLen > 0 && receivedToLen < bufferlen){
            tos.write(buff,0,receivedToLen);
        }
        out.write("sjldf".getBytes());

        System.out.println(new String(tos.toByteArray()));
    }
}
