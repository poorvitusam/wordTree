package wordTree.tree;

import wordTree.store.Results;
import wordTree.util.MyLogger;
import wordTree.util.MyLogger.DebugLevel;




public class WordsStatistics {

	Results results;
	Tree tree;

	public WordsStatistics(Results resultsI, Tree treeI) {
		MyLogger.writeMessage("WordsStatistics Parameterized Constructor is called ", DebugLevel.CONSTRUCTOR);
		tree = treeI;
		results = resultsI;
	}

	public void populate() {
		tree.calculateStats();

		results.storeNewResult("The total number of words: " + tree.getNumberOfTotalWords());
		results.storeNewResult("The total number of characters: " + tree.getNumberOfCharacters());
		results.storeNewResult("The total number of distinct words: " + tree.getNumberOfDistinctWords());

		results.writeToFile();
	}





}
