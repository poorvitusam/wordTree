package wordTree.tree;

import wordTree.util.MyLogger;
import wordTree.util.MyLogger.DebugLevel;

/**
 * Class that represents Node of a tree.
 * @author suresh
 *
 */
public class Node {


	Node leftNode;
	Node rightNode;

	String word;
	int occurrence;

	@SuppressWarnings("unused")
	private Node() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Parameterized constructor to restrict 1 Node to 1 BNumber relation. Also, initializes
	 * all member variables
	 * @param bNumber
	 */
	public Node(String wordI) {
		MyLogger.writeMessage("Node Parameterized Constructor is called ", DebugLevel.CONSTRUCTOR);
		word = wordI;
		leftNode = null;
		rightNode = null;
	}

	/**
	 * Getter method for Word
	 * @return
	 */
	public String getWord() {
		return word;
	}


	/**
	 * Getter method for occurrences of the word
	 * @return
	 */
	public int getOccurrence() {
		return occurrence;
	}

	/**
	 * Increase the word count by one
	 */
	public void incrementWordCount()  {
		occurrence += 1;
	}


	/**
	 * Decrease the word count by one
	 */
	public void decrementWordCount()  {
		if(occurrence > 0)
			occurrence -= 1;
	}

	/**
	 * Getter Method for Left Node 
	 * @return Node
	 */
	public Node getLeftNode() {
		return leftNode;
	}

	/**
	 * Setter method for LeftNode
	 * @param leftNode
	 */
	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	/**
	 * Getter Method for Right Node
	 * @return Node
	 */
	public Node getRightNode() {
		return rightNode;
	}

	/**
	 * Setter method for Right Node 
	 * @param rightNode
	 */
	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}



	@Override
	public Object clone() throws CloneNotSupportedException {
		Node node = new Node(this.word);
		node.occurrence = this.getOccurrence();
		return node;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();	

		builder.append(word);
		builder.append(" [" + occurrence + "] ");

		return builder.toString();
	}


}
