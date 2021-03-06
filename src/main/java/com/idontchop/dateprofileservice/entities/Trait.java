package com.idontchop.dateprofileservice.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Trait {
	
	public Trait () {}
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne ( fetch = FetchType.EAGER)
	@JoinColumn (name = "profile_id")
	private Profile profile;
	
	@NotNull
	@ManyToOne ( fetch = FetchType.EAGER)
	@JoinColumn ( name = "trait_type_id")
	private TraitType traitType;
	
	@ManyToMany ( fetch = FetchType.EAGER)
	private List<Selection> selections = new ArrayList<>();

	/**
	 * Checks traittype.selectiontype to varify number of selections
	 * matches possible options.
	 * RADIO: 0-1
	 * CHECKBOX: 0-maxchoices
	 * 
	 * @return
	 */
	public boolean isValidSelections () {
		
		if ( selections.size() == 0) 
			return true;
		if ( traitType.isRadio() && selections.size() == 1)
			return true;
		if ( selections.size() <= traitType.getMaxChoices() )
			return true;
		
		return false;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonIgnore
	public Profile getProfile() {
		return profile;
	}

	public Trait setProfile(Profile profile) {
		this.profile = profile;
		return this;
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
	
	@Transient
	public boolean isType ( String typeName ) {
		return traitType.getName().equals(typeName);
		
	}
	
	@Transient
	public String getType () {
		return traitType.getName();
	}
	
	

}
