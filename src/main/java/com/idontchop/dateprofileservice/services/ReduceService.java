package com.idontchop.dateprofileservice.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idontchop.dateprofileservice.dtos.TraitSelectionPair;
import com.idontchop.dateprofileservice.entities.Profile;
import com.idontchop.dateprofileservice.entities.Selection;
import com.idontchop.dateprofileservice.entities.Trait;
import com.idontchop.dateprofileservice.repositories.ProfileRepository;

@Service
public class ReduceService {
	
	@Autowired
	ProfileRepository profileRepository;
	
	
	/**
	 * Takes a list of potential users and checks they have the selections.
	 * 
	 * This is not a smart check, which should be performed by the front end.
	 * 
	 * If the user has a selection that is in the selection list, they will not
	 * be reduced.
	 * 
	 * @param potentials
	 * @return
	 */
	public List<String> reduceByTraitSelections ( List<String> potentials, List<TraitSelectionPair> selections ) {
		
		return potentials.stream().filter( name -> {
			
			Optional<Profile> profileOpt = profileRepository.findByName(name);
			
			// profile found
			// this is going to be depth hell
			if ( profileOpt.isPresent() ) {
				
				Profile profile = profileOpt.get();
				
				// cycle through each selection of each trait for user
				for ( Trait t : profile.getTraits() ) {
					for ( Selection s : t.getSelections() ) {
						
						// Only need to find one and we can return this element for filter
						if ( traitInSelections (t.getTraitType().getName(), s.getName(), selections)) {
							return true;
							
						}
					}
				}
				
				
			}
			
			return false;
		}).collect(Collectors.toList());				
	}
	
	/**
	 * helper to check if a trait name is in the passed selections
	 * 
	 * @param name
	 * @param selections
	 * @return
	 */
	private boolean traitInSelections ( String trait, String selection, List<TraitSelectionPair> selections ) {
		
		for ( TraitSelectionPair s : selections ) {
			if (s.equals(trait,selection)) return true;
		}
		
		return false;
	}

}
