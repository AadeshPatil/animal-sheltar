package com.mca.project.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mca.project.entity.UserMaster;

public interface AuthRepo extends JpaRepository<UserMaster, Long>{

	Optional<UserMaster> findByUsername(String username);

	
}
