package homework.game;

import java.util.Arrays;

public class GameMain {

	int stage;
	boolean turn;

	Hero hero;
	Card[] cardDeck;
	Enemy[] enemyPool;

	GameMain() {

		this.stage = 0;
		this.turn = false;
		this.cardDeck = new Card[47];
		for (int i = 0; i < 25; i++) {
			this.cardDeck[i] = Card.BANG;
		}
		for (int i = 25; i < 37; i++) {
			this.cardDeck[i] = Card.MISSED;
		}
		for (int i = 37; i < 43; i++) {
			this.cardDeck[i] = Card.BEER;
		}
		for (int i = 43; i < 47; i++) {
			this.cardDeck[i] = Card.PANIC;
		}

//		System.out.println(Arrays.toString(cardDeck));

		for (int i = 0; i < 10000; i++) {
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

		for (int i = 0; i < this.enemyPool.length; i++) {
			for (int j = 0; j < this.enemyPool[i].life; j++) {
				this.enemyPool[i].hand[j] = this.cardDeck[RandomUtil.random(0,
						this.cardDeck.length - 1)];
			}
		}

		this.hero = new Hero("전재한", 4, 5);
		for (int i = 0; i < this.hero.life; i++) {
			this.hero.hand[i] = this.cardDeck[RandomUtil.random(0,
					RandomUtil.random(0, this.cardDeck.length - 1))];
		}

	}

	public static void clearScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

//	public int unableTurn(Player player) {
//		int check = 0;
//		for (int i = 0; i < hero.hand.length; i++) {
//			if (Card.MISSED.equals(hero.hand[i])
//					|| Card.BEER.equals(hero.hand[i])) {
//				check++;
//			}
//		}
//		return check;
//	}

	public void battle() {

		System.out.printf("%s와 싸웁니다.\n", this.enemyPool[this.stage].name);

		while (true) {

			TimeUtil.secondsSleep(3);

			Player inputPlayer = !this.turn ? this.hero
					: this.enemyPool[this.stage];

			if (inputPlayer.life <= 0) {
				clearScreen();
				System.out.printf("%s가 죽었습니다.\n", inputPlayer.name);
				stage++;
				TimeUtil.secondsSleep(3);
				break;
			}

//			페이즈 1
//			덱에서 카드를 2장 가져온다. 자신의 캐릭터가 카드 가져오기 관련 특수 능력이 있을 경우 특수 능력대로 카드를 가져온다.

			clearScreen();
			inputPlayer.drawCard(cardDeck);
			System.out.printf("%s(이)가 카드를 두 장 뽑습니다.\n", inputPlayer.name);
			TimeUtil.secondsSleep(3);
			if (this.hero.equals(inputPlayer)) {
				for (int i = 0; i < inputPlayer.hand.length; i++) {
					System.out.printf("%d. %-6s", (i + 1),
							inputPlayer.hand[i].name);
				}
				System.out.println();
			}
			TimeUtil.secondsSleep(3);

//			페이즈 2
//			패의 카드를 쓸 수 있다면 원하는 만큼 사용한다.
//			기본적으론 사용 횟수에 제한은 없지만, 뱅은 캐릭터 능력이나 기타 장비 카드 등이 없다면 한 턴에 한 장만 사용 가능하고,
//			빗나감처럼 자신의 턴에 사용할 수 없는 카드 등 때문에 손에 든 카드를 사용할 수 없는 경우가 생긴다. 
//			전략적으로 카드를 선택해서 남길 수도 있다.

			int unableTurnCheck = 0;
			for (int i = 0; i < inputPlayer.hand.length; i++) {
				if (Card.MISSED.equals(inputPlayer.hand[i])
						|| Card.BEER.equals(inputPlayer.hand[i])) {
					unableTurnCheck++;
				}
			}
			if (inputPlayer.hand.length == unableTurnCheck) {
				inputPlayer.drawed = false;
				if (this.hero.equals(inputPlayer)) {
					this.turn = true;
				} else {
					this.turn = false;
				}
				continue;
			}

			int selectCardNum = 0;
			if (!turn) {
				System.out.print("카드 선택 : ");
				selectCardNum = ScanUtil.nextInt() - 1;
				this.hero.useCard(this.hero, this.enemyPool[this.stage],
						selectCardNum);
			} else {
				selectCardNum = RandomUtil.random(0,
						this.enemyPool[this.stage].hand.length - 1);
				this.enemyPool[this.stage].useCard(this.enemyPool[this.stage],
						this.hero, selectCardNum);
			}

//			페이즈 3
//			패에 있는 카드가 자신의 현재 라이프보다 많다면, 현재 라이프 수만큼만 카드를 소지하고 여분의 카드를 전부 버린다.
//			라이프보다 많을 때만 카드를 버릴 수 있지, 카드가 현재 라이프 수 이하일 때는 카드를 버리고 싶다고 임의로 카드를 버리는 것은 불가능하다.

			clearScreen();
			if (inputPlayer.hand.length != 0
					|| inputPlayer.hand.length > inputPlayer.life) {
				for (int i = 0; i < (inputPlayer.hand.length - inputPlayer.life
						+ 1); i++) {
					int disCardNum = 0;
					if (!this.turn) {
						for (int j = 0; j < inputPlayer.hand.length; j++) {
							System.out.printf("%d. %-6s", (j + 1),
									inputPlayer.hand[j].name);
						}
						System.out.println();
						System.out.print("버릴 카드 선택 : ");
						disCardNum = ScanUtil.nextInt() - 1;
					} else {
						disCardNum = RandomUtil.random(0,
								inputPlayer.hand.length - 1);
					}
					inputPlayer.disCard(disCardNum);
				}
			}
//			System.out.println(inputPlayer.name + " 버리기");
//			System.out.println(Arrays.toString(inputPlayer.hand));
//			System.out.println(inputPlayer.hand.length);
//			TimeUtil.secondsSleep(3);

			inputPlayer.drawed = false;
			if (this.hero.equals(inputPlayer)) {
				this.turn = true;
			} else {
				this.turn = false;
			}

		}

	}

	public void start() {
		System.out.println("===========게임 시작===========");
		System.out.println();
		int input = 0;
		game: while (true) {
			if (hero.life <= 0) {
				System.out.println("GAMA OVER");
				System.out.println();
				TimeUtil.secondsSleep(3);
				break game;
			}
			if (stage > 3) {
				System.out.println("THANK YOU FOR PLAYING.");
				System.out.println();
				TimeUtil.secondsSleep(3);
				System.out.println("GAMA OVER");
				System.out.println();
				TimeUtil.secondsSleep(3);
				break game;
			}
			System.out.println("1. 내 정보    2. 전투    0. 종료");
			System.out.println();
			System.out.print(">>> ");
			input = ScanUtil.nextInt();
			switch (input) {
			case 1:
				clearScreen();
				hero.showInfo();
				System.out.println();
				break;
			case 2:
				clearScreen();
				battle();
				break;
			case 0:
				clearScreen();
				System.out.println("게임을 종료합니다.");
				System.out.println();
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
