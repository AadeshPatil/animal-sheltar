package com.mca.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mca.project.entity.Meeting;
import com.mca.project.repo.MeetingRepository;

import java.util.List;

@RestController
@RequestMapping("/meetings")
@CrossOrigin("*")
public class MeetingController {

    @Autowired
    private MeetingRepository meetingRepository;

    // Get a single meeting by ID
    @GetMapping("/{id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable Long id) {
        Meeting meeting = meetingRepository.findById(id).orElse(null);
        if (meeting != null) {
            return ResponseEntity.ok(meeting);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all meetings
    @GetMapping("/")
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    // Create a new meeting
    @PostMapping("/createmeeting")
    public ResponseEntity<Meeting> createMeeting(@RequestBody Meeting meeting) {
        Meeting newMeeting = meetingRepository.save(meeting);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMeeting);
    }

    // Update an existing meeting by ID
    @PutMapping("/{id}")
    public ResponseEntity<Meeting> updateMeeting(@PathVariable Long id, @RequestBody Meeting meeting) {
        Meeting existingMeeting = meetingRepository.findById(id).orElse(null);
        if (existingMeeting != null) {
            existingMeeting.setName(meeting.getName());
            existingMeeting.setEmail(meeting.getEmail());
            existingMeeting.setPetName(meeting.getPetName());
            existingMeeting.setMeetingDate(meeting.getMeetingDate());
            existingMeeting.setMeetingTime(meeting.getMeetingTime());
            existingMeeting.setMessage(meeting.getMessage());
            Meeting updatedMeeting = meetingRepository.save(existingMeeting);
            return ResponseEntity.ok(updatedMeeting);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
