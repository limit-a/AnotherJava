package homework.game;

public abstract class Player {

	String name;
	int life;
	int maxLife;

	Card[] hand;

	private Player() {
	}

	public Player(String name, int life, int maxLife) {
		this.name = name;
		this.life = life;
		this.maxLife = maxLife;
		this.hand = new Card[life];
	}

	public void drawCard(Card[] cardDeck) {
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
	}

	public void disCard(int index) {
		Card[] temp = new Card[this.hand.length - 1];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = i >= index ? this.hand[i + 1] : this.hand[i];
		}
		this.hand = new Card[this.hand.length - 1];
		this.hand = temp;
	}

	public void useCard(Enemy enemy, int input) {
		if (Card.MISSED.equals(this.hand[input - 1])) {
			System.out.println("빗나감 카드는 사용할 수 없습니다.");
		} else {
			this.hand[input - 1].activeCard(enemy);
			disCard(input - 1);
		}
	}

}
