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
		if (!this.drawed) {
			Card[] temp = new Card[this.hand.length + 2];
			for (int i = 0; i < this.hand.length; i++) {
				temp[i] = this.hand[i];
			}
			temp[temp.length - 2] = cardDeck[RandomUtil.random(0,
					cardDeck.length - 1)];
			temp[temp.length - 1] = cardDeck[RandomUtil.random(0,
					cardDeck.length - 1)];
			this.hand = new Card[this.hand.length + 2];
			this.hand = temp;
			this.drawed = true;
		}
	}

	public void disCard(int index) {

		Card[] temp = new Card[this.hand.length - 1];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = i >= index ? this.hand[i + 1] : this.hand[i];
		}
		this.hand = new Card[this.hand.length - 1];
		this.hand = temp;

	}

	public void useCard(Player oneself, Player opponent, int index) {
		this.hand[index].activeCard(oneself, opponent, index);
//		disCard(index);
	}

	public void addCard(Card card) {
		Card[] temp = new Card[this.hand.length + 1];
		for (int i = 0; i < this.hand.length; i++) {
			temp[i] = this.hand[i];
		}
		temp[temp.length - 1] = card;
		this.hand = new Card[this.hand.length + 1];
		this.hand = temp;
	}

}
