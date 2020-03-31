package com.idontchop.dateprofileservice.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Trait {
	
	public Trait () {}
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne ( fetch = FetchType.EAGER)
	private Profile profile;
	
	@ManyToOne ( fetch = FetchType.EAGER)
	private TraitType traitType;
	
	@OneToMany
	private List<Selection> selections;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public TraitType getTraitType() {
		return traitType;
	}

	public void setTraitType(TraitType traitType) {
		this.traitType = traitType;
	}

	public List<Selection> getSelections() {
		return selections;
	}

	public void setSelections(List<Selection> selections) {
		this.selections = selections;
	}
	
	

}
