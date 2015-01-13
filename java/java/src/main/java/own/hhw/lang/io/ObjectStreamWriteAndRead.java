package own.hhw.lang.io;

import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: ����3:28
 * hhw:tag �����л������������к�,Ȼ���Զ��������ݴ洢
 * hhw:tag �����л���������������ת���ɶ���
 */
public class ObjectStreamWriteAndRead {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        writeAndRead();

        writeAndRead2();
    }

    /*
    * ���������к��Զ��������ݴ洢
    * Ȼ�󽫶���������ת���ɶ���
    */
    private static void writeAndRead() throws IOException, ClassNotFoundException {
        SerializableObj serializableObj = new SerializableObj();
        serializableObj.setName("hanwei");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(serializableObj);
        oos.flush();
        String byteStr = new String(baos.toByteArray());
        System.out.println(byteStr);

        ByteArrayInputStream bs = new ByteArrayInputStream(byteStr.getBytes());
        ObjectInputStream ois = new ObjectInputStream(bs);
        SerializableObj serializableObj_temp = ((SerializableObj) ois.readObject());
        System.out.println(serializableObj.getName());
    }

    /**
     * obj2�����кŶ��󣬵���obj�������кŶ�������Ҳ�������кŴ洢
     */
    private static void writeAndRead2() throws IOException, ClassNotFoundException {
        Obj2 obj2 = new Obj2();
        obj2.setObj(new Obj());
        obj2.getObj().setName("hanwei");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj2);
        oos.flush();
        String byteStr = new String(baos.toByteArray());
        System.out.println(byteStr);

        ByteArrayInputStream bs = new ByteArrayInputStream(byteStr.getBytes());
        ObjectInputStream ois = new ObjectInputStream(bs);
        Obj2 obj2_temp = ((Obj2) ois.readObject());
        System.out.println(obj2.getObj().getName());
    }
}

class Obj {
    private String name;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}

class Obj2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private Obj obj;

    Obj getObj() {
        return obj;
    }

    void setObj(Obj obj) {
        this.obj = obj;
    }
}

class SerializableObj implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
