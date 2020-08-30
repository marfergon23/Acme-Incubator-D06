/*
 * UserIdentity.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.datatypes;

import java.beans.Transient;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import acme.framework.datatypes.DomainDatatype;
import lombok.Getter;
import lombok.Setter;


@Embeddable
@Getter
@Setter
public class Email extends DomainDatatype {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------


	@NotBlank
	private String				email;

	@NotBlank
	private String				displayName;



	// Derived attributes -----------------------------------------------------

	@Transient
	public String toString() {
		StringBuilder result;
		
		result = new StringBuilder();
		if(this.getDisplayName().isEmpty()) {
			result.append(this.displayName);
			result.append(" ");
			result.append("<");
			result.append(this.email);
			result.append(">");
		}else {
			result.append(this.email);
		}
		return result.toString();
	}

}
