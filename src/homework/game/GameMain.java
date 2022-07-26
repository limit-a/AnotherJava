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
		for (int i = 44; i < 47; i++) {
			this.cardDeck[i] = Card.PANIC;
		}

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

	public int unableTurn(Player player) {
		int check = 0;
		for (int i = 0; i < hero.hand.length; i++) {
			if (Card.MISSED.equals(hero.hand[i])
					|| Card.BEER.equals(hero.hand[i])) {
				check++;
			}
		}
		return check;
	}

	public void battle() {

		System.out.println("==========전투 시작==========");
		System.out.println();
		System.out.printf("%s와(과) 싸웁니다.\n", enemyPool[stage].name);
		TimeUtil.secondsSleep(3);

		battle: while (true) {
			if (!turn) {
				heroTurn: while (true) {
					clearScreen();
					if (hero.life <= 0) {
						clearScreen();
						System.out.printf("%s(이)가 죽었습니다.\n", hero.name);
						System.out.println();
						TimeUtil.secondsSleep(3);
						break battle;
					}
					// 영웅이 드로우
					hero.drawCard(cardDeck);
					TimeUtil.secondsSleep(3);
					// 영웅의 정보창
					hero.showInfo();
					System.out.println();
					// 영웅이 카드를 사용 못하는 상황 체크
					if (hero.hand.length == unableTurn(hero)) {
						System.out.println("영웅이 카드를 사용할 수 없습니다.");
						this.turn = true;
						TimeUtil.secondsSleep(3);
						break heroTurn;
					}
					System.out
							.println("---적의 이름--- : " + enemyPool[stage].name);
					System.out
							.println("---적의 체력--- : " + enemyPool[stage].life);
					System.out.println();
					// 영웅이 카드 선택
					System.out.print("카드를 선택하세요 : ");
					int input = ScanUtil.nextInt();
					// 영웅의 카드를 선택하는 스위치문
					switch (hero.hand[input - 1]) {
					case MISSED:
						clearScreen();
						System.out.println("빗나감 카드는 사용할 수 없습니다.");
						TimeUtil.secondsSleep(3);
						continue heroTurn;
					case BANG:
						clearScreen();
						System.out.printf("%s(이)가 뱅!을 사용했습니다.\n", hero.name);
						System.out.println();
						hero.useCard(hero, enemyPool[stage], input - 1);
						break;
					case BEER:
						clearScreen();
						if (hero.life == hero.maxLife) {
							System.out.println("체력이 충만하여 맥주 카드를 사용할 수 없습니다.");
							System.out.println();
							TimeUtil.secondsSleep(3);
						} else {
							hero.useCard(hero, enemyPool[stage], input - 1);
						}
						continue heroTurn;
					case PANIC:
						clearScreen();
						hero.useCard(hero, enemyPool[stage], input - 1);
						continue heroTurn;

					default:
						break;
					}
					// 영웅이 드로우를 했는지 체크
					this.hero.drawed = false;

					// 영웅이 체력보다 많은 카드 버리기
					if (hero.life < hero.hand.length) {
						System.out.println("카드를 버려야 합니다");
						for (int i = 0; i < hero.hand.length - hero.life; i++) {
							hero.showInfo();
							System.out.print("카드를 선택하세요 : ");
							int inputLife = ScanUtil.nextInt();
							hero.disCard(inputLife - 1);
						}
					}
					this.turn = true;
					break heroTurn;
				}
			} else {
				enemyTurn: while (true) {
					clearScreen();
					// 적의 라이프 체크
					if (enemyPool[stage].life <= 0) {
						clearScreen();
						System.out.printf("%s(이)가 죽었습니다.\n",
								enemyPool[stage].name);
						System.out.println();
						TimeUtil.secondsSleep(3);
						stage++;
						break battle;
					}
					// 적 드로우
					enemyPool[stage].drawCard(cardDeck);
					System.out.println();
					// 적이 카드를 사용 못하는 상황 체크
					if (enemyPool[stage].hand.length == unableTurn(
							enemyPool[stage])) {
						System.out.println("적이 카드를 사용할 수 없습니다.");
						this.turn = false;
						TimeUtil.secondsSleep(3);
						break enemyTurn;
					}
					// 적 카드 선택
					int randomIndex = RandomUtil.random(0,
							enemyPool[stage].hand.length - 1);
					// 적 카드 선택 스위치문
					switch (enemyPool[stage].hand[randomIndex]) {
					case MISSED:
						continue enemyTurn;
					case BANG:
						System.out.printf("%s(이)가 뱅!을 사용했습니다.\n",
								enemyPool[stage].name);
						System.out.println();
						enemyPool[stage].useCard(enemyPool[stage], hero,
								randomIndex);
						break;
					case BEER:
						if (enemyPool[stage].life == enemyPool[stage].maxLife) {
						} else {
							enemyPool[stage].useCard(enemyPool[stage], hero,
									randomIndex);
							System.out.println();
						}
						continue enemyTurn;
					case PANIC:
						enemyPool[stage].useCard(enemyPool[stage], hero,
								randomIndex);
						System.out.println();
						continue enemyTurn;

					default:
						break;
					}
					// 적이 드로우를 했는지 체크
					this.enemyPool[this.stage].drawed = false;

					// 적이 체력보다 많은 카드 버리기
					if (enemyPool[stage].life < enemyPool[stage].hand.length) {
						System.out.printf("%s(이)가 카드를 버립니다\n",
								enemyPool[stage].name);
						TimeUtil.secondsSleep(3);
						for (int i = 0; i < enemyPool[stage].hand.length
								- enemyPool[stage].life; i++) {
							enemyPool[stage].disCard(RandomUtil.random(0,
									enemyPool[stage].hand.length - 1));
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
