package classes;

import java.util.EmptyStackException;

public class StackNode<E> implements StackInterface<E> {

	public class Node<F> {
		// Data fields
		private F data;
		private Node<F> next;
		
		public Node(F data) {
			super();
			this.data = data;
		}
		public Node(F data, Node<F> next) {
			super();
			this.data = data;
			this.next = next;
		}
	}
	
	// Data fields
	private Node<E> stack;
	private int size;
	
	// Constructor
	StackNode() {
		stack=null;
		size=0;
	}
	
	// Methods
	
	public E push(E item) {
		stack = new Node<E>(item,stack);
		size++;
		return item;
	}
	
	public E peek() {
		if (size==0) {
		  throw new EmptyStackException();	
		  }
		return stack.data;
	}
	
	public E pop() {
		if (size==0) {
			throw new EmptyStackException();
		}
		E temp = stack.data;
		stack = stack.next;
		size--;
		return temp;
	}
	
	public boolean empty() {
		return size==0;
	}
	
	public int size() {
		return size;
	}
	
	
	}
	
	
