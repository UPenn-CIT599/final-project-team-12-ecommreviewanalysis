import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * We will run the analysis on input file and result from sentiment analysis together
 * for us to get answers to our questions in this class.
 * @author Xiting, Xinyi, Yong-Jin
 *
 */
public class ReviewAnalysis {

	private ArrayList<Review> reviews;
	/**
	 * Constructor for ReviewAnalysis class.
	 * @param reviews It will take ArrayList of reviews that we extracted from csv file and sentiment analysis.
	 */
	public ReviewAnalysis(ArrayList<Review> reviews) {
		this.reviews= reviews;
	}

	/**
	 * To get the negative reviews
	 * Consider the review is negative if the sentiment score is less then -3
	 * @return negative reviews arraylist of reviews.
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

	/**
	 * To get the negative reviews
	 * Consider the review is negative if the sentiment score is more then 3
	 * @return negative reviews arraylist<Review>
	 */
	public ArrayList<Review> getPositiveReviews(){
		ArrayList<Review> posReviews = new ArrayList<Review>();
		for (Review review : reviews) {
			if(review.getSentimentScore() >= 3) {
				posReviews.add(review);
			}
		}
		return posReviews;
	}

	/**
	 * to get average age of positive reviews to better target high potential customers
	 * @param posReviews
	 * @return double average age 
	 */
	public static double getAveAgeOfPosReviews(ArrayList<Review> posReviews) {
		return 0.0;
	}

	/**
	 * to get average age of positive reviews to better target high potential customers
	 * @param posReviews
	 * @return double median age
	 */
	public static double getMedianAgeOfPosReviews(ArrayList<Review> posReviews) {
		return 0.0;
	}

	/**
	 * for each clothing class, get the model(clothingId) that has highest sentiment scores
	 * and get the top 5 keywords of that model
	 * @return className + "," + clothingId + "," + keywords
	 */
	public String getModelWithBestReviews(){
		return null;
	}

	/**
	 * for each clothing class, get the model(clothingId) that has lowest sentiment scores
	 * and get the top 5 keywords of that model
	 * @return className + "," + clothingId + "," + keywords
	 */
	public String getModelWithWorstReviews(){

		return "";
	}

	/**
	 * to get the HashMap ClassName to ClothingIDs(clothing model)
	 * for the clothingID list, sort based on sentiment score from low to high
	 * @return HashMap classToClothingIDs
	 */
	public HashMap<String, ArrayList<String>> getClassToClothingIDs(){
		return null;
	}

	/**
	 * to get hashmap clothingId to SentimentScore
	 * @return lothingIdToSentimentScore
	 */
	public HashMap<String, Integer> getClothingIdToSentimentScore(){
		return null;
	}

	/**
	 * to get the hashmap clothingId to Reviews text
	 * @return modelToReviews
	 */
	public HashMap<String, String> getModelToReviews(){
		return null;
	}

	/**
	 * to get top five frequent words in the review text
	 * remove the punctuation, convert to lowercase, and remove stop words first
	 * @param review
	 * @return
	 */
	public List<String> getTopFiveKeyWords(String review){
		return null;
	}

	/**
	 * get the list of stop words(words that occur frequently but meaningless)
	 * @return
	 */
	public ArrayList<String> getStopWords(){
		return null;
	}	



}
