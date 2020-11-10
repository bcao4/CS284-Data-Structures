package Huffman;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.PriorityQueue;

/*
 * Instructions: 
 * First: Read through the assignment specification, make sure you understand what the assignment is asking for.
 * Second: There are number of "TODO" instructions within this code, make sure to complete all of them fully.
 * Third: Test you code.
 */

// Brandon Cao
// I pledge my honor that I have abided by the Stevens Honor System

/**
 * HW4 CS284 Spring 2019
 * Implements a Huffman encoding tree.
 * The included code has been commented for the student's benefit, feel free to read through it.
 */
public class HuffmanTree {

	// ******************** Start of Stub Code ******************** //
	// ************************************************************ //
    /** Node<E> is an inner class and it is abstract.
     * There will be two kinds
     * of Node, one for leaves and one for internal nodes. */
    abstract static class Node implements Comparable<Node>{
        /** The frequency of all the items below this node */
        protected int frequency;
        protected abstract Node left();
        protected abstract Node right();
        protected abstract char data();
        
        
        public Node(int freq) {
        	this.frequency = freq;
        }
        
		/** Needed for the Minimum Heap used later in this stub. */
		public int compareTo(Node other) {
			return this.frequency - other.frequency;
		}
    }
    /** Leaves of a Huffman tree contain the data items */
    protected static class LeafNode extends Node {
        // Data Fields
        /** The data in the node */
        protected char data;
        /** Constructor to create a leaf node (i.e. no children) */
        public LeafNode(char data, int freq) {
            super(freq);
            this.data = data;
        }
       
		@Override
		protected Node left() {
			return null;
		}
		@Override
		protected Node right() {
			return null;
		}
		@Override
		protected char data() {
			return this.data;
		}
		 /** toString method */
        public String toString() {
            return "[value= "+this.data + ",freq= "+frequency+"]";
        }
    }
    /** Internal nodes contain no data,
     * just references to left and right subtrees */
    protected static class InternalNode extends Node {
        /** A reference to the left child */
        protected Node left;
        /** A reference to the right child */
        protected Node right;

        /** Constructor to create an internal node */
        public InternalNode(Node leftC, Node rightC) {
            super(leftC.frequency + rightC.frequency);
            left = leftC; right = rightC;
        }
		@Override
		protected Node left() {
			// TODO Auto-generated method stub
			return left;
		}
		@Override
		protected Node right() {
			// TODO Auto-generated method stub
			return right;
		}
		@Override
		protected char data() {
			// TODO Auto-generated method stub
			return 0;
		}
		 public String toString() {
	            return "(freq= "+frequency+")";
	    }
    }
	
	// Enough space to encode all "extended ascii" values
	// This size is probably overkill (since many of the values are not "printable" in the usual sense)
	private static final int codex_size = 256;
	
	/* Data Fields for Huffman Tree */
	private Node root;
	
	public HuffmanTree(String s) {
		root = buildHuffmanTree(s);
	}
	
	/**
	 * Returns the frequencies of all characters in s.
	 * @param s
	 * @return
	 */
	public static int[] frequency(String s) {
		int[] freq = new int[codex_size];
		for (char c: s.toCharArray()) {
			freq[c]++;
		}
		return freq;
	}
	
	/**
	 * Builds the actual Huffman tree for that particular string.
	 * @param s
	 * @return
	 */
	private static Node buildHuffmanTree(String s) {
		int[] freq = frequency(s);
		
		// Create a minimum heap for creating the Huffman Tree
		// Note to students: You probably won't know what this data structure
		// is yet, and that is okay.
		PriorityQueue<Node> min_heap = new PriorityQueue<Node>();
		
		// Go through and create all the nodes we need
		// as in, all the nodes that actually appear in our string (have a frequency greater then 0)
		for(int i = 0; i < codex_size; i++) {
			if (freq[i] > 0) {
				// Add a new node (for that character) to the min_heap, notice we have to cast our int i into a char.
				min_heap.add(new LeafNode((char) i, freq[i]));
			}
		}
		
		// Edge case (string was empty)
		if (min_heap.isEmpty()) {
			throw new NullPointerException("Cannot encode an empty String");
		}
		
		// Now to create the actual Huffman Tree 
		// NOTE: this algorithm is a bit beyond what we cover in cs284, 
		// you'll see this in depth in cs385
		
		// Merge smallest subtrees together
		while (min_heap.size() > 1) {
			Node left = min_heap.poll();
			Node right = min_heap.poll();
			Node merged_tree = new InternalNode(left, right);
			min_heap.add(merged_tree);
		}
		
		// Return our structured Huffman Tree
		return min_heap.poll();
	}
	
	// ******************** End of Stub Code ******************** //
	// ********************************************************** //
	
	private void preOrderTraverse(Node node,int depth,StringBuilder sb) {
		for(int i = 1; i < depth; i++) {
			sb.append("  ");
		}
		
		if(node ==null) {
			sb.append("");
		} else {
			sb.append(node.toString());
			sb.append("\n");
			if(node.left() != null) {
				preOrderTraverse(node.left(), depth + 1, sb);
			}
			if (node.right() != null) {
				preOrderTraverse(node.right(), depth + 1, sb);
			}
		}
	}
	
