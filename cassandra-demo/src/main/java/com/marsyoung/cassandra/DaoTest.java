package com.marsyoung.cassandra;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest {

	private static Log log = LogFactory.getLog(DaoTest.class);

	public static void main(String[] args) {

//		DaoTest.testUserTokenDao();
//		DaoTest.testUserTokenDao2();
		DaoTest.testUserTokenDao3();
	}

	public static void testUserTokenDao() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao utDao = context.getBean(UserTokenDao.class);
		// insert
		UserToken ut = new UserToken("testUserDao@sohu.com", 10l,
				"gidgidgidgidgidgidgid", 1l, "usertokenusertoken", 1367777777l);
		UserToken ut2 = utDao.save(ut);
		log.info(ut2);
		// read
		// 没有
		context.close();
	}

	public static void testUserTokenDao2() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao2 utDao2 = context.getBean(UserTokenDao2.class);
		// insert
		UserToken ut = new UserToken("testUserDao2@sohu.com", 10l,
				"gidgidgidgidgidgidgid", 1l, "usertokenusertoken", 1367777777l);
		UserToken ut2 = utDao2.save(ut);
		log.info(ut2);
		// read
		UserToken ut2ReadFromDB = utDao2.findUserTokenByPrimaryKeyColumn(
				"testUserDao2@sohu.com", 10l, "gidgidgidgidgidgidgid", 1l);
		log.info(ut2ReadFromDB);
		context.close();
	}

	public static void testUserTokenDao3() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao3 utDao3 = context.getBean(UserTokenDao3.class);
		// insert 1
		UserToken3 ut3 = new UserToken3("testUserDao31@sohu.com", 10l,
				"gidgidgidgidgidgidgid", 1l, "usertokenusertoken", 1367777777l);
		utDao3.insertUserToken(ut3);
		// insert 2
		ut3 = new UserToken3("testUserDao32@sohu.com", 10l,
				"gidgidgidgidgidgidgid", 1l, "usertokenusertoken", 1367777777l);
		utDao3.insertUserToken2(ut3);
		// insert 3
		ut3 = new UserToken3("testUserDao33@sohu.com", 10l,
				"gidgidgidgidgidgidgid", 1l, "usertokenusertoken", 1367777777l);
		utDao3.insertUserToken3(ut3);
		
		//udpate
		ut3 = new UserToken3("testUserDao33@sohu.com", 10l,
				"gidgidgidgidgidgidgid", 1l, "updateusertokenupdateusertoken", 1367777777l);
		utDao3.updateUserToken(ut3);
		
		context.close();
	}

}
