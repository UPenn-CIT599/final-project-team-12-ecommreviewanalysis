import java.io.IOException;
import java.util.ArrayList;
/**
 * This is the tester class to ensure if things are working correctly as we designed.
 * This actually covers overall process flow.
 * @author Xinyi, Xiting, Yong-Jin
 *
 */
public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReviewReader rr = new ReviewReader();
		try {
			rr.readFile();
			ArrayList<Review> reviews = rr.getReviews();
			SentimentAnalysisOnReviews saor = new SentimentAnalysisOnReviews(reviews);

			//System.out.println(reviews.size());
			
			saor.runSentimentAnalysis();
			SentimentAnalysisOutputFileWriter fileWriter =
					new SentimentAnalysisOutputFileWriter(saor.getUpdatedReviews());

			fileWriter.generateOutputFile();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Oops...Something went wrong with csvParser");
		}

	}

}
