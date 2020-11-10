package classes;

import java.util.Arrays;

public class MyList<E> {
	// Data fields
	private E[] data;
	private int free;
	
	// Constructor
	MyList(int length) {
		data = (E[]) new Object[length];
		free=0;
	}
	
	// Methods
	public int size() {
		return free;
	}
	
	public boolean isEmpty() {
		return free==0;
	}
	
	private void resize() {
		data = Arrays.copyOf(data,data.length*2);
	}

	public boolean add(E item) {
		if (free==data.length) {
			resize();
		}	
		data[free]=item;
		free++;
		return true;
	}
	
	public boolean add(E item, int index) {
		if (free==data.length) {
			resize();
		}
		if (index<0 || index>free) {
			throw new IllegalArgumentException();
		}
		// complete code: shift then insert
		
		for (int i=free; i>index; i--) {
			data[i]=data[i-1];
		}
		data[index]=item;
		free++;
		return true;
		
	}
	
	public boolean member(E item) {
		boolean found=false;
		for (int i=0; i<free && !found; i++) {
			found = found || data[i].equals(item);
		}
		return found;
	}

	public boolean member2(E item) {
		for (int i=0; i<free; i++) {
			if (data[i].equals(item))
			{ return true;}
		}
		return false;
	}
	
	/**
	 * Removes item at index index.
	 * @param index
	 * @return The contents of the item at index index, if removal successful
	 */
	public E remove(int index) {
		if (index<0 || index>=free) {
			throw new IllegalArgumentException();
		}
		
		E temp = data[index];
		
		for (int i=index; i<free-1; i++) {
			data[i] = data[i+1];
		}
		free--;
		return temp;
	}
	
	/**
	 * Removes the first occurrence of item 
	 * @param item Item to be removed
	 * @return True if the item was removed; false if it wasn't present
	 */
	public boolean remove(E item) {

		int index=0;
		for (int i=0; i<free && !data[i].equals(item); i++ ) {
			index++;
		}
		if (index==free) { // item is not in the list
			return false;
		} else {			// item is in the list
			this.remove(index);
			return true;
		}
		
		
	}
	
	
	public String toString() { 
		return Arrays.toString(data);
	}
	
	public static void main(String[] args) {
		MyList<Integer> l = new MyList<Integer>(20);
		
		for (int i=1;i<21;i++) {
			l.add(i);
		}
		l.add(12,3);
		System.out.println(l);
		System.out.println(l.member(12));
		System.out.println(l.member(24));
		l.remove(19);
		System.out.println(l);
		l.remove(7);
		System.out.println(l);
	}
	
	
}
