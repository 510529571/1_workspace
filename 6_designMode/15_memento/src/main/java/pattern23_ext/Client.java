package pattern23_ext;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: 下午4:31
 */
public class Client {
    public static void main(String[] args){
        Originator ori = new Originator();
        Caretaker caretaker = new Caretaker();
        ori.setState1("中国");
        ori.setState2("强盛");
        ori.setState3("繁荣");
        System.out.println("===初始化状态===\n"+ori);

        caretaker.setMemento("001",ori.createMemento());
        ori.setState1("软件");
        ori.setState2("架构");
        ori.setState3("优秀");
        System.out.println("===修改后状态===\n"+ori);

        ori.restoreMemento(caretaker.getMemento("001"));
        System.out.println("===恢复后状态===\n"+ori);
    }
}