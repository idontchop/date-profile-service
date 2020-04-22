package com.idontchop.dateprofileservice.controllers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.idontchop.dateprofileservice.dtos.RestMessage;
import com.idontchop.dateprofileservice.dtos.UserProfileDto;
import com.idontchop.dateprofileservice.entities.Profile;
import com.idontchop.dateprofileservice.repositories.ProfileRepository;
import com.idontchop.dateprofileservice.services.ProfileService;

/**
 *  GET  	/api/profile/{name}
 *  POST 	/api/profile
 *  DELETE	/api/profile/{name}
 *  
 * @author nate
 *
 */
@RestController
public class UserProfileController {
	
	@Autowired
	ProfileService profileService;
	
	@GetMapping ("/api/profile/{name}")
	public Profile getProfile (@PathVariable String name) {
		return profileService.getProfile(name);
	}
	
	@PostMapping ("/api/profile")
	public Profile postProfile (@RequestBody UserProfileDto userProfileDto ) {
		return profileService.addProfile(userProfileDto);
	}
	
	@DeleteMapping ( "/api/profile/{name}" )
	public RestMessage deleteProfile(@PathVariable String name ) {
		profileService.deleteProfile(name);
		return RestMessage.build("");
	}
	
	@ExceptionHandler ({NoSuchElementException.class})
	public ResponseEntity<RestMessage> notFound () {
		return ResponseEntity.notFound().build();
	}

}
