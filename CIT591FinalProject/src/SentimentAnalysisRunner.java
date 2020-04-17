import java.io.IOException;
import java.util.ArrayList;
/**
 * Run SentimentAnalysis on our input file then generate 
 * the output file which can be used for the further analysis.
 * @author Xinyi, Xiting (Cindy), Yong-Jin
 *
 */
public class SentimentAnalysisRunner {

	public static void main(String[] args) {

		/*
		 * Testing first version of reading review files
		 */
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
