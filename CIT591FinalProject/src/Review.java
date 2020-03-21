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
		this.reviewText = reviewText;
		this.rating = rating;
		this.recommendedInd = recommendedInd;
		this.positiveFeedbackCount = positiveFeedback;
		this.divisionName = divisionName;
		this.departmentName = departmentName;
		this.className = className;
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

	public String getReviewText() {
		return reviewText;
	}

	public int getRating() {
		return rating;
	}

	public boolean isRecommendedInd() {
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
