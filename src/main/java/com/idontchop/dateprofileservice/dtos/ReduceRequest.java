package com.idontchop.dateprofileservice.dtos;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * ReduceRequest Profile Service Version.
 * 
 * @author micro
 *
 */
public class ReduceRequest {

	// This list is some potential options the api has considered.
	// Using the name, the service will check the potentials against the interestedins
	@NotEmpty(message = "Need at least one potential")
	List<String> potentials;
	
	@NotEmpty(message = "Need at least one selection")
	List<TraitSelectionPair> selections;

	public List<String> getPotentials() {
		return potentials;
	}

	public void setPotentials(List<String> potentials) {
		this.potentials = potentials;
	}

	public List<TraitSelectionPair> getSelections() {
		return selections;
	}

	public void setSelections(List<TraitSelectionPair> selections) {
		this.selections = selections;
	}


	
	
}
