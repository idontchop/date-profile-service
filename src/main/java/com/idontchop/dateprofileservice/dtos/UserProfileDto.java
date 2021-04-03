package com.idontchop.dateprofileservice.dtos;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.idontchop.dateprofileservice.entities.PostLink;
import com.idontchop.dateprofileservice.entities.Profile;
import com.idontchop.dateprofileservice.entities.Trait;

public class UserProfileDto {
	

	private String username;			// Not editable
	
	// Profile variables
	private String title;			
	private String aboutMe;
	private String lookingFor;
	private int age;
	private List<TraitSelectionPair> traits;
	private List<PostLink> postLinks;
	
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public List<PostLink> getPostLinks() {
		return postLinks;
	}
	public void setPostLinks(List<PostLink> postLinks) {
		this.postLinks = postLinks;
	}
	public UserProfileDto from ( Profile profile ) {
		username = profile.getName();
		title = profile.getTitle();
		aboutMe = profile.getAboutMe();
		lookingFor = profile.getLookingFor();
		traits = new ArrayList<>();
		postLinks = profile.getPostLinks();
		
		// calculate age based on 1st of month
		LocalDate startDate;
		if ( profile.getBirthday() == null) {
			// development temporary
			startDate = LocalDate.of(1997, 7, 30);
		} else {
			startDate = profile.getBirthday().withDayOfMonth(1);
		}
		LocalDate endDate = LocalDate.now().withDayOfMonth(1);
		age = Period.between(startDate, endDate).getYears();
		
		profile.getTraits().forEach( trait -> {
			trait.getSelections().forEach( selection -> {
				traits.add( new TraitSelectionPair ( trait.getType(), selection.getName()));
			});
		});
		
		return this;
	}

}
