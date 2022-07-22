package homework.game;

public abstract class Player {

	String name;
	int life;
	int maxLife;
	int handCount;

	Card[] hand;

	private Player() {
	}
	
	public Player(String name, int life, int maxLife) {
		this.name = name;
		this.life = life;
		this.maxLife = maxLife;
		this.handCount = 0;
		this.hand = new Card[life];
	}

}
