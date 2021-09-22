public class HuffmanTreeNode {

	String character;
	int weight = 0;
	HuffmanTreeNode leftChild;
	HuffmanTreeNode rightChild;

	public HuffmanTreeNode() {

	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public HuffmanTreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(HuffmanTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public HuffmanTreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(HuffmanTreeNode rightChild) {
		this.rightChild = rightChild;
	}

}
