package com.idontchop.dateprofileservice.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.mapping.Set;

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
	
	private LocalDate birthday;
	
	private Date created = new Date();
	
	private String profileImageId;
	
	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "profile", fetch = FetchType.EAGER)	
	private List<Trait> traits = new ArrayList<>();		// list of traits for this user, can be empty
	
	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "profile")
	private List<PostLink> postLinks = new ArrayList<>();
	
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
	
	
	
	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	

	public List<PostLink> getPostLinks() {
		return postLinks;
	}

	public void setPostLinks(List<PostLink> postLinks) {
		this.postLinks = postLinks;
	}

	public void fromdto ( UserProfileDto userProfileDto ) {
		
		this.setName( userProfileDto.getUsername() );  
		this.setTitle( userProfileDto.getTitle() );		// display name
		this.setAboutMe( userProfileDto.getAboutMe() );
		this.setLookingFor( userProfileDto.getLookingFor() );
		
		// set reference in trait to this profile
		userProfileDto.getNewTraits().stream().forEach( this::addTrait );
	}
	
	/**
	 * True if the profile has a trait of a certain trait type.
	 * 
	 * @param traitType
	 * @return
	 */
	public boolean hasTrait ( TraitType traitType ) {
		
		boolean retVal = false;
		for ( Trait trait : this.traits ) {
			if ( trait.isType(traitType.getName()))
				retVal = true;
		}
		
		return retVal;
		
	}
	
	public void addTrait ( Trait trait ) {
		
		// filter traits for the traittype
		List<Trait> old = this.traits.stream()
				.filter( t -> t.isType(trait.getType()))
				.collect(Collectors.toList()); // foreach(this.traits:remove) why it doesn't work?
		this.traits.removeAll(old);
		
		// reference this profile in trait
		trait.setProfile(this);
		this.traits.add(trait);
		
	}

	public String getProfileImageId() {
		return profileImageId;
	}

	public void setProfileImageId(String profileImageId) {
		this.profileImageId = profileImageId;
	}

}
