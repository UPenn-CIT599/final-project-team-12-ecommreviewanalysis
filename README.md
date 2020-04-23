# final-project-team-12-ecommreviewanalysis
final-project-team-12-ecommreviewanalysis created by GitHub Classroom

MCIT Final Project - Data Analysis on Women's Clothing E-Commerce Reviews.

Team members :
	Yong Jin Lee <ylee0503@seas.upenn.edu>
	Xinyi Samore <xsamore@seas.upenn.edu>,
	Xiting Wang <xiting@seas.upenn.edu>
	
Objective: Run data analysis on Women's Clothing E-Commerce Review csv file from Internet.

Packages/Libraries Used:
	apache.commons.csv (commons-csv-1.8) (http://commons.apache.org/proper/commons-csv/download_csv.cgi)
	,Stanford coreNLP (https://stanfordnlp.github.io/CoreNLP/)
	,jfreechart.org http://www.jfree.org/jfreechart/
*DoctFile on how to configure Eclipse Buildpath with External jar and libarries for our project is included in this Github rpeository with the name: Adding Project12 External Jars asnd Libraries.docx

Two Main Runner class to be run in sequence:
	1) SentimentAnalysisRunner <- to run SentimentAnalysis and generate output with Sentiments analysis outcome.
	2) ReviewAnalysisRunner <- take the output from Sentiment analysis, run data analysis, then generate output in txt and plots.
