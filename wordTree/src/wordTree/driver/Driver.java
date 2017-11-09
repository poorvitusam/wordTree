package wordTree.driver;

import wordTree.store.Results;
import wordTree.threadMgmt.CreateWorkers;
import wordTree.tree.Tree;
import wordTree.util.FileProcessor;
import wordTree.util.FileProcessor.Permission;
import wordTree.util.MyLogger;
import wordTree.util.WordsStatistics;

public class Driver {
	public static void main(String args[]) {

		if (!validateArguments(args)) {
			System.err.println("Please provide valid number of arguments. 5 Arguments are expected: \n1.Input File \n2.Output File \n3.No. of thresads \n4.Words \n5.Log Level");
			System.exit(0);
		}

		String inputFile = args[0];
		String outputFile = args[1];

		String words = args[3];
		String[] wordsToDelete = words.split(" ");

		int numOfThreads = 0;
		int debugLevel = -1;

		try {
			numOfThreads = Integer.parseInt(args[2]);
			debugLevel = Integer.parseInt(args[4]);
		} catch (NumberFormatException e) {
			System.err.println("Invalid Log level found in the command");
			System.exit(0);
		}

		if (numOfThreads != wordsToDelete.length) {
			System.err.println("Number of Threads and Words to delete count should be same");
			System.exit(0);
		}

		if (debugLevel < 0 || debugLevel > 4) {
			System.err.println("Invalid Log level found in the command");
			System.exit(0);
		}

		MyLogger.setDebugValue(debugLevel);

		FileProcessor inputFileProcess = new FileProcessor(inputFile, Permission.READ, false);

		Results results = new Results(outputFile);
		Tree tree = new Tree();

		CreateWorkers createWorkers = new CreateWorkers(numOfThreads, inputFileProcess, tree);
		createWorkers.startPopulateWorkers();

		// After populate tree is done
		createWorkers.startDeleteWorkers(wordsToDelete);

		WordsStatistics statistics = new WordsStatistics(results, tree);
		statistics.populate();

		inputFileProcess.closeFile();

	}

	private static boolean validateArguments(String[] args) {
		if (args == null || args.length != 5) {
			return false;
		}

		if ((args[0].trim().length() == 0 || args[0].contains("${arg0}"))
				|| (args[1].trim().length() == 0 || args[1].contains("${arg1}"))
				|| (args[2].trim().length() == 0 || args[2].contains("${arg2}"))
				|| (args[3].trim().length() == 0 || args[3].contains("${arg3}"))
				|| (args[4].trim().length() == 0 || args[4].contains("${arg4}"))) {
			return false;
		}

		return true;
	}
}
