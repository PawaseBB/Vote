package com.bhu.V.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhu.V.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long>{
	Admin findByAdminname(String adminname);
}
