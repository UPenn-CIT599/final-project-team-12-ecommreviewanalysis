import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is for us to run our different java code class in one main location
 * executing sequence of actions we need. We will read the data, analyze, and
 * output the data over here.
 * 
 * @author YongJin, Xinyi, Xiting
 *
 */
public class ReviewAnalysisRunner {

	public static void main(String[] args) {

		ReviewReader secondRR = new ReviewReader("Sentiment_Analysis_Output.csv", true);
		try {
			secondRR.readFile();
			ArrayList<Review> newValidReviews = secondRR.getValidReviews();
			ReviewAnalysis ra = new ReviewAnalysis(newValidReviews);
			
			System.out.println("The average age of positive reviews is " + ra.getAveAgeOfPosReviews());
			System.out.println();
			
			System.out.println("The median age of positive reviews is " + ra.getMedianAgeOfPosReviews());
			System.out.println();
			
			System.out.println("Model with best overall reviews in each class:");
			ra.findModelWithKeywords(1);
			System.out.println();
			
			System.out.println("Model with worst overall reviews in each class:");
			ra.findModelWithKeywords(-1);
			System.out.println();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Oops...Something went wrong with csvParser");
		}	
	}
}
