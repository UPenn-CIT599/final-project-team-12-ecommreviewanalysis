import java.util.Scanner;

/*
 * BlackjackSolitare class gathers all other objects and process the game.
 */
public class BlackjackSolitaire {
	private Deck deck;
	private Grid grid;

	//constructor will initialize deck and grid.
	public BlackjackSolitaire() {
		deck = new Deck();
		grid = new Grid();
	}

	//method to play...
	public void play() {
		// 1. shuffle the card.
		deck.shuffle();
		Scanner input = new Scanner(System.in);
		// the loop will continue until all the 16 spots (1 through 16) is filled.
		while( !grid.checkIfAllSpotsFilled()) {
			//2. show grid.
			grid.showGridPane();
			//3. deal a card.
			Card cardToBePlaced = deck.DealACard();
			//4a. Have user to input the spot location
			System.out.println("Please type the number from 1 to 20 where you want to place the card:");
			int userSelection = input.nextInt();
			//4b. if the user input on the spot where Card is already placed, ask for the input
			//until the user input the right spot.
			//since the user's view of spot index is one bigger than actual index,
			//we are subtracting one.
			//Added one additional check to ensure the users put in the correct spot index.

			while( grid.isInputOutOfPlace(userSelection)) { 
				System.out.println("You entered invalid number...Please enter the spot number again:");
				userSelection = input.nextInt();
			}

			while( grid.isCardAlreadyPlaced(userSelection)) { 
				System.out.println("There is card already in the spot... Please enter the spot number again: ");
				userSelection = input.nextInt();
			}
			//place the card to the user's selection
			grid.placeCardToGrid(userSelection, cardToBePlaced);
		}
		//the game is finished. doing some clean up, calculate score, present it.
		//show the game is over.
		input.close();
		//show final grid.
		grid.showGridPane();
		//print out the score.
		System.out.println("All 16 spots are filled.... Calculating your score...");
		ScoreCalculator scoreCalculator = new ScoreCalculator();
		int score = scoreCalculator.getScore(grid);
		System.out.println("You scored....." + score + "!!!");
		System.out.println("You are done with the game.");

	}

}
