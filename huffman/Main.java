import java.io.*;
import java.util.*;

/**
 * Program to find compression ratio using Huffman Coding
 */
public class Main {

	// Main method for the program
	public static void main(String[] args) throws IOException {

		// start the timer
		long start = System.currentTimeMillis();

		/**
		 * 
		 * REMEMBER TO CHANGE BACK TO THIS LINE FOR GETTING THE FILE
		 */
		String inputFileName = args[0];
		//String inputFileName = "C:\\Users\\aidan\\OneDrive\\Documents\\Eclipse-Workspace\\algi_set_up(2).tar\\algi_set_up\\small.txt";

		FileReader reader = new FileReader(inputFileName);

		Scanner in = new Scanner(reader);

		// create a hashmap to store the characters and their associated frequencies as
		// keys and values respectively
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		
		// used to store the number of bits in the file
		int numberOfCharacters = 0;
		
		StringBuilder line = new StringBuilder();

		/**
		 * Read in each line of the file 
		 **/
		while (in.hasNextLine()) {
			
			line.delete(0, line.length());

			// store the current line of the file
			line.append(in.nextLine());

			// for each character
			for (int i=0;i<line.length();i++) {

				// convert it to a string 
				String s = String.valueOf(line.charAt(i));

				/**
				 * add the character to the dictionary of frequencies 
				 * with the initiial value 0 if the character is not already in the dictionary, otherwise 
				 * add 1 to the character's frequency
				**/
				dict.put(s, dict.getOrDefault(s, 0) + 1);

				// add 1 to the number of characters in the original file
				numberOfCharacters += 1;

			}

			// add the newline character to the end of the line and increment the number of
			// characters counter
			dict.put("\\n", dict.getOrDefault("\\n", 0) + 1);

			numberOfCharacters += 1;

		}

		// close the file reader
		reader.close();

		// close the scanner
		in.close();

		// Create an Arraylist of Huffman Nodes which will then be truned into a min heap of huffman nodes
		ArrayList<HuffmanTreeNode> nodes = new ArrayList<HuffmanTreeNode>();

		// for each of the characters in the dictionary
		for (String key : dict.keySet()) {

			// Create a huffman node for the character setting the weight of the node as the character's frequency 
			HuffmanTreeNode node = new HuffmanTreeNode();
			node.setCharacter(key);
			node.setWeight(dict.get(key));
			nodes.add(node);

		}

		// create a new min heap of huffman nodes 
		HuffmanHeap minHeap = new HuffmanHeap(nodes);

		// store the Weighted Path Lenght which will equal the length of the compressed file in bits
		double WPL = 0.0;

		int startSize = minHeap.getSize();

		// Build the huffman tree 
		for (int i = 0; i < startSize-1; i++) {
			
			// remove the 2 minimum weighted nodes from the min heap
			HuffmanTreeNode root1 = minHeap.deleteMin();

			HuffmanTreeNode root2 = minHeap.deleteMin();

			// Create a new node which has the 2 minimum weighted nodes that have been removed as its children 
			HuffmanTreeNode newNode = new HuffmanTreeNode();

			newNode.setWeight(root1.getWeight() + root2.getWeight());

			// add the weights of the minumum weight nodes to the weighted path length
			WPL += root1.getWeight() + root2.getWeight();

			newNode.setLeftChild(root1);

			newNode.setRightChild(root2);

			// add this node to the min heap - the size of the heap will be reduced by 1 
			minHeap.insert(newNode);

		}

		/**
		 * set the size of the compressed file in bits as the number of characters 
		 * times 8 as each character is 8 bits
		**/		
		 int fileSize = (int) (numberOfCharacters * 8);

		// Print the results 
		System.out.println("Original file length in bits = " + fileSize);

		System.out.println("Compressed file length in bits = " + (int) WPL);

		double ratio = WPL / fileSize;

		System.out.println("Compression ratio " + ratio);

		// end timer and print elapsed time as last line of output
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
	}

}