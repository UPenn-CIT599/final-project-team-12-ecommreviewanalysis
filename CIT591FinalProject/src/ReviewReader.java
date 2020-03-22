import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

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
	
	private final String CSV_FILE_NAME = "/TEMP/Womens Clothing E-Commerce Reviews.csv";
	private ArrayList<Review> reviews;
	/**
	 * The constructor for ReviewReader does not take in the parameter.
	 */
	public ReviewReader() {
		reviews = new ArrayList<>();
	}
	
	public void readFile() throws IOException {
		try {
			Reader in = new FileReader(CSV_FILE_NAME);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			//reading the record
			for (CSVRecord record : records) {
				String id = record.get("ID");
				String clothingId = record.get("Clothing ID");
				int age = Integer.parseInt(record.get("Age"));
				String title = record.get("Title");
				String reviewText = record.get("Review Text");
				int rating = Integer.parseInt(record.get("Rating"));
				boolean recommendedInd = false;
				if (record.get("Recommended IND").equals("1")) {
					recommendedInd = true;
				}
				int positiveFeedbackCount = Integer.parseInt(record.get("Positive Feedback Count"));
				String divisionName = record.get("Division Name");
				String departmentName = record.get("Department Name");
				String className = record.get("Class Name");
				
				reviews.add(new Review(id, clothingId, age, title, reviewText, rating, recommendedInd,
						positiveFeedbackCount, divisionName, departmentName, className));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found. Existing...");
		}
		
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}
	
	
}
