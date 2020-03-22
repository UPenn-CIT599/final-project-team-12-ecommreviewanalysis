import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

public class SentimentAnalysisOutputFileWriter {
	private ArrayList<Review> reviews;

	private static final String OUTPUT_FILE_PATH = "/TEMP/Sentiment_Analysis_Output.csv";

	public SentimentAnalysisOutputFileWriter(ArrayList<Review> reviews){
		this.reviews = reviews;
	}

	public void generateOutputFile() {
		try(
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT_FILE_PATH));
				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
						"ID", "ClothingID", "Age", "Title", "ReviewText", "Rating"
						,"Recommended Indicator", "PositiveFeedbackCnt", "Division Name"
						,"Department Name", "Class Name", "Sentiments", "SentimentScore"));
				){
			
		    int batchCount = 0;
			for(Review review: reviews) {
				csvPrinter.printRecord(review.getId(), review.getClothingId()
						, String.valueOf(review.getAge()), review.getTitle(), review.getReviewText()
						, String.valueOf(review.getRating()), String.valueOf(review.getRecommendedInd())
						, review.getPositiveFeedback(), review.getDivisionName()
						, review.getDepartmentName(), review.getClassName()
						, review.getSentiments(), review.getSentimentScore());
				batchCount++;
				
				if(batchCount % 500 == 0) {
					csvPrinter.flush();
				}
			}
			//for the reminder
			csvPrinter.flush();



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
