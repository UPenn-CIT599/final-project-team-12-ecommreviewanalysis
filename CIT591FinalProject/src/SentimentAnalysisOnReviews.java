import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
/**
 * SentimentAnalysisOnReviews class is created to actually capture sentiment
 * and the score calculation we came up with to evaluate each of the review.
 * Will be returning new review items with updated variables called
 * sentiments (ArrayList of String) and sentimentScore (int)
 * @author Xinyi, Xiting, Yong-Jin
 *
 */
public class SentimentAnalysisOnReviews {
	private ArrayList<Review> reviews;

	/**
	 * The constructor for SentimentAnalysis
	 * @param reviews
	 */
	public SentimentAnalysisOnReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * This one will actually run sentiment analysis per sentence from the review we extracted from
	 * csv file.
	 */
	public void runSentimentAnalysis() {
		SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
		StanfordCoreNLP stanfordCoreNLP = sentimentAnalyzer.getSentimentAnalyzer();

		for (Review review : reviews) {
			if (!review.getReviewText().isEmpty()) {
				CoreDocument coreDocument = new CoreDocument(review.getReviewText());
				ArrayList<String> sentiments = review.getSentiments();
				int sentimentScore = review.getSentimentScore();

				stanfordCoreNLP.annotate(coreDocument);

				List<CoreSentence> sentences = coreDocument.sentences();

				for(CoreSentence sentence: sentences) {
					String sentiment = sentence.sentiment();

					sentiments.add(sentiment);

					//add score by 1 if the sentiment is Positive
					//subtract score by 1 if the sentiment is Negative
					//do nothing if the sentiment is neutral
					if(sentiment.equals("Positive")) {
						sentimentScore++;
					} else if(sentiment.equals("Negative")) {
						sentimentScore--;
					} else if(sentiment.equals("Very positive")) {
						sentimentScore += 2;
					} else if(sentiment.equals("Very negative")) {
						sentimentScore -= 2;
					}

					//System.out.println(sentiment + "\t" + sentence);

					//update the reviews with newly calculated variables.
					review.setSentiments(sentiments);
					review.setSentimentScore(sentimentScore);
				}
			}
		}
	}

	public ArrayList<Review> getUpdatedReviews(){
		return this.reviews;
	}
}
