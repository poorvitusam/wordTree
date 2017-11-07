package wordTree.threadMgmt;

import wordTree.tree.Tree;

public class DeleteThread implements Runnable{

	Tree tree;
	String wordToDelete;

	public DeleteThread(Tree treeI, String wordToDeleteI) {
		tree = treeI;
		wordToDelete = wordToDeleteI;
	}


	@Override
	public void run() {
		tree.delete(wordToDelete);
	}

}
