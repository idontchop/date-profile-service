package com.idontchop.dateprofileservice.controllers;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	/**
	 * Returns a list of profiles. This is called by the frontend when a list of posts
	 * has been downloaded and the profile details should be filled in.
	 * 
	 * Any proper JWT can pull any number of profiles with this endpoint.
	 * 
	 * This may need to go through a reducer for blocks later. Though a bad actor could
	 * see the profile of someone, he wouldn't be able to contact or see posts still.
	 * 
	 * @param names
	 * @return
	 */
	@GetMapping ("/api/profile/{names}")
	@CrossOrigin
	public List<UserProfileDto> getProfile (@PathVariable List<String> names) {
		
		return profileService.getProfiles(names).stream().map ( profile -> {
			return new UserProfileDto()
					.from(profile);
		})
		.collect(Collectors.toList());
		
	}
	
	/**
	 * Returns the raw profile for the authenticated user.
	 * 
	 * @param principal
	 * @return
	 */
	@GetMapping("/api/myProfile")
	@CrossOrigin
	public Profile getMyProfile (Principal principal) {
		return profileService.getProfile(principal.getName());
	}
	
	@PostMapping ("/api/profile")
	@CrossOrigin
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
