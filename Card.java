/*
 * This class is made for Playing cards.
 * @author Yong Jin Lee.
 */
public class Card {
	private String cardName; 
	private String cardRank;
	private int numberValue; 
	private boolean AceFlag = false;
	/*
	 * Constructor for Card. Can be defined like AH, implies Ace Heart.
	 * 3D, 3 diamonds, etc. 
	 * The constructor by default extract the card type such as Heart, Diamond, Clover, and Spade.
	 * As well as basic numerical value for Cards.
	 * **Special Note on Ace, for now, Ace's numerical value is defined as 1. 
	 * ** 1 or 11 logic would be applied at BlackJackSolitaire class. 
	 * @param cardName
	 */
	public Card(String cardName){
		this.cardName = cardName;		
		if( cardName.length() == 2) {
			this.cardRank = cardName.substring(0, 1);
		}
		else if (cardName.length() == 3) {
			this.cardRank = cardName.substring(0, 2);
		}
		else {
			System.out.println("Wrong cardName has been entered!!");
		}
		//if the card is Ace, put place AceFlag = true so we can put special logic for 
		//score calculation.
		if(cardRank.equals("A") ) {
			numberValue = 1;
			AceFlag = true;
		}
		if(cardRank.equals("2")) {
			numberValue = 2;
		}
		if(cardRank.equals("3")) {
			numberValue = 3;
		}
		if(cardRank.equals("4")) {
			numberValue = 4;
		}
		if(cardRank.equals("5")) {
			numberValue = 5;
		}
		if(cardRank.equals("6")) {
			numberValue = 6;
		}
		if(cardRank.equals("7")) {
			numberValue = 7;
		}
		if(cardRank.equals("8")) {
			numberValue = 8;
		}
		if(cardRank.equals("9")) {
			numberValue = 9;
		}
		if(cardRank.equals("10")) {
			numberValue = 10;
		}
		if(cardRank.equals("J")) {
			numberValue = 10;
		}
		if(cardRank.equals("Q")) {
			numberValue = 10;
		}
		if(cardRank.equals("K")) {
			numberValue = 10;
		}


	}
	//give the string version of the card name.
	public String getCardName() {
		return this.cardName;
	}

	//return the numerical value of card for points and further more score.
	public int getNumberValue() {
		return this.numberValue;
	}
	//check if the card is ace or not so special logic can be applied later on.
	public boolean isAce() {
		return this.AceFlag;
	}

}
