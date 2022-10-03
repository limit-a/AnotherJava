package jdbc_example;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class JavaBoardUtil {

	static Scanner scanner = new Scanner(System.in);

	static String scanLine() {
		return scanner.nextLine();
	}

	static int scanInt() {
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자가 아닙니다");
			return -1;
		}

	}

	static void clearScreen() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}

	static void timeSleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}