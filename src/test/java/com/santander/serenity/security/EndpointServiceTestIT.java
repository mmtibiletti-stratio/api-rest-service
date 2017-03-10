package com.santander.serenity.security;

import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

/**
 * Integration tests on endpoint.
 */
public class EndpointServiceTestIT extends AbstractIntegrationTest {

	/**
	 * Test <i>/endpoint</i> endpoint with valid input data.
	 * <p>
	 * Preconditions:
	 * -
	 * <p>
	 * Expected result:  a 200 - OK is returned
	 */
	@Test
	public void generateNonce_ok() throws Exception {
		//ServiceRequest request = new ServiceRequest();
		//testEndpointService_ok(request, GENERATE_NONCE_ENDPOINT);
	}

}
