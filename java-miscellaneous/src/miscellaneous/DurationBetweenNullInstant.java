package miscellaneous;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DurationBetweenNullInstant {

	public static void main(String[] args) {
		Instant startTime = Instant.now();
		String time = startTime.toString();
		System.out.println(startTime);
		System.out.println(time);
		Map <String, String> map = new HashMap<String, String>();
		map.put("start_time", time);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Instant.parse(time));
		String startString = (String) map.get("start_time");
		System.out.println("start time string: " + startString);
		Instant start = Instant.parse(map.get("start_time"));
		System.out.println("time difference: " + Duration.between(start, Instant.now()).toMillis());
		
		
		Date startDate = Date.from(LocalDateTime.now(ZoneOffset.UTC).atZone(ZoneOffset.UTC).toInstant());
		System.out.println(startDate);
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDate);
		System.out.println("formatted date is : " + date);
		
	
	}

}
