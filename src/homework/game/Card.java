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

	public void activeCard(Player oneself, Player opponent) {
		switch (name) {
		case "뱅!":
			for (int i = 0; i < opponent.hand.length; i++) {
				if (Card.MISSED.equals(opponent.hand[i])) {
					System.out.printf("%s(이)가 빗나감!을 사용했습니다.\n", opponent.name);
					opponent.disCard(i);
					System.out.println();
					TimeUtil.secondsSleep(3);
					break;
				}
			}
			System.out.printf("%s(이)가 공격받아 체력이 1 내려갔습니다.\n", opponent.name);
			opponent.life--;
			System.out.println();
			TimeUtil.secondsSleep(3);
			break;

		case "맥주":
			System.out.printf("%s(이)가 맥주를 사용해 체력을 1 올렸습니다.\n", oneself.name);
			oneself.life++;
			TimeUtil.secondsSleep(3);
			break;

		case "강탈!":
			int index = RandomUtil.random(0, opponent.hand.length - 1);
			oneself.panicCard(oneself, opponent, index);
			opponent.disCard(index);
			System.out.printf("%s가 %s의 %s를 강탈했습니다.\n", oneself.name,
					opponent.name, opponent.hand[index]);
			TimeUtil.secondsSleep(3);
			break;

		default:
			break;
		}
	}

}
