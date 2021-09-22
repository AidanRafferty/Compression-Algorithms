import java.util.ArrayList;

public class HuffmanHeap {

	ArrayList<HuffmanTreeNode> items;

	int size = 0;

	public HuffmanHeap(ArrayList<HuffmanTreeNode> heap) {

		size = heap.size();

		items = new ArrayList<HuffmanTreeNode>(heap);

		build();
	}

	public int getSize(){

		return this.size;
	}

	public void build() {
		// for each non leaf node (branch node) in bottom to top right to left order,
		// impose heap property on the node
		// the last node in the heap is at position size -1 so its parent is the last
		// (bottom right) non-leaf node in the heap
		// floor(size -1-1)/2 = floor(size - 2)/2 = (size - 2)/2 in java as floor by
		// default and this is the last
		for (int i = (size - 2) / 2; i >= 0; i--) {

			impose(i);

		}

	}

	public void impose(int index) {

		if (items.isEmpty()) {

			return;

		}

		HuffmanTreeNode temp = items.get(index);

		int currentPosition = index;

		boolean finished = false;

		// while the node to be imposed is not in the leaves(while the current position has a child below) 
		// and not found the correct positon for the node in the heap for the min-heap property to hold (not finished)
		while ((2 * currentPosition) + 1 < size && !finished) {

			// intially set the smaller child to be the left child
			int smallerChildPos = 2 * currentPosition + 1;

			// if there is a right child node and has a lesser weight then set its index as
			// the smaller child position
			if (smallerChildPos + 1 < size

					&& items.get(smallerChildPos + 1).getWeight() < items.get(smallerChildPos).getWeight()) {

				smallerChildPos += 1;

			}

			/**
			 If the smaller child has a smaller weight than the node being imposed
			 then swap the lesser child into the current position of the node
			 being imposed and set the node being imposed's position to the lesser child's
			 position
			 so this position can be tested in the next iteration to see if the node being
			 at this position
			 would satisfy the min heap property.
			 */
			if (items.get(smallerChildPos).getWeight() < temp.getWeight()) {

				items.set(currentPosition, items.get(smallerChildPos));

				currentPosition = smallerChildPos;

			}

			/**
			 * 
			 * otherwise set finished to true as min - heap is imposed with the node at its
			 * current position
			**/ 
			else {

				finished = true;
			}

		}

		// set the position of the node in the min heap to its correct position
		items.set(currentPosition, temp);

	}

	public void insert(HuffmanTreeNode node) {

		size++;

		int current = size - 1;

		items.add(node);

		// while not in the root and the parent of the current node is
		// greater than the current node in terms of weight
		while (current > 0 && items.get((current - 1) / 2).getWeight() > node.getWeight()) {

			// move the parent node into the current position
			items.set(current, items.get((current - 1) / 2));

			// move up another level to the position of the parent node
			current = (current - 1) / 2;

		}

		// when the root reached or the current position is correct for the node add the node at thois postion in the heap.
		items.set(current, node);

	}

	public HuffmanTreeNode deleteMin() {

		// code for the deletion of the root

		// get the root node
		HuffmanTreeNode root = items.get(0);

		// swap with bottom right node
		HuffmanTreeNode last = items.get(size - 1);
		items.set(0, last);
		items.set(size - 1, root);
		items.remove(items.size() - 1);
		size--;
		// delete the bottom right node

		// impose on the root
		impose(0);

		return root;

	}

}
