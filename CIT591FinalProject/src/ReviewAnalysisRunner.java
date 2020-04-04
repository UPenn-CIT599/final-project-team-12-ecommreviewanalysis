import java.io.IOException;
import java.util.ArrayList;

/**
 * This is for us to run our different java code class in one main location executing sequence of actions we need.
 * We will read the data, analyze, and output the data over here.
 * @author YongJin, Xinyi, Xiting
 *
 */
public class ReviewAnalysisRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReviewReader reviewReader = new ReviewReader();
		try {
			reviewReader.readFile();
		} catch (IOException e) {
			System.out.println("Could not find the input file");
		}

	}


}
