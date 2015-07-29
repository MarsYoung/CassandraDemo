package com.marsyoung.cassandra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

/**
 * 该类是一个Repository,通过spring注入cassandraTemplate直接操作的。 有的方法需要操作的对象是 经过 cassandra
 * annotated的.
 * 
 * @author zhiyuma
 *
 */
@Repository
public class UserTokenDao5 {
	@Autowired
	@Qualifier("cqlTemplate")
	CassandraOperations cassandraTemplate;

//	static final String passport = "cyrilTest@sohu.com";
//	static final long appId = 1;
//	static final String gid = "cyrilTestgid";
//	static final long plat = 1;
//	static final String userToken = "cyrilTestuserToken";

	public void insertToken(String passport,String appId,String gid,String plat,String userToken) {
		long time = System.currentTimeMillis();
		Insert insert = QueryBuilder.insertInto("usertoken").values(
				new String[] { "passport", "appId", "gid", "plat", "usertoken",
						"expireTime" },
				new Object[] { passport, appId, gid, plat,
						userToken + time,time + 30l * 24 * 60 * 60 * 1000 }
				);

		insert.setConsistencyLevel(ConsistencyLevel.QUORUM);
		insert.using(QueryBuilder.ttl(10));
		// System.out.println(insert.getQueryString());
		cassandraTemplate.execute(insert);
	}

	public UserToken getToken(String passport,String appId,String gid,String plat) {
		Select select = QueryBuilder.select("passport", "appId", "gid", "plat",
				"usertoken", "expireTime").from("usertoken");
		select.where(QueryBuilder.eq("passport", passport))
				.and(QueryBuilder.eq("appId", appId))
				.and(QueryBuilder.eq("gid", gid))
				.and(QueryBuilder.eq("plat", plat));
		select.setConsistencyLevel(ConsistencyLevel.QUORUM);
		// System.out.println(select.getQueryString());
		UserToken ut = cassandraTemplate.selectOne(select, UserToken.class);
		if (ut == null) {
			System.out.println("ut is null");
		} else {
			System.out.println(ut.toString());
		}
		return ut;
	}
}
