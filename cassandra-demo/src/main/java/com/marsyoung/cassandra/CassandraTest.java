package com.marsyoung.cassandra;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CassandraTest {

	private static Log log=LogFactory.getLog(CassandraTest.class);
	public static void main(String[] args) {
		CassandraTest.testRead();
	}
	
	
	public static void testWrite(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao utDao = context.getBean(UserTokenDao.class);
		//List<UserToken> uts = new ArrayList<UserToken>();
		log.info("开始插入10000条数据到cassandra中。");
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			String s = new Integer(i).toString();
			long l = (long) i;
			UserToken ut = new UserToken(s+s+"@sohu.com", l, s+s, l+l, s+s+s+s, l+l+l);
			//uts.add(ut);
			utDao.save(ut);
			log.info(i);
		}
		log.info("完成插入10000条数据到cassandra中。");
		long endTime = System.currentTimeMillis();
		context.close();
		System.out.println("本次操作用时" + (endTime - startTime) + "ms");
	}
	
	public static void testRead(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
//		UserTokenDao utDao = context.getBean(UserTokenDao.class);
		UserTokenDao2 utDao2 = context.getBean(UserTokenDao2.class);
		//List<UserToken> uts = new ArrayList<UserToken>();
		log.info("开始读取10000条数据从cassandra中。");
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			String s = new Integer(i).toString();
			long l = (long) i;
			String passport=s+s+"@sohu.com";
			long appId=l;
			String gid= s+s;
			long plat=l+l;
			UserToken ut =utDao2.findUserTokenByPrimaryKeyColumn(passport, appId, gid, plat);
			log.info(ut.toString());
		}
		log.info("完成读取10000条数据从cassandra中。");
		long endTime = System.currentTimeMillis();
		context.close();
		System.out.println("本次操作用时" + (endTime - startTime) + "ms");
		
		
		System.out.println(Float.valueOf(128));
	}

}
