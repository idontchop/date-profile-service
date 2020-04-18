package com.idontchop.dateprofileservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.idontchop.dateprofileservice.repositories.ProfileRepository;

@RestController
public class ProfileController {
	
	@Autowired
	ProfileRepository profileRepository;
	
	

}
