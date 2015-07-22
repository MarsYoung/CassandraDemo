package com.marsyoung.cassandra;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 该接口继承自CrudRepository本身有一套CRUD的实现。
 * @author zhiyuma
 *
 */
@Repository
public interface UserTokenDao extends CrudRepository<UserToken, String> {
}