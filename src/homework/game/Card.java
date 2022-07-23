package homework.game;

import java.util.Arrays;

public enum Card {

	BANG("뱅!"), MiSSED("빗나감!"), BEER("맥주");

	public final String name;

	private Card() {
		this.name = "";
	}

	public String toString() {
		return this.name;
	}

	public void activeCard(Player player) {
		System.out.println("선택한 카드 : " + this.name);
		missed: switch (this.name) {
		case "뱅!":
			System.out.println(player.name + "의 핸드");
			System.out.println(Arrays.toString(player.hand));
			for (int i = 0; i < player.hand.length; i++) {
				if (Card.MiSSED.name.equals(player.hand[i].name)) {
					System.out.println(
							(i + 1) + "번째 빗나감 확인 : " + player.hand[i].name);
					player.disCard(i + 1);
					System.out.println(Arrays.toString(player.hand));
					System.out.println(player.name + "의 체력 " + player.life);
					break missed;
				}
			}
			System.out.println("빗나감 없음");
			player.life--;
			System.out.println(Arrays.toString(player.hand));
			System.out.println(player.name + "의 체력 " + player.life);
			break;

		case "맥주":

			break;

		default:
			break;
		}
	}

	Card(String name) {
		this.name = name;
	}

}
