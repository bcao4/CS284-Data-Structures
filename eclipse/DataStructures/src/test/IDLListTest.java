//Brandon Cao
//CS284B
//I pledge my honor that I have abided by the Stevens Honor System

package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class IDLListTest {

	
	@Test
	public void Add() {
		IDLList<Integer> l = new IDLList<Integer>();
		
		l.add(23);
		l.add(54);
		l.add(93);
		
		String result = l.toString();
		assertEquals(result, "(93, 54, 23)");
	
	}
	
	@Test
	public void Append() {
		IDLList<Integer> l = new IDLList<Integer>();
		
		l.append(23);
		l.append(54);
		l.append(93);
		
		String result = l.toString();
		assertEquals(result, "(23, 54, 93)");
	
	}
	
	@Test
	public void get() {
		IDLList<Integer> l = new IDLList<Integer>();
		
		l.add(23);
		l.add(54);
		l.add(93);
		
		assertEquals((Object)93, l.getHead());
	}	
	
	@Test
	public void getHead() {
		IDLList<Integer> l = new IDLList<Integer>();
		
		l.add(23);
		l.add(54);
		l.add(93);
		
		assertEquals((Object)93, l.getHead());
	}	
	
	
	@Test
	public void getLast() {
		IDLList<Integer> l = new IDLList<Integer>();
		
		l.add(23);
		l.add(54);
		l.add(93);
		
		assertEquals((Object)23, l.getLast());
	}	
	
	@Test
	public void remove() {
		IDLList<Integer> l = new IDLList<Integer>();
		
		l.add(23);
		l.add(54);
		l.add(93);
		
		int result = l.remove();
		assertEquals(result, 93);
		
	}
	
	@Test
	public void removeLast() {
		IDLList<Integer> l = new IDLList<Integer>();
		
		l.add(23);
		l.add(54);
		l.add(93);
		
		int result = l.removeLast();
		assertEquals(result, 23);
		
	}
	
	@Test
	public void removeAt() {
		IDLList<Integer> l = new IDLList<Integer>();
		
		l.add(23);
		l.add(54);
		l.add(93);
		
		int result = l.removeAt(1);
		assertEquals(result, 54);
		
	}
	
	@Test
	public void Add1() {
		IDLList<Integer> l = new IDLList<Integer>();
		
		l.add(23);
		l.add(54);
		l.add(93);
		l.add(2, 83);
		l.append(8);
		l.add(4,39);

		String result = l.toString();
		assertEquals(result, "(93, 54, 83, 23, 39, 8)");
	
	}
	
	public void Remove1() {
		IDLList<Integer> l = new IDLList<Integer>();
		
		l.add(23);
		l.add(54);
		l.add(93);
		l.add(2, 83);
		l.append(8);
		l.add(4,39);

		int result = l.removeAt(4);
		assertEquals(result, 39);
		
	}

}
 