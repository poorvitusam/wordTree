package wordTree.threadMgmt;

import wordTree.tree.Tree;
import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;
import wordTree.util.MyLogger.DebugLevel;

public class PopulateThread implements Runnable {
	
	Tree tree;
	FileProcessor fileProcessor;
	 
	public PopulateThread(Tree treeI, FileProcessor fileProcessorI) {
		MyLogger.writeMessage("PopulateThread Parameterized Constructor is called ", DebugLevel.CONSTRUCTOR);
		tree = treeI;
		fileProcessor = fileProcessorI;
	}

	@Override
	public void run() {
		
		MyLogger.writeMessage("Running  Populate Thread - " + Thread.currentThread().getName(), DebugLevel.RUNNABLE);
		
		String line = "";
		while((line = fileProcessor.readLine()) != null ) {
			line = line.trim();
			
			if(line.length() == 0) continue;
			
			String[] words = line.split(" ");
			
			for(String word : words) {
				tree.addWord(word.trim());
			}
		}
	}
}
