package com.marsyoung.cassandra;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CassandraTest3 {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-data-cassandra.xml" });
		UserTokenDao2 utDao2 = context.getBean(UserTokenDao2.class);
		
		ExecutorService executor = Executors.newFixedThreadPool(100);
		CountDownLatch latch = new CountDownLatch(100);
		for (int i = 0; i < 100; i++) {
			if (i < 100) {
				executor.submit(new WriteCassandra(latch, utDao2));
			} else {
				executor.submit(new ReadCassandra(latch, utDao2));
			}
		}
	}

	static class WriteCassandra implements Runnable {
		CountDownLatch latch;
		UserTokenDao2 utDao2;

		public WriteCassandra(CountDownLatch latch, UserTokenDao2 utDao2) {
			this.latch = latch;
			this.utDao2 = utDao2;
		}

		public void run() {
			latch.countDown();
			while (true) {
				for (int i = 0; i < 10000; i++) {
					UserToken ut=createWrite(i);
					long start = System.currentTimeMillis();
					utDao2.save(ut);
					if (i % 10000 == 0) {
						long end = System.currentTimeMillis();
						long cost = end - start;
						System.out.println("write 10000 cost : " + cost + " ms");
						i = 0;
					}
				}
			}

		}
	}
	
	static UserToken createWrite(Integer i){
		String s = new Integer(i).toString();
		long l = (long) i;
		String passport=s+s+"@sohu.com";
		long appId=l;
		String gid= s+s;
		long plat=l+l;
		UserToken ut = new UserToken(passport, appId, gid,plat, s+s+s+s, l+l+l);
		return ut;
	}

	static class ReadCassandra implements Runnable {
		CountDownLatch latch;
		UserTokenDao2 utDao2;

		public ReadCassandra(CountDownLatch latch, UserTokenDao2 utDao2) {
			this.latch = latch;
			this.utDao2 = utDao2;
		}

		public void run() {
			latch.countDown();
			while (true) {
				for (int i = 0; i < 10000; i++) {
					String s = new Integer(i).toString();
					long l = (long) i;
					String passport=s+s+"@sohu.com";
					long appId=l;
					String gid= s+s;
					long plat=l+l;
					long start = System.currentTimeMillis();
					utDao2.findUserTokenByPrimaryKeyColumn(passport, appId, gid,
							plat);
					if (i % 10000 == 0) {
						long end = System.currentTimeMillis();
						long cost = end - start;
						System.out
								.println("write 10000 cost : " + cost + " ms");
						i = 0;
					}
				}

			}

		}
	}

}
