package wordTree.threadMgmt;

import wordTree.tree.Tree;
import wordTree.util.MyLogger;
import wordTree.util.MyLogger.DebugLevel;

public class DeleteThread implements Runnable{

	Tree tree;
	String wordToDelete;

	public DeleteThread(Tree treeI, String wordToDeleteI) {
		MyLogger.writeMessage("DeleteThread Parameterized Constructor is called ", DebugLevel.CONSTRUCTOR);
		tree = treeI;
		wordToDelete = wordToDeleteI;
	}


	@Override
	public void run() {
		MyLogger.writeMessage("Running DeleteThread ~ Deleting word - " + wordToDelete, DebugLevel.RUNNABLE);
		tree.delete(wordToDelete);
	}

}
