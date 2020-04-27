import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.ui.ApplicationFrame;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;

/**
 * The Plots class is to generate graphs of review analysis data and to write
 * the graphs into jpeg files into directory.
 * 
 * @author Xinyi, Xiting, Yong-jin
 *
 */
public class Plots {

    public ArrayList<Review> reviews;
    ReviewAnalysis ra = new ReviewAnalysis(reviews);

    /**
     * Constructor for Plots class
     * 
     * @param reviews It will take Arraylist of reviews that we extracted from
     *                sentiment analysis output file
     */

    public Plots(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * To get an arraylist of customer age for plotting histogram of customer age
     * distribution and other plots we might come up with.
     * 
     * @return an arraylist of customer age
     */
    private ArrayList<Integer> getCustomerAge() {
        ArrayList<Integer> customerAgeList = new ArrayList<Integer>();
        for (Review review : reviews) {

            int customerAge = review.getAge();
            customerAgeList.add(customerAge);
        }
        return customerAgeList;
    }

    /**
     * To get an arraylist of sentiment score for generating histogram of sentiment
     * score distribution and other plots we might come up with.
     * 
     * @return an arraylist of sentiment score
     */
    private ArrayList<Integer> getSentimentScore() {
        ArrayList<Integer> sentimentscoreList = new ArrayList<Integer>();
        for (Review review : reviews) {

            int sentimentScore = review.getSentimentScore();
            sentimentscoreList.add(sentimentScore);

        }
        return sentimentscoreList;
    }

    /**
     * To get an arraylist of departments for generating the barchart of number of
     * negative/neutral/positive reviews by department
     * 
     * @return
     */
    private ArrayList<String> getDepartments() {
        ArrayList<String> departments = new ArrayList<>();
        for (Review review : reviews) {
            String department = review.getDepartmentName();
            departments.add(department);
        }
        return departments;
    }

    /**
     * to convert arraylist to array, this is a helper method for plotHistogram1()
     * and plotHistogram2().
     * 
     * @param arraylist
     * @return array
     */
    public double[] convertArrayListToArray(ArrayList<Integer> arraylist) {
        double[] nums = new double[arraylist.size()];

        for (int i = 0; i < arraylist.size(); i++) {

            double num = (double) arraylist.get(i);
            nums[i] = num;

        }
        return nums;
    }

    /**
     * To generate histogram of data distribution and write it to a jpeg
     * 
     * @param chartTitle
     * @param xAxisLabel
     * @param yAxisLabel
     * @param value
     * @throws IOException
     */
    private void plotHistogram(String chartTitle, String xAxisLabel, String yAxisLabel, double[] value)
            throws IOException {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", value, 50);
        JFreeChart histogram = ChartFactory.createHistogram(chartTitle, xAxisLabel, yAxisLabel, dataset,
                PlotOrientation.VERTICAL, false, false, false);

        String chartName = "Distribution_of_" + yAxisLabel + ".jpeg";
        File histogramToJPEG = new File(chartName);
        ChartUtilities.saveChartAsJPEG(histogramToJPEG, histogram, 640, 480);
        System.out.println(chartName + " is created in your directory");
    }

    /**
     * To generate histogram of customer age distribution and write it to a jpeg
     * file
     * 
     * @throws IOException
     */
    public void plotHistogram1() throws IOException {

        String chartTitle = "Distribution of Customer Age";
        String xAxisLabel = "Number of Reviews";
        String yAxisLabel = "Customer Age";
        ArrayList<Integer> customerAge = getCustomerAge();
        double[] value = convertArrayListToArray(customerAge);
        plotHistogram(chartTitle, xAxisLabel, yAxisLabel, value);
    }

    /**
     * To generate histogram of sentiment score distribution and write it to a jpeg
     * file
     * 
     * @throws IOException
     */
    public void plotHistogram2() throws IOException {

        String chartTitle = "Distribution of SentimentScore";
        String xAxisLabel = "Number of Reviews";
        String yAxisLabel = "Sentiment Score";
        ArrayList<Integer> sentimentScore = getSentimentScore();

        double[] value = convertArrayListToArray(sentimentScore);
        plotHistogram(chartTitle, xAxisLabel, yAxisLabel, value);
    }

    /**
     * to generate piechart of positive,negative and neutral reviews and write it to
     * a jpeg file
     * 
     * @throws IOException
     */
    public void plotPieChart(int PositiveReviews, int NegativeReviews, int NeutralReviews) throws IOException {

        DefaultPieDataset dataset2 = new DefaultPieDataset();
        dataset2.setValue("Positive Reviews", PositiveReviews);
        dataset2.setValue("Negative Reviews", NegativeReviews);
        dataset2.setValue("Neutral Reviews", NeutralReviews);
        JFreeChart chart = ChartFactory.createPieChart("Number Of Reviews", dataset2, true, true, false);

        File pieChart1ToJPEG = new File("PieChart_of_Reviews.jpeg");
        ChartUtilities.saveChartAsJPEG(pieChart1ToJPEG, chart, 640, 480);
        System.out.println("PieChart_of_Reviews.jpeg is created in your directory");
    }

    /**
     * To generate bar chart of number of negative/neutral/positive reviews by
     * department and write it to a jpeg
     * 
     * @throws IOException
     */
    public void plotBarDeparmentToNumOfReviews(HashMap<String, Integer> departmentToNegativeRev,
            HashMap<String, Integer> departmentToNeutralRev, HashMap<String, Integer> departmentToPositiveRev)
            throws IOException {

        final String negative = "Negative Reviews";
        final String neutral = "Neutral Reviews";
        final String positive = "Positive Reviews";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String department : departmentToNegativeRev.keySet()) {
            int numOfNegativeRev = departmentToNegativeRev.get(department);
            dataset.addValue(numOfNegativeRev, negative, department);
        }
        for (String department : departmentToNeutralRev.keySet()) {
            int numOfNeutralRev = departmentToNeutralRev.get(department);
            dataset.addValue(numOfNeutralRev, neutral, department);
        }
        for (String department : departmentToPositiveRev.keySet()) {
            int numOfPositiveRev = departmentToPositiveRev.get(department);
            dataset.addValue(numOfPositiveRev, positive, department);
        }

        JFreeChart barChart = ChartFactory.createBarChart("Number of Reviews by Department", "Department",
                "Number of Reviews", dataset, PlotOrientation.VERTICAL, true, true, false);

        File barChart1ToJPEG = new File("BarChart_of_NumOfReviews.jpeg");
        ChartUtilities.saveChartAsJPEG(barChart1ToJPEG, barChart, 640, 480);
        System.out.println("BarChart_of_NumOfReviews.jpeg is created in your directory");
    }

}