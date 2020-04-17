package com.idontchop.dateprofileservice.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.idontchop.dateprofileservice.dtos.UserProfileDto;

@Entity
public class Profile {
	
	public Profile () {}
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String name;		// username supplied by token
	
	@NotEmpty
	private String title;		// "Name" of user
	
	private Date created = new Date();
	
	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "profile", fetch = FetchType.EAGER)	
	List<Trait> traits = new ArrayList<>();		// list of traits for this user, can be empty
	
	/**
	 * This is the main profile, it goes on the browse pages.
	 * 
	 */
	@Column ( length = 5000, nullable = false )
	private String aboutMe;
	
	/**
	 * This is a sub profile. Seen only if the user is looking at the profile.
	 */
	@Column ( length = 5000, nullable = false )
	private String lookingFor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public List<Trait> getTraits() {
		return traits;
	}

	public void setTraits(List<Trait> traits) {
		this.traits = traits;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void fromdto ( UserProfileDto userProfileDto ) {
		
		this.setName( userProfileDto.getUsername() );  
		this.setTitle( userProfileDto.getTitle() );		// display name
		this.setAboutMe( userProfileDto.getAboutMe() );
		this.setLookingFor( userProfileDto.getLookingFor() );
		
		List<Trait> newTraits = new ArrayList<>();
		
		userProfileDto.getTraits().stream()
			.forEach( pair -> {
				/* pain
				 * Cycle through traits,
				 * 	check newTraits, add selection 
				 * 	 if new check this.traits for pair.trait
				 * 	 if found, delete
				 *   create new trait in newTraits
				 * then
				 *  after loop, add newTraits to this.traits
				 * 
				 */
			
			});
	}

}
