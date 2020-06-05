package miscellaneous;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SleepAlternative {

	public static void main(String[] args) {
		LocalDateTime time1 = LocalDateTime.now();
		System.out.println("Current time : " + time1);
		try {
			//Thread.sleep(3000);
			ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
			int value = scheduler.schedule(() -> 0, 3000, TimeUnit.MILLISECONDS).get();
			System.out.println("value : " + value);
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Exception occured");
			e.printStackTrace();
		}
		LocalDateTime time2 = LocalDateTime.now();
		System.out.println("Current time : " + time2);
		System.out.println("Time difference : ");
	}

}
