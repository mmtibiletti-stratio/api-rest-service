package com.santander.serenity.security.service.controller;

import com.santander.serenity.security.service.json.ErrorResponse;
import com.santander.serenity.security.service.json.ServiceRequest;
import com.santander.serenity.security.service.json.ServiceResponse;
import com.santander.serenity.security.service.service.ServiceName;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * This service description
 */
@RestController
public class ServiceController {

	// Logger instance
	private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

	@Autowired
	private ServiceName serviceName;

	@ApiOperation ( value = "endpointMethod", notes = "" )
	@RequestMapping ( method = RequestMethod.POST, path = "/endpointMethod", produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiResponses ( value = {
			@ApiResponse ( code = 200, message = "Success", response = ServiceResponse.class ),
			@ApiResponse ( code = 400, message = "Request is malformed or there are missing mandatory parameters.", response =
					ErrorResponse.class ),
			@ApiResponse( code = 401, message = "Incorrect credentials.", response = ErrorResponse.class),
			@ApiResponse ( code = 405, message = "Request HTTP method not supported.", response = ErrorResponse.class ),
			@ApiResponse ( code = 500, message = "Internal server error.", response = ErrorResponse.class )
	} )
	public ServiceResponse endpointMethod(
			@RequestHeader(value="GS-AUTH-TOKEN") String headers,
			@RequestBody ServiceRequest request) throws Exception {

		return null;

	}
}
