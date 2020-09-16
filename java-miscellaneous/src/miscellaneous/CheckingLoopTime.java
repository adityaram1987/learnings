package miscellaneous;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class CheckingLoopTime {

	public static void main(String[] args) {
		LocalDateTime currentTime= LocalDateTime.now(ZoneOffset.UTC);
		System.out.println(currentTime);
		DateTime applicationStartTime = DateTime.now();
		int div_3=0, div_5=0,div_6=0;
		
		for(int i=0; i< 100000;i++) {
			if(i%3 == 0) {
				div_3++;
			}
			if(i%5 == 0) {
				div_5++;
			}
			if(i%6 == 0) {
				div_6++;
			}
		}
		System.out.println("div_3: "+ div_3 + " div_5: "+ div_5 + " div_6: "+ div_6);
		DateTime applicationEndTime = DateTime.now();
		
		System.out.println("start time: " + applicationStartTime);
		System.out.println("end time: " + applicationEndTime);
	}

}
