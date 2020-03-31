package com.idontchop.dateprofileservice.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Profile {
	
	public Profile () {}
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String name;		// username supplied by token
	
	private Date created = new Date();
	
	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
	List<Trait> traits;		// list of traits for this user, can be empty
	
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
	
	

}