	private StringBuilder find(Node node, StringBuilder temp, char ch) {
		if(node.left() == null || node.right() == null) {
			if(node.data() == ch) {
				return temp;
			}
			temp.delete(temp.length() - 1, temp.length());
			return null;
		} else {
			if(node.left() != null) {
				temp.append("0");
				if(find(node.left(), temp, ch) != null) {
					return temp;
				}
			}
			if(node.right() != null) {
				temp.append("1");
				if(find(node.right(), temp, ch) != null) {
					return temp;
				}
			}
			if (temp.length() > 0) { 
				temp.delete(temp.length() - 1, temp.length());
			}	
		}
		return null;
	}
	
	private void Map(Node node, Dictionary<Character, String> map, StringBuilder temp) {
		if(node.left() == null || node.right() == null) {
			map.put(node.data(), temp.toString());
			if(temp.length() > 0) {
				temp.delete(temp.length() - 1, temp.length());
			}
			return;
		} else {
			if(node.left() != null) {
				temp.append('0');
				Map(node.left(), map, temp);
			}
			if(node.right() != null) {
				temp.append('1');
				Map(node.right(), map, temp);
			}
			if(temp.length() > 0) {
				temp.delete(temp.length() - 1, temp.length());
			}	
		}
	}
	
	/*
	 * represents bit strings as arrays of boolean
	 */
	public String bitsToString(Boolean[] encoding) {
		// TODO Complete bitsToString method
		StringBuilder bitString = new StringBuilder();
		for (int i = 0; i < encoding.length; i++) {
			if(encoding[i] == true) {
				bitString.append("1");
			} else {
				bitString.append("0");
			}
		}
		return bitString.toString();
	}
	
	public String toString() {
		// TODO Complete toString method (see assignment specification)
		// HINT: Might need helper method for preOrderTraversal
		StringBuilder temp = new StringBuilder();
		preOrderTraverse(root, 1, temp);
		return temp.toString();
    }
	
	/*
	 * Uses the Huffman tree to decode a given sequence of bits
	 */
	public String decode(Boolean[] coding) {
		// TODO Complete decode method
		Node current = root; 
		StringBuilder temp = new StringBuilder();
		for(int i = 0; i < coding.length; i++) {
			if(current.left() == null && current.right() == null) {
				temp.append(current.data());
				current = root;
			}
			if(coding[i] == true) { 
				if(current.right() != null) {
					current = current.right();
				} else {
					throw new IllegalArgumentException();
				}
			}
			else if(coding[i] == false ){
				if(current.left() != null) {
					current = current.left();
				} else {
					throw new IllegalArgumentException();
				}
			} else {
				throw new IllegalArgumentException();
			}	
		}
		if(current.left() == null && current.right() == null) {
			temp.append(current.data());
		} else {
			throw new IllegalArgumentException();
		}
		return temp.toString();
	}
	
	/*
	 * Encodes a string into an array of booleans
	 */
	public Boolean[] encode(String inputText) {
		// TODO Complete encode method
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < inputText.length(); i++) {
			temp = find(root, temp, inputText.charAt(i));
			if(temp.length() == 0) {
				throw new IllegalArgumentException();
			}
		}
		Boolean str[] = new Boolean[temp.length()];
		for(int i = 0; i < temp.toString().length(); i++) {
			if(temp.charAt(i) == '1') {
				str[i] = true;
			} else {
				str[i] = false;
			}
		}
 		return str;
	}
	
	/*
	 * More efficient way to encode a string into an array of booleans using dictionaries
	 */
	public Boolean[] efficientEncode(String inputText) {
		// TODO Complete efficientEncode method
		// NOTE: Should only go through the tree once.
		Dictionary dict = new Hashtable();
		StringBuilder temp = new StringBuilder();
		Object booleanValue;
		Map(root, dict, temp);
		temp.setLength(0);
		for(int i = 0; i < inputText.length(); i++) {
			booleanValue = dict.get(inputText.charAt(i));
			if(booleanValue != null) {
				temp.append(booleanValue.toString());
			} else {
				throw new IllegalArgumentException();
			}
		}
		Boolean str[] = new Boolean[temp.length()];
		for (int i = 0; i < temp.toString().length(); i++) {
			if(temp.charAt(i) == '1') {
				str[i] = true;
			} else {
				str[i] = false;
			}
		}
		return str;
	}
	
	/*public static void main(String[] args) {
		String s = "Short";
		HuffmanTree t = new HuffmanTree(s);
		System.out.println(s.toString());
		System.out.println(t.toString());
		Boolean coding[] = new Boolean[] {true, false, true};
		System.out.println(t.bitsToString(coding));
		Boolean[] str = t.encode(s);
		System.out.println(t.bitsToString(str));
		for(int i=0; i < str.length; i++) {
			System.out.format("%s ", str[i]);
		}

	}*/
	
}
