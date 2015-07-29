package com.marsyoung.cassandra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CassandraTTLTest {

private static Logger log=LoggerFactory.getLogger(CassandraThreadTest.class);
	
	@Autowired
	UserTokenDao5 utDao;
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao5 utDao = context.getBean(UserTokenDao5.class);
		
		
	}
}
