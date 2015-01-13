package own.hhw.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * hhw:tag ��socket����ˡ��������socket�����ʾ��
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-23
 * Time: ����5:37
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
                //����ͻ��˷��͵��ַ���
                InputStream in = socket.getInputStream();
                //����ͻ��˷��ص��ַ���
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

                //hhw:tag �����д�������⣬Ϊʲô�أ� ò��socket����ͨ��in.read(buff)������������
//                while ((receivedLen = in.read(buff, 0, bufferLen)) != -1) {
//                    baos.write(buff, 0, receivedLen);
//                    System.out.println(receivedLen);
//                }
                String receiveData= baos.toString("gbk");
                System.out.println("������������ݣ�");
                os.write(("���Ƿ���ˣ�������������ǣ�" +receiveData).getBytes("gbk"));

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
