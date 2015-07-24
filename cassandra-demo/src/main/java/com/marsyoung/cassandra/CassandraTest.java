package com.marsyoung.cassandra;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CassandraTest {

	private static Log log=LogFactory.getLog(CassandraTest.class);
	public static void main(String[] args) {
		//CassandraTest.testWrite();
		CassandraTest.test95Read5Write();
	}
	
	/**
	 * 测试5% read 95%写
	 */
	public static void test5Read95Write(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao utDao = context.getBean(UserTokenDao.class);
		UserTokenDao2 utDao2 = context.getBean(UserTokenDao2.class);
		//List<UserToken> uts = new ArrayList<UserToken>();
		log.info("开始插入或读取100000条数据到cassandra中。");
		long totalTime=0;
		for (int i = 0; i < 100000; i++) {
			String s = new Integer(i).toString();
			long l = (long) i;
			String passport=s+s+"@sohu.com";
			long appId=l;
			String gid= s+s;
			long plat=l+l;
			UserToken ut = new UserToken(s+s+"@sohu.com", l, s+s, l+l, s+s+s+s, l+l+l);
			long startTime = 0l;
			if(i%20!=1){
				startTime = System.currentTimeMillis();
				utDao.save(ut);
			}else{
				startTime = System.currentTimeMillis();
				utDao2.findUserTokenByPrimaryKeyColumn(passport, appId, gid, plat);
			}
			long endTime = System.currentTimeMillis();
			long opTime=endTime - startTime;
			log.info("本次操作用时" + opTime + "ms");
			totalTime=totalTime+opTime;
		}
		log.info("完成插入或读取100000条数据到cassandra中,用时"+totalTime+"毫秒");
		context.close();
	}
	

	public static void test95Read5Write(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao utDao = context.getBean(UserTokenDao.class);
		UserTokenDao2 utDao2 = context.getBean(UserTokenDao2.class);
		//List<UserToken> uts = new ArrayList<UserToken>();
		log.info("开始插入或读取100000条数据到cassandra中。");
		long totalTime=0;
		for (int i = 0; i < 100000; i++) {
			String s = new Integer(i).toString();
			long l = (long) i;
			String passport=s+s+"@sohu.com";
			long appId=l;
			String gid= s+s;
			long plat=l+l;
			UserToken ut = new UserToken(s+s+"@sohu.com", l, s+s, l+l, s+s+s+s, l+l+l);
			long startTime = 0l;
			if(i%20==1){
				startTime = System.currentTimeMillis();
				utDao.save(ut);
			}else{
				startTime = System.currentTimeMillis();
				utDao2.findUserTokenByPrimaryKeyColumn(passport, appId, gid, plat);
			}
			long endTime = System.currentTimeMillis();
			long opTime=endTime - startTime;
			log.info("本次操作用时" + opTime + "ms");
			totalTime=totalTime+opTime;
		}
		log.info("完成插入或读取100000条数据到cassandra中,用时"+totalTime+"毫秒");
		context.close();
	}
	
	
	public static void test50Read50Write(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao utDao = context.getBean(UserTokenDao.class);
		UserTokenDao2 utDao2 = context.getBean(UserTokenDao2.class);
		//List<UserToken> uts = new ArrayList<UserToken>();
		log.info("开始插入或读取100000条数据到cassandra中。");
		long totalTime=0;
		for (int i = 0; i < 100000; i++) {
			String s = new Integer(i).toString();
			long l = (long) i;
			String passport=s+s+"@sohu.com";
			long appId=l;
			String gid= s+s;
			long plat=l+l;
			UserToken ut = new UserToken(s+s+"@sohu.com", l, s+s, l+l, s+s+s+s, l+l+l);
			long startTime = 0l;
			if(i%2==0){
				startTime = System.currentTimeMillis();
				utDao.save(ut);
			}else{
				startTime = System.currentTimeMillis();
				utDao2.findUserTokenByPrimaryKeyColumn(passport, appId, gid, plat);
			}
			long endTime = System.currentTimeMillis();
			long opTime=endTime - startTime;
			log.info("本次操作用时" + opTime + "ms");
			totalTime=totalTime+opTime;
		}
		log.info("完成插入或读取100000条数据到cassandra中,用时"+totalTime+"毫秒");
		context.close();
	}
	
	public static void testWriteOf100Percent(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao utDao = context.getBean(UserTokenDao.class);
		//List<UserToken> uts = new ArrayList<UserToken>();
		log.info("开始插入100000条数据到cassandra中。");
		long totalTime=0;
		for (int i = 0; i < 100000; i++) {
			String s = new Integer(i).toString();
			long l = (long) i;
			UserToken ut = new UserToken(s+s+"@sohu.com", l, s+s, l+l, s+s+s+s, l+l+l);
			long startTime = System.currentTimeMillis();
			utDao.save(ut);
			long endTime = System.currentTimeMillis();
			long opTime=endTime - startTime;
			log.info("本次操作用时" + opTime + "ms");
			totalTime=totalTime+opTime;
		}
		log.info("完成插入100000条数据到cassandra中,用时"+totalTime+"毫秒");
		context.close();
	}
	
	public static void testWrite2(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao utDao = context.getBean(UserTokenDao.class);
		List<UserToken> uts = new ArrayList<UserToken>();
		log.info("开始插入10000条数据到cassandra中。");
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			String s = new Integer(i).toString();
			long l = (long) i;
			UserToken ut = new UserToken(s+s+"@sohu.com", l, s+s, l+l, s+s+s+s, l+l+l);
			uts.add(ut);
			utDao.save(uts);
		}
		log.info("完成插入10000条数据到cassandra中。");
		long endTime = System.currentTimeMillis();
		context.close();
		System.out.println("本次操作用时" + (endTime - startTime) + "ms");
	}
	
	public static void testRead(){
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao2 utDao2 = context.getBean(UserTokenDao2.class);
		log.info("开始读取100000条数据从cassandra中。");
		long totalTime=0;
		for (int i = 0; i < 100000; i++) {
			String s = new Integer(i).toString();
			long l = (long) i;
			String passport=s+s+"@sohu.com";
			long appId=l;
			String gid= s+s;
			long plat=l+l;
			long startTime = System.currentTimeMillis();
			UserToken ut =utDao2.findUserTokenByPrimaryKeyColumn(passport, appId, gid, plat);
			long endTime = System.currentTimeMillis();
			long opTime=endTime - startTime;
			log.info("本次操作用时" + opTime + "ms");
			totalTime=totalTime+opTime;
			log.info(ut.toString());
		}
		log.info("完成读取100000条数据到cassandra中,用时"+totalTime+"毫秒");
	}
	

}
