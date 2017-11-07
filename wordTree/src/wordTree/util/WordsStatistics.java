package wordTree.util;

import wordTree.store.Results;
import wordTree.tree.Tree;




public class WordsStatistics {

	Results results;
	Tree tree;

	public WordsStatistics(Results resultsI, Tree treeI) {
		tree = treeI;
		results = resultsI;
	}

	public void populate() {
		tree.calculateStats();

		results.storeNewResult("The total number of words: " + tree.getNumberOfTotalWords());
		results.storeNewResult("The total number of characters: " + tree.getNumberOfCharacters());
		results.storeNewResult("The total number of distinct words: " + tree.getNumberOfDistinctWords());

		results.writeToStdout();
		results.writeToFile();
	}





}
