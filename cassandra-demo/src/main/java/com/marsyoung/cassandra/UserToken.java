package com.marsyoung.cassandra;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Table(value="usertoken")
@Persistent
public class UserToken {
	
	
	//Note:partition key是用来计算分区的token的，对于单列主键的就是first column in the primary key,对于复合主键的则是可以定义，至少要选择一个，下面我选择了4个作为共同的分区主键。
	@PrimaryKeyColumn(ordinal = 0,type=PrimaryKeyType.PARTITIONED)
	String passport;
	@PrimaryKeyColumn(ordinal = 1,type=PrimaryKeyType.PARTITIONED)
	long appId;
	@PrimaryKeyColumn(ordinal = 2,type=PrimaryKeyType.PARTITIONED)
	String gid;
	@PrimaryKeyColumn(ordinal = 3,type=PrimaryKeyType.PARTITIONED)
	long plat;
	String usertoken;
	long expireTime;
	
	public UserToken() {
	}

	public UserToken(String passport, long appId, String gid, long plat,
			String usertoken, long expireTime) {
		super();
		this.passport = passport;
		this.appId = appId;
		this.gid = gid;
		this.plat = plat;
		this.usertoken = usertoken;
		this.expireTime = expireTime;
	}

}
