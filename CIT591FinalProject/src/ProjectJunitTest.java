import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

/**
 * This is our junit testing on overall project analysis. This covers the
 * following classes :ReviewReader, ReviewAnalysis, SentimentAnalysisOnReview,
 * Plots SentimentAnalysisOutputFileWriter.
 */
class ProjectJunitTest {

    static ReviewReader reviewReader;
    static ArrayList<Review> reviews;
    // After we run the sentiment scoring. We will generate the output file
    // due to the long running time sentiment analyzer takes.
    // We will try to read this secondary file in order to make sure it works.
    static ReviewReader secondReviewReader;
    static ArrayList<Review> reviews_with_sentiments;
    static ArrayList<Review> newValidReviews;
    static ReviewAnalysis reviewAnalysis = new ReviewAnalysis(newValidReviews);

    private ArrayList<Review> getTestReviews() {
        ArrayList<Review> testReviews = new ArrayList<Review>();
        ArrayList<String> alist = new ArrayList<String>();
        Review review1 = new Review("0", "767", 33, "Absolutely wonderful", "silk and sexy and comfortable", 4, true, 0,
                "Initmates", "Intimate", "Intimates", alist, 2);
        Review review2 = new Review("0", "734", 34, "Absolutely wonderful", "silk and sexy and comfortable", 4, true, 0,
                "Initmates", "Intimate", "Intimates", alist, 2);
        Review review3 = new Review("0", "1207", 35, "Absolutely wonderful", "silk and sexy and comfortable", 4, true,
                0, "Dresses", "Dresses", "Dresses", alist, 2);
        testReviews.add(review1);
        testReviews.add(review2);
        testReviews.add(review3);

        return testReviews;

    }

    /**
     * test if the # of records match with what's in csv file.
     * 
     * @throws IOException
     */
    @Test
    public void testNumberOfReviews() throws IOException {
        reviewReader = new ReviewReader();
        // reviews = new ArrayList<Review>();
        reviewReader.readFile();
        reviews = reviewReader.getReviews();
        int actualNumberOfRecords = 23486;
        assertEquals(actualNumberOfRecords, reviews.size(), "# of records do not match!");
    }

    /**
     * test if the data are being read correctly.
     */
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

    /**
     * Once we create an output file with the sentiment analysis result. We output
     * the file in csv and will read the file again using ReviewReader class with a
     * constructor that takes in a different argument. We will try to make sure if
     * this process works well.
     */
    @Test
    public void testNumberOfReviews2() {
        secondReviewReader = new ReviewReader("Sentiment_Analysis_Output.csv", true);
        secondReviewReader.readFile();
        reviews_with_sentiments = secondReviewReader.getReviews();
        assertEquals(23486, reviews_with_sentiments.size());
    }

    /**
     * With this output, we want to make sure if the both sentiments and sentiment
     * scores are what we have expected as well as ReviewClass and how the records
     * are being read.
     */
    @Test
    public void testSentiments() {
        HashMap<String, ArrayList<String>> idToSentiments = new HashMap<>();
        for (Review review : reviews_with_sentiments) {
            String id = review.getId();
            ArrayList<String> sentiments = review.getSentiments();
            idToSentiments.put(id, sentiments);
        }

        // contain one
        ArrayList<String> id0 = new ArrayList<>();
        // contain multiple
        ArrayList<String> id39 = new ArrayList<>();
        // this one contains nothing
        ArrayList<String> id92 = new ArrayList<>();

        id0.add("Very positive");
        List<String> id39list = Arrays.asList("Positive", "Positive", "Negative", "Positive");
        id39.addAll(id39list);

        assertEquals(id0, idToSentiments.get("0"), "A single value sentiment does not work");
        assertEquals(id39, idToSentiments.get("39"), "Multiple Sentiments insertion does not work.");
        assertEquals(id92.isEmpty(), idToSentiments.get("92").isEmpty(), "Null (empty) list is giving an error");
    }

    /**
     * This test ist to ensure scoring we performed on the sentiment analysis to
     * ensure if the scoring worked as we intended then outputed.
     */
    @Test
    public void testScores() {
        HashMap<String, Integer> idToSentimentScores = new HashMap<>();
        for (Review review : reviews_with_sentiments) {
            String id = review.getId();
            int sentimentScores = review.getSentimentScore();
            idToSentimentScores.put(id, sentimentScores);
        }

        assertEquals(0, idToSentimentScores.get("4"), "0 Scoring did not work!");
        assertEquals(-7, idToSentimentScores.get("154"), "Negative scoring did not work!");
        assertEquals(3, idToSentimentScores.get("120"), "Positive scoring did not work!");
    }

    /**
     * Test review analysis algorithm to get the clothingId with most reviews.
     */
    @Test
    public void testAnalysisOnProductWithMostReviews() {

        newValidReviews = secondReviewReader.getValidReviews();
        reviewAnalysis = new ReviewAnalysis(newValidReviews);
        String itemWithMostReviews = reviewAnalysis.getClothingIDWithMostReviews();

        assertEquals("1078", itemWithMostReviews);

    }

