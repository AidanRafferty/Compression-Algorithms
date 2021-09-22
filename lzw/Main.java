import java.io.*;
import java.util.*;

/**
 * program to find compression ratio using LZW compression
 */
public class Main {

	// Main method for the program
	public static void main(String[] args) throws IOException {

		// start the timer
		long start = System.currentTimeMillis();

		String inputFileName = args[0];
		//String inputFileName = "C:\\Users\\aidan\\OneDrive\\Documents\\Eclipse-Workspace\\algi_set_up(2).tar\\algi_set_up\\small.txt";

		// create a file reader for the text file 
		FileReader reader = new FileReader(inputFileName);
		
		// create a file scanner using the fie reader to read each line of the text file 
		Scanner in = new Scanner(reader);

		// Create a stringbuilder to store the characters in the file as a string
		StringBuilder file = new StringBuilder();

		// while there is a line to be read in the file
		while (in.hasNextLine()) {

			// add the current line of the file and the newline character to the
			// stringbuilder
			file.append(in.nextLine() + "\n");

		}

		// close the scanner and file reader
		in.close();

		reader.close();

		/**
		 * 
		 * create the dictionary that will store the strings as keys and their codewords
		 * as the values
		 * 
		 */
		Trie dictionary = new Trie();

		// store the size of the dictionary
		int dictionarySize = 0;

		// intialise the dictionary with all the standard ascii characters - 0 to 127
		for (int i = 0; i < 128; i++) {

			char character = (char) i;

			dictionary.insert(String.valueOf(character));

			dictionarySize += 1;

		}

		// the codeword length is originally 8
		int codeWordLength = 8;

		// the number of codewords the dictionary can hold with this codeword length is
		// 2 to the power 8 = 256
		int limit = 256;

		// store the current position in the file
		int current = 0;

		// store the next position in the file
		int nextPosition = 0;

		/**
		 * create a string builder that will hold the longest string in the file that is
		 * in the dictionary starting from the current starting postiion in the file
		 */
		StringBuilder longest = new StringBuilder();

		// store the length of the compressed file
		int compressedLength = 0;

		// store the length of the original uncompressed file
		int fileLength = file.length();

		/**
		 * implement the actual compression here
		 * 
		 * while the file has not been exhausted
		 */
		while (current < fileLength) {

			// erase the contents of the longest string from the last iteration
			longest.delete(0, longest.length());

			// add the character at the current position in the file to the longest string
			longest.append(file.charAt(current));

			/**
			 * set the next position to start the compression from to be the postion of the
			 * character after the last character of the current longest string in the file
			 */
			nextPosition = current + longest.length();

			/**
			 * find the longest string s starting from the current position in the file that
			 * is also in the dictionary by searching for strings starting from the current
			 * position and adding on characters until the string cant be found - keeping
			 * track of the last string that was found
			 * 
			 * nextChar is the next character after the current longest string in the that
			 * is already in the dictionary
			 * 
			 * while the last character in the file not reached and the current longest
			 * string with the next character added onto is also in the dictionary
			 **/
			while (nextPosition < (file.length())
					&& dictionary.search(longest.toString() + String.valueOf(file.charAt(nextPosition)))) {

				// add the next character onto the current longest string
				longest.append(file.charAt(nextPosition));

				// update the next position as the longest string has been extended by 1
				// character
				nextPosition += 1;

			}

			/**
			 * add the current codeword length to the length of the compressed file
			 **/
			compressedLength += codeWordLength;

			/**
			 * start the compression in the next iteration in the next position in the text
			 * after the longest string
			 **/
			current += longest.length();

			// if we have reached the end of the file
			if (current < file.length()) {

				// if the dictionary is full
				if (Integer.valueOf(dictionarySize).equals(limit)) {

					// extend the codeword length by 1 and update the capacity of the dictionary
					codeWordLength += 1;

					limit = limit * 2;

				}

				/**
				 * add the longest string followed by the next character in the file to the
				 * dictionary with the next available codeword
				 **/
				longest.append(String.valueOf(file.charAt(current)));
				
				dictionary.insert(longest.toString());

				// add 1 to the current size of the dictionary
				dictionarySize += 1;

			}

		}

		// print the results
		System.out.println("Original file length in bits " + file.length() * 8);
		System.out.println("Compressed File length in bits " + compressedLength);
		System.out.println("Compression Ratio = " + (double) compressedLength / (double) (file.length() * 8));
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");

	}

}
