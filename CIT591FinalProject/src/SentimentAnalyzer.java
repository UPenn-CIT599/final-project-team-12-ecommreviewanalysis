import java.util.Properties;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

/**
 * This SentimentAnalyzer is created to configure and set up 
 * StanfordCoreNLP class for our sentiment analysis purpose.
 * @author Xiting, Xinyi, Yong-Jin
 *
 */

public class SentimentAnalyzer {

	private static StanfordCoreNLP stanfordCoreNLP;
	private static Properties properties;
	private static String propertiesName;
	
	/**
	 * Constructor for SentimentAnalyzer.
	 * Will be initialized by the default value that we configured.
	 */
	public SentimentAnalyzer() {
		propertiesName = "tokenize,ssplit, pos, lemma, ner, parse, sentiment";
		properties = new Properties();
		properties.setProperty("annotators", propertiesName);
	}

	public StanfordCoreNLP getSentimentAnalyzer() {
		if(stanfordCoreNLP == null) {
			stanfordCoreNLP = new StanfordCoreNLP(properties);
		}
		return stanfordCoreNLP;
	}

}
