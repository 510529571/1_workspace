package own.hhw.socket;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServerSocket extends ServerSocket {

    private static int SERVER_PORT;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(500);

    /*
     * ���췽��
     *
     */
    public MyServerSocket() throws IOException {
        super(SERVER_PORT);
        while (true) {
            try {
                Socket socket = accept();
                //new CreateServerThread(socket, SERVER_IP_TO, SERVER_PORT_TO).start();
                EXECUTOR.execute(new CreateServerThread(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * �ڲ���̳��߳�Thread��
     */
    class CreateServerThread extends Thread {
        //����socket
        private Socket client;
        //����ͻ��˷��͵��ַ���
        private DataInputStream in;
        //����ͻ��˷��ص��ַ���
        private DataOutputStream os;

        private ByteArrayOutputStream baos;

        /**
         * ���췽����ȡ��ʼ����,�����߳�
         *
         * @param s
         * @throws java.io.IOException
         */
        public CreateServerThread(Socket s) throws IOException {
            client = s;
            in = new DataInputStream(client.getInputStream());
            os = new DataOutputStream(client.getOutputStream());
        }

        /**
         * run����
         */
        public void run() {
            try {
                //�ַ����鳤��
                int bufferlen = 100;
                //��������
                byte[] buff = new byte[bufferlen];
                //����ͻ��˷��ͱ���
                baos = new ByteArrayOutputStream();
                //�������з��ر���
                //1.��ȡ�ͻ��˴��ݹ����ı���
                int receivedLen;
                while ((receivedLen = in.read(buff, 0, bufferlen)) == bufferlen) {
                    baos.write(buff, 0, receivedLen);
                }
                if (receivedLen > 0 && receivedLen < bufferlen) {
                    baos.write(buff, 0, receivedLen);
                }

                System.out.println("�յ��ͻ�������"+baos.toString("gbk"));
//                Thread.sleep(20000);
                os.write("����˽��ܵ����������".getBytes("gbk"));
                os.flush();
            } catch (SocketTimeoutException stoe) {
                stoe.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeSocket(client);
            }
        }
    }

    private void closeSocket(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ����ڣ������б�
     * args[0]����ļ����˿�
     * args[1]����ת��Ŀ��url
     *
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("�������񣬶˿ںţ�"+10001);
        SERVER_PORT = 10001;
        new MyServerSocket();

//        System.out.println(Integer.parseInt("13001"));

    }

}
