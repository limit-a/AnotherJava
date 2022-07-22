package homework.game;

import java.util.Scanner;

public class ScanUtil {

	
	// 스캐너를 손쉽게 사용할 수 있는 static 메서드를 가지고 있음
	
		static Scanner scanner = new Scanner(System.in);
		
		public static String nextLine() {
			return scanner.nextLine();
		}
		
		public static int nextInt() {
			return Integer.parseInt(scanner.nextLine());
		}
	
}
