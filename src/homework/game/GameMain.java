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
			if (!inputPlayer.drawed) {
				System.out.printf("%s(이)가 카드를 두 장 뽑습니다.\n", inputPlayer.name);
				System.out.println();
				TimeUtil.secondsSleep(3);
			}
			inputPlayer.drawCard(cardDeck);
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
			boolean repeat = false;
			if (!turn) {
				System.out.print("카드 선택 : ");
				selectCardNum = ScanUtil.nextInt();
				repeat = this.hero.useCard(this.hero,
						this.enemyPool[this.stage], selectCardNum - 1);
			} else {
				selectCardNum = RandomUtil.random(0,
						this.enemyPool[this.stage].hand.length - 1);
				repeat = this.enemyPool[this.stage].useCard(
						this.enemyPool[this.stage], this.hero, selectCardNum);
			}
			if (repeat) {
				continue;
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
						System.out.println(inputPlayer.name + "의 체력 : "
								+ inputPlayer.life);
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
			if (this.hero.equals(inputPlayer)) {
//			System.out.println(inputPlayer.name + " 버리기");
				System.out.println(inputPlayer.name + "의 남은 카드");
				System.out.println();
				for (int i = 0; i < inputPlayer.hand.length; i++) {
					System.out.printf("%d. %-6s", (i + 1),
							inputPlayer.hand[i].name);
				}
				System.out.println();
//			System.out.println(inputPlayer.hand.length);
				TimeUtil.secondsSleep(3);
			}

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
			System.out.println("1. 게임 방법    2. 내 정보    3. 전투    0. 종료");
			System.out.println();
			System.out.print(">>> ");
			input = ScanUtil.nextInt();
			switch (input) {
			case 1:
				clearScreen();
				System.out.println("당신은 보안관으로서");
				System.out.println();
				System.out.println("네 명의 서부의 무법자와 싸우게 됩니다.");
				System.out.println();
				TimeUtil.secondsSleep(5);
				clearScreen();
				System.out.println("자신의 턴에");
				System.out.println();
				System.out.println("카드 두 장을 뽑고 시작합니다.");
				System.out.println();
				TimeUtil.secondsSleep(5);
				clearScreen();
				System.out.println("뱅! 카드는 상대방을");
				System.out.println();
				System.out.println("공격하는 카드로");
				System.out.println();
				TimeUtil.secondsSleep(5);
				System.out.println("자신의 턴에 한 장만 쓸 수");
				System.out.println();
				System.out.println("있습니다.");
				System.out.println();
				TimeUtil.secondsSleep(5);
				clearScreen();
				System.out.println("빗나감! 카드는 상대방의 공격을");
				System.out.println();
				System.out.println("회피하는 카드로 자신의 턴에는");
				System.out.println();
				System.out.println("사용할 수 없습니다.");
				System.out.println();
				TimeUtil.secondsSleep(5);
				clearScreen();
				System.out.println("맥주 카드는 체력을 하나");
				System.out.println();
				System.out.println("올려줍니다.");
				System.out.println();
				TimeUtil.secondsSleep(5);
				clearScreen();
				System.out.println("강탈! 카드는 상대방의 카드를");
				System.out.println();
				System.out.println("한 장 뺏어옵니다.");
				System.out.println();
				TimeUtil.secondsSleep(5);
				clearScreen();
				System.out.println("뱅! 카드 이외에는");
				System.out.println();
				System.out.println("카드를 사용하지 않고");
				System.out.println();
				System.out.println("보존할 수 있습니다.");
				System.out.println();
				TimeUtil.secondsSleep(5);
				clearScreen();
				System.out.println("자신의 턴이 끝나면");
				System.out.println();
				System.out.println("자신의 체력만큼의 ");
				System.out.println();
				System.out.println("카드수를 남기고 버리게 됩니다.");
				System.out.println();
				TimeUtil.secondsSleep(5);
				break;
			case 2:
				clearScreen();
				hero.showInfo();
				System.out.println();
				break;
			case 3:
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
