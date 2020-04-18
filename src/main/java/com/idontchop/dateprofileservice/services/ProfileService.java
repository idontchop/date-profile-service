package com.idontchop.dateprofileservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idontchop.dateprofileservice.dtos.UserProfileDto;
import com.idontchop.dateprofileservice.entities.Profile;
import com.idontchop.dateprofileservice.entities.Trait;
import com.idontchop.dateprofileservice.repositories.ProfileRepository;

@Service
public class ProfileService {
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	TraitService traitService;
	
	public Profile addProfile( UserProfileDto userProfileDto ) {
		
		// find or create new profile
		Profile profile = profileRepository.findByName( userProfileDto.getUsername() )
				.orElse( new Profile() );
		
		// Trait lists 
		List<Trait> traitList = traitService.traitListFromSelectionPairs(userProfileDto.getTraits());
		userProfileDto.setNewTraits(traitList);
		
		// now use fromDto
		profile.fromdto(userProfileDto);
		profileRepository.save(profile);
		
		return profile;
	}

}
