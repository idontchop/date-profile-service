package com.idontchop.dateprofileservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.idontchop.dateprofileservice.entities.TraitCategory;

public interface TraitCategoryRepository extends CrudRepository <TraitCategory, Long> {
	
	public Optional<TraitCategory> findByName ( String name );
	public List<TraitCategory> findByGender ( String gender );

}
