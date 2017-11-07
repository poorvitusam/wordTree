package wordTree.threadMgmt;

import wordTree.tree.Tree;
import wordTree.util.FileProcessor;

public class DeleteThread implements Runnable{

	Tree tree;
	FileProcessor fileProcessor;
	String[] wordsToDelete;
	 
	public DeleteThread(Tree treeI, FileProcessor fileProcessorI, String[] wordsToDeleteI) {
		tree = treeI;
		fileProcessor = fileProcessorI;
		wordsToDelete = wordsToDeleteI;
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
