package com.santander.serenity.security.service.json;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This bean represent a response in JSON format.
 */
public class ServiceResponse {

	public ServiceResponse() { }

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				//.append("", )
				.toString();
	}
}
