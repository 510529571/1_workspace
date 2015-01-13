package own.hhw.util;


import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBConnection
{
	public Connection conn = null; // 声明Connection对象的实例
	public Statement stmt = null; // 声明Statement对象的实例
	public ResultSet rs = null; // 声明ResultSet对象的实例
	/*
//	MySQL：    
	    String Driver="com.mysql.jdbc.Driver";    //驱动程序
	    String URL="jdbc:mysql://localhost:3306/db_name";    //连接的URL,db_name为数据库名    
	    String Username="username";    //用户名
	    String Password="password";    //密码
	    Class.forName(Driver).new Instance();
	    Connection con=DriverManager.getConnection(URL,Username,Password);
//	Microsoft SQL Server 2.0驱动(3个jar的那个):
	    String Driver="com.microsoft.jdbc.sqlserver.SQLServerDriver";    //连接SQL数据库的方法
	    String URL="jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=db_name";    //db_name为数据库名
	    String Username="username";    //用户名
	    String Password="password";    //密码
	    Class.forName(Driver).new Instance();    //加载数据可驱动
	    Connection con=DriverManager.getConnection(URL,UserName,Password);    //
//	Microsoft SQL Server 3.0驱动(1个jar的那个): // 老紫竹完善
	    String Driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";    //连接SQL数据库的方法
	    String URL="jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=db_name";    //db_name为数据库名
	    String Username="username";    //用户名
	    String Password="password";    //密码
	    Class.forName(Driver).new Instance();    //加载数据可驱动
	    Connection con=DriverManager.getConnection(URL,UserName,Password);    //
//	Sysbase:
	    String Driver="com.sybase.jdbc.SybDriver";    //驱动程序
	    String URL="jdbc:Sysbase://localhost:5007/db_name";    //db_name为数据可名
	    String Username="username";    //用户名
	    String Password="password";    //密码
	    Class.forName(Driver).newInstance();    
	    Connection con=DriverManager.getConnection(URL,Username,Password);
//	Oracle(用thin模式):
	    String Driver="oracle.jdbc.driver.OracleDriver";    //连接数据库的方法
	    String URL="jdbc:oracle:thin:@loaclhost:1521:orcl";    //orcl为数据库的SID
	    String Username="username";    //用户名
	    String Password="password";    //密码
	    Class.forName(Driver).newInstance();    //加载数据库驱动
	    Connection con=DriverManager.getConnection(URL,Username,Password);    
//	PostgreSQL:
	    String Driver="org.postgresql.Driver";    //连接数据库的方法
	    String URL="jdbc:postgresql://localhost/db_name";    //db_name为数据可名
	    String Username="username";    //用户名
	    String Password="password";    //密码
	    Class.forName(Driver).newInstance();    
	    Connection con=DriverManager.getConnection(URL,Username,Password);
//	DB2：
	    String Driver="com.ibm.db2.jdbc.app.DB2.Driver";    //连接具有DB2客户端的Provider实例
	    //String Driver="com.ibm.db2.jdbc.net.DB2.Driver";    //连接不具有DB2客户端的Provider实例
	    String URL="jdbc:db2://localhost:5000/db_name";    //db_name为数据可名
	    String Username="username";    //用户名
	    String Password="password";    //密码
	    Class.forName(Driver).newInstance();    
	    Connection con=DriverManager.getConnection(URL,Username,Password);
//	Informix:
	    String Driver="com.informix.jdbc.IfxDriver";    
	    String URL="jdbc:Informix-sqli://localhost:1533/db_name:INFORMIXSER=myserver";    //db_name为数据可名
	    String Username="username";    //用户名
	    String Password="password";    //密码
	    Class.forName(Driver).newInstance();    
	    Connection con=DriverManager.getConnection(URL,Username,Password);
//	JDBC-ODBC:
	    String Driver="sun.jdbc.odbc.JdbcOdbcDriver";
	    String URL="jdbc:odbc:dbsource";    //dbsource为数据源名
	    String Username="username";    //用户名
	    String Password="password";    //密码
	    Class.forName(Driver).newInstance();    
	    Connection con=DriverManager.getConnection(URL,Username,Password);
	    */
	
	private static String dbClassName = "com.microsoft.jdbc.sqlserver.SQLServerDriver";// 定义保存数据库驱动的变量
	private static String dbUrl = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=DB_ATM";
	private static String dbUser = "sa";
	private static String dbPwd = "sa";

	public JDBConnection(String propertyFileName)
	{// 带属性文件名的构造方法
		Properties prop = new Properties();// 属性集合对象
		InputStream is = null;
		try
		{
//			is = JDBConnection.class.getClassLoader().getResourceAsStream(propertyFileName);// 属性文件输入流
			is = new FileInputStream(propertyFileName);
			prop.load(is);// 将属性文件流装载到Properties对象中
			is.close();// 关闭流
			dbClassName = prop.getProperty("dbClassName");
			dbUrl = prop.getProperty("dbUrl");
			dbUser = prop.getProperty("dbUser");
			dbPwd = prop.getProperty("dbPwd");
			System.out.println(dbClassName);
		} catch (Exception e)
		{
		}
		try
		{

			Class.forName(dbClassName);// 1.注册驱动
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void testNew()
	{
		new JDBConnection("src/jdbc.properties");
	}
	
	public JDBConnection()
	{// 默认的不带参数的构造函数
		try
		{

			Class.forName(dbClassName);// 1.注册驱动
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			// Class.forName(dbClassName);// 1.注册驱动
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);// 2.建立与数据库的链接
		} catch (Exception ee)
		{
			ee.printStackTrace();
		}
		if (conn == null)
		{
			System.err.println("警告: DbConnectionManager.getConnection() 获得数据库链接失败.\r\n\r\n链接类型:" + dbClassName + "\r\n链接位置:" + dbUrl + "\r\n用户/密码" + dbUser + "/" + dbPwd);
		}
		return conn;
	}

	/*
	 * 功能：执行查询语句
	 */
	public ResultSet executeQuery(String sql)
	{
		try
		{ // 捕捉异常
			conn = getConnection(); // 调用getConnection()方法构造Connection对象的一个实例conn
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// 3.创建语句
			rs = stmt.executeQuery(sql);// 4.执行查询
		} catch (SQLException ex)
		{
			System.err.println(ex.getMessage()); // 输出异常信息
		}
		return rs; // 返回结果集对象 5.结果处理
	}

	/*
	 * 功能:执行更新操作
	 */
	public int executeUpdate(String sql)
	{
		int result = 0; // 定义保存返回值的变量
		try
		{ // 捕捉异常
			conn = getConnection(); // 调用getConnection()方法构造Connection对象的一个实例conn
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql); // 执行更新操作
		} catch (SQLException ex)
		{
			result = 0; // 将保存返回值的变量赋值为0
		}
		return result; // 返回保存返回值的变量
	}

	/*
	 * 功能:关闭数据库的连接
	 */
	public void close()
	{// 6.释放资源
		try
		{ // 捕捉异常
			try
			{
				if (rs != null)
				{ // 当ResultSet对象的实例rs不为空时
					rs.close(); // 关闭ResultSet对象
				}
			} finally
			{
				try
				{
					if (stmt != null)
					{ // 当Statement对象的实例stmt不为空时
						stmt.close(); // 关闭Statement对象
					}
				} finally
				{
					if (conn != null)
					{ // 当Connection对象的实例conn不为空时
						conn.close(); // 关闭Connection对象
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace(System.err); // 输出异常信息
		}
	}

}
