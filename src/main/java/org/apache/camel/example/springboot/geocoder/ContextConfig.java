package org.apache.camel.example.springboot.geocoder;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Bean
	public CamelContextConfiguration contextConfiguration() {
		return new CamelContextConfiguration() {
			@Override
			public void beforeApplicationStart(CamelContext camelContext) {
				// LOG INFO
				logger.info("★beforeApplicationStart : GlobalOptions = {}", camelContext.getGlobalOptions());
				// 試しにここでセットしても、GeoCodeComponentはGeoCodeEndPointの設定に使っていない。
				camelContext.getGlobalOptions().put("proxyHost", "proxy.host");
				camelContext.getGlobalOptions().put("proxyPort", "8080");
			}

			@Override
			public void afterApplicationStart(CamelContext camelContext) {
				// LOG INFO
				logger.info("★afterApplicationStart : GlobalOptions = {}", camelContext.getGlobalOptions());
			}
		};
	}
}
