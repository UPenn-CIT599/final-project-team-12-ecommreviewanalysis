import java.util.Properties;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class SentimentAnalyzer {

	private static StanfordCoreNLP stanfordCoreNLP;
	private static Properties properties;
	private static String propertiesName;

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
