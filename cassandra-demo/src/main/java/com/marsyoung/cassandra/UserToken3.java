package com.marsyoung.cassandra;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Table(value="usertoken")
@Persistent
public class UserToken3 {
	
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
	

	public UserToken3(String passport, long appId, String gid, long plat,
			String usertoken, long expireTime) {
		super();
		this.passport = passport;
		this.appId = appId;
		this.gid = gid;
		this.plat = plat;
		this.usertoken = usertoken;
		this.expireTime = expireTime;
	}

	
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public long getAppId() {
		return appId;
	}
	public void setAppId(long appId) {
		this.appId = appId;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public long getPlat() {
		return plat;
	}
	public void setPlat(long plat) {
		this.plat = plat;
	}
	public String getUsertoken() {
		return usertoken;
	}
	public void setUsertoken(String usertoken) {
		this.usertoken = usertoken;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (appId ^ (appId >>> 32));
		result = prime * result + (int) (expireTime ^ (expireTime >>> 32));
		result = prime * result + ((gid == null) ? 0 : gid.hashCode());
		result = prime * result
				+ ((passport == null) ? 0 : passport.hashCode());
		result = prime * result + (int) (plat ^ (plat >>> 32));
		result = prime * result
				+ ((usertoken == null) ? 0 : usertoken.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserToken3 other = (UserToken3) obj;
		if (appId != other.appId)
			return false;
		if (expireTime != other.expireTime)
			return false;
		if (gid == null) {
			if (other.gid != null)
				return false;
		} else if (!gid.equals(other.gid))
			return false;
		if (passport == null) {
			if (other.passport != null)
				return false;
		} else if (!passport.equals(other.passport))
			return false;
		if (plat != other.plat)
			return false;
		if (usertoken == null) {
			if (other.usertoken != null)
				return false;
		} else if (!usertoken.equals(other.usertoken))
			return false;
		return true;
	}
	
	
}
