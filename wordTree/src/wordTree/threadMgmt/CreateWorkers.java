package wordTree.threadMgmt;

import wordTree.store.Results;
import wordTree.tree.Tree;
import wordTree.util.FileProcessor;

public class CreateWorkers {

	int numOfThreads;
	FileProcessor fileProcessor;
	Results results;

	Tree tree;

	public CreateWorkers() {
		// TODO Auto-generated constructor stub
	}


	public CreateWorkers(int numOfThreadsI, FileProcessor fileProcessorI, Tree treeI) {
		numOfThreads = numOfThreadsI;
		fileProcessor = fileProcessorI;
		tree = treeI;
	}

	public void startPopulateWorkers() {
		int i = numOfThreads;

		try {
			while(i > 0) {
				PopulateThread populateThread = new PopulateThread(tree, fileProcessor);

				Thread thread = new Thread(populateThread);
				thread.setName("Thread"+i);
				thread.start();
				thread.join();
				--i;
			}
		} catch (InterruptedException e) {
			System.err.println("CreateWorked:startPopulateWorkers- Exception occured " + e.getLocalizedMessage());
		}
	}
	

	public void startDeleteWorkers(String[] wordsToDelete) {
		int i = numOfThreads;

		try {
			while(i > 0) {
				DeleteThread deleteThread = new DeleteThread(tree, wordsToDelete);

				Thread thread = new Thread(deleteThread);
				thread.start();
				thread.join();
				--i;
			}
		} catch (InterruptedException e) {
			System.err.println("CreateWorked:startPopulateWorkers- Exception occured " + e.getLocalizedMessage());
		}
	}
	
	
	




}
