package homework.game;

import java.util.Arrays;

public abstract class Player {

	String name;
	int life;
	int maxLife;
	boolean drawed;

	Card[] hand;

	private Player() {
	}

	public Player(String name, int life, int maxLife) {
		this.name = name;
		this.life = life;
		this.maxLife = maxLife;
		this.hand = new Card[life];
		this.drawed = false;
	}

	public void drawCard(Card[] cardDeck) {
		if (!drawed) {
			Card[] temp = new Card[this.hand.length + 2];
			for (int i = 0; i < hand.length; i++) {
				temp[i] = this.hand[i];
			}
			temp[temp.length - 2] = cardDeck[RandomUtil.random(0,
					cardDeck.length - 1)];
			temp[temp.length - 1] = cardDeck[RandomUtil.random(0,
					cardDeck.length - 1)];
			this.hand = new Card[this.hand.length + 2];
			this.hand = temp;
			this.drawed = true;
			System.out.printf("%s(이)가 카드를 두 장 뽑습니다.\n", name);
			TimeUtil.secondsSleep(3);
		}
	}

	public void disCard(int index) {
		if ((hand.length - 1) <= 0) {
			System.out.printf("더이상 카드를 버릴 수 없습니다.");
		} else {
			Card[] temp = new Card[this.hand.length - 1];
			for (int i = 0; i < temp.length; i++) {
				temp[i] = i >= index ? this.hand[i + 1] : this.hand[i];
			}
			this.hand = new Card[this.hand.length - 1];
			this.hand = temp;
		}
	}

	public void useCard(Player oneself, Player opponent, int index) {
		hand[index].activeCard(oneself, opponent);
		disCard(index);
	}

	public void panicCard(Player oneself, Player opponent, int index) {
		if ((opponent.hand.length - 1) <= 0) {
			System.out.printf("더이상 카드를 강탈할 수 없습니다.");
		} else {
			Card[] temp = new Card[oneself.hand.length + 1];
			for (int i = 0; i < oneself.hand.length; i++) {
				temp[i] = oneself.hand[i];
			}
			temp[temp.length - 1] = opponent.hand[index];
			oneself.hand = new Card[oneself.hand.length + 1];
			oneself.hand = temp;
		}
	}

}
