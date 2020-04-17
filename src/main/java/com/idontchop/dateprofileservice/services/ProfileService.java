package com.idontchop.dateprofileservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idontchop.dateprofileservice.dtos.UserProfileDto;
import com.idontchop.dateprofileservice.entities.Profile;
import com.idontchop.dateprofileservice.repositories.ProfileRepository;

@Service
public class ProfileService {
	
	@Autowired
	ProfileRepository profileRepository;
	
	public Profile addProfile( UserProfileDto userProfileDto ) {
		
		// find or create new profile
		Profile profile = profileRepository.findByName( userProfileDto.getUsername() )
				.orElse( new Profile() );
		
		// update profile 
		profile.setName( userProfileDto.getUsername() );  
		profile.setTitle( userProfileDto.getTitle() );		// display name
		profile.setAboutMe( userProfileDto.getAboutMe() );
		profile.setLookingFor( userProfileDto.getLookingFor() );
	}

}
