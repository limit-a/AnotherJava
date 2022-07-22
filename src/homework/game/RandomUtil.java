package homework.game;

import java.util.Random;

public class RandomUtil {

	static Random random = new Random();

	public static int random(int firstInt, int lastInt) {
		return random.nextInt((lastInt - firstInt + 1)) + firstInt;
	}

}
