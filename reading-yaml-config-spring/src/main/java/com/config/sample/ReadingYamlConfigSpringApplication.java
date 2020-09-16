package com.config.sample;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.config.sample.configuration.AppConfig;
import com.config.sample.configuration.AppConfig.Availability;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@SpringBootApplication
@PropertySource("NA/application.yaml")
public class ReadingYamlConfigSpringApplication implements CommandLineRunner{

	@Autowired
	AppConfig config;
	
	public static void main(String[] args) {
		SpringApplication.run(ReadingYamlConfigSpringApplication.class, args);
	}
	
	public void run(String... args) {
		
		System.out.println("integration: " + config.getIntegration());
		System.out.println("frequency: " + config.getSchedule_Frequency_InMs());
		System.out.println("cloudWatch: " + config.getCloudWatchLogGroup());
		
		System.out.println(config.getMeasures());
		/*for(Availability availability: config.getMeasures().getAvailibility()) {
			System.out.println(availability.getCloudWatchQueryString() + " " + availability.getCloudWatchSearchKeyName()
					+ " " + availability.getName());
		} */

	}

}
