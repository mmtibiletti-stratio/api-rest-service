package com.santander.serenity.security.service.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

/**
 * Configuration used for testing.
 */
@Configuration
@ActiveProfiles ( {"test"} )
public class TestCfg {

	private static final Logger logger = LoggerFactory.getLogger(TestCfg.class);

}
