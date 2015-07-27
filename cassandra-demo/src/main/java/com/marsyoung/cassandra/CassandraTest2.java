package com.marsyoung.cassandra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CassandraTest2 implements Runnable {

	private static Log log = LogFactory.getLog(CassandraTest2.class);
	public static Map<Integer, List<UserToken>> map = new ConcurrentHashMap<Integer, List<UserToken>>();
	public Integer thread=new Integer(0);
	
	public CassandraTest2(int i) {
		this.thread=i;
	}

	public static void main(String[] args) {
		// 开始生成数据
		for (int i = 0; i < 1000000; i++) {
			String s = new Integer(i).toString();
			long l = (long) i;
			String passport = s + s + "@sohu.com";
			long appId = l+l;
			String gid = s + s;
			long plat = l + l+l;
			UserToken ut = new UserToken(passport, appId, gid, plat, s + s + s
					+ s, l + l + l);
			switch (i % 10) {
			case 1: {
				List<UserToken> list = map.get(1);
				if (list == null) {
					list = new ArrayList<UserToken>();
					list.add(ut);
					map.put(1, list);
				} else {
					list.add(ut);
				}
				break;
			}
			case 2: {
				List<UserToken> list = map.get(2);
				if (list == null) {
					list = new ArrayList<UserToken>();
					list.add(ut);
					map.put(2, list);
				} else {
					list.add(ut);
				}
				break;
			}
			case 3:
			{
				List<UserToken> list = map.get(3);
				if (list == null) {
					list = new ArrayList<UserToken>();
					list.add(ut);
					map.put(3, list);
				} else {
					list.add(ut);
				}
				break;
			}
			case 4:
			{
				List<UserToken> list = map.get(4);
				if (list == null) {
					list = new ArrayList<UserToken>();
					list.add(ut);
					map.put(4, list);
				} else {
					list.add(ut);
				}
				break;
			}
			case 5:
			{
				List<UserToken> list = map.get(5);
				if (list == null) {
					list = new ArrayList<UserToken>();
					list.add(ut);
					map.put(5, list);
				} else {
					list.add(ut);
				}
				break;
			}
			case 6:
			{
				List<UserToken> list = map.get(6);
				if (list == null) {
					list = new ArrayList<UserToken>();
					list.add(ut);
					map.put(6, list);
				} else {
					list.add(ut);
				}
				break;
			}
			case 7:
			{
				List<UserToken> list = map.get(7);
				if (list == null) {
					list = new ArrayList<UserToken>();
					list.add(ut);
					map.put(7, list);
				} else {
					list.add(ut);
				}
				break;
			}
			case 8:
			{
				List<UserToken> list = map.get(8);
				if (list == null) {
					list = new ArrayList<UserToken>();
					list.add(ut);
				} else {
					list.add(ut);
					map.put(8, list);
				}
				break;
			}
			case 9:
			{
				List<UserToken> list = map.get(9);
				if (list == null) {
					list = new ArrayList<UserToken>();
					list.add(ut);
					map.put(9, list);
				} else {
					list.add(ut);
				}
				break;
			}
			case 10:
			{
				List<UserToken> list = map.get(10);
				if (list == null) {
					list = new ArrayList<UserToken>();
					list.add(ut);
					map.put(10, list);
				} else {
					list.add(ut);
				}
				break;
			}
			default:
				break;
			}
		}
		log.info("数据生成完成。");
		log.info("开始处理数据");
		long startTime=System.currentTimeMillis();
		for (int i = 1; i <= 32; i++) {
			new Thread(new CassandraTest2(i%10)).start();
		}
		long endTime=System.currentTimeMillis();
		log.info("完成处理数据用时"+(endTime-startTime));
	}

	public void run() {
		testWriteOf100Percent();
	}

	public void testWriteOf100Percent() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao utDao = context.getBean(UserTokenDao.class);
		log.info("线程"+thread+"开始插入100000条数据到cassandra中。");
		long totalTime = 0;
		for (UserToken ut:map.get(thread)) {
			long startTime = System.currentTimeMillis();
			utDao.save(ut);
			long endTime = System.currentTimeMillis();
			long opTime = endTime - startTime;
			log.info("线程"+thread+"本次操作用时" + opTime + "ms");
			totalTime = totalTime + opTime;
		}
		log.info("线程"+thread+"完成插入100000条数据到cassandra中,用时" + totalTime + "毫秒");
		context.close();
	}

}
