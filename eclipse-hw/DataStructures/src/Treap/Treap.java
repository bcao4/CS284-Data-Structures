//Brandon Cao
//CS284B
//I pledge my honor that I have abided by the Stevens Honor System

package Treap;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	
	private static class Node<E>{

		/**
		 * Data fields
		 */
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E> left; 
		public Node <E> right;
		
		/**
		 * Constructors
		 * Creates a new node with the given data and priority. 
		 * The pointers to child nodes are null. 
		 * Throw exceptions if data is null.
		 */
		public Node(E data, int priority) {
			if (data == null) {
				throw new NullPointerException();
			} 
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}
		
		/**
		 * Methods
		 */
		public Node<E> rotateRight(){
			Node<E> head = this.left;
			Node<E> temp = head.right;
			head.right = this;
			this.left = temp;
			return head;
		}
		
		public Node<E>rotateLeft(){
			Node<E> head = this.right;
			Node<E> temp = head.left;
			head.left = this;
			this.right = temp;
			return head;
		}
		
		@Override
		public String toString() {
			return "(key=" + data.toString() + ", priority=" + priority +")";
		}
	
	}
	
	/**
	 * Data fields
	 */
	private Random priorityGenerator;
	private Node<E> root;
	
	/**
	 * Constructors
	 */
	public Treap() {
		root = null;
		priorityGenerator = new Random();
	}
	
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
	}
	
	/**
	 * Add operations. The method returns true, if a node with the key was successfully 
	 * added to the treap. If there is already a node containing the given key, the 
	 * method returns false and does not modify the treap. 
	 * Insert the new node as a leaf of the tree at the appropriate position according to the ordering on E, just like in any BST.
	 * Maintains max heap priority by bubbling up using rotation methods
	 */
	
	boolean add (E key) {
		return add(key, priorityGenerator.nextInt());
	}
	
	boolean add (E key ,int priority) {
		Node<E> temp = new Node<E>(key, priority);
		Node<E> current;
		Stack<Node<E>> heapStack = new Stack<Node<E>>();
		
		if (root == null) {
			root = temp;
			return true;
		}
		current = root;
		while (current != null) {
			heapStack.push(current);
			if (current.data == key) {
				return false;
			} else if (current.data.compareTo(key) > 0) {
				if(current.left != null) {
				    current = current.left;
				} else {
					break;
				}	
			} else {
				if (current.right != null) {
					current = current.right;
				} else {
					break;
				}
			}
		}
		if (current.data.compareTo(key) > 0) {
			current.left = temp;
		} else {
			current.right = temp;
		}
		root = reheap(heapStack);
		return true;
	}
	
	/*
	 * Helper function used to restore the heap invariant
	 */
	public Node<E> reheap(Stack<Node<E>> stack) {
		Node<E> current = stack.pop();
		Node<E> temp = null;
		while (current != null) {
			temp = current;
			if (current.left != null && current.left.priority > current.priority) {
				temp = current.rotateRight();
			} else if (current.right != null && current.right.priority > current.priority) {
				temp = current.rotateLeft();
			}
			if (stack.isEmpty() == false) {
				current = stack.pop();
				if (current.data.compareTo(temp.data) < 0) {
					current.right = temp;
				} else {
					current.left = temp;
				}
			} else {
				break;
			}	
		}
		return temp;
		
	}
	
	/*
	 * Delete operation. Deletes the node with the given key from the treap 
	 * and returns true. If the key was not found, the method does not modify 
	 * the treap and returns false. 
	 */
 	private Node<E> delete(Node<E> parent, E key) {
		if (parent == null) {
			return parent;
		} else if (parent.data.compareTo(key) > 0) {
			parent.left = delete(parent.left, key);
		} else if (parent.data.compareTo(key) < 0) {
			parent.right = delete(parent.right, key);
		} else {
			if (parent.left == null && parent.right == null) {
				return null;
			} else if (parent.left == null) {
				parent = parent.right;
			} else if (parent.right == null) {
				parent = parent.left;
			} else {
				if (parent.right.priority > parent.left.priority) {
					parent = parent.rotateLeft();
					parent.left = delete(parent.left, key);
				} else {
					parent = parent.rotateRight();
					parent.right = delete(parent.right, key);
				}
			}
		}
		return parent;
	}
	
	boolean delete (E key) {
		if (find(key) == false) {
			return false;
		} else {
			root = delete(root, key);
			return true;
		}
		
		
	}
	
	/*
	 * Find operations. 
	 * Finds a node with the given key in the treap rooted at root and returns true if it finds it and false otherwise.
	 * Finds a node with the given key in the treap and returns true if it finds it and false otherwise.
	 */
	private boolean find(Node<E> root, E key, Stack<Node<E>> nodeStack) {
		Node<E> current = root;
		if (root == null) {
			return false;
		} else {
			while(current != null) {
				nodeStack.push(current);
				if(current.data.compareTo(key) > 0) {
					current = current.left;
				} else if (current.data.compareTo(key) < 0){
					current = current.right;
				} else if (current.data == key) { 
					return true;
				} else {
					return false;
				}
				
			}
		}
		return false;
	}
	
	boolean find(E key) {
		Stack<Node<E>> nodeStack = new Stack<Node<E>>();
		return find(root, key, nodeStack);
	}
	
	
	private void preOrderTraverse(Node<E> node,int depth,StringBuilder sb) {
		for(int i = 1; i < depth; i++) {
			sb.append("  ");
		}
		if(node ==null) {
			sb.append("null\n");
		} else {
			sb.append(node.toString());
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
		
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb =new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(8,174);
		testTree.add(9,943);
		testTree.add(4,19);
		testTree.add(2,31);
		testTree.add(6,70);
		testTree.add(1,84);
		testTree.add(3,12);
		testTree.add(5,83);
		testTree.add(7,26);
		//System.out.println(testTree.toString());
		//System.out.println(testTree.find(4));
	    testTree.delete(8);
	    System.out.println(testTree.toString());
	}
	

}
