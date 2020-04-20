import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class write the result of our analysis as txt output in formatted manner.
 * @author Xinyi, Xiting, YongJin
 *
 */
public class AnalysisResultFileWriter {
	/**
	 * Writes a .txt of the results
	 */
	public void writeReport(ArrayList<String> results){
		File out = new File("report.txt");
		
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