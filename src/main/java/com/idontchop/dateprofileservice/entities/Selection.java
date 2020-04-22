package com.idontchop.dateprofileservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table ( name = "selection")
public class Selection {
	
	public Selection () {}
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private TraitType traitType;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public TraitType getTraitType() {
		return traitType;
	}

	public void setTraitType(TraitType traitType) {
		this.traitType = traitType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Overridden for name only to make .contains easy on a list.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Selection other = (Selection) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
