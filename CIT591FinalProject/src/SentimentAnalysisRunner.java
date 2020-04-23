import java.util.ArrayList;
/**
 * This block of Code is used to run the Sentiment analysis and create the output file for
 * Our first data analysis with Sentiment data.
 * We are splitting out the process because we want to look at the output file with Sentiment
 * analysis outcome being appended before we walk on the final data analysis.
 * @author Xinyi, Xiting, Yong-Jin
 *
 */
public class SentimentAnalysisRunner {

	public static void main(String[] args) {

		ReviewReader rr = new ReviewReader();
		//read the ecommerce input file.
		rr.readFile();
		ArrayList<Review> reviews = rr.getReviews();
		//Run SentimentAnalysis on this review.
		SentimentAnalysisOnReviews saor = new SentimentAnalysisOnReviews(reviews);
		saor.runSentimentAnalysis();
		//Generate output file with Sentiment Analysis results.
		SentimentAnalysisOutputFileWriter fileWriter =
				new SentimentAnalysisOutputFileWriter(saor.getUpdatedReviews());
		fileWriter.writeOutputFile();
		
	}

}
