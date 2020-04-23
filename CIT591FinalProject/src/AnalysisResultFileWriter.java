import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * This class write the result of our analysis as txt output in formatted manner.
 * @author Xinyi, Xiting, YongJin
 *
 */
public class AnalysisResultFileWriter implements FileWriter {
	private String dataAnalysisOutputFileName = "report.txt";
	private ArrayList<String> results;
	
	public AnalysisResultFileWriter(ArrayList<String> results) {
		this.results = results;
	}
	
	/**
	 * Writes a .txt of the results
	 */
	public void writeOutputFile(){
		File out = new File(dataAnalysisOutputFileName);
		
		try( PrintWriter pw = new PrintWriter(out) ) {
			
			for( String r : results ) {
				pw.println(r);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not write the File out.  Check permissions, or contact course staff for help");
		}
	}


	
}