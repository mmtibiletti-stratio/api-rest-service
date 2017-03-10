package com.santander.serenity.security.service.json;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This bean represent a request in JSON format.
 */
public class ServiceRequest {

	public ServiceRequest() {
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				//.append("", )
				.toString();
	}
}
