package com.marsyoung.cassandra;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;

public class UserTokenDao4 extends SimpleCassandraRepository<UserToken3, String>{

	public UserTokenDao4(
			CassandraEntityInformation<UserToken3, String> metadata,
			CassandraOperations operations) {
		super(metadata, operations);
	}
	
	public void insertUserToken(UserToken3 userToken) {
		Insert insert = QueryBuilder.insertInto("usertoken");
		insert.setConsistencyLevel(ConsistencyLevel.QUORUM);
		insert.value("passport", userToken.getPassport());
		insert.value("appId", userToken.getAppId());
		insert.value("gid", userToken.getGid());
		insert.value("plat", userToken.getPlat());
		insert.value("usertoken", userToken.getUsertoken());
		insert.value("expireTime", userToken.getExpireTime());
		operations.execute(insert);
	}
	
	public void insertUserToken2(UserToken3 userToken) {
		String cql = "insert into usertoken (passport,appId,gid,plat,usertoken,expireTime) values('"
				+ userToken.getPassport()
				+ "',"
				+ userToken.getAppId()
				+ ",'"
				+ userToken.getGid()
				+ "',"
				+ userToken.getPlat()
				+ ",'"
				+ userToken.getUsertoken()
				+ "',"
				+ userToken.getExpireTime()
				+ ")";
		operations.execute(cql);
	}
	
	public void insertUserToken3(UserToken3 userToken) {
		operations.insert(userToken);//need userToken be annotated 
	}

	public void updateUserToken(UserToken3 userToken) {
		Update update = QueryBuilder.update("usertoken");
		update.setConsistencyLevel(ConsistencyLevel.QUORUM);
		update.with(QueryBuilder.set("usertoken", userToken.getUsertoken()));
		update.with(QueryBuilder.set("expireTime", userToken.getExpireTime()));
		update.where(QueryBuilder.eq("passport", userToken.getPassport()));
		update.where(QueryBuilder.eq("appId", userToken.getAppId()));
		update.where(QueryBuilder.eq("gid", userToken.getGid()));
		update.where(QueryBuilder.eq("plat", userToken.getPlat()));

		operations.execute(update);
	}

}
