package com.idontchop.dateprofileservice.dtos;

/**
 * For passing in the ReduceRequest.
 * 
 * Looks for the selection under the trait.
 * 
 * @author micro
 *
 */
public class TraitSelectionPair {
	
	public TraitSelectionPair () {}
	
	

	private String trait;
	
	private String selection;
	
	public TraitSelectionPair (String trait, String selection ) {
		this.trait = trait;
		this.selection = selection;
	}

	public String getTrait() {
		return trait;
	}

	public void setTrait(String trait) {
		this.trait = trait;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((selection == null) ? 0 : selection.hashCode());
		result = prime * result + ((trait == null) ? 0 : trait.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TraitSelectionPair other = (TraitSelectionPair) obj;
		if (selection == null) {
			if (other.selection != null)
				return false;
		} else if (!selection.equals(other.selection))
			return false;
		if (trait == null) {
			if (other.trait != null)
				return false;
		} else if (!trait.equals(other.trait))
			return false;
		return true;
	}
	
	public boolean equals ( String trait, String selection ) {
		TraitSelectionPair other = new TraitSelectionPair(trait, selection);
		return equals(other);
	}
	
	
	
	
}
