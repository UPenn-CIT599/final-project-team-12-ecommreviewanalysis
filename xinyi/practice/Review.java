package xinyi.practice;

public class Review {
private int ID;
private int clothingID;
private int age;
//private String title;
private String reviewText;
private int rating;
private boolean recommendedIND;
private int positiveFeedback;
private String divisionName;
private String departmentName;
private String className;
public int getID() {
    return ID;
}
public void setID(int iD) {
    ID = iD;
}
public int getClothingID() {
    return clothingID;
}
public void setClothingID(int clothingID) {
    this.clothingID = clothingID;
}
public int getAge() {
    return age;
}
public void setAge(int age) {
    this.age = age;
}
/*public String getTitle() {
    return title;
}
public void setTitle(String title) {
    this.title = title;
}*/
public String getReviewText() {
    return reviewText;
}
public void setReviewText(String reviewText) {
    reviewText = reviewText;
}
public int getRating() {
    return rating;
}
public void setRating(int rating) {
    this.rating = rating;
}
public boolean isRecommendedIND() {
    return recommendedIND;
}
public void setRecommendedIND(boolean recommendedIND) {
    this.recommendedIND = recommendedIND;
}
public int getPositiveFeedback() {
    return positiveFeedback;
}
public void setPositiveFeedback(int positiveFeedback) {
    this.positiveFeedback = positiveFeedback;
}
public String getDivisionName() {
    return divisionName;
}
public void setDivisionName(String divisionName) {
    this.divisionName = divisionName;
}
public String getDepartmentName() {
    return departmentName;
}
public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
}
public String getClassName() {
    return className;
}
public void setClassName(String className) {
    this.className = className;
}


}
