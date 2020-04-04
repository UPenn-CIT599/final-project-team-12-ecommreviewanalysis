import java.util.ArrayList;

/**
 * We will run the analysis on input file and result from sentiment analyis together
 * for us to get answers to our questions in this class.
 * @author Xiting, Xinyi, Yong-Jin
 *
 */
public class ReviewAnalysis {
	
	private ArrayList<Review> reviews;
	
	public ReviewAnalysis(ArrayList<Review> reviews) {
		this.reviews= reviews;
	}
	
	/**
	 * To get the negative reviews
	 * Consider the review is negative if the sentiment score is less then -3
	 * @return negative reviews arraylist<Review>
	 */
	public ArrayList<Review> getNegativeReviews() {
		ArrayList<Review> negReviews = new ArrayList<Review>();
		for (Review review : reviews) {
			if(review.getSentimentScore() <= -3) {
				negReviews.add(review);
			}
		}
		return negReviews;
	}
	
	

}
