package homework.game;

import java.util.Arrays;

public class Hero extends Player {

	public Hero(String name, int life, int maxLife) {
		super(name, life, maxLife);
	}

	public void showInfo() {
		System.out.println("-------------------영웅------------------");
		System.out.println();
		System.out.printf("현재 라이프 : %d/%d\n", life, maxLife);
		System.out.println();
		System.out.println("----------현재 가지고 있는 카드----------");
		System.out.println();
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] != null) {
				System.out.printf("%d. %-6s", i + 1, hand[i]);
			}
		}
		System.out.println();

	}

}
