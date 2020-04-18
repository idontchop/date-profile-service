package com.idontchop.dateprofileservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idontchop.dateprofileservice.dtos.TraitSelectionPair;
import com.idontchop.dateprofileservice.entities.Selection;
import com.idontchop.dateprofileservice.entities.Trait;
import com.idontchop.dateprofileservice.entities.TraitType;
import com.idontchop.dateprofileservice.repositories.SelectionRepository;
import com.idontchop.dateprofileservice.repositories.TraitRepository;
import com.idontchop.dateprofileservice.repositories.TraitTypeRepository;

@Service
public class TraitService {
	
	@Autowired
	TraitRepository traitRepository;
	
	@Autowired
	TraitTypeRepository traitTypeRepository;
	
	@Autowired
	SelectionRepository selectionRepository;
	
	/**
	 * Creates a list of traits from the TraitSelectionPair.
	 *  
	 * If the Trait Type is not found, the trait is ignored.
	 * 
	 * TODO: test thoroughly
	 * 
	 * @param pairs
	 * @return
	 */
	public List<Trait> traitListFromSelectionPairs ( List<TraitSelectionPair> pairs ) {
		
		List<Trait> traitList = new ArrayList<>();
		
		for ( TraitSelectionPair pair : pairs) {
			
			// Get traittype, can't do anything without
			Optional<TraitType> traitType = traitTypeRepository.findByName(pair.getTrait());			
			if ( traitType.isPresent() ) {
				
				// Build a Selection
				Optional<Selection> selection = selectionRepository.findByNameAndTraitType(pair.getSelection(), traitType.get());
				if ( selection.isPresent() ) {
					
					// here we have a valid Selection and Trait type.
					traitList = addTrait (traitType.get(), selection.get(), traitList);
				}
				
			}
			
		}
		
		traitRepository.saveAll(traitList);
		
		return traitList;
	}
	
	/**
	 * Helper to traitListFromSelectionPairs
	 * Adds a trait to the list or adds selection
	 * to trait that exists
	 * 
	 * @param trait
	 * @param traitList
	 * @return
	 */
	private List<Trait> addTrait ( TraitType traitType, Selection selection, List<Trait> traitList ) {
		
		boolean traitInList = false;
		for (Trait t : traitList ) { 
			if ( t.isType(traitType.getName()) ) {
				
				// update if not already in selections
				if ( ! t.getSelections().contains(selection) ) {
					t.getSelections().add(selection);
				}
				traitInList = true;
			} 
		}
		
		if ( ! traitInList ) {
			Trait newTrait = new Trait();
			newTrait.setTraitType(traitType);
			newTrait.getSelections().add(selection);
			traitList.add(newTrait);
		}
		
		return traitList;
	}

}
