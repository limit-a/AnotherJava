package homework.game;

import java.util.Arrays;

public class Card {

	String cardName;

	private Card() {
	}

	public Card(String cardName) {
		this.cardName = cardName;
	}

	@Override
	public String toString() {
		return cardName;
	}

	public void activeCard(Player player) {
		System.out.println("선택한 카드 : " + this.cardName);
		switch (this.cardName) {
		case "뱅!":
			player.life -= player.life;
			break;
		case "빗나감!":

			break;
		case "맥주":

			break;

		default:
			break;
		}
	}

}
