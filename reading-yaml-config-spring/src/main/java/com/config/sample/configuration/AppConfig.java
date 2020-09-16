package com.config.sample.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Setter
@Getter
@NoArgsConstructor
@PropertySource("classpath:/NA/application.yaml")
public class AppConfig {
	
	private String integration;
	private String schedule_Frequency_InMs;
	private String cloudWatchLogGroup;
	//private String measures;
	private Map<String, String> measures;
	
	@Setter
	@Getter
	@NoArgsConstructor
	public class Measures{
		private String name;
	}
	
	@Setter
	@Getter
	@NoArgsConstructor
	public class Availability{
		private String name;
		private String cloudWatchSearchKeyName;
		private String cloudWatchQueryString;
	}
}
