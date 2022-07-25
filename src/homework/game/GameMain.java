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
		this.cardDeck = new Card[43];
		for (int i = 0; i < 25; i++) {
			this.cardDeck[i] = Card.BANG;
		}
		for (int i = 25; i < 37; i++) {
			this.cardDeck[i] = Card.MISSED;
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

	public void battle() {

		System.out.println("==========전투 시작==========");
		System.out.println();
		System.out.printf("%s와(과) 싸웁니다.\n", this.enemyPool[this.stage].name);
		TimeUtil.secondsSleep(3);
		battle: while (true) {
			if (!turn) {
				heroTurn: while (true) {
					clearScreen();
					// 영웅 드로우
					this.hero.drawCard(this.cardDeck);
					System.out.println();
					// 영웅 정보창
					this.hero.showInfo();
					System.out.println();
					System.out.println(
							"---적의 이름--- : " + this.enemyPool[this.stage].name);
					System.out.println(
							"---적의 체력--- : " + this.enemyPool[this.stage].life);
					System.out.println();
					// 영웅 카드 선택
					System.out.print("카드를 선택하세요 : ");
					int input = ScanUtil.nextInt();
					// 영웅 카드 선택 스위치문
					switch (this.hero.hand[input - 1]) {
					case MISSED:
						clearScreen();
						System.out.println("빗나감 카드는 사용할 수 없습니다.");
						TimeUtil.secondsSleep(3);
						continue heroTurn;
					case BANG:
						clearScreen();
						System.out.printf("%s(이)가 뱅!을 사용했습니다.\n",
								this.hero.name);
						System.out.println();
						this.hero.useCard(this.enemyPool[stage], input - 1);
						break;
					case BEER:
						clearScreen();
						if (this.hero.life == this.hero.maxLife) {
							System.out.println("맥주 카드를 사용할 수 없습니다.");
							System.out.println();
							TimeUtil.secondsSleep(3);
						} else {
							this.hero.useCard(this.hero, input - 1);
						}
						continue heroTurn;

					default:
						break;
					}
					// 영웅 드로우 체크
					this.hero.drawed = false;

					// 영웅 체력보다 많은 카드 버리기
					if (this.hero.life < this.hero.hand.length) {
						System.out.printf("카드를 %d장 버려야 합니다\n",
								this.hero.hand.length - this.hero.life);
						for (int i = 0; i < this.hero.hand.length
								- this.hero.life; i++) {
							this.hero.showInfo();
							System.out.print("카드를 선택하세요 : ");
							input = ScanUtil.nextInt();
							this.hero.disCard(input - 1);
						}
					}
					this.turn = true;
					break heroTurn;
				}
			} else {
				enemyTurn: while (true) {
					clearScreen();
					// 적 라이프 체크
					if (this.enemyPool[this.stage].life <= 0) {
						clearScreen();
						System.out.printf("%s(이)가 죽었습니다.\n",
								this.enemyPool[this.stage].name);
						System.out.println();
						TimeUtil.secondsSleep(3);
						this.stage++;
						break battle;
					}
					// 적 드로우
					this.enemyPool[this.stage].drawCard(this.cardDeck);
					System.out.println();
					// 적 카드 선택
					int randomIndex = RandomUtil.random(0,
							this.enemyPool[this.stage].hand.length - 1);
					// 적 카드 선택 스위치문
					switch (this.enemyPool[this.stage].hand[randomIndex]) {
					case MISSED:
						continue enemyTurn;
					case BANG:
						System.out.printf("%s(이)가 뱅!을 사용했습니다.\n",
								this.enemyPool[this.stage].name);
						System.out.println();
						this.enemyPool[this.stage].useCard(this.hero,
								randomIndex);
						break;
					case BEER:
						if (this.enemyPool[this.stage].life == this.enemyPool[this.stage].maxLife) {
						} else {
							this.enemyPool[this.stage].useCard(
									this.enemyPool[this.stage], randomIndex);
							System.out.println();
						}
						continue enemyTurn;

					default:
						break;
					}
					// 적 드로우 체크
					this.enemyPool[this.stage].drawed = false;

					// 적 체력보다 많은 카드 버리기
					if (this.enemyPool[this.stage].life < this.enemyPool[this.stage].hand.length) {
						System.out.printf("%s(이)가 카드를 %d장 버립니다\n",
								this.enemyPool[this.stage].name,
								this.enemyPool[this.stage].hand.length
										- this.enemyPool[this.stage].life);
						TimeUtil.secondsSleep(3);
						for (int i = 0; i < this.enemyPool[this.stage].hand.length
								- this.enemyPool[this.stage].life; i++) {
							this.hero.disCard(RandomUtil.random(0,
									this.enemyPool[this.stage].hand.length
											- 1));
						}
					}
					this.turn = false;
					break enemyTurn;
				}
			}

		}
	}

	public void start() {
		System.out.println("===========게임 시작===========");
		System.out.println();
		int input = 0;
		game: while (true) {
			if (this.stage > 3) {
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
				this.hero.showInfo();
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
