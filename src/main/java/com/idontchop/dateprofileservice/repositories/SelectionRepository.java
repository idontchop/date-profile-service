package com.idontchop.dateprofileservice.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.idontchop.dateprofileservice.entities.Selection;
import com.idontchop.dateprofileservice.entities.TraitType;

public interface SelectionRepository extends CrudRepository <Selection, Long> {
	
	public Optional<Selection> findByTitle (String title );

	public Optional<Selection> findByNameAndTraitType (String name, TraitType traitType);
}
