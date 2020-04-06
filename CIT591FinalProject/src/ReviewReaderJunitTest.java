import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

	//test if the # of records match with what's in csv file.
	@Test
	public void testNumberOfReviews() throws IOException {
		reviewReader = new ReviewReader();
		reviews = new ArrayList<Review>();
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

}
