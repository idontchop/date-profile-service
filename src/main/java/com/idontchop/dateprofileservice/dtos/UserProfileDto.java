package com.idontchop.dateprofileservice.dtos;

import java.util.List;

public class UserProfileDto {
	

	private String username;			// Not editable
	
	// Profile variables
	private String title;			
	private String aboutMe;
	private String lookingFor;
	private List<TraitSelectionPair> traits;
	
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
	
	

}
