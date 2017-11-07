package wordTree.tree;

import wordTree.store.Results;

/**
 * Helper class that holds and create a Binary Search Tree for given words and count
 * @author suresh
 *
 */
public class Tree {
	
	Node rootNode; 
	
	int numberOfDistinctWords;
	int numberOfTotalWords;
	int numberOfCharacters;
	
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}
	
	/**
	 * Add new node to tree
	 * @param node
	 */
	public void addWord(String word) {
		if(word == null || word.length() == 0) return;
		traverseAndAdd(rootNode, word);
	}
	
	/**
	 * Traverse through the tree and find the appropriate place for new node to store.
	 * @param root
	 * @param nodeToAdd
	 * @return node
	 */
	private Node traverseAndAdd(Node root, String wordToAdd) {
		
		if(root == null) {
			root = new Node(wordToAdd);
			return root;
		}
		
		if(wordToAdd.compareTo(root.getWord()) > 0) {
			root.setRightNode(traverseAndAdd(root.getLeftNode(), wordToAdd));
		} else if(wordToAdd.compareTo(root.getWord()) < 0) {
			root.setLeftNode(traverseAndAdd(root.getRightNode(), wordToAdd));
		} else {
			root.incrementWordCount();
		}
		
		return root;
	}
	
	/**
	 * Remove course from list or unregister student from given course
	 * @param bNumber
	 * @param course
	 */
	public void delete(String word) {
		Node node = lookup(rootNode, word);
		
		if(node != null)
			node.decrementWordCount();
	}
	
	/**
	 * Lookup node for given bNumber and return
	 * @param rootNode
	 * @param bNumber
	 * @return <b>node</b> if bnumber found in tree, <b>null</b> otherwise
	 */
	public Node lookup(Node rootNode, String word) {
		Node toReturn = null;
		
		if (rootNode == null) return null;
		
		if(rootNode.getWord().compareTo(word) > 0) {
			toReturn = lookup(rootNode.leftNode, word);
		} else if(rootNode.getWord().compareTo(word) < 0) {
			toReturn = lookup(rootNode.rightNode, word);
		} else {
			toReturn = rootNode;
		}
		
		return toReturn;
	}
	
	/**
	 * Store and write results to file in format 1234:A B C
	 * @param results
	 */
	public void printNodes(Results results) {
		printInAscendingOrder(rootNode, results);
		results.writeToFile();
	}
	
	/**
	 * Traverse tree in In Order form and store result in Results object
	 * @param node
	 * @param result
	 */
	private void printInAscendingOrder(Node node, Results result) {
		if(node == null) return;
		
		printInAscendingOrder(node.getLeftNode(), result);
		
		result.storeNewResult(node.toString());
		
		printInAscendingOrder(node.getRightNode(), result);
	}
	
	/**
	 * Traverse tree in In Order form and store result in Results object
	 * @param node
	 * @param result
	 */
	private void calculateCount(Node node) {
		if(node == null) return;
		
		calculateCount(node.getLeftNode());
		
		if(node.occurrence > 0) {
			numberOfCharacters += node.word.length() * node.occurrence;
			numberOfDistinctWords += 1;
			numberOfTotalWords += node.occurrence;
		}
		
		calculateCount(node.getRightNode());
	}
	
	public void calculateStats() {
		calculateCount(rootNode);
	}
	
	public int getNumberOfCharacters() {
		return numberOfCharacters;
	}
	
	
	public int getNumberOfTotalWords() {
		return numberOfTotalWords;
	}

	
	public int getNumberOfDistinctWords() {
		return numberOfDistinctWords;
	}
}
