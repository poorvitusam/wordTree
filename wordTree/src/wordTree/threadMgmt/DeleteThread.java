package wordTree.threadMgmt;

import wordTree.tree.Tree;

public class DeleteThread implements Runnable{

	Tree tree;
	String[] wordsToDelete;
	 
	public DeleteThread(Tree treeI, String[] wordsToDeleteI) {
		tree = treeI;
		wordsToDelete = wordsToDeleteI;
	}

	
	@Override
	public void run() {
		for(String word : wordsToDelete) {
			tree.delete(word);
		}
	}

}
