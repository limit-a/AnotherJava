package homework.game;

import java.util.Arrays;

public enum Card {

	BANG("뱅!"), MISSED("빗나감!"), BEER("맥주");

	public final String name;

	private Card() {
		this.name = "";
	}

	Card(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public void activeCard(Player player) {
		missed: switch (this.name) {
		case "뱅!":
			for (int i = 0; i < player.hand.length; i++) {
				if (Card.MISSED.equals(player.hand[i])) {
					System.out.printf("%s(이)가 빗나감!을 사용했습니다.\n", player.name);
					player.disCard(i);
					TimeUtil.secondsSleep(3);
					break missed;
				}
			}
			System.out.printf("%s(이)가 공격받아 체력이 1 내려갔습니다.\n", player.name);
			player.life--;
			TimeUtil.secondsSleep(3);
			break;

		case "맥주":
			System.out.printf("%s(이)가 맥주를 사용해 체력을 1 올렸습니다.\n", player.name);
			player.life++;
			TimeUtil.secondsSleep(3);
			break;

		default:
			break;
		}
	}

}
