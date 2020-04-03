package com.idontchop.dateprofileservice.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

/**
 * Trait Type is for configuration of profile pages.
 * 
 * For example:
 * 
 * Smokes?
 * 
 * value1 = Never
 * value2 = Sometimes ...
 *
 */
@Entity
@Table ( name = "trait_type" )
public class TraitType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Length ( min = 1, max = 8 )
	private String name;
	
	@NotEmpty
	@Length ( min = 1, max = 32 )
	private String title;
	
	@OneToMany ( cascade = CascadeType.ALL, mappedBy = "traitType" )
	List<Selection> selections = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn( name = "category_id")
	TraitCategory category;
	
	public enum SelectionType {
		RADIO,
		CHECKBOX
	};
	
	private SelectionType selectionType = SelectionType.RADIO;
	
	private int maxChoices;		// how many checkboxes the user is allowed to pick, 0 is any

	public TraitType() {
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
	public List<Selection> getSelections() {
		return selections;
	}
	public void setSelections(List<Selection> selections) {
		this.selections = selections;
	}
	public TraitCategory getCategory() {
		return category;
	}
	public void setCategory(TraitCategory category) {
		this.category = category;
	}
	public SelectionType getSelectionType() {
		return selectionType;
	}
	public void setSelectionType(SelectionType selectionType) {
		this.selectionType = selectionType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMaxChoices() {
		return maxChoices;
	}
	public void setMaxChoices(int maxChoices) {
		this.maxChoices = maxChoices;
	}
	
	
	
   
}
