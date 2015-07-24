package com.marsyoung.cassandra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CassandraThreadTest extends Thread{
	
	private static Logger log=LoggerFactory.getLogger(CassandraThreadTest.class);
	
	@Autowired
	UserTokenDao utDao;
	
	@Override
	public void run() {
		super.run();
		this.insert();
	}

	public void insert(){
		log.info("开始插入10000条数据到cassandra中。");
		long startTime = System.currentTimeMillis();
		for (int i = 21000000; i < 30000000; i++) {
			String s = new Integer(i).toString();
			long l = (long) i;
			UserToken ut = new UserToken(s+s+"@sohu.com", l, s+s, l+l, s+s+s+s, l+l+l);
			//uts.add(ut);
			UserToken ut2=utDao.save(ut);
			log.info(ut2.toString());
		}
		log.info("完成插入10000条数据到cassandra中。");
		long endTime = System.currentTimeMillis();
		System.out.println("本次操作用时" + (endTime - startTime) + "ms");
	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		for(int i=0;i<5;i++){
			log.info("初始化第{}个线程",i);
			context.getBean(CassandraThreadTest.class).start();
		}
	}
	
}
