/*
 * Grid class contains 16 spots where users can place their cards from deck for scoring.
 * Also it contians 4 discard spot where users can throw away  their cards.
 * 
 * @author Yong Jin Lee
 *
 */
public class Grid {
	//there are 16 places to place the card + 4 discards spots = 20.
	private Card[] spots = new Card[20];
	//for easier readability, I did not subtract -1 on these indexes. Instead on the indexes on method call
	//I am subtracting -1.
	public final int[][] VERTICAL_INDEXES = {{1, 6},{2, 7, 11, 14},{3, 8, 12, 15},{4, 9, 13, 16}, {5, 10}};
	public final int[][] HORIZONTAL_INDEXES = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13},
			{14,15,16}};

	public Grid(){

	}
	//this is to display the string value of grid spot. If there is a card on grid spot, 
	//return cardName. Otherwise, return corresponding number.
	public String getSpotValue(int spotIndexOnGrid) {
		if(spots[spotIndexOnGrid] == null) {
			return (spotIndexOnGrid+1) + "";
		}

		else {
			return spots[spotIndexOnGrid].getCardName();
		}
	}
	//return numerical value of the card on specific grid on spot. 
	public int getCardNumericalValue(int spotIndexOnGrid) {
		return spots[spotIndexOnGrid-1].getNumberValue();
	}
	//return card on Spot.
	public Card getSpot(int spotIndexOnGrid) {
		return spots[spotIndexOnGrid-1];
	}

	//place a card to the grid spot.
	public void placeCardToGrid(int positionIndex, Card dealtCard) {
		spots[positionIndex - 1] = dealtCard;
	}
	//printout the GridPane.
	public void showGridPane() {
		String output = 
				"--------------Gid Spots---------------\n"
						+ getSpotValue(0) + "\t" + getSpotValue(1) + "\t" + getSpotValue(2)
						+ "\t" + getSpotValue(3)+ "\t" + getSpotValue(4) + "\n"
						+ getSpotValue(5) + "\t" + getSpotValue(6) + "\t" + getSpotValue(7)
						+ "\t" + getSpotValue(8)+ "\t" + getSpotValue(9) + "\n"
						+ "\t" + getSpotValue(10) + "\t" + getSpotValue(11) + "\t" + getSpotValue(12)+ "\n" 
						+ "\t" +getSpotValue(13) + "\t" + getSpotValue(14) + "\t" + getSpotValue(15)+ "\n" 
						+ "------------Discards Spots------------\n"
						+ getSpotValue(16) + "\t" +getSpotValue(17) + "\t" + getSpotValue(18) 
						+ "\t" + getSpotValue(19) + "\n";				
		;
		System.out.println(output);
	}
	//check in case if the user input the spot where the card is already located.
	public boolean isCardAlreadyPlaced(int positionIndex) {
		//int actualIndex = PositionIndex -1 ;
		if(this.spots[positionIndex - 1] == null) {
			return false;
		}
		else {
			return true;
		}
	}
	//check in case if the user input the spot that is out of the index.
	public boolean isInputOutOfPlace(int positionIndex) {
		boolean result = true;
		if (positionIndex >= 1 && positionIndex <= 20) {
			result = false;
		}
		return result;
	}
	
	
	//this method is to be used to check if all the spots are filled in the game except for discard spots.
	public boolean checkIfAllSpotsFilled() {
		boolean result = true;
		for(int i = 1; i <= 16 ; i++) { // need to check if all 16 grid spots are filled.
			if(!isCardAlreadyPlaced(i)) {
				return false;
			}
		}
		return result;
	}

}
