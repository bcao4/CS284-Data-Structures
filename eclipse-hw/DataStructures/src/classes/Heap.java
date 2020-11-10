package classes;

import java.util.Arrays;
import java.util.Random;

public class Heap<E extends Comparable<E>> {
	// data fields
	private E[] data;
	private int last;
	
	// Constructors
	Heap(int size) {
		data =  (E[]) new Comparable[size];
		last = 0;
	}
	
	// Methods
	
	private void swap(int i, int j) {
		E temp = data[i];
		data[i]=data[j];
		data[j]= temp;
	}
	
	public void add(E item) {
		data[last] = item;
		int current = last;
		int parent = (current-1)/2;
		
		while (parent>=0 && data[parent].compareTo(data[current])>0) {
			swap(parent,current);
			current = parent;
			parent = (current-1)/2;
		}
		
		last++;
	}
	
	public E remove() {//from heap
		E temp = data[0];
		data[0] = data[last - 1];
		int parent = 0;
		int minChild;
		
		while (true) {
			int leftChild = (parent * 2) + 1;
			int rightChild = leftChild + 1;
			if (leftChild >= last) { // no more children
				break;
			}
			minChild = leftChild;
			if (rightChild < last && data[rightChild].compareTo(data[leftChild]) < 0) {
				minChild = rightChild;
			}
			// have at least one child and minChild points to the smallest
			if (data[minChild].compareTo(data[parent]) > 0) { 
				break;
			}
			swap(parent, minChild);
			parent = minChild;
		}
		last--;
		return temp;
	}
	
	public String toString() {
		return Arrays.toString(data);
	}
	
	public static void main(String[] args) {
		Heap<Integer> h = new Heap<>(20);
		Random r = new Random();
		
		for (int i=10; i>0; i--) {
			h.add(i);
		}
		
		System.out.println(h);
		System.out.println(h.remove());
		System.out.println(h);
	}
	
}
