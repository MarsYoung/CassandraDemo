package com.marsyoung.cassandra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;

/**
 * 该类是一个Repository,通过spring注入cassandraTemplate直接操作的。
 * 有的方法需要操作的对象是 经过 cassandra annotated的.
 * @author zhiyuma
 *
 */
@Repository
public class UserTokenDao3 {
	@Autowired
	CassandraOperations cassandraOperations;

	
	public void insertUserToken(UserToken3 userToken) {
		Insert insert = QueryBuilder.insertInto("usertoken");
		insert.setConsistencyLevel(ConsistencyLevel.QUORUM);
		insert.value("passport", userToken.getPassport());
		insert.value("appId", userToken.getAppId());
		insert.value("gid", userToken.getGid());
		insert.value("plat", userToken.getPlat());
		insert.value("usertoken", userToken.getUsertoken());
		insert.value("expireTime", userToken.getExpireTime());
		cassandraOperations.execute(insert);
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
		cassandraOperations.execute(cql);
	}
	
	public void insertUserToken3(UserToken3 userToken) {
		cassandraOperations.insert(userToken);//need userToken be annotated 
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

		cassandraOperations.execute(update);
	}

}
