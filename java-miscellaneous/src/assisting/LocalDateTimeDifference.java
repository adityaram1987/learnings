package assisting;

import java.time.Duration;
import java.time.LocalDateTime;

public class LocalDateTimeDifference {

	public static void main(String[] args) {
		LocalDateTime start = LocalDateTime.now();
		System.out.println(start);
		
		for(int i=0 ; i<100000; i++) {
		}
		
		LocalDateTime end = LocalDateTime.now();
		System.out.println(end);
		
		Long duration = Duration.between(start, end).toMillis();
		System.out.println("Duration : "+ duration);
	}
}
