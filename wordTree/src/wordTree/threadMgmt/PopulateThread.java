package wordTree.threadMgmt;

import wordTree.tree.Tree;
import wordTree.util.FileProcessor;

public class PopulateThread implements Runnable {
	
	Tree tree;
	FileProcessor fileProcessor;
	 
	public PopulateThread(Tree treeI, FileProcessor fileProcessorI) {
		tree = treeI;
		fileProcessor = fileProcessorI;
	}

	@Override
	public void run() {
		String line = "";
		while((line = fileProcessor.readLine()) != null ) {
			line = line.trim();
			
			if(line.length() == 0) continue;
			
			String[] words = line.split(" ");
			
			for(String word : words) {
				tree.addWord(word);
			}
		}
	}
}
