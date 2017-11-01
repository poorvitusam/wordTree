package src.wordTree.driver;

import src.wordTree.util.FileProcessor;
import src.wordTree.util.MyLogger;

public class Driver {
	public static void main(String args[]) {
		String inputFile = args[0];
		String outputFile = args[1];
		int NUM_THREADS = Integer.parseInt(args[2]);
		String words = args[3];
		int debugLevel = Integer.parseInt(args[4]);
		
		MyLogger.setDebugValue(debugLevel);
		
		FileProcessor reader = new FileProcessor(inputFile, "read", "read");;
		FileProcessor writer = new FileProcessor(outputFile, "write", "output");
	}
}
