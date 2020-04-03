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

	@Test
	public void testNumberOfReviews() throws IOException {
		reviewReader = new ReviewReader();
		reviews = new ArrayList<Review>();
		reviewReader.readFile();
		reviews = reviewReader.getReviews();
		int actualNumberOfRecords = 23486;
		assertEquals(actualNumberOfRecords, reviews.size(),  "# of records do not match!" );
	}

	@Test
	public void testDataExtraction() {
		Review review1 = reviews.get(0);
		Review review6 = reviews.get(5);
		Review review14 = reviews.get(13);
		
		assertEquals("0", review1.getId(), "ID data extraction does not work!");
		assertEquals("1080", review6.getClothingId(), "ClothingID extracition does not work!");
		assertEquals(44, review14.getAge(), "Age does not work!");
		
	}

}
