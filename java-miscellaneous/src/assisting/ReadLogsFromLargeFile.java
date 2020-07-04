package assisting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class ReadLogsFromLargeFile {

	public static void main(String[] args) {
		LocalDateTime startTime = LocalDateTime.now();
		//File file = new File("C:\\mywork\\learnings\\logs-folder\\LogFile000001.log");
		File dir = new File("C:\\mywork\\learnings\\custom-logs-folder");
		List<File> list = Arrays.asList(dir.listFiles(new FilenameFilter(){
	        @Override
	        public boolean accept(File dir, String name) {
	            return name.startsWith("LogFile") && name.endsWith(".log");
	        }}));
		System.out.println(list);
		
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("cores : " + cores);
		
		for(File file : list) {
			try {
				BufferedReader reader = Files.newBufferedReader(
				        file.toPath(), StandardCharsets.UTF_8);
				DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
								
				String startLine = reader.readLine();
				String logFileStartDateString = startLine.substring(0, startLine.indexOf(','));
				DateTime logFileStartDateTime = DateTime.parse(logFileStartDateString, dateFormatter);
				System.out.println("Starting time of logs in file - " + file + " : " + logFileStartDateTime);
				reader.close();
				
				
				StringBuilder builder = new StringBuilder();
				RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
			      long fileLength = file.length() - 1;
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
			      System.out.println("Last Line - " + builder.toString());
			      randomAccessFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	
		LocalDateTime endTime = LocalDateTime.now();
		System.out.println("Time taken - " + Duration.between(startTime, endTime).toMillis());
	}

}
