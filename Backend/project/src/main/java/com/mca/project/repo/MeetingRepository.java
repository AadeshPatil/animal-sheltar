package com.mca.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mca.project.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long>{

}
