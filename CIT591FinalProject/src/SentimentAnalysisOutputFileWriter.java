import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * This will generate csv file of array list of reviews. Since coreNLP takes
 * long time to execute. We are separating the process into two. One is
 * SentimentAnalysis part and other is our main data analysis so we can have
 * answers to your question.
 * 
 * @author Xiting, YongJin, Xinyi
 *
 */
public class SentimentAnalysisOutputFileWriter implements FileWriter {
    private ArrayList<Review> reviews;

    private String outputFileName = "Sentiment_Analysis_Output.csv";

    public SentimentAnalysisOutputFileWriter(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * This method writes SentimentAnalysis results into a csv file
     */
    public void writeOutputFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFileName));
                CSVPrinter csvPrinter = new CSVPrinter(writer,
                        CSVFormat.DEFAULT.withHeader("ID", "Clothing ID", "Age", "Title", "Review Text", "Rating",
                                "Recommended IND", "Positive Feedback Count", "Division Name", "Department Name",
                                "Class Name", "Sentiments", "Sentiment Score"));) {

            int batchCount = 0;
            for (Review review : reviews) {
                csvPrinter.printRecord(review.getId(), review.getClothingId(), String.valueOf(review.getAge()),
                        review.getTitle(), review.getReviewText(), String.valueOf(review.getRating()),
                        String.valueOf(review.getRecommendedInd()), review.getPositiveFeedback(),
                        review.getDivisionName(), review.getDepartmentName(), review.getClassName(),
                        review.getSentiments(), review.getSentimentScore());
                batchCount++;

                if (batchCount % 500 == 0) {
                    csvPrinter.flush();
                }
            }
            // for the reminder
            csvPrinter.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
