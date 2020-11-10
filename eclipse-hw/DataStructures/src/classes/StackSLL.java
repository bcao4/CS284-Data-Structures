package classes;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackSLL<E> implements StackInterface<E> {
	// Data fields
	private SingleLL<E> stack;
	
	// Constructor
	StackSLL() {
		stack = new SingleLL<E>();
	}
	
	// Methods
	public E push(E item) {
		stack.addFirst(item);
		return item;
	}
	
	public E pop() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.removeFirst();
	}

	public E peek() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.get(0);
	}
	
	public boolean empty() {
		return stack.isEmpty();
	}
	
	public int size() {
		return stack.size();
	}
	
	public String toString() {
		return stack.toString();
	}

	public static ArrayList<Integer> toBinary(int i) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		StackSLL<Integer> binaryStack = new StackSLL<Integer>();

		if (i==0) {
			result.add(0);
			return result;
		}

		while(i>0) {
			binaryStack.push(i%2);
			i=i/2;
		}

		int size = binaryStack.size();
		for (int j =0; j<size; j++) {
			result.add(binaryStack.pop());
		}

		return result;

	}
	
	public static void main(String[] args) {
		StackSLL<Integer> s = new StackSLL<Integer>();
		
		for (int i=0; i<10; i++) {
			s.push(i);
		}
		
		System.out.println(s);
		s.pop();
		System.out.println(s);
	}
	
}
