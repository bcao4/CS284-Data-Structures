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


// TODO: Name and Pledge

// Pledge:
// Name:


/**
 * HW4 CS284 Spring 2019
 * Implements a Huffman encoding tree.
 * The included code has been commented for the student's benefit, feel free to read through it.
 */
public class dHuffmanTree {

	// ******************** Start of Stub Code ******************** //
	// ************************************************************ //
    /** Node<E> is an inner class and it is abstract.
     * There will be two kinds
     * of Node, one for leaves and one for internal nodes. */
    abstract static class Node implements Comparable<Node>{
        /** The frequency of all the items below this node */
        protected int frequency;
 //       protected Node left;
 //       protected Node right;
   
        abstract protected Node getLeftNode();
        abstract protected Node getRightNode();
        abstract protected char getData();
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
        /** leadf noode has no left */
        public Node getLeftNode() {
        	return null;
        }
        /** leaf node has no right */
        public Node getRightNode() {
        	return null;
        }
        /** Return data in the node */
        public char getData() {
        	return this.data;
        }
        /** toString method */
        public String toString() {
            return "[value= "+this.data + ",freq="+frequency+"]";
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
        
        /** Return left node */
        protected Node getLeftNode() {
        	return left;
        }
        /** Return right node */
        protected Node getRightNode() {
        	return right;
        }
        /** Return data in a node - internal node has node data */
        protected char getData() {
        	return 0;
        }
        public String toString() {
            return "(freq="+frequency+")";
        }
    }
	
	// Enough space to encode all "extended ascii" values
	// This size is probably overkill (since many of the values are not "printable" in the usual sense)
	private static final int codex_size = 256;
	
	/* Data Fields for Huffman Tree */
	private Node root;
	
	public dHuffmanTree(String s) {
		if(s.length() == 0) {
			throw new IllegalArgumentException("Cannot build a tree from an empty string");	
		}
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
				Node leafNode= new LeafNode((char) i, freq[i]);
				min_heap.add(leafNode);
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
			min_heap.add(new InternalNode(left, right));
		}
		
		// Return our structured Huffman Tree
		return min_heap.poll();
	}
	
	// ******************** End of Stub Code ******************** //
	// ********************************************************** //
	/**
	 * Traverse Huffman tree and print the free from sb
	 * @param node 
	 * @param depth
	 * @param sb
	 */
	private void preOrderTraverse(Node node,int depth,StringBuilder sb) {
		for(int i = 1; i < depth; i++) {
			sb.append("  ");
		}
		
		if(node ==null) {
			sb.append("");
		} else {
			sb.append(node.toString());
			sb.append("\n");
			if(node.getLeftNode() != null ) {
			    preOrderTraverse(node.getLeftNode(), depth + 1, sb);
			}
			if(node.getRightNode() != null ) {
			    preOrderTraverse(node.getRightNode(), depth + 1, sb);
			}
		}
	}
	/**
	 * Traverse Huffman tree to build encoding string based on each character in text string
	 * @param node
	 * @param sb
	 * @param ch
	 * @return
	 */
	private StringBuilder preOrderTraverseSearch(Node node, StringBuilder sb, char ch) {
		if(node.getLeftNode() == null || node.getRightNode() == null) {
			if(node.getData() == ch) {
				return sb;
			}
			sb.delete(sb.length()-1, sb.length());
			return null;
		}
		else {
			if(node.getLeftNode() != null ) {
				sb.append("0");
			    if(preOrderTraverseSearch(node.getLeftNode(), sb, ch) != null) {
			    	return sb;
			    }
			}
			if(node.getRightNode() != null ) {
				sb.append("1");
			    if( preOrderTraverseSearch(node.getRightNode(), sb, ch) != null ) {
			    	return sb;
			    }
			}
			if(sb.length() > 0)
			    sb.delete(sb.length()-1, sb.length());
		}
		return null;
	}
	/**
	 * Build map of data to encoding from Huffman tree
	 * @param node
	 * @param encodeMap
	 * @param sb
	 */
	private void preOrderTraverseMap(Node node, Dictionary<Character, String> encodeMap, StringBuilder sb) {
		if(node.getLeftNode() == null || node.getRightNode() == null) {
			encodeMap.put(node.getData(), sb.toString());
			if( sb.length() > 0) {
			    sb.delete(sb.length()-1, sb.length());
			}
			return;
		} else {
			if(node.getLeftNode() != null ) {
				sb.append("0");
			    preOrderTraverseMap(node.getLeftNode(), encodeMap, sb);
			}
			if(node.getRightNode() != null ) {
				sb.append("1");
			    preOrderTraverseMap(node.getRightNode(), encodeMap, sb);
			}
			if(sb.length() > 0)
			    sb.delete(sb.length()-1, sb.length());
		}
	}
	/**
	 * Input: arrary of [true, false, true], return 101 
	 * @param encoding
	 * @return
	 */
	public String bitsToString(Boolean[] encoding) {
		StringBuilder bitString = new StringBuilder();
		for( int i=0; i < encoding.length; i++) {
			if(encoding[i] == true) {
				bitString.append("1");
			}
			else {
				bitString.append("0");
			}
		}
		return bitString.toString();
	}
	
    /**
     * Build Huffman tree, like:
     * (freq=15)
     *     (freq=11)
     *         [value=a, freq=9]
     *         [value=x, freq=2]
     *     [value=e, freq=4]	
     */
	@ Override
	public String toString() {
		// HINT: Might need helper method for preOrderTraversal
		StringBuilder sb =new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
    }
	
