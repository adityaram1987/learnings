package com.logs.extraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogExtractor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogExtractor.class);

	public static void main(String[] args) {
		SpringApplication.run(LogExtractor.class, args);
		
		if(args.length < 3) {
			System.out.println("Expecting 3 arguments - start time, end time and File location");
			return;
		}
		DateTime applicationStartTime = DateTime.now();
		
		String startDateTimeString = args[0];
		String endDateTimeString = args[1];
		String path = args[2];
		LOGGER.info("path : " + path + " startDateTimeString : " + startDateTimeString + " endDateTimeString : " + endDateTimeString);
		
		DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
		DateTime startDateTime = DateTime.parse(startDateTimeString, dateFormatter);
		DateTime endDateTime = DateTime.parse(endDateTimeString, dateFormatter);
		File dir = new File(path);
		List<File> list = Arrays.asList(dir.listFiles(new FilenameFilter(){
	        @Override
	        public boolean accept(File dir, String name) {
	            return name.startsWith("LogFile") && name.endsWith(".log");
	        }}));
		
		LOGGER.info("Files in the specified path : " + list);
		
		Consumer<File> printLogs = printInboundedLogsMethod(startDateTime, endDateTime);
		Predicate<? super File> filterFiles = filterOutOfBoundLogFiles(startDateTime, endDateTime);
		
		LOGGER.info("Performing parallel extract from the log files. Output consists logs in different order");
		list.parallelStream().filter(filterFiles)
				.forEach(printLogs);
		
		DateTime applicationEndTime = DateTime.now();
		
		LOGGER.info("Time taken for the extraction : " + 
				(new Duration(applicationStartTime, applicationEndTime)).getMillis() + " milli seconds");
	}

	private static Predicate<? super File> filterOutOfBoundLogFiles(DateTime startDateTime, DateTime endDateTime) {
		return fileName -> {
			BufferedReader reader = null;
			try {
				reader = Files.newBufferedReader(
				        fileName.toPath(), StandardCharsets.UTF_8);
				DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
								
				String startLine = reader.readLine();
				String logFileStartDateString = startLine.substring(0, startLine.indexOf(','));
				DateTime logFileStartDateTime = DateTime.parse(logFileStartDateString, dateFormatter);
				LOGGER.info("Starting time of logs in file - " + fileName + " : " + logFileStartDateTime);
				
				long lineCount = Files.lines(fileName.toPath()).count();
				
				List<String> logFileEndLine = reader.lines().skip(lineCount-2).limit(1).collect(Collectors.toList());
				String logFileEndDateTimeString = logFileEndLine.get(0).substring(0, logFileEndLine.get(0).indexOf(','));
				DateTime logFileEndDateTime = DateTime.parse(logFileEndDateTimeString, dateFormatter);
				LOGGER.info("Ending time of logs in file - " + fileName + " : " + logFileEndDateTime);
				
				Boolean isValidFile = !(logFileStartDateTime.isAfter(endDateTime) || 
						logFileEndDateTime.isBefore(startDateTime));
				LOGGER.info("FIle - " + fileName + " consists logs of the required time period? " + isValidFile);
				
				return isValidFile;
			} catch (IOException e) {
				LOGGER.error("Error in accessing file - " + fileName + " : " + e);
			}
			return false;
		};
	}

	private static Consumer<File> printInboundedLogsMethod(DateTime startDateTime, DateTime endDateTime) {
		return fileName -> {
						DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
			try (BufferedReader b = new BufferedReader(new FileReader(fileName))) {
				String logLine = "";
				while ((logLine = b.readLine()) != null) {
					String loggedDateString = logLine.substring(0, logLine.indexOf(','));
					DateTime loggedDate = DateTime.parse(loggedDateString, dateFormatter);
					if((loggedDate.isAfter(startDateTime) || loggedDate.equals(startDateTime)) && 
								(loggedDate.isBefore(endDateTime) || loggedDate.equals(endDateTime))){
						System.out.println(logLine);
					}
					if(loggedDate.isAfter(endDateTime)) {
						break;
					}
				}
			} catch (IOException e) {
				LOGGER.error("Error in accessing file - " + fileName + " : " + e);
			} 
			
		};
	}

}
