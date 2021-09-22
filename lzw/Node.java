
public class Node {
	
	private char letter; // label on incoming branch
	private boolean isWord; // true when node represents a word
	private Node sibling; // next sibling (when it exists)
	private Node child; // first child (when it exists)
	
	/** create a new node with letter c */
	/**
	 *  for the LZW will add the character c that is after the longest string s fopund from the current position
	 *  in the compressed file in the dictionary as a child node to the node that represents the end of the word 
	 *  s in the trie. 
	 * @param c
	 */
	public Node(char c){
		letter = c;
		isWord = false;
		sibling = null;
		child = null;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public boolean getIsWord() {
		return isWord;
	}

	public void setIsWord(boolean isWord) {
		this.isWord = isWord;
	}

	public Node getSibling() {
		return sibling;
	}

	public void setSibling(Node sibling) {
		this.sibling = sibling;
	}

	public Node getChild() {
		return child;
	}

	public void setChild(Node child) {
		this.child = child;
	}
	
	// include accessors and mutators for
	// the various components of the class
	
}
