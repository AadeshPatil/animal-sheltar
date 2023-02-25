package com.mca.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mca.project.entity.UserCredentials;
import com.mca.project.entity.UserMaster;
import com.mca.project.repo.AuthRepo;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private AuthRepo authRepo;

	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody UserMaster user) {
		try {
			Optional<UserMaster> existingUser = authRepo.findByUsername(user.getUsername());
			if (existingUser.isPresent()) {
				return ResponseEntity.badRequest()
						.body("User with username " + user.getUsername() + " already exists.");
			}
			authRepo.save(user);
			return ResponseEntity.ok().body("User registered successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to register user. Please try again later.");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserCredentials credentials) {
		try {
			Optional<UserMaster> user = authRepo.findByUsername(credentials.getUsername());
			if (!user.isPresent()) {
				return ResponseEntity.badRequest().body("Invalid username or password.");
			}
			if (credentials.getPassword().equals(user.get().getPassword()) == false ) {
				return ResponseEntity.badRequest().body("Invalid password.");
			}

			return ResponseEntity.ok().body("LoggedIn successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to login. Please try again later.");
		}
	}

}
