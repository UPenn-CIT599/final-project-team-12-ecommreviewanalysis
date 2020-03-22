import java.io.IOException;
import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReviewReader rr = new ReviewReader();
		try {
			rr.readFile();
			ArrayList<Review> reviews = rr.getReviews();
			
			SentimentAnalysisOnReviews saor = new SentimentAnalysisOnReviews(reviews);
			
			saor.runSentimentAnalysis();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Oops...Something went wrong with csvParser");
		}
		
	}

}
