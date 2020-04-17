package com.idontchop.dateprofileservice.dtos;

/**
 * Holds only an ID.
 * 
 * The trait type can be inferred from the id of the selector.
 * 
 * @author micro
 *
 */
public class GenericSelector {

	private long id;
	private String name;
	
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
	
	
}
