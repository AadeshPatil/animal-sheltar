package com.mca.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mca.project.entity.Pet;

public interface PetRepo extends JpaRepository<Pet, Long>{
	

}
