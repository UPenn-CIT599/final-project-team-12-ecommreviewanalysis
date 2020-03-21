import java.util.Random;
/*
 * Deck class contains  52 standard Cards.
 * Enables user to shuffle, deal cards. 
 * @author Yong Jin lee
 *
 */
public class Deck {

	private Card[] cards;
	private int nbrOfCardDealt; // for dealing a card each time.

	public Deck() {
		nbrOfCardDealt = 0;
		cards = new Card[52];
		String[] numberAssignment = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		String[] typesOfCards = {"S","C","H","D"};

		int i = 0;
		for(int j = 0; j < numberAssignment.length; j++) {
			for (int k = 0; k < typesOfCards.length; k++) {
				cards[i] = new Card(numberAssignment[j] + typesOfCards[k]);
				i++;
			}
		}
	}
	/*
	 * Shuffle the card by switching cards by (52) number of cards times. 
	 */
	public void shuffle() {
		Random generator = new Random();
		//show the users that the decks are being shuffled
		System.out.println("Shuffling a deck......");
		for(int i =0; i < cards.length; i++) {
			int shuffleIndex = generator.nextInt(cards.length);
			Card temp = this.cards[shuffleIndex];
			this.cards[shuffleIndex] = this.cards[i];
			this.cards[i] = temp;
		}
	}
	/*
	 * Deal a new card in the deck.
	 */
	public Card DealACard() {
		nbrOfCardDealt++;
		System.out.println("\n*********************************************************************");
		System.out.println("Here is your new card to be placed :" + cards[nbrOfCardDealt-1].getCardName());
		System.out.println("**********************************************************************\n");
		return cards[nbrOfCardDealt-1];
	}

}
