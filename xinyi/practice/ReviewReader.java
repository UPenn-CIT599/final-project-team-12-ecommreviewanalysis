package xinyi.practice;

import java.io.FileReader;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class ReviewReader {
    //why using static final
private static final String CSV_FILENAME = "D:\\upenn\\MCIT 591\\final project\\Womens Clothing E-Commerce Reviews.csv";
public static void main(String[] args) throws Exception{
    csvReader();
}

private static CellProcessor[] getProcessors() {
    final CellProcessor[] processors= new CellProcessor[] {new NotNull(new ParseInt()),// review ID
            new NotNull(new ParseInt()),// Clothing ID
            new NotNull(new ParseInt()),// Age
          //  new Optional(),// Title
            new Optional(),// Review Text
            new Optional(new ParseInt()),//Rating
            new Optional(new ParseBool()),//Recommended IND
            new Optional(new ParseInt()),//Positive Feedback
            new Optional(),//Division Name
            new Optional(),//Department Name
            new Optional(),//Class Name
    };
    return processors;
}

private static void csvReader() throws Exception{
    ICsvBeanReader beanReader=null;
    try {
        beanReader= new CsvBeanReader(new FileReader(CSV_FILENAME),CsvPreference.STANDARD_PREFERENCE);
        final String[] header= beanReader.getHeader(true);
        final CellProcessor[] processors= getProcessors();
        Review review;
        
        while( (review = beanReader.read(Review.class, header, processors)) != null ) {
                                       System.out.println(String.format("lineNo=%s, rowNo=%s, Review=%s", beanReader.getLineNumber(),
                                                 beanReader.getRowNumber(), review.getReviewText()));
                                }
            
        
        
        
                
    }
    finally {
        if(beanReader!=null) {
            beanReader.close();
        }
    }
}
}
