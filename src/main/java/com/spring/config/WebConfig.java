package com.spring.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class WebConfig {
	
	@Bean("messageSource")
	MessageSource getMessageSource() {
		
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setDefaultEncoding(StandardCharsets.UTF_8.name());
		source.setBasename("classpath:/i18n/message");
		
		return source;
	}
}
