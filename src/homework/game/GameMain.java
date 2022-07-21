package homework.game;

import java.util.Random;
import java.util.Scanner;

public class GameMain {

	int stage;
	Hero hero;
	Card[] cardDeck;
	Enemy[] enemyPool;

	Random random = new Random();
	Scanner scanner = new Scanner(System.in);

	GameMain() {
		this.stage = 0;
		this.cardDeck = new Card[43];
		for (int i = 0; i < 25; i++) {
			this.cardDeck[i] = new Card("뱅!");
		}
		for (int i = 25; i < 37; i++) {
			this.cardDeck[i] = new Card("빗나감!");
		}
		for (int i = 37; i < 43; i++) {
			this.cardDeck[i] = new Card("맥주");
		}

		this.enemyPool = new Enemy[4];
		this.enemyPool[0] = new Enemy("럭키 듀크", 2, 5);
		this.enemyPool[1] = new Enemy("블랙 잭", 3, 5);
		this.enemyPool[2] = new Enemy("윌리 더 키드", 4, 5);
		this.enemyPool[3] = new Enemy("슬랩 더 킬러", 5, 5);

		for (int i = 0; i < enemyPool.length; i++) {
			for (int j = 0; j < this.enemyPool[i].life; j++) {
				this.enemyPool[i].hand[j] = this.cardDeck[random.nextInt(43)];
				this.enemyPool[i].handCount++;
			}
		}

		this.hero = new Hero("전재한", 4, 5);
		for (int i = 0; i < this.hero.life; i++) {
			this.hero.hand[i] = this.cardDeck[random.nextInt(43)];
			this.hero.handCount++;
		}

	}

	public static void clearScreen() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}

	public void battle() {

		System.out.println("===전투 시작=====");
		int input = 0;
		while (true) {
			this.hero.drawCard();
			this.hero.showInfo();
			System.out.print("카드를 선택하세요 : ");
			input = Integer.parseInt(scanner.nextLine());
			this.hero.useCard(this.enemyPool[stage], input);
			this.hero.disCard();
		}
	}

	void start() {
		System.out.println("=====게임 시작======");
		int input = 0;
		System.out.println("1. 내 정보    2. 전투    0. 종료");
		input = Integer.parseInt(scanner.nextLine());
		switch (input) {
		case 1:
			clearScreen();
			this.hero.showInfo();
			break;
		case 2:
			clearScreen();
			battle();
			break;

		default:
			break;
		}
//		System.out.println(Arrays.toString(cardDeck));
	}

	public static void main(String[] args) {

		new GameMain().start();

	}

}
