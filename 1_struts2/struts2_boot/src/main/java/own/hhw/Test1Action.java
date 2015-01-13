package own.hhw;

import com.mysql.jdbc.ResultSet;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1Action extends ActionSupport {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public String test_method1() {
        HttpServletRequest request = ServletActionContext.getRequest();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String username_data = "";

        Connection conn;
        Statement stmt;
        ResultSet res;
        //����Connector/J����
        //��һ��Ҳ��дΪ��Class.forName("com.mysql.jdbc.Driver");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //������MySQL������
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123");
            //ִ��SQL���
            stmt = conn.createStatement();
            res = (ResultSet) stmt.executeQuery("select * from user where username='" + username + "' and password='" + password + "'");

            //��������
            while (res.next()) {
                username_data = res.getString("username");
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println(request.getParameter(username_data));
        if ("".equals(username_data))
            return "fail";
        else {
            request.getSession().setAttribute("username", username_data);
            return "success";
        }
    }

    public static void main(String[] args) {
        Connection conn;
        Statement stmt;
        ResultSet res;
        //����Connector/J����
        //��һ��Ҳ��дΪ��Class.forName("com.mysql.jdbc.Driver");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //������MySQL������
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                    "root", "123");
            //ִ��SQL���
            stmt = conn.createStatement();
            res = (ResultSet) stmt.executeQuery("select * from user");
            //��������
            while (res.next()) {
                String name = res.getString("username");
                System.out.println(name);
            }
            res.close();
            stmt.close();
            conn.close();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
