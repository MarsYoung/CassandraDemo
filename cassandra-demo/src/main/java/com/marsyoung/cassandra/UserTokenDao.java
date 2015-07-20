package com.marsyoung.cassandra;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenDao extends CrudRepository<UserToken, String> {
}