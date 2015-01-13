package own.hhw;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 13-12-27
 * Time: 上午10:44
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
    hhw:new 字符串也是对象，而且这个对象的值是不能改变的，我们所看到的改变字符串的值，实际上是重新生成了一个
    字符串对象，给了应用变量
     */
    private void doSomeThingAboutStr(javax.servlet.http.HttpServletRequest request) {
        String nameSession = "huhanwei";
        String nameSession2 = "huhanwei";  //debug的时候nameSession2所指向的对象和nameSession是同一个对象
        String nameSession3 = new String("huhanwei");//nameSession3所指向的对象和nameSession不是同一个对象，也就是说它是一个新的对象，只是它们的值是相同的
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
