package com.marsyoung.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 该接口继承自CassandraRepository，通过Query标签来指定接口的实现。
 * @author zhiyuma
 *
 */
@Repository
public interface UserTokenDao2 extends CassandraRepository<UserToken>{
	
	@Query("select * from usertoken where passport=?0 and appId=?1 and gid=?2 and plat=?3")
	public UserToken findUserTokenByPrimaryKeyColumn(String passport,
			long appId, String gid, long plat);
	
}