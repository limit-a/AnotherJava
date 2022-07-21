package homework.game;

public class Hero {

	String name;
	int life;
	int maxLife;
	int handCount;
	
	Card[] hand;

	private Hero() {
	}

	public Hero(String name, int life, int maxLife) {
		this.name = name;
		this.life = life;
		this.maxLife = maxLife;
		this.handCount = 0;
		this.hand = new Card[100];
	}
	
	public void drawCard() {
		
	}
	
	public void useCard(Enemy enemy, int input) {
		
	}
	
	public void disCard() {
		
	}
	
	public void showInfo() {
		System.out.println("---영웅---");
		System.out.printf("현재 라이프 : %d/%d\n", this.life, this.maxLife);
		System.out.println("∇∇∇∇∇현재 가지고 있는 카드∇∇∇∇∇");
		for (int i = 0; i < this.hand.length; i++) {
			if (this.hand[i] != null) {
				System.out.printf("%d. %s    \n", i + 1, this.hand[i]);
			}
		}

	}

	

}
