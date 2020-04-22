package com.idontchop.dateprofileservice.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: TraitCategory
 *
 */
@Entity
@Table ( name = "category" )
public class TraitCategory  {
	
	@Id
	@GeneratedValue
	private long id;

	@NotEmpty
	@Length ( min = 1, max = 8 )
	private String name;		// slug for api
	
	@NotEmpty
	@Length ( min = 1, max = 32 )
	private String title;
	
	@NotEmpty
	private String gender;  	// will be supplied in queries
	
	private Date created = new Date();	
	private boolean active = true;
	
	@OneToMany ( mappedBy = "category", cascade = CascadeType.ALL)
	List<TraitType> traitTypes;

	public TraitCategory() {
		super();
	}   
	public long getId() {
		return this.id;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@JsonIgnore
	public List<TraitType> getTraitTypes() {
		return traitTypes;
	}
	public void setTraitTypes(List<TraitType> traitTypes) {
		this.traitTypes = traitTypes;
	}
	
	
   
}
