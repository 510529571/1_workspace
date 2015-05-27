package own.hhw.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.Reader;

public class MybatisUtil {
	public static String resource_location = "configuration.xml";
	private static SqlSessionFactory sessionFactory;

    private static SqlSessionManager sqlSessionManager;
	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource_location);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}

	public static SqlSession getSqlSession() {
		return sessionFactory.openSession();
	}

    public static SqlSession getAutoSqlSession(){
        sqlSessionManager=SqlSessionManager.newInstance(sessionFactory);
        return sqlSessionManager;
    }

    public static SqlSessionManager getSqlSessionManager() {
        return sqlSessionManager;
    }

    public static SqlSession getBatchSqlSession() {
		return sessionFactory.openSession(ExecutorType.BATCH, false);
	}

	public static void closeSession(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}
}
