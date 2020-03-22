import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class SentimentAnalysisOnReviews {
	private ArrayList<Review> reviews;

	public SentimentAnalysisOnReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public void runSentimentAnalysis() {
		SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
		StanfordCoreNLP stanfordCoreNLP = sentimentAnalyzer.getSentimentAnalyzer();

		for (Review review : reviews) {
			if (!review.getReviewText().isEmpty()) {
				CoreDocument coreDocument = new CoreDocument(review.getReviewText());
				
				stanfordCoreNLP.annotate(coreDocument);

				List<CoreSentence> sentences = coreDocument.sentences();

				for(CoreSentence sentence: sentences) {
					String sentiment = sentence.sentiment();

					System.out.println(sentiment + "\t" + sentence);
				}
			}
		}
	}
}
