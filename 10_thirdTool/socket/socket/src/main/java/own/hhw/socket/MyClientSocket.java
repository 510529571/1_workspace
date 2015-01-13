package own.hhw.socket;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyClientSocket {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 10002);
        socket.setSoTimeout(10000);


        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        out.write("hihi~~~~~~r~".getBytes("gbk"));


        //字符数组长度
        int bufferlen = 100;
        //声明数组
        byte[] buff = new byte[bufferlen];
        //保存客户端发送报文
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int receivedLen;

        while ((receivedLen = in.read(buff, 0, bufferlen)) == bufferlen) {
            baos.write(buff, 0, receivedLen);
        }
        if (receivedLen > 0 && receivedLen < bufferlen) {
            baos.write(buff, 0, receivedLen);
        }
        System.out.println(baos.toString("gbk"));



        out.close();
        in.close();
        socket.close();
    }
}
