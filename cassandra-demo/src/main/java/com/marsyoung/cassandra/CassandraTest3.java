package com.marsyoung.cassandra;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CassandraTest3 {

	public static void main(String[] args) throws InterruptedException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao utDao = context.getBean(UserTokenDao.class);
		UserTokenDao2 utDao2 = context.getBean(UserTokenDao2.class);

		ExecutorService executor = Executors.newFixedThreadPool(100);
		CountDownLatch latch = new CountDownLatch(100);
		for (int i = 0; i < 100; i++) {
			if (i < 95) {
				executor.submit(new WriteCassandra(latch, utDao));
			} else {
				executor.submit(new ReadCassandra(latch, utDao2));
			}
		}
		latch.await();
	}

	static class WriteCassandra implements Runnable {
		CountDownLatch latch;
		UserTokenDao utDao;

		public WriteCassandra(CountDownLatch latch, UserTokenDao utDao) {
			this.latch = latch;
			this.utDao = utDao;
		}

		public void run() {
			latch.countDown();
			Random random = new Random(1000);
			int i = 0;
			long start = System.currentTimeMillis();
			while (true) {
				utDao.save(createWrite(random));
				if (++i % 10000 == 0) {
					long end = System.currentTimeMillis();
					long cost = end - start;
					System.out.println("write 10000 cost : " + cost + " ms");
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
					System.out.println("read 10000 cost : " + cost + " ms");
					start = System.currentTimeMillis();
				}

			}

		}
	}

}