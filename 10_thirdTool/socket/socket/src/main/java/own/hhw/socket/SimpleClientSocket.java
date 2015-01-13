package own.hhw.socket;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *  hhw:tag 【socket客户端】 最基本的socket客户端示例
 *  Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-23
 * Time: 下午5:36
 * To change this template use File | Settings | File Templates.
 */
public class SimpleClientSocket {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 10002);
        socket.setSoTimeout(10000);


        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        out.write("我是客户端发送的数据".getBytes("gbk"));

        //字符数组长度
        int bufferlen = 100;
        //声明数组
        byte[] buff = new byte[bufferlen];
        //保存服务端返回报文
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
