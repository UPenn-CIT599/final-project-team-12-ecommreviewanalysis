import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * ReviewReader class is designed to read the files in and store the data in 
 * Review class object. It would generate arraylist of reviews and return it to other class
 * for the analysis.
 * @author Xinyi, Xiting, Yong-Jin
 *
 */
public class ReviewReader {

	private String inputFileName = "Womens Clothing E-Commerce Reviews.csv";
	private ArrayList<Review> reviews;
	//this is used when we know that we are using secondary input file
	//secondary input file means it is the input file we created with sentiment analysis results appended 
	//to the original data.
	private boolean isSecondary;
	/**
	 * The constructor for ReviewReader does not take in the parameter.
	 */
	public ReviewReader() {
		reviews = new ArrayList<>();
	}
	/**
	 * We will read secondary input file once the  sentiment Analysis is done.
	 * @param fileName Name of the input file name
	 */
	public ReviewReader(String fileName, boolean isSecondary) {
		this();
		this.inputFileName = fileName;
		this.isSecondary = isSecondary;
	}

	/**
	 * This one mainly reads the CSV file.
	 * @throws IOException
	 */
	public void readFile() throws IOException {
		try {
			Reader in = new FileReader(inputFileName);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			//reading the record
			for (CSVRecord record : records) {
				String id = record.get("ID");
				String clothingId = record.get("Clothing ID");
				int age = Integer.parseInt(record.get("Age"));
				String title = record.get("Title");
				String reviewText = record.get("Review Text");
				int rating = Integer.parseInt(record.get("Rating"));

				//translate indicator value to boolean for later purpose.
				boolean recommendedInd = false;
				if (record.get("Recommended IND").equals("1")) {
					recommendedInd = true;
				}
				int positiveFeedbackCount = Integer.parseInt(record.get("Positive Feedback Count"));
				String divisionName = record.get("Division Name");
				String departmentName = record.get("Department Name");
				String className = record.get("Class Name");

				//when we read the input file for the scond time after we run Sentiment Analysis
				//we need to read the file again.
				//So we need to be able to capture newly appended data columns : Sentiments and SentimentScore
				if (isSecondary) {
					String rawSentiments = record.get("Sentiments").substring(1, record.get("Sentiments").length() - 1);
					String[] sentimentsInArray = rawSentiments.split(",");
					ArrayList<String> sentiments = new ArrayList<>();
					for(String sentiment : sentimentsInArray) {
						sentiments.add(sentiment.trim());
					}
					//Noticed there are white spaces generated when I ingest the data. trying to fix it.
					//Collections.addAll(sentiments, sentimentsInArray);
					int sentimentScore = Integer.parseInt(record.get("Sentiment Score"));
					reviews.add(new Review(id, clothingId, age, title, reviewText, rating, recommendedInd,
							positiveFeedbackCount, divisionName, departmentName, className, sentiments, sentimentScore));
				}
				else {
					//inserting review data into the ArrayList  of reviews.
					reviews.add(new Review(id, clothingId, age, title, reviewText, rating, recommendedInd,
							positiveFeedbackCount, divisionName, departmentName, className));			
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found. Existing...");
		}

	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}
	
	public ArrayList<Review> getValidReviews(){
		ArrayList<Review> validReviews = new ArrayList<Review>();
		for(Review r : reviews) {
			if(!r.getReviewText().equals("")) {
				validReviews.add(r);
			}
		}
		return validReviews;
	}


}