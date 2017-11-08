package wordTree.store;

import java.util.ArrayList;
import java.util.List;

import wordTree.util.FileDisplayInterface;
import wordTree.util.FileProcessor;
import wordTree.util.FileProcessor.Permission;
import wordTree.util.MyLogger;
import wordTree.util.MyLogger.DebugLevel;
import wordTree.util.StdoutDisplayInterface;

/**
 * Class to store all results in string format and process those results to write in
 * a file or print on console
 * @author suresh
 *
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	List<String> testResults = new ArrayList<String>();;
	String outputFilePath;

	public Results(String outputFilePath) {
		MyLogger.writeMessage("Results Parameterized Constructor is called for file - " + outputFilePath, DebugLevel.CONSTRUCTOR);
		this.outputFilePath = outputFilePath;
	}


	/**
	 * Store new result
	 * @param resultString
	 */
	public void storeNewResult(String resultString) {
		testResults.add(resultString);
	}

	
	
	@Override
	public void writeToFile() {
		FileProcessor fileProcessor = new FileProcessor(outputFilePath, Permission.WRITE, true);
		fileProcessor.writeLines(testResults);
		fileProcessor.closeFile();
		
		System.out.println("Result is generated at path = " + fileProcessor.getFilePath());
	}



	@Override
	public void writeToStdout() {
		for (String line : testResults) {
			System.out.println(line);
		}
	}


}
