import java.io.IOException;
import java.util.ArrayList;

/**
 * This is for us to run our different java code class in one main location executing sequence of actions we need.
 * We will read the data, analyze, and output the data over here.
 * @author YongJin, Xinyi, Xiting
 *
 */
public class ReviewAnalysisRunner {

	public static void main(String[] args) {
		/*
		 * This first block of Code is used to run the Sentiment analysis and create the output file for
		 * our secondary data analysis with Sentiment data.
		 * We are splitting out the process because we want to look at the output file with Sentiment analysis outcome
		 * being appended before we walk on the final data analysis.
		 */
		/*
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
		fileWriter.generateOutputFile();

	
		//This second analysis will generate our final analysis output in txt file and also some plots from our analysis.


		ReviewReader secondRR = new ReviewReader(fileWriter.getOutputFileName(), true);
		*/
		
	
		ReviewReader secondRR = new ReviewReader("Sentiment_Analysis_Output.csv", true);

		secondRR.readFile();
		ArrayList<Review> newValidReviews = secondRR.getValidReviews();
		ReviewAnalysis ra = new ReviewAnalysis(newValidReviews);
		AnalysisResultFileWriter arfw = new AnalysisResultFileWriter();
		ArrayList<String> report = new ArrayList<String>();
		
		/*
		System.out.println("We are generating the analysis report for you...");
		System.out.println("It may take a few minutes, thanks for your patience." + "\n");

		String result1 = "The average age of positive reviews is " + ra.getAveAgeOfPosReviews();
		report.add(result1 + "\n");

		String result2 = "The median age of positive reviews is " + ra.getMedianAgeOfPosReviews();
		report.add(result2 + "\n");

		report.add("Clothing ID " + ra.getClothingIDWithMostReviews()
		+ " has the most number of reviews.");
		report.add( "The average age of customers for the most popular item is " 
				+ ra.averageAgeOfMostPopular());
		report.add("The median age of customers for the most popular item is " 
				+ ra.medianAgeOfMostPopular());
		report.add("");

		report.add("In each class, the model with best reviews and the keywords in its reviews are:");
		ArrayList<String> bestReviewModel = ra.findModelWithKeywords(1);
		for (int i = 0; i < bestReviewModel.size(); i++) {
			report.add(bestReviewModel.get(i));
		}
		report.add("");

		report.add("In each class, the model with worst reviews and the keywords in its reviews are:");
		ArrayList<String> worstReviewModel = ra.findModelWithKeywords(-1);
		for (int i = 0; i < worstReviewModel.size(); i++) {
			report.add(worstReviewModel.get(i));
		}
		report.add("");

		report.add(ra.numberOfReviews());

		arfw.writeReport(report);

		System.out.println("Report is ready!" + "\n");

		System.out.println("Generating charts..." + "\n");

*/
		Plots plot = new Plots(newValidReviews);
		
		try {
			plot.plotHistogram1();
			plot.plotHistogram2();
			plot.plotPieChart(ra.getPositiveReviews().size(), ra.getNegativeReviews().size(),
					ra.getNeutralReviews().size());
			plot.plotBarDeparmentToNumOfReviews(ra.getDepartmentToNumOfReview(ra.getNegativeReviews()), 
					ra.getDepartmentToNumOfReview(ra.getNeutralReviews()), ra.getDepartmentToNumOfReview(ra.getPositiveReviews()) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();

		System.out.println("Contact us if you need more analysis reports!");       


	}
}