package com.marsyoung.cassandra;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value="usertoken")
@Persistent
public class UserToken2 {
	
	
	@PrimaryKey
	UserTokenKey userTokenKey;
	@Column
	String usertoken;
	@Column
	long expireTime;
	
	public UserToken2() {
	}

	public UserToken2(UserTokenKey userTokenKey, String usertoken,
			long expireTime) {
		super();
		this.userTokenKey = userTokenKey;
		this.usertoken = usertoken;
		this.expireTime = expireTime;
	}

}
