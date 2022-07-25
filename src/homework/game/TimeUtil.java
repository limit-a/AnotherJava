package homework.game;

import java.util.concurrent.TimeUnit;

public class TimeUtil {
	public static void secondsSleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
