package homework.game;

import java.util.Arrays;

public enum Card {

	BANG("뱅!"), MISSED("빗나감!"), BEER("맥주"), PANIC("강탈!");

	public final String name;

	private Card() {
		this.name = "";
	}

	Card(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public void activeCard(Player oneself, Player opponent, int index) {
		activeCard: while (true) {
			switch (this.name) {
			case "뱅!":
				System.out.printf("%s(이)가 %s에게 뱅!\n", oneself.name,
						opponent.name);
				oneself.disCard(index);
				TimeUtil.secondsSleep(3);
				for (int i = 0; i < opponent.hand.length; i++) {
					if (Card.MISSED.equals(opponent.hand[i])) {
						System.out.printf("%s(이)가 빗나감! 사용\n", opponent.name);
						opponent.disCard(i);
						TimeUtil.secondsSleep(3);
						return;
					}
				}
				System.out.printf("%s가 데미지 1 받음\n", opponent.name);
				opponent.life--;
				System.out.printf("%s의 체력 %d\n", opponent.name, opponent.life);
				TimeUtil.secondsSleep(3);
				return;
			case "빗나감!":
				System.out.println("빗나감! 카드는 사용할 수 없습니다.");
				TimeUtil.secondsSleep(3);
				break;

			case "맥주":
				if (oneself.life >= oneself.maxLife) {
					System.out.println("맥주 사용 불가능");
				} else {
					System.out.printf("%s(이)가 라이프 1 증가\n", oneself.name);
					oneself.disCard(index);
					oneself.life++;
				}
				TimeUtil.secondsSleep(3);
				return;

			case "강탈!":
				if (opponent.hand.length == 0) {
					System.out.println("강탈할 카드 없음");
				} else {
					oneself.disCard(index);
					oneself.addCard(opponent.hand[index]);
					opponent.disCard(index);
					System.out.printf("%s(이)가 %s의 %s를 훔침\n", oneself.name,
							opponent.name, opponent.hand[index].name);
				}
				TimeUtil.secondsSleep(3);
				return;

			default:
				break;
			}
		}
	}

}
