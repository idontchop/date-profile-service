package com.idontchop.dateprofileservice.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.idontchop.dateprofileservice.entities.Profile;

public interface ProfileRepository extends CrudRepository <Profile, Long> {
	
	public Optional<Profile> findByName (String name);

}
