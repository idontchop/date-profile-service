package com.idontchop.dateprofileservice.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.idontchop.dateprofileservice.entities.Selection;

public interface TraitSelectionRepository extends CrudRepository <Selection, Long> {
	
	public Optional<Selection> findByTitle (String title );

}
