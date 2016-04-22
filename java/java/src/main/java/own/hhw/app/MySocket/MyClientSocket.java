package own.hhw.app.MySocket;

import java.io.*;
import java.net.Socket;

/**
 * User: hanwei
 * Date: 15-9-9
 * Time: обнГ5:48
 */
public class MyClientSocket   {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket=new Socket("localhost",2222);
        OutputStream os=socket.getOutputStream();
        InputStream inputStream = new DataInputStream(socket.getInputStream());
        os.write("123456".getBytes());
        byte[] recv = new byte[100];
        inputStream.read(recv);
        os.close();
    }
}
