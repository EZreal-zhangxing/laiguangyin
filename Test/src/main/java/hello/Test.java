package hello;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class Test {
	private SqlSessionFactory sqlsessionFactory;
	public void initMethod(){
		System.out.println(this.getClass()+" is init");
		System.out.println("The sqlSessionFactory is "+sqlsessionFactory);
		SqlSession sqlsession=sqlsessionFactory.openSession();
		System.out.println("The sqlsession is "+sqlsession);
	}
	public SqlSessionFactory getSqlsessionFactory() {
		return sqlsessionFactory;
	}
	public void setSqlsessionFactory(SqlSessionFactory sqlsessionFactory) {
		this.sqlsessionFactory = sqlsessionFactory;
	}
	
}
