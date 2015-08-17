package com.marsyoung.cassandra;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CassandraTest3 {
	private static Log log = LogFactory.getLog(CassandraTest3.class);

	public static void main(String[] args) throws InterruptedException {
		
		if(args==null||args.length<2){
			log.info("need thread number and write-read percent.");
			return ;
		}
		log.info("total "+args[0]+ ";read "+args[1]);
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao utDao = context.getBean(UserTokenDao.class);
		UserTokenDao2 utDao2 = context.getBean(UserTokenDao2.class);

		ExecutorService executor = Executors.newFixedThreadPool(Integer.valueOf(args[0]));
		CountDownLatch latch = new CountDownLatch(Integer.valueOf(args[0]));
		for (int i = 0; i < Integer.valueOf(args[0]); i++) {
			if (i < Integer.valueOf(args[1])) {
				executor.submit(new WriteCassandra(latch, utDao));
			} else {
				executor.submit(new ReadCassandra(latch, utDao2));
			}
			latch.countDown();
		}
	}

	static class WriteCassandra implements Runnable {
		CountDownLatch latch;
		UserTokenDao utDao;

		public WriteCassandra(CountDownLatch latch, UserTokenDao utDao) {
			this.latch = latch;
			this.utDao = utDao;
		}

		public void run() {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Random random = new Random(1000);
			int i = 0;
			long start = System.currentTimeMillis();
			while (true) {
				utDao.save(createWrite(random));
				if (++i % 10000 == 0) {
					long end = System.currentTimeMillis();
					long cost = end - start;
					log.info("write 10000 cost : " + cost + " ms");
					start = System.currentTimeMillis();
				}

			}
		}
	}

	static final long appId = 1;
	static final String gid = "cyrilTestgid---daeaete";
	static final long plat = 1;
	static final String userToken = "cyrilTestuserToken--daedae";

	static UserToken createWrite(Random random) {
		int i = random.nextInt(1000);
		String passport = i + "@sohu.com";
		UserToken ut = new UserToken(passport, appId, gid, plat, userToken,
				random.nextInt(1000));
		return ut;
	}

	static String createRead(Random random) {
		int i = random.nextInt(1000);
		String passport = i + "@sohu.com";
		return passport;
	}

	static class ReadCassandra implements Runnable {
		CountDownLatch latch;
		UserTokenDao2 utDao;

		public ReadCassandra(CountDownLatch latch, UserTokenDao2 utDao) {
			this.latch = latch;
			this.utDao = utDao;
		}

		public void run() {
			latch.countDown();
			Random random = new Random(1000);
			int i = 0;
			long start = System.currentTimeMillis();
			while (true) {
				
				utDao.findUserTokenByPrimaryKeyColumn(createRead(random),
						appId, gid, plat);
				if (++i % 10000 == 0) {
					long end = System.currentTimeMillis();
					long cost = end - start;
					log.info("read 10000 cost : " + cost + " ms");
					start = System.currentTimeMillis();
				}

			}

		}
	}

}