package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogbackWithMdcApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogbackWithMdcApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LogbackWithMdcApplication.class, args);
		MDC.put("key", "value");
		LOGGER.info("Logging in main class");
		System.out.println("Checking in another class");
		NonRootClass object = new NonRootClass();
		object.checkLogsAndMCD();
		LOGGER.info("Completed another class");
	}

}
