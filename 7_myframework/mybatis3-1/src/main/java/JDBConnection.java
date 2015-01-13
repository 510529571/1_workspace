
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBConnection {
    public Connection conn = null; // ����Connection�����ʵ��
    public Statement stmt = null; // ����Statement�����ʵ��
    public ResultSet rs = null; // ����ResultSet�����ʵ��
    /*
//	MySQL��
	    String Driver="com.mysql.jdbc.Driver";    //��������
	    String URL="jdbc:mysql://localhost:3306/db_name";    //���ӵ�URL,db_nameΪ���ݿ���
	    String Username="username";    //�û���
	    String Password="password";    //����
	    Class.forName(Driver).new Instance();
	    Connection con=DriverManager.getConnection(URL,Username,Password);
//	Microsoft SQL Server 2.0����(3��jar���Ǹ�):
	    String Driver="com.microsoft.jdbc.sqlserver.SQLServerDriver";    //����SQL���ݿ�ķ���
	    String URL="jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=db_name";    //db_nameΪ���ݿ���
	    String Username="username";    //�û���
	    String Password="password";    //����
	    Class.forName(Driver).new Instance();    //�������ݿ�����
	    Connection con=DriverManager.getConnection(URL,UserName,Password);    //
//	Microsoft SQL Server 3.0����(1��jar���Ǹ�): // ����������
	    String Driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";    //����SQL���ݿ�ķ���
	    String URL="jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=db_name";    //db_nameΪ���ݿ���
	    String Username="username";    //�û���
	    String Password="password";    //����
	    Class.forName(Driver).new Instance();    //�������ݿ�����
	    Connection con=DriverManager.getConnection(URL,UserName,Password);    //
//	Sysbase:
	    String Driver="com.sybase.jdbc.SybDriver";    //��������
	    String URL="jdbc:Sysbase://localhost:5007/db_name";    //db_nameΪ���ݿ���
	    String Username="username";    //�û���
	    String Password="password";    //����
	    Class.forName(Driver).newInstance();
	    Connection con=DriverManager.getConnection(URL,Username,Password);
//	Oracle(��thinģʽ):
	    String Driver="oracle.jdbc.driver.OracleDriver";    //�������ݿ�ķ���
	    String URL="jdbc:oracle:thin:@loaclhost:1521:orcl";    //orclΪ���ݿ��SID
	    String Username="username";    //�û���
	    String Password="password";    //����
	    Class.forName(Driver).newInstance();    //�������ݿ�����
	    Connection con=DriverManager.getConnection(URL,Username,Password);
//	PostgreSQL:
	    String Driver="org.postgresql.Driver";    //�������ݿ�ķ���
	    String URL="jdbc:postgresql://localhost/db_name";    //db_nameΪ���ݿ���
	    String Username="username";    //�û���
	    String Password="password";    //����
	    Class.forName(Driver).newInstance();
	    Connection con=DriverManager.getConnection(URL,Username,Password);
//	DB2��
	    String Driver="com.ibm.db2.jdbc.app.DB2.Driver";    //���Ӿ���DB2�ͻ��˵�Providerʵ��
	    //String Driver="com.ibm.db2.jdbc.net.DB2.Driver";    //���Ӳ�����DB2�ͻ��˵�Providerʵ��
	    String URL="jdbc:db2://localhost:5000/db_name";    //db_nameΪ���ݿ���
	    String Username="username";    //�û���
	    String Password="password";    //����
	    Class.forName(Driver).newInstance();
	    Connection con=DriverManager.getConnection(URL,Username,Password);
//	Informix:
	    String Driver="com.informix.jdbc.IfxDriver";
	    String URL="jdbc:Informix-sqli://localhost:1533/db_name:INFORMIXSER=myserver";    //db_nameΪ���ݿ���
	    String Username="username";    //�û���
	    String Password="password";    //����
	    Class.forName(Driver).newInstance();
	    Connection con=DriverManager.getConnection(URL,Username,Password);
//	JDBC-ODBC:
	    String Driver="sun.jdbc.odbc.JdbcOdbcDriver";
	    String URL="jdbc:odbc:dbsource";    //dbsourceΪ����Դ��
	    String Username="username";    //�û���
	    String Password="password";    //����
	    Class.forName(Driver).newInstance();
	    Connection con=DriverManager.getConnection(URL,Username,Password);
	    */


    private static String dbClassName = "oracle.jdbc.driver.OracleDriver";// ���屣�����ݿ������ı���
    private static String dbUrl = "jdbc:oracle:thin:@10.88.115.115:1521:iftestdb";
    private static String dbUser = "xnnsu8";
    private static String dbPwd = "xnnsu8";


    public JDBConnection(String propertyFileName) {// �������ļ����Ĺ��췽��
        Properties prop = new Properties();// ���Լ��϶���
        InputStream is = null;
        try {
//			is = JDBConnection.class.getClassLoader().getResourceAsStream(propertyFileName);// �����ļ�������
            is = new FileInputStream(propertyFileName);
            prop.load(is);// �������ļ���װ�ص�Properties������
            is.close();// �ر���
            dbClassName = prop.getProperty("dbClassName");
            dbUrl = prop.getProperty("dbUrl");
            dbUser = prop.getProperty("dbUser");
            dbPwd = prop.getProperty("dbPwd");
            System.out.println(dbClassName);
        } catch (Exception e) {
        }
        try {

            Class.forName(dbClassName);// 1.ע������
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void testNew() {
        new JDBConnection("src/jdbc.properties");
    }

    public JDBConnection() {// Ĭ�ϵĲ��������Ĺ��캯��
        try {

            Class.forName(dbClassName);// 1.ע������
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Class.forName(dbClassName);// 1.ע������
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);// 2.���������ݿ������
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        if (conn == null) {
            System.err.println("����: DbConnectionManager.getConnection() ������ݿ�����ʧ��.\r\n\r\n��������:" + dbClassName + "\r\n����λ��:" + dbUrl + "\r\n�û�/����" + dbUser + "/" + dbPwd);
        }
        return conn;
    }

    /*
     * ���ܣ�ִ�в�ѯ���
     */
    public ResultSet executeQuery(String sql) {
        try { // ��׽�쳣
            conn = getConnection(); // ����getConnection()��������Connection�����һ��ʵ��conn
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// 3.�������
            rs = stmt.executeQuery(sql);// 4.ִ�в�ѯ
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); // ����쳣��Ϣ
        }
        return rs; // ���ؽ�������� 5.�������
    }

    /*
     * ����:ִ�и��²���
     */
    public int executeUpdate(String sql) {
        int result = 0; // ���屣�淵��ֵ�ı���
        try { // ��׽�쳣
            conn = getConnection(); // ����getConnection()��������Connection�����һ��ʵ��conn
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            result = stmt.executeUpdate(sql); // ִ�и��²���
        } catch (SQLException ex) {
            result = 0; // �����淵��ֵ�ı�����ֵΪ0
        }
        return result; // ���ر��淵��ֵ�ı���
    }

    /*
     * ����:�ر����ݿ������
     */
    public void close() {// 6.�ͷ���Դ
        try { // ��׽�쳣
            try {
                if (rs != null) { // ��ResultSet�����ʵ��rs��Ϊ��ʱ
                    rs.close(); // �ر�ResultSet����
                }
            } finally {
                try {
                    if (stmt != null) { // ��Statement�����ʵ��stmt��Ϊ��ʱ
                        stmt.close(); // �ر�Statement����
                    }
                } finally {
                    if (conn != null) { // ��Connection�����ʵ��conn��Ϊ��ʱ
                        conn.close(); // �ر�Connection����
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.err); // ����쳣��Ϣ
        }
    }

}
