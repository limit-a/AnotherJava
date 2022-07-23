package homework.game;

import java.util.Arrays;

public class GameMain {

	int stage;
	Hero hero;
	Card[] cardDeck;
	Enemy[] enemyPool;

	GameMain() {

		this.stage = 0;
		this.cardDeck = new Card[43];
		for (int i = 0; i < 25; i++) {
			this.cardDeck[i] = Card.BANG;
		}
		for (int i = 25; i < 37; i++) {
			this.cardDeck[i] = Card.MiSSED;
		}
		for (int i = 37; i < 43; i++) {
			this.cardDeck[i] = Card.BEER;
		}

		for (int i = 0; i < 1000; i++) {
			Card temp = this.cardDeck[this.cardDeck.length - 1];
			int randomNumber = RandomUtil.random(0, this.cardDeck.length - 1);
			this.cardDeck[this.cardDeck.length
					- 1] = this.cardDeck[randomNumber];
			this.cardDeck[randomNumber] = temp;
		}

//		System.out.println(Arrays.toString(cardDeck));

		this.enemyPool = new Enemy[4];
		this.enemyPool[0] = new Enemy("럭키 듀크", 2, 5);
		this.enemyPool[1] = new Enemy("블랙 잭", 3, 5);
		this.enemyPool[2] = new Enemy("윌리 더 키드", 4, 5);
		this.enemyPool[3] = new Enemy("슬랩 더 킬러", 5, 5);

		for (int i = 0; i < enemyPool.length; i++) {
			for (int j = 0; j < this.enemyPool[i].life; j++) {
				this.enemyPool[i].hand[j] = this.cardDeck[RandomUtil.random(0,
						this.cardDeck.length - 1)];
				this.enemyPool[i].handCount++;
			}
		}

		this.hero = new Hero("전재한", 4, 5);
		for (int i = 0; i < this.hero.life; i++) {
			this.hero.hand[i] = this.cardDeck[RandomUtil.random(0,
					RandomUtil.random(0, this.cardDeck.length - 1))];
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
		System.out.printf("%s와 싸웁니다.\n", this.enemyPool[this.stage].name);
		int input = 0;
		battle: while (true) {
			this.hero.drawCard(this.cardDeck);
			System.out.println("카드를 두 장 드로우 했습니다.");
			this.hero.showInfo();
			System.out.print("카드를 선택하세요 : ");
			input = ScanUtil.nextInt();
			this.hero.useCard(this.enemyPool[stage], input);

//			if (this.hero.life < this.hero.hand.length) {
//				System.out.printf("카드를 %d장 버려야 합니다\n",
//						this.hero.hand.length - this.hero.life);
//				for (int i = 0; i < this.hero.hand.length
//						- this.hero.life; i++) {
//					this.hero.showInfo();
//					System.out.print("카드를 선택하세요 : ");
//					input = ScanUtil.nextInt();
//					this.hero.disCard(input);
//				}
//			}
			this.stage++;
			break battle;
		}
	}

	public void start() {
		System.out.println("=====게임 시작======");
		int input = 0;
		game: while (true) {
			if (this.stage > 3) {
				System.out.println("엔딩");
				break game;
			}
			System.out.println("1. 내 정보    2. 전투    0. 종료");
			System.out.print(">>> ");
			input = ScanUtil.nextInt();
			switch (input) {
			case 1:
				clearScreen();
				this.hero.showInfo();
				break;
			case 2:
				clearScreen();
				battle();
				break;
			case 0:
				clearScreen();
				System.out.println("게임을 종료합니다.");
				break game;

			default:
				break;
			}
		}
//		System.out.println(Arrays.toString(cardDeck));
	}

	public static void main(String[] args) {

		new GameMain().start();

	}

}
