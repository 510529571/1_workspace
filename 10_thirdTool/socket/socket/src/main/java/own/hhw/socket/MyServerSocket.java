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
     * 构造方法
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
     * 内部类继承线程Thread类
     */
    class CreateServerThread extends Thread {
        //拨号socket
        private Socket client;
        //输入客户端发送的字符流
        private DataInputStream in;
        //输出客户端返回的字符流
        private DataOutputStream os;

        private ByteArrayOutputStream baos;

        /**
         * 构造方法获取初始参数,启动线程
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
         * run方法
         */
        public void run() {
            try {
                //字符数组长度
                int bufferlen = 100;
                //声明数组
                byte[] buff = new byte[bufferlen];
                //保存客户端发送报文
                baos = new ByteArrayOutputStream();
                //保存银行返回报文
                //1.获取客户端传递过来的报文
                int receivedLen;
                while ((receivedLen = in.read(buff, 0, bufferlen)) == bufferlen) {
                    baos.write(buff, 0, receivedLen);
                }
                if (receivedLen > 0 && receivedLen < bufferlen) {
                    baos.write(buff, 0, receivedLen);
                }

                System.out.println("收到客户端数据"+baos.toString("gbk"));
//                Thread.sleep(20000);
                os.write("服务端接受到你的请求了".getBytes("gbk"));
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
     * 主入口，参数列表：
     * args[0]服务的监听端口
     * args[1]服务转发目的url
     *
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("开启服务，端口号："+10001);
        SERVER_PORT = 10001;
        new MyServerSocket();

//        System.out.println(Integer.parseInt("13001"));

    }

}
