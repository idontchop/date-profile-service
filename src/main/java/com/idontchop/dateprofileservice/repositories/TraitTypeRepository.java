package com.idontchop.dateprofileservice.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.idontchop.dateprofileservice.entities.TraitType;

public interface TraitTypeRepository extends CrudRepository<TraitType, Long> {
	
	public Optional<TraitType> findByName( String name );

}