    /**
     * This is a helper method from ReviewAnalysis class. Since this is helper
     * method with accessor being private. We are pasting the function code here.
     * given a HashMap<String, Integer>, return the String whose Integer Value is
     * maximum
     * 
     * @param hashmap
     * @return String with maximum Integer value
     */
    private String findMax(HashMap<String, Integer> hashmap) {
        int maxCount = -999;
        String maxID = null;
        for (String key : hashmap.keySet()) {
            if (maxCount < hashmap.get(key)) {
                maxCount = hashmap.get(key);
                maxID = key;
            }
        }
        return maxID;
    }

    /**
     * Testing findMax function
     */
    @Test
    public void testFindMaxFunction() {
        HashMap<String, Integer> toBeTested = new HashMap<>();
        toBeTested.put("lowest", -5);
        toBeTested.put("Middle", 0);
        toBeTested.put("Highest", 10);

        String maxKey = findMax(toBeTested);
        assertEquals("Highest", maxKey);
    }

    /**
     * test GetTopFiveWords() method in ReviewAnalysis class
     */
    @Test
    public void testGetTopFiveKeyWords() {
        String testTxt = "I love love sweater sweater, black black black, small 'small' small"
                + "small. test test test 2 test test would but it again. ";
        String topKeyWord = reviewAnalysis.getTopFiveKeyWords(testTxt).get(0);
        assertEquals("test", topKeyWord);
    }

    @Test
    public void testGetNegativeRevierws() {
        ArrayList<Review> negativeReviews = reviewAnalysis.getNegativeReviews();
        assertEquals(5027, negativeReviews.size());
    }

    /**
     * This helper method is to get the average age of ArrayList for other analysis
     * classes. * Since this is helper method with accessor being private. We are
     * pasting the function code here.
     * 
     * @param ArrayList of reviews
     * @return average age
     */
    private double getAverageAge(ArrayList<Review> reviews) {
        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getAge();
        }
        return sum / reviews.size();
    }

    /**
     * This is a helper method to get Median Average from Arraylist of Reviews *
     * Since this is helper method with accessor being private. We are pasting the
     * function code here.
     * 
     * @param ArrayList of reviews
     * @return Median Age from the reviews
     */
    private double getMedianAge(ArrayList<Review> reviews) {
        ArrayList<Integer> ages = new ArrayList<Integer>();
        for (Review review : reviews) {
            ages.add(review.getAge());
        }
        Collections.sort(ages);
        if (ages.size() % 2 == 1) {
            return ages.get(ages.size() / 2);
        } else {
            if (ages.size() == 0) {
                return 0;
            } else {
                return (ages.get(ages.size() / 2) + ages.get((ages.size() / 2) - 1)) / 2.0;
            }
        }
    }

    /**
     * test getAverageAge() method in ReviewAnalysis class
     */
    @Test
    public void testAvgAge() {
        double avgAge = getAverageAge(getTestReviews());
        assertEquals(34, avgAge);
    }

    /**
     * test getMeianAge() method in ReviewAnalysis class
     */
    @Test
    public void testMedianAge() {
        double medianAge = getMedianAge(getTestReviews());
        assertEquals(34, medianAge);
    }

    /**
     * test getDepartmentToNumOfReview() method in ReviewAnalysis class
     */
    @Test
    public void testDepartmentToNumOfReview() {
        HashMap<String, Integer> departmentToNumOfReview = reviewAnalysis.getDepartmentToNumOfReview(getTestReviews());
        Integer count = departmentToNumOfReview.get("Dresses");
        assertEquals(1, count);
    }

    /**
     * test getStopWords() method in ReviewAnalysis class
     */
    @Test
    public void testGetTopWords() {

        ArrayList<String> stopwords = reviewAnalysis.getStopWords();
        boolean isStopWord = false;
        String word = "again";
        if (stopwords.contains(word)) {
            isStopWord = true;
        }
        assertEquals(true, isStopWord);
    }

    /**
     * test convertArrayListToArray method in Plots class
     */
    @Test
    public void testConvertArrayListToArray() {

        ArrayList<Integer> arraylist = new ArrayList<>();
        arraylist.add(1);
        arraylist.add(2);
        arraylist.add(3);
        Plots plots = new Plots(newValidReviews);
        double array[] = plots.convertArrayListToArray(arraylist);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
        assertEquals(3, array[2]);
    }

    /**
     * Helper method to reduce the redundancy in counting data element Since this is
     * helper method with accessor being private. We are pasting the function code
     * here.
     * 
     * @param map
     * @param element
     */
    private void incrementHashMapPerElement(HashMap<String, Integer> map, String element) {
        if (!map.containsKey(element)) {
            map.put(element, 1);
        } else {
            int count = map.get(element);
            count++;
            map.put(element, count);
        }
    }

    /**
     * test IncrementHashMapPerElement() method in ReviewAnalysis class
     */
    @Test
    public void testIncrementHashMapPerElement() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("good", 1);
        incrementHashMapPerElement(map, "good");
        incrementHashMapPerElement(map, "bad");
        assertEquals(2, map.get("good"));
        assertEquals(1, map.get("bad"));
    }

}
