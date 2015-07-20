package com.marsyoung.cassandra;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class UserTokenKey {
	
	//Note:partition key是用来计算分区的token的，对于单列主键的就是first column in the primary key,对于复合主键的则是可以定义，至少要选择一个，下面我选择了4个作为共同的分区主键。
	@PrimaryKeyColumn(ordinal = 0,type=PrimaryKeyType.PARTITIONED)
	String passport;
	@PrimaryKeyColumn(ordinal = 1,type=PrimaryKeyType.PARTITIONED)
	long appId;
	@PrimaryKeyColumn(ordinal = 2,type=PrimaryKeyType.PARTITIONED)
	String gid;
	@PrimaryKeyColumn(ordinal = 3,type=PrimaryKeyType.PARTITIONED)
	long plat;
	
	public UserTokenKey(String passport, long appId, String gid, long plat) {
		super();
		this.passport = passport;
		this.appId = appId;
		this.gid = gid;
		this.plat = plat;
	}

	public UserTokenKey() {
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (appId ^ (appId >>> 32));
		result = prime * result + ((gid == null) ? 0 : gid.hashCode());
		result = prime * result
				+ ((passport == null) ? 0 : passport.hashCode());
		result = prime * result + (int) (plat ^ (plat >>> 32));
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
		UserTokenKey other = (UserTokenKey) obj;
		if (appId != other.appId)
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
		return true;
	}
	
	

}
