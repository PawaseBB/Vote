package com.bhu.V.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhu.V.entity.User;
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);

}
