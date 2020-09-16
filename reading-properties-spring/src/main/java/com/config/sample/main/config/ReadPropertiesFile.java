package com.config.sample.main.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
public class ReadPropertiesFile {

	@Value("${app.name}")
	public String name;
	
	@Bean
	public void testing() {
		System.out.println("testing " + name);
	}
}
