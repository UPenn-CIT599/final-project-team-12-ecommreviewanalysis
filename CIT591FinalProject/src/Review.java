import java.util.ArrayList;

/**
 * Review class will store the data regarding individual reviews.
 * @author Xinyi, Xiting, Yong-Jin
 *
 */



public class Review {
	//for ids, even though they are in numeric, it is better to keep these as string 
	//since we are not going to perform any arithmetic operations to these.
	private String id;
	private String clothingId;
	private int age;
	private String title;
	private String reviewText;
	private int rating;
	private boolean recommendedInd;
	private int positiveFeedbackCount;
	private String divisionName;
	private String departmentName;
	private String className;
	//sentiment flag will be set up later on.
	private ArrayList<String> sentiments;
	private int sentimentScore;
	
	/**
	 * Constructor for Review Class
	 * @param id
	 * @param clothingId
	 * @param age
	 * @param reviewText
	 * @param rating
	 * @param recommendedInd
	 * @param positiveFeedbackCount
	 * @param divisionName
	 * @param departmentName
	 * @param className
	 */
	public Review(String id, String clothingId, int age, String title, String reviewText, int rating,
			boolean recommendedInd, int positiveFeedback,
			String divisionName, String departmentName, String className) {
		this.id = id;
		this.clothingId = clothingId;
		this.age = age;
		this.title = title;
		this.reviewText = reviewText;
		this.rating = rating;
		this.recommendedInd = recommendedInd;
		this.positiveFeedbackCount = positiveFeedback;
		this.divisionName = divisionName;
		this.departmentName = departmentName;
		this.className = className;
		//initialize sentiments and scores
		//will set these variables later on once SentimentAnalysis has been run.
		sentiments = new ArrayList<>();
		sentimentScore = 0;
	}

	/**
	 * Secondary Constructor for the time we read the review file for the second time.
	 * @param id
	 * @param clothingId
	 * @param age
	 * @param title
	 * @param reviewText
	 * @param rating
	 * @param recommendedInd
	 * @param positiveFeedbackCount
	 * @param divisionName
	 * @param departmentName
	 * @param className
	 * @param sentiments
	 * @param sentimentScore
	 */
	public Review(String id, String clothingId, int age, String title, String reviewText, int rating,
			boolean recommendedInd, int positiveFeedbackCount, String divisionName, String departmentName,
			String className, ArrayList<String> sentiments, int sentimentScore) {
		
		this.id = id;
		this.clothingId = clothingId;
		this.age = age;
		this.title = title;
		this.reviewText = reviewText;
		this.rating = rating;
		this.recommendedInd = recommendedInd;
		this.positiveFeedbackCount = positiveFeedbackCount;
		this.divisionName = divisionName;
		this.departmentName = departmentName;
		this.className = className;
		//initialize sentiments and scores
		//will set these variables later on once SentimentAnalysis has been run.
		this.sentiments = sentiments;
		this.sentimentScore = sentimentScore;
	}


	public ArrayList<String> getSentiments() {
		return sentiments;
	}


	public void setSentiments(ArrayList<String> sentiments) {
		this.sentiments = sentiments;
	}


	public int getSentimentScore() {
		return sentimentScore;
	}


	public void setSentimentScore(int sentimentScore) {
		this.sentimentScore = sentimentScore;
	}


	public String getId() {
		return id;
	}

	public String getClothingId() {
		return clothingId;
	}

	public int getAge() {
		return age;
	}
	
	public String getTitle() {
		return title;
	}

	public String getReviewText() {
		return reviewText;
	}

	public int getRating() {
		return rating;
	}

	public boolean getRecommendedInd() {
		return recommendedInd;
	}

	public int getPositiveFeedback() {
		return positiveFeedbackCount;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getClassName() {
		return className;
	}


}
