package com.chenx.repository;

import com.chenx.pojo.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author chenx
 * @create 2023-07-17 15:12
 */
public interface UserEntityRepository extends CrudRepository<UserEntity,Integer> {
}