	/**
	 * Uses the Huffman tree to decode a given sequence of bits
	 * given sequence of bits [true, false, true], return a character "o" based on Huffman tree build from
	 * "Some string you want to encode"
	 * @param coding
	 * @return
	 */
	public String decode(Boolean[] coding) {
		Node current = root;
		int expectedTotalChars = root.frequency;
		int getTotalChars = 0;
		StringBuilder sb =new StringBuilder();
		for( int i = 0; i < coding.length; i++) {
			if(current.getLeftNode() == null && current.getRightNode() == null) {
				sb.append(current.getData());
				getTotalChars++;
				current = root;
			}
			if(coding[i] == true) {  // go right
				if(current.getRightNode() != null) {
					current = current.getRightNode();
				}
				else {
					throw new IllegalArgumentException("Cannot find node on right");
				}
			}
			else if( coding[i] == false ){ // go left
				if(current.getLeftNode() != null) {
					current = current.getLeftNode();
				}
				else {
					throw new IllegalArgumentException("Cannot find node on left");
				}
			}
			else {
				throw new IllegalArgumentException("Cannot find node");
			}	
		}
		if(current.getLeftNode() == null && current.getRightNode() == null) {
			sb.append(current.getData());
			getTotalChars++;
		}
		
		if( getTotalChars != expectedTotalChars) {
			System.out.println("Invalid decoding string");
			throw new IllegalArgumentException("Invalid decoding string");
		}
		else 
		    return sb.toString();
	}
    /**
     * Input a text string, looks up the boolean sequence for each one, and return an array that concatenates all of 
     * true/false sequences
     * For Example: Input: "Some string you want to encode"
     * return: [true, true, false, ....] 
     * @param inputText
     * @return
     */
	public Boolean[] encode(String inputText) {
		StringBuilder sb =new StringBuilder();
		if(inputText.length() == 0) {
			throw new IllegalArgumentException("Cannot encode an empty string");	
		}
		for( int i=0; i<inputText.length(); i++ ) {
			sb = preOrderTraverseSearch(root, sb, inputText.charAt(i));
			if( sb.length() == 0 ) {
				throw new IllegalArgumentException("Cannot find char " + inputText.charAt(i) + " in the tree");
			}
		}
		Boolean encodeStr[] = new Boolean[sb.length()];
		for( int i=0; i < sb.toString().length(); i++) {
			if(sb.charAt(i) == '1') {
				encodeStr[i] = true;
			}
			else {
				encodeStr[i] = false;
			}
		}
		return encodeStr;
	}
	/**
	 * Similar to encode function above, but it only call Huffman tree once to build map of character to encoding 
	 * @param inputText
	 * @return
	 */
	public Boolean[] efficientEncode(String inputText) {
		// NOTE: Should only go through the tree once.	
		Dictionary<Character, String> encodingMap = new Hashtable<Character, String>();
		StringBuilder sb =new StringBuilder();
		Object booleanValue;
		
		if(inputText.length() == 0) {
			throw new IllegalArgumentException("Cannot encode an empty string");	
		}
		
		preOrderTraverseMap(root, encodingMap, sb);
		//System.out.println(">>>> Encoding map");
		//System.out.println(encodingMap.toString());
		/** encodingmap Example from text String "Some string you want to encode"
		 *  encodingMap = {r=11010, o=101, n=001, m=01110, S=11000, i=10011, g=11011,  =111, e=010, d=01111, c=10010, 
		 *                y=11001, a=01101, w=10001, u=10000, t=000, s=01100}
		 */
		sb.setLength(0);
		for(int i=0; i<inputText.length(); i++) {
			/*
			 * Given a character "r", return 11010 in this example
			 */
			booleanValue = encodingMap.get(inputText.charAt(i));
			if( booleanValue != null) {
			    sb.append(booleanValue.toString());
			}
			else {
				throw new IllegalArgumentException();
			}
		}
//		System.out.println(sb.toString());
		/* the sb.toString will be (in this example):
		 * 110001010111001011101100000110101001100111011111110011011000011110001011010010001110001011110100011001010101111010
		 */
		Boolean encodeStr[] = new Boolean[sb.length()];
		for( int i=0; i < sb.toString().length(); i++) {
			if(sb.charAt(i) == '1') {
				encodeStr[i] = true;
			}
			else {
				encodeStr[i] = false;
			}
		}
		/*
		 * return format [true, true, false ...] due to defined return code as Boolean[]
		 */
		return encodeStr;
	}
	
	public static void main(String[] args) {
		// Code to see if stuff works...
		String s = "Some string you want to encode";
        System.out.println(">>>> Input String to encode");
        System.out.println(s.toString());
		dHuffmanTree t = new dHuffmanTree(s); // Creates specific Huffman Tree for "s"
		// Now you can use encode, decode, and toString to interact with your specific Huffman Tree
  	    System.out.println(">>>> Huffman Tree");
		System.out.println(t.toString());
//		Boolean coding[] = new Boolean[]{true, false, true};
	 //   System.out.println(t.bitsToString(coding));
		Boolean decoding[] = new Boolean[]{true, true, false, false, false, true, true, false, true, true};
		System.out.println(t.decode(decoding));
		System.out.println(">>>> Encoding string using efficientEncode");
		Boolean[] encodeStr = t.efficientEncode(s);
		System.out.println(t.bitsToString(encodeStr));
//		System.out.println(t.encode(s));
//		for( int i=0; i < encodeStr.length; i++) {
//			System.out.println(encodeStr[i]);
//		}
		System.out.println(">>>> Decoding string");
		System.out.println(t.decode(encodeStr));
		System.out.println("");
		System.out.println(">>>> Encoding string using encode");
		Boolean[] encodeStr2 = t.encode(s);
		System.out.println(t.bitsToString(encodeStr2));
		System.out.println(t.decode(encodeStr2));
		
	}
}
