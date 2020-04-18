import java.io.IOException;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;


import java.io.File;

/**
 * The Plots class is to generate graphs of review analysis data and to write
 * the graphs into jpeg files into directory.
 * 
 * @author Xinyi, Xiting, Yong-jin
 *
 */
public class Plots {

    private ArrayList<Review> reviews;

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
     * score distribution  and other plots we might come up with.
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
     * to convert arraylist to array, this is a helper method for plotHistogram1() and plotHistogram2().
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
     * To generate histogram of customer age distribution and write it to a jpeg
     * file
     * 
     * @throws IOException
     */
    public void plotHistogram1() throws IOException {
        ArrayList<Integer> customerAge = getCustomerAge();

        double[] val = convertArrayListToArray(customerAge);
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", val, 50);
        JFreeChart histogram = ChartFactory.createHistogram("Distribution of Customer Age", "Number of Reviews",
                "Customer Age", dataset, PlotOrientation.VERTICAL, false, false, false);

        File HistogramToJPEG = new File("Distribution_of_Customer_Age.jpeg");
        ChartUtilities.saveChartAsJPEG(HistogramToJPEG, histogram, 640, 480);
        System.out.println("Distribution_of_Customer_Age.jpeg is created in your directory");
    }

    /**
     * To generate histogram of sentiment score distribution and write it to a jpeg
     * file
     * 
     * @throws IOException
     */
    public void plotHistogram2() throws IOException {
        ArrayList<Integer> sentimentScore = getSentimentScore();

        double[] val1 = convertArrayListToArray(sentimentScore);
        HistogramDataset dataset1 = new HistogramDataset();
        dataset1.addSeries("key", val1, 50);
        JFreeChart histogram1 = ChartFactory.createHistogram("Distribution of SentimentScore", "Number of Reviews",
                "Sentiment Score", dataset1, PlotOrientation.VERTICAL, false, false, false);

        File HistogramToJPEG1 = new File("Distribution_of_Sentiment_Score.jpeg");
        ChartUtilities.saveChartAsJPEG(HistogramToJPEG1, histogram1, 640, 480);
        System.out.println("Distribution_of_Sentiment_Score.jpeg is created in your directory");

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

        File PieChart1ToJPEG = new File("PieChart_of_Reviews.jpeg");
        ChartUtilities.saveChartAsJPEG(PieChart1ToJPEG, chart, 640, 480);
        System.out.println("PieChart_of_Reviews.jpeg is created in your directory");
    }

}