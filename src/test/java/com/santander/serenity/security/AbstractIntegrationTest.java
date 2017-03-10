package com.santander.serenity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Base test class for nonce service endpoints. Hide the spring test scaffolding to the main test classes.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( classes = {/*ServiceApplication.class*/} )
@WebIntegrationTest( randomPort = true )
@ActiveProfiles( {"test"} )
public abstract class AbstractIntegrationTest {

    // Endpoints
    // protected static final String SERVICE_ENDPOINT = "/serviceEndPoint";

    //Expressions to evaluate JSONPath
    private final static String ERROR_CODE_JSON = ".code";
    private final static String ERROR_MESSAGE_JSON = ".message";
    private final static String SERVICE_JSON = ".service";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public final void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    /*protected ResultActions testEndpointService_ok(ServiceRequest request, String endpoint) throws Exception {
        return invokeEndpointWithJsonBody(
                HttpMethod.POST,
                endpoint,
                new ObjectMapper().writeValueAsBytes(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath(SERVICE_JSON).exists())
                .andDo(log());
    }*/

    /*protected ResultActions testEndpointService_ko(ServiceRequest request, String endpoint, HttpMethod method, ErrorService
            errorExpected) throws Exception {
        return invokeEndpointWithJsonBody(
                method,
                endpoint,
                new ObjectMapper().writeValueAsBytes(request))
                .andExpect(status().is(errorExpected.getCode()))
                .andExpect(jsonPath(ERROR_CODE_JSON).value(errorExpected.getCode()))
                .andExpect(jsonPath(ERROR_MESSAGE_JSON).value(errorExpected.getError()))
                .andDo(log());
    }*/

    private ResultActions invokeEndpointWithJsonBody(HttpMethod method, String endpoint, byte[] requestBody) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        List<String> test = new ArrayList<String>();
        test.add("eyJraWQiOiJJRFBTRVJFTklUWV9TSEExd2l0aFJTQSIsInR5cCI6IkpXVCIsImFsZyI6IlJTMjU2In0" +
                ".eyJzdWIiOiJ4MDIxMDk2IiwiYXVkIjoiU0FSQVNFUkVOSVRZIiwibmJmIjoxNDY4OTE5MTE5LCJpc3MiOiJJRFBTRVJFTklUWSIsImV4cCI6MzYwMTQ2ODkxOTExOSwiaWF0IjoxNDY4OTE5MTE5LCJqdGkiOiI4MmEzODY4Ny1kMGUxLTRkNGItYTFhZC0xNTdkNzViOTk0ZjkifQ.YWFPwGR-Nz1NuNy5NZzLpyPaBRcfXtwHtN8WX-NjNvSyE8b8XE_qRghu12ejEC8TmBqelBvm3sXfv8cMev2znZDTMhJqPlf4YLgC6vYXdqTHCVPpPrIGOCDBOD0GTZJjiJPRe4toXZ_DcBEqXkUATKk1tYqTY71eWQLeJVOtduFjimJluEAfKqekLcS_XZGoFtB4hF2whrbQ6aEiAPzT872NsZFKicZFJtn8BcTtSEAf_RL8C0qzF4mhsO3DqHaHbJ0ZbPRGmEURss80xbmWld5oG7hJS38Hh_c4NkYqa_FV_LSzgIhQkAKLGvnFkDkRd9Fxp5zqHKZ3eW3RdrIBjQ");
        headers.put("GS-AUTH-TOKEN", test);
        if (HttpMethod.GET.equals(method)) {
            return mockMvc.perform(get(endpoint)
                    .headers(headers)
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        }
        else {
            return mockMvc.perform(post(endpoint)
                    .headers(headers)
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        }
    }

}
