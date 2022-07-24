package homework.game;

import java.util.Arrays;

public class Hero extends Player {

//	String name;
//	int life;
//	int maxLife;
//
//	Card[] hand;

//	private Hero() {
//	}

	public Hero(String name, int life, int maxLife) {
//		this.name = name;
//		this.life = life;
//		this.maxLife = maxLife;
//		this.hand = new Card[life];
		super(name, life, maxLife);
	}

//	public void drawCard(Card[] cardDeck) {
//		Card[] temp = new Card[this.hand.length + 2];
//		for (int i = 0; i < this.hand.length; i++) {
//			temp[i] = this.hand[i];
//		}
//		temp[temp.length - 2] = cardDeck[RandomUtil.random(0,
//				cardDeck.length - 1)];
//		temp[temp.length - 1] = cardDeck[RandomUtil.random(0,
//				cardDeck.length - 1)];
//		this.hand = new Card[this.hand.length + 2];
//		this.hand = temp;
//	}
//
//	public void disCard(int input) {
//		Card[] temp = new Card[this.hand.length - 1];
//		for (int i = 0; i < temp.length; i++) {
//			temp[i] = i >= (input - 1) ? this.hand[i + 1] : this.hand[i];
//		}
//		this.hand = new Card[this.hand.length - 1];
//		this.hand = temp;
//	}
//
//	public void useCard(Enemy enemy, int input) {
//		this.hand[input - 1].activeCard(enemy);
//		disCard(input);
//		Card[] temp = new Card[this.hand.length - 1];
//		for (int i = 0; i < temp.length; i++) {
//			temp[i] = i >= (input - 1) ? this.hand[i + 1] : this.hand[i];
//		}
//		this.hand = new Card[this.hand.length - 1];
//		this.hand = temp;
//	}

	public void showInfo() {
		System.out.println("---영웅---");
		System.out.printf("현재 라이프 : %d/%d\n", this.life, this.maxLife);
		System.out.println("∇∇∇∇∇현재 가지고 있는 카드∇∇∇∇∇");
		for (int i = 0; i < this.hand.length; i++) {
			if (this.hand[i] != null) {
				System.out.printf("%d. %-6s", i + 1, this.hand[i]);
			}
		}
		System.out.println();

	}

}
