
public class ScoreCalculator {
	private int score;

	public ScoreCalculator() {
		this.score = 0;
	}

	public int getScore(Grid finishedGrid) {
		Grid grid = finishedGrid;
		int sum = 0;
		int nbrOfCards = 0;
		//will count number of Aces to calculate score.
		int countOfAce = 0; 
		for(int i=0; i < grid.VERTICAL_INDEXES.length; i++) {
			for (int j=0; j < grid.VERTICAL_INDEXES[i].length; j++) {
				sum += grid.getSpot(grid.VERTICAL_INDEXES[i][j]).getNumberValue();
				if( grid.getSpot(grid.VERTICAL_INDEXES[i][j]).isAce()) {
					countOfAce++;
				}
				nbrOfCards++;
			}

			for (int k = 0 ; k < countOfAce && sum <= 11; k++) {
				sum += 10; //Ace can be either 1 or 11. It only make sense to add 10 when it is not going to burst.
			}
			score += calculatePoints(sum, nbrOfCards);

			sum = 0;
			nbrOfCards = 0;

		}
		for(int i=0; i < grid.HORIZONTAL_INDEXES.length; i++) {
			for (int j=0; j < grid.HORIZONTAL_INDEXES[i].length; j++) {
				sum += grid.getSpot(grid.HORIZONTAL_INDEXES[i][j]).getNumberValue();
				if( grid.getSpot(grid.HORIZONTAL_INDEXES[i][j]).isAce()) {
					countOfAce++;
				}
				nbrOfCards++;
			}

			for (int k = 0 ; k < countOfAce && sum <= 11; k++) {
				sum += 10; //Ace can be either 1 or 11. It only make sense to add 10 when it is not going to burst.
			}
			score += calculatePoints(sum, nbrOfCards);
			sum = 0;
			nbrOfCards = 0;

		}
		return this.score;
	}


	private int calculatePoints(int sum, int nbrOfCards) {
		int points = 0;
		if (sum == 21 && nbrOfCards == 2) {
			points = 10;
		}
		if (sum == 21 && nbrOfCards > 2) {
			points = 7;
		}
		if (sum == 20) {
			points = 5;
		}
		if (sum == 19) {
			points = 4;
		}
		if (sum == 18) {
			points = 3;
		}
		if (sum == 17) {
			points = 2;
		}
		if (sum <= 16) {
			points = 1;
		}
		return points;	
	}

}
