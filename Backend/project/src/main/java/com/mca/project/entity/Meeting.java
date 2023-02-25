package com.mca.project.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Meeting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String petName;
	private LocalDate meetingDate;
	private LocalTime meetingTime;
	private String message;

	public Meeting(Long id, String name, String email, String petName, LocalDate meetingDate, LocalTime meetingTime,
			String message) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.petName = petName;
		this.meetingDate = meetingDate;
		this.meetingTime = meetingTime;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public LocalDate getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(LocalDate meetingDate) {
		this.meetingDate = meetingDate;
	}

	public LocalTime getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(LocalTime meetingTime) {
		this.meetingTime = meetingTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Meeting() {
		super();
		// TODO Auto-generated constructor stub
	}

}
