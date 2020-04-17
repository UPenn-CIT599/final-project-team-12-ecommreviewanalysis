import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * We will run the analysis on input file and result from sentiment analysis
 * together for us to get answers to our questions in this class.
 * 
 * @author Xiting, Xinyi, Yong-Jin
 *
 */
public class ReviewAnalysis {

    private ArrayList<Review> reviews;

    /**
     * Constructor for ReviewAnalysis class.
     * 
     * @param reviews It will take ArrayList of reviews that we extracted from csv
     *                file and sentiment analysis.
     */
    public ReviewAnalysis(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * To get the negative reviews Consider the review is negative if the sentiment
     * score equals -3 or is less then -3
     * 
     * @return negative reviews arraylist of reviews.
     */
    public ArrayList<Review> getNegativeReviews() {
        ArrayList<Review> negReviews = new ArrayList<Review>();
        for (Review review : reviews) {
            if (!review.getReviewText().isEmpty() && review.getSentimentScore() <= -3) {
                negReviews.add(review);
            }
        }
        return negReviews;
    }

    /**
     * To get the positive reviews Consider the review is negative if the sentiment
     * score equals 3 or is more then 3
     * 
     * @return negative reviews arraylist<Review>
     */
    public ArrayList<Review> getPositiveReviews() {
        ArrayList<Review> posReviews = new ArrayList<Review>();
        for (Review review : reviews) {
            if (!review.getReviewText().isEmpty() && review.getSentimentScore() >= 3) {
                posReviews.add(review);
            }
        }
        return posReviews;
    }

    /**
     * To get the neutral reviews Consider the review is neutral if the sentiment
     * score is larger than -3 and less than 3
     * 
     * @return neutral reviews arraylist<Review>
     */
    public ArrayList<Review> getNeutralReviews() {
        ArrayList<Review> neuReviews = new ArrayList<Review>();
        for (Review review : reviews) {
            if (!review.getReviewText().isEmpty()
                    && (review.getSentimentScore() > -3 && review.getSentimentScore() < 3)) {
                neuReviews.add(review);
            }
        }
        return neuReviews;
    }

    /**
     * to get average age of positive reviews to better target high potential
     * customers
     * 
     * @param posReviews
     * @return double average age
     */
    public double getAveAgeOfPosReviews() {
        ArrayList<Review> posReviews = getPositiveReviews();
        double sum = 0;
        double count = 0;
        for (Review posReview : posReviews) {
            sum += posReview.getAge();
            count++;
        }
        return sum / count;
    }

    /**
     * to get average age of positive reviews to better target high potential
     * customers
     * 
     * @param posReviews
     * @return double median age
     */
    public double getMedianAgeOfPosReviews() {
        ArrayList<Review> posReviews = getPositiveReviews();
        ArrayList<Integer> ages = new ArrayList<Integer>();
        for (Review posReview : posReviews) {
            ages.add(posReview.getAge());
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
     * for each clothing class, get the model(clothingId) that has the
     * highest/lowest accumulated sentiment scores and get the top 5 keywords of
     * that model print out List of className + clothingId + keywords[]
     * 
     * @param reviewType, 1 for best overall review (has highest sentiment score) -1
     *                    for worst overall review (has lowest sentiment score)
     */
    public void findModelWithKeywords(int reviewType) {
        HashMap<String, ArrayList<String>> classToClothingIDs = getClassToClothingIDs();

        String result = "";
        ArrayList<String> results = new ArrayList<String>();

        for (String className : classToClothingIDs.keySet()) {
            ArrayList<String> clothingIDs = classToClothingIDs.get(className);
            if (!className.equals("")) {
                int maxScore = -999999;
                int minScore = 999999;
                String model = "";
                String review = "";
                List<String> keywords = new ArrayList<String>();
                for (String clothingID : clothingIDs) {
                    int score = getClothingIdToSentimentScore().get(clothingID);
                    if (reviewType == 1 && score > maxScore) {
                        maxScore = score;
                        model = clothingID;
                    } else if (reviewType == -1 && score < minScore) {
                        minScore = score;
                        model = clothingID;
                    }
                }
                review = getModelToReviews().get(model);
                keywords = getTopFiveKeyWords(review);
                result = className + "," + model + "," + keywords;
                results.add(result);
            }
        }
        for (String r : results) {
            System.out.println(r);
        }
    }

    /**
     * Helper method to reduce the redundancy in counting data element
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
     * to get the HashMap ClassName to ClothingIDs(clothing model) for the
     * clothingID list, sort based on sentiment score from low to high
     * 
     * @return HashMap classToClothingIDs
     */
    public HashMap<String, ArrayList<String>> getClassToClothingIDs() {

        HashMap<String, ArrayList<String>> classToClothingIDs = new HashMap<>();
        for (Review review : reviews) {
            ArrayList<String> clothingIDs = new ArrayList<String>();
            String className = review.getClassName();
            String clothingID = review.getClothingId();
            if (!classToClothingIDs.containsKey(className)) {
                clothingIDs.add(clothingID);
                classToClothingIDs.put(className, clothingIDs);
            } else {
                if (!clothingIDs.contains(clothingID)) {
                    clothingIDs = classToClothingIDs.get(className);
                    clothingIDs.add(clothingID);
                    classToClothingIDs.put(className, clothingIDs);
                }
            }
        }
        return classToClothingIDs;
    }

    /**
     * to get hashmap clothingId to accumulated SentimentScore
     * 
     * @return clothingIdToSentimentScore
     */
    public HashMap<String, Integer> getClothingIdToSentimentScore() {
        HashMap<String, Integer> clothingIdToSentimentScore = new HashMap<>();
        for (Review review : reviews) {
            String clothingId = review.getClothingId();
            Integer sentimentScore = review.getSentimentScore();
            if (!clothingIdToSentimentScore.containsKey(clothingId)) {
                clothingIdToSentimentScore.put(clothingId, sentimentScore);
            } else {
                Integer totalScore = sentimentScore + clothingIdToSentimentScore.get(clothingId);
                clothingIdToSentimentScore.put(clothingId, totalScore);
            }
        }
        return clothingIdToSentimentScore;
    }

    /**
     * to get the hashmap clothingId to Reviews text
     * 
     * @return modelToReviews
     */
    public HashMap<String, String> getModelToReviews() {

        HashMap<String, String> modelToReviews = new HashMap<>();

        for (Review review : reviews) {
            String clothingId = review.getClothingId();
            String reviewText = review.getReviewText();
            if (!modelToReviews.containsKey(clothingId)) {
                modelToReviews.put(clothingId, reviewText);
            } else {
                String storedReviews = modelToReviews.get(clothingId);
                modelToReviews.put(clothingId, storedReviews + reviewText);
            }
        }
        return modelToReviews;
    }

    /**
     * to get top five frequent words in the review text remove the punctuation,
     * convert to lowercase words, and remove stop words first
     * 
     * @param review
     * @return
     */
    public List<String> getTopFiveKeyWords(String review) {

        // Split into words, use Regex to remove punctuation and special characters
        ArrayList<String> reviewWords = new ArrayList<>(Arrays.asList(review.toLowerCase().split("\\W+")));

        // Remove stop words (words that occur frequently but meaningless) and numbers
        ArrayList<String> stopWords = getStopWords();
        ArrayList<String> words = new ArrayList<String>();
        String regex = "\\d+";
        for (String word : reviewWords) {
            if (!stopWords.contains(word) && !word.matches(regex)) {
                words.add(word);
            }
        }

        // find top five frequent words
        HashMap<String, Integer> wordToFrequency = new HashMap<>();
        for (String word : words) {
            incrementHashMapPerElement(wordToFrequency, word);
        }

        List<String> candidates = new ArrayList<String>(wordToFrequency.keySet());
        Collections.sort(candidates,
                (w1, w2) -> wordToFrequency.get(w1).equals(wordToFrequency.get(w2)) ? w1.compareTo(w2)
                        : wordToFrequency.get(w2) - wordToFrequency.get(w1));

        return candidates.subList(0, 5);
    }

    /**
     * get the list of stop words(words that occur frequently but meaningless)
     * 
     * @return
     */
    public ArrayList<String> getStopWords() {
        ArrayList<String> stopWords = new ArrayList<String>();
        File fr = new File("stopwords.txt");
        Scanner s;
        try {
            s = new Scanner(fr);
            while (s.hasNext()) {
                String stopWord = s.next();
                stopWords.add(stopWord);
            }
            s.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stopWords;
    }

    /**
     * to get the number of positive reviews, negative reviews and neutral reviews
     * 
     * @return String "positive reviews:n1; negative reviews:n2; neutral reviews:n3"
     */
    public String numberOfReviews() {
        ArrayList<Review> posReviews = getPositiveReviews();
        int countPos = posReviews.size();
        ArrayList<Review> negReviews = getNegativeReviews();
        int countNeg = negReviews.size();
        ArrayList<Review> neuReviews = getNeutralReviews();
        int countNeu = neuReviews.size();
        return ("PositiveReviews:" + countPos + ";NegativeReviews:" + countNeg + ";NeutralReviews:" + countNeu);
    }

    /**
     * to get the clothingID with the most number of reviews
     *
     * @return clothingID of the most number of reviews
     */
    public String productWithMostReviews() {

        HashMap<String, Integer> clothingIDToNumOfReviews = new HashMap<String, Integer>();
        String maxClothingID = null;
        for (int i = 0; i < reviews.size(); i++) {
            Integer num = clothingIDToNumOfReviews.get(reviews.get(i).getClothingId());
            if (num == null) {
                num = 1;
            } else {
                num = num + 1;
            }
            clothingIDToNumOfReviews.put(reviews.get(i).getClothingId(), num);
        }

        maxClothingID = findMax(clothingIDToNumOfReviews);

        return maxClothingID;

    }

    /**
     * to get the average age of customers of the most popular clothingID
     * (clothingID with the most number of Reviews)
     * 
     * @return average age of customers
     */

    public double averageAgeOfMostPopular() {
        double sum = 0;
        int count = 0;
        for (Review r : reviews) {
            if (r.getClothingId().equals(productWithMostReviews())) {
                count++;
                sum += r.getAge();
            }

        }
        double average = sum / count;
        return average;

    }

    /**
     * to get the median age of customers of the most popular clothingID (clothingID
     * with the most number of Reviews)
     * 
     * @return median age of customers
     */
    public double medianAgeOfMostPopular() {
        ArrayList<Integer> ageOfMostPopularClothingID = new ArrayList<Integer>();

        for (Review r : reviews) {
            if (r.getClothingId().equals(productWithMostReviews())) {
                ageOfMostPopularClothingID.add(r.getAge());
            }
        }

        Collections.sort(ageOfMostPopularClothingID);
        double median = 0;
        int n = ageOfMostPopularClothingID.size();
        if (n % 2 == 0) {
            median = (double) (ageOfMostPopularClothingID.get(n / 2) + ageOfMostPopularClothingID.get(n / 2 - 1)) / 2;
        } else {
            median = (double) ageOfMostPopularClothingID.get(n / 2);
        }
        return median;

    }

    /**
     * given a HashMap<String, Integer>, return the String whose Integer Value is
     * maximum
     * 
     * @param hashmap
     * @return String with maximum Integer value
     */
    public String findMax(HashMap<String, Integer> hashmap) {
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

}
