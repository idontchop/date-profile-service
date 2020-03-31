package com.idontchop.dateprofileservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table ( name = "selection")
public class Selection {
	
	public Selection () {}
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotEmpty
	@Length ( min = 1, max = 8 )
	private String name;		// a slug for api, unique per trait type or completely unique?
	
	@NotEmpty
	@Length ( min = 1, max = 32 )
	private String title;		// the choice
	
	@Length ( max = 500 )
	private String description; // probably only a tooltip

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
