package homework.game;

public class Enemy {
	
	String name;
	int life;
	int maxLife;
	int handCount;

	Card[] hand;

	private Enemy() {
	}

	public Enemy(String name, int life, int maxLife) {
		this.name = name;
		this.life = life;
		this.maxLife = maxLife;
		this.handCount = 0;
		this.hand = new Card[100];
	}
	
	

	

}
