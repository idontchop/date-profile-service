package com.idontchop.dateprofileservice.dtos;

import java.util.List;

import com.idontchop.dateprofileservice.entities.Trait;

public class UserProfileDto {
	

	private String username;			// Not editable
	
	// Profile variables
	private String title;			
	private String aboutMe;
	private String lookingFor;
	private List<TraitSelectionPair> traits;
	
	
	private List<Trait> newTraits;		// built in profile service from traitselectionpair
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public String getLookingFor() {
		return lookingFor;
	}
	public void setLookingFor(String lookingFor) {
		this.lookingFor = lookingFor;
	}
	public List<TraitSelectionPair> getTraits() {
		return traits;
	}
	public void setTraits(List<TraitSelectionPair> traits) {
		this.traits = traits;
	}
	public List<Trait> getNewTraits() {
		return newTraits;
	}
	public void setNewTraits(List<Trait> newTraits) {
		this.newTraits = newTraits;
	}
	
	

}
