package homework.game;

public class Card {
	String cardName;
	

	private Card() {}


	public Card(String cardName) {
		this.cardName = cardName;
	}


	@Override
	public String toString() {
		return cardName;
	}
	
//	public void activeCard(Player player) {
//		
//	}
	
	

}
