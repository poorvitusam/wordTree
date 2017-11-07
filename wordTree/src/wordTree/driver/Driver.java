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
		
		
		if(args == null || args.length != 5) {
			System.err.println("Please provide valid number of arguments. 5 Arguments are expected: \n1.Input File \n2.Output File \n3.No. of thresads \n4.Words \n5.Log Level");
			System.exit(0);
			return;
		}

		if((args[0].trim().length() == 0 || args[0].contains("${arg0}")) || 
				(args[1].trim().length() == 0 || args[1].contains("${arg1}")) || 
				(args[1].trim().length() == 0 || args[1].contains("${arg2}")) || 
				(args[1].trim().length() == 0 || args[1].contains("${arg3}")) || 
				(args[2].trim().length() == 0 || args[2].contains("${arg4}"))) {

			System.err.println("Please provide valid number of arguments. 5 Arguments are expected: \n1.Input File \n2.Output File \n3.No. of thresads \n4.Words \n5.Log Level");
			System.exit(0);
			return;
		}


		String inputFile = args[0];
		String outputFile = args[1];
		
		String words = args[3];
		String[] wordsToDelete = words.split(" ");
		
		int NUM_THREADS = 0;
		int debugLevel = -1;

		try {
			NUM_THREADS = Integer.parseInt(args[2]);
			debugLevel = Integer.parseInt(args[4]);
		} catch (NumberFormatException e) {
			System.err.println("Invalid Log level found in the command");
			System.exit(1);
		}
		
		if(NUM_THREADS != wordsToDelete.length) {
			System.err.println("Number of Threads and Words to delete count should be same");
			System.exit(1);
		}
		
		MyLogger.setDebugValue(debugLevel);
		
		FileProcessor inputFileProcess = new FileProcessor(inputFile, Permission.READ);
		inputFileProcess.allowEmptyFile(false);
		
		Results results = new Results(outputFile);
		Tree tree = new Tree();
		
		CreateWorkers createWorkers = new CreateWorkers(NUM_THREADS, inputFileProcess, tree);
		createWorkers.startPopulateWorkers();
		
		//After populate tree is done
		createWorkers.startDeleteWorkers(wordsToDelete);
		
		WordsStatistics statistics = new WordsStatistics(results, tree);
		statistics.populate();
		
		inputFileProcess.closeFile();
		
	}
}
