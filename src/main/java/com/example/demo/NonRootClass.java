package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class NonRootClass {
	private static final Logger LOGGER = LoggerFactory.getLogger(NonRootClass.class);
	public void checkLogsAndMCD() {
		MDC.put("nonroot","randomvalue");
		MDC.put("key", "overriding");
		LOGGER.info("Inside NonRootClass");
		MDC.remove("nonroot");
	}
}
