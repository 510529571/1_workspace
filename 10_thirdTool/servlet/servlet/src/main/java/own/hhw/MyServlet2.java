package own.hhw;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 13-12-27
 * Time: ����10:44
 * To change this template use File | Settings | File Templates.
 */
public class MyServlet2 extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        System.out.println("MyServlet2.doPost()");
        /*String mhd = request.getParameter("mhd");
        if ("doSomeThing".equals(mhd)) {
            doSomeThing(request);
        } else if ("doSomeThingAboutStr".equals(mhd)) {
            doSomeThingAboutStr(request);
        }*/
    }
    public void test(){
        System.out.println("123");
    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        doPost(request, response);
    }

    private void doSomeThing(javax.servlet.http.HttpServletRequest request) {
        request.getQueryString();

        User userSession = new User("huhanwei", "123456");
        request.getSession().setAttribute("user", userSession);

        User user = (User) request.getSession().getAttribute("user");
        user.setUsername("hanwei");

        System.out.println(((User) request.getSession().getAttribute("user")).getUsername());
    }

    /*
    hhw:new �ַ���Ҳ�Ƕ��󣬶�����������ֵ�ǲ��ܸı�ģ������������ĸı��ַ�����ֵ��ʵ����������������һ��
    �ַ������󣬸���Ӧ�ñ���
     */
    private void doSomeThingAboutStr(javax.servlet.http.HttpServletRequest request) {
        String nameSession = "huhanwei";
        String nameSession2 = "huhanwei";  //debug��ʱ��nameSession2��ָ��Ķ����nameSession��ͬһ������
        String nameSession3 = new String("huhanwei");//nameSession3��ָ��Ķ����nameSession����ͬһ������Ҳ����˵����һ���µĶ���ֻ�����ǵ�ֵ����ͬ��
        String nameSession4 = nameSession3.replace("h", "w");
        int a = 1;
        request.getSession().setAttribute("name", nameSession);

        String name = (String) request.getSession().getAttribute("name");
        name = "hanwei";
        System.out.print("nameSession3==nameSession3?");
        System.out.println( nameSession2 == nameSession3);
        System.out.println("nameSession2.equals(nameSession3)?" + nameSession2.equals(nameSession3));
        System.out.println(((String) request.getSession().getAttribute("name")));
    }
}
