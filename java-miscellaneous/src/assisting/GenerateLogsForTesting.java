package assisting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;



public class GenerateLogsForTesting {

	public static void main(String[] args) {
		//2020-01-31T20:12:40.1234Z 2020-02-02T12:12:38.1234Z C:\mywork\learnings\logs-folder
		try {
	         File file = new File("C:\\mywork\\learnings\\custom-logs-folder\\LogFile000003.log");
	         if (!file.exists()) {
	            file.createNewFile();
	         } 
	         FileWriter fw = new FileWriter(file.getAbsoluteFile());
	         BufferedWriter bw = new BufferedWriter(fw);
	         String startDateTimeString = "2035-12-05T21:06:00.1233Z";
	 		
	 		DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
	 		LocalDateTime time = LocalDateTime.parse(startDateTimeString, dateFormatter);
	         for(int i =0; i<50000000;i++) {
	        	 bw.write(time.plusSeconds(5*i) + "3Z, Some Field, Other Field, And so on in the log file, Till new line LogFile 3 - line " + (i+1) + "\n");
	         }
	         bw.close();
	         
	         System.out.println("Done");
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
       
	}

}
