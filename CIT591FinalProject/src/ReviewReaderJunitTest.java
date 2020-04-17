import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

/*
 * Key to data analysis is to get the input data in precise manner.
 * We created this test to ensure our data extraction process.
 * We opened our original csv file in Excel and will validate the contents 
 * between our Review class and csv file.
 */
public class ReviewReaderJunitTest {
	static ReviewReader reviewReader;
	static ArrayList<Review> reviews;
	//After we run the sentiment scoring. We will generate the output file
	//due to the long running time sentiment analyzer takes.
	//We will try to read this secondary file in order to make sure it works.
	static ReviewReader secondReviewReader;
	static ArrayList<Review> reviews_with_sentiments;

	//test if the # of records match with what's in csv file.
	@Test
	public void testNumberOfReviews() throws IOException {
		reviewReader = new ReviewReader();
		//reviews = new ArrayList<Review>();
		reviewReader.readFile();
		reviews = reviewReader.getReviews();
		int actualNumberOfRecords = 23486;
		assertEquals(actualNumberOfRecords, reviews.size(),  "# of records do not match!" );
	}

	//test if the data are being read correctly.
	@Test
	public void testDataExtraction() {
		Review review1 = reviews.get(0);
		Review review6 = reviews.get(5);
		Review review14 = reviews.get(13);

		assertEquals("0", review1.getId(), "ID data extraction does not work!");
		assertEquals("1080", review6.getClothingId(), "ClothingID extracition does not work!");
		assertEquals(44, review14.getAge(), "Age does not work!");
		assertEquals("", review1.getTitle(), "Null Value is not being handled correclty.");
		assertEquals("Not for the very petite", review6.getTitle(), "Title not being inserted correctly.");
		assertEquals("Absolutely wonderful - silky and sexy and comfortable", review1.getReviewText(), 
				"Review Text is not being inserted correctly");
		assertEquals(4, review1.getRating(), "Rating insertion does not work properly");
		assertEquals(5, review14.getRating(), "Rating insertion does not work properly");
		assertEquals(true, review1.getRecommendedInd(), "True value not being inserted correctly");
		assertEquals(false, review6.getRecommendedInd(), "False value not being inserted correctly");
		assertEquals(4, review6.getPositiveFeedback(), "Positive feed back not being inserted correctly");
		assertEquals("General", review6.getDivisionName(), "Divisiosn  name not being inserted correclty");
		assertEquals("Dresses", review6.getDepartmentName(), "Department info not being inserted correctly.");
		assertEquals("Intimates", review14.getClassName(), "Class Name data not being inserted correctly");	
	}

	/*
	 * Once we create an output file with the sentiment analysis result. We output the file in csv
	 * and will read the file again using ReviewReader class with a constructor that takes in a different
	 * argument. We will try to make sure if this process works well.
	 */
	@Test
	public void testNumberOfReviews2() {
		secondReviewReader = new ReviewReader("Sentiment_Analysis_Output.csv", true);
		//reviews_with_sentiments = new ArrayList<Review>();
		try {
			secondReviewReader.readFile();
			reviews_with_sentiments = secondReviewReader.getReviews();
			assertEquals(23486, reviews_with_sentiments.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * With this output, we want to make sure if the both sentiments 
	 * and sentiment scores are what we have expected as well as ReviewClass and how the records
	 * are being read.
	 */
	@Test
	public void testSentiments() {
		HashMap<String, ArrayList<String>> idToSentiments = new HashMap<>();
		for( Review review : reviews_with_sentiments) {
			String id = review.getId();
			ArrayList<String> sentiments = review.getSentiments();
			idToSentiments.put(id, sentiments);
		}
		
		//contain one
		ArrayList<String> id0 = new ArrayList<>();
		//contain multiple
		ArrayList<String> id39 = new ArrayList<>();
		//this one contains nothing
		ArrayList<String> id92 = new ArrayList<>();

		id0.add("Very positive");
		List<String> id39list = Arrays.asList("Positive", "Positive", "Negative", "Positive");
		id39.addAll(id39list);
		
		assertEquals(id0, idToSentiments.get("0"), "A single value sentiment does not work");
		assertEquals(id39, idToSentiments.get("39"), "Multiple Sentiments insertion does not work.");
		assertEquals(id92, idToSentiments.get("92"), "Null (empty) list is giving an error");
		
		
	}


}
