package wordTree.threadMgmt;

import java.util.ArrayList;

import wordTree.store.Results;
import wordTree.tree.Tree;
import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;
import wordTree.util.MyLogger.DebugLevel;

public class CreateWorkers {

	int numOfThreads;
	FileProcessor fileProcessor;
	Results results;

	Tree tree;

	public CreateWorkers() {
		// TODO Auto-generated constructor stub
	}


	public CreateWorkers(int numOfThreadsI, FileProcessor fileProcessorI, Tree treeI) {
		MyLogger.writeMessage("CreateWorkers Parameterized Constructor is called ", DebugLevel.CONSTRUCTOR);
		numOfThreads = numOfThreadsI;
		fileProcessor = fileProcessorI;
		tree = treeI;
	}

	public void startPopulateWorkers() {
		int i = numOfThreads;
		ArrayList<Thread> threadList=new ArrayList<Thread>();
		try {
			while(i > 0) {
				PopulateThread populateThread = new PopulateThread(tree, fileProcessor);

				Thread thread = new Thread(populateThread);
				thread.setName("Thread"+i);
				thread.start();
				threadList.add(thread);
				MyLogger.writeMessage("Thread-" + thread.getName() + " Started for PopulateWorkers", DebugLevel.VERBOSE);
				--i;
			}
		} catch (Exception e) {
			System.err.println("CreateWorked:startPopulateWorkers- Exception occured " + e.getLocalizedMessage());
		}
		
		try {
			for(Thread thread : threadList) {
				thread.join();
				MyLogger.writeMessage("Thread-" + thread.getName() + " Joined for PopulateWorkers", DebugLevel.VERBOSE);
			}
			threadList.clear();
		} catch (InterruptedException e) {
			System.err.println("CreateWorked:startPopulateWorkers- Exception occured " + e.getLocalizedMessage());
		}
	}
	

	public void startDeleteWorkers(String[] wordsToDelete) {
		int i = numOfThreads;
		ArrayList<Thread> threadList=new ArrayList<Thread>();
		
		try {
			while(i > 0) {
				DeleteThread deleteThread = new DeleteThread(tree, wordsToDelete[i-1]);
				Thread thread = new Thread(deleteThread);
				thread.start();
				threadList.add(thread);
				MyLogger.writeMessage("Thread-" + thread.getName() + " Started for DeleteWorkers", DebugLevel.VERBOSE);
				--i;
			}
		} catch (Exception e) {
			System.err.println("CreateWorked:startPopulateWorkers- Exception occured " + e.getLocalizedMessage());
		}
		
		try {
			for(Thread thread : threadList) {
				thread.join();
				MyLogger.writeMessage("Thread-" + thread.getName() + " Joined for DeleteWorkers", DebugLevel.VERBOSE);
			}
			threadList.clear();
		} catch (InterruptedException e) {
			System.err.println("CreateWorked:startPopulateWorkers- Exception occured " + e.getLocalizedMessage());
		}
	}
}
