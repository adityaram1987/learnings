package com.logs.extraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.Predicate;
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
	private static Scanner sc;

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
		//setting no. of parallel threads
		int cores = Runtime.getRuntime().availableProcessors();
		LOGGER.info("Setting the number of threads as per available processors : " + cores);
		ForkJoinPool customThreadPool = new ForkJoinPool(cores);
		try {
			customThreadPool.submit(() ->
			list.parallelStream().filter(filterFiles)
					.forEach(printLogs)	).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error("Exception occured during stream process - " + e);
		}
		
		DateTime applicationEndTime = DateTime.now();
		LOGGER.info("Time taken for the extraction : " + 
				(new Duration(applicationStartTime, applicationEndTime)).getMillis() + " milli seconds");
	}
	

	private static Predicate<? super File> filterOutOfBoundLogFiles(DateTime startDateTime, DateTime endDateTime) {
		return fileName -> {
			try {
				BufferedReader reader = Files.newBufferedReader(
				        fileName.toPath(), StandardCharsets.UTF_8);
				DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
								
				String startLine = reader.readLine();
				String logFileStartDateString = startLine.substring(0, startLine.indexOf(','));
				DateTime logFileStartDateTime = DateTime.parse(logFileStartDateString, dateFormatter);
				LOGGER.info("Starting time of logs in file - " + fileName + " : " + logFileStartDateTime);
				
				if(logFileStartDateTime.isAfter(endDateTime)) {
					LOGGER.info("FIle - " + fileName + " consists logs of the required time period? " + false +" due to start");
					return false;
				}
				
				StringBuilder builder = new StringBuilder();
				RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "r");
			    long fileLength = fileName.length() - 1;
			    // Set the pointer at the last of the file
			    randomAccessFile.seek(fileLength);
			    boolean end = true;
			    for(long pointer = fileLength; pointer >= 0; pointer--){
			      randomAccessFile.seek(pointer);
			      char c;
			      c = (char)randomAccessFile.read(); 
			      // break when end of the line
			      if(c == '\n'){
			      	if(end) {
			      		end = false;
			       	} else {
			       		break;
			       	}
			      }
			      builder.append(c);
			    }
			    // Since line is read from the last so it 
			    // is in reverse so use reverse method to make it right
			    builder.reverse();
			    String logFileEndDateTimeString = builder.toString().substring(0, builder.toString().indexOf(','));
			    DateTime logFileEndDateTime = DateTime.parse(logFileEndDateTimeString, dateFormatter);
			    LOGGER.info("Ending time of logs in file - " + fileName + " : " + logFileEndDateTime);
			    randomAccessFile.close();
			    
			    if(logFileEndDateTime.isBefore(startDateTime)) {
			    	LOGGER.info("FIle - " + fileName + " consists logs of the required time period? " + false +" due to end");
			    	return false;
			    }
			} catch (IOException e) {
				LOGGER.error("Error in accessing file - " + fileName + " : " + e);
			}
			LOGGER.info("FIle - " + fileName + " consists logs of the required time period? " + true);
			return true;
		};
	}

	private static Consumer<File> printInboundedLogsMethod(DateTime startDateTime, DateTime endDateTime) {
		return fileName -> {
			DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
			try {
			/*	inputStream = new FileInputStream(fileName);
				sc = new Scanner(inputStream, "UTF-8");
				while (sc.hasNextLine()) {
					String logLine = sc.nextLine();
					String loggedDateString = logLine.substring(0, logLine.indexOf(','));
					DateTime loggedDate = DateTime.parse(loggedDateString, dateFormatter);
					if (loggedDate.isAfter(startDateTime) && loggedDate.isBefore(endDateTime)) {
						System.out.println(logLine);
					}
					if (loggedDate.isAfter(endDateTime)) {
						break;
					}
				}
				sc.close();
				inputStream.close(); */
				int cores = Runtime.getRuntime().availableProcessors();
				LOGGER.info("Setting the number of threads as per available processors : " + cores);
				ForkJoinPool filterLogsThreadPool = new ForkJoinPool(cores);
				
				filterLogsThreadPool.submit( ()->
						{
							try {
								Files.lines(fileName.toPath()).parallel().forEach(logLine -> {
									String loggedDateString = logLine.substring(0, logLine.indexOf(','));
									DateTime loggedDate = DateTime.parse(loggedDateString, dateFormatter);
									if (loggedDate.isAfter(startDateTime) && loggedDate.isBefore(endDateTime)) {
										System.out.println(logLine);
									}
								});
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						).get();
				;
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

		};
	}

}
