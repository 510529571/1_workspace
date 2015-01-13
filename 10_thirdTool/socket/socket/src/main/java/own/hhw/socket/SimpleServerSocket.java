package own.hhw.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * hhw:tag 【socket服务端】最基本的socket服务端示例
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-23
 * Time: 下午5:37
 * To change this template use File | Settings | File Templates.
 */
public class SimpleServerSocket extends ServerSocket {


    public SimpleServerSocket(int port) throws IOException {
        super(port);
        while (true) {
            Socket socket = accept();
            new ServerThread(socket).start();
        }
    }

    public static void main(String[] args) throws IOException {
        new SimpleServerSocket(10002);
    }

    class ServerThread extends Thread {
        private Socket socket;

        ServerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                //输入客户端发送的字符流
                InputStream in = socket.getInputStream();
                //输出客户端返回的字符流
                OutputStream os = socket.getOutputStream();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                int receivedLen;
                int bufferLen = 100;
                byte[] buff = new byte[bufferLen];

                while ((receivedLen = in.read(buff, 0, bufferLen)) == bufferLen) {
                    baos.write(buff, 0, receivedLen);
                }
                if (receivedLen > 0 && receivedLen < bufferLen) {
                    baos.write(buff, 0, receivedLen);
                }

                //hhw:tag 下面的写法有问题，为什么呢？ 貌似socket的流通过in.read(buff)读，最后会阻塞
//                while ((receivedLen = in.read(buff, 0, bufferLen)) != -1) {
//                    baos.write(buff, 0, receivedLen);
//                    System.out.println(receivedLen);
//                }
                String receiveData= baos.toString("gbk");
                System.out.println("服务端请求数据：");
                os.write(("我是服务端，你的请求数据是：" +receiveData).getBytes("gbk"));

                in.close();
                os.close();
                baos.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
