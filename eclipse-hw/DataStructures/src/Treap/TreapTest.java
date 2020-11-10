package Treap;

import static org.junit.Assert.*;
import org.junit.Test;

public class TreapTest {

	@Test
	public void test() {
		Treap<Integer> testTree = new Treap<Integer>();
		assertTrue(testTree.add(8, 10));
		assertTrue(testTree.add(9, 12));
		assertTrue(testTree.add(5, 18));
		assertTrue(testTree.add(4, 13));
		assertFalse(testTree.add(8, 17));
		assertTrue(testTree.find(4));
		assertTrue(testTree.delete(4));
		assertFalse(testTree.delete(20));
		assertFalse(testTree.find(4));
	}
	
	@Test
	public void add() {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(3,29);
		testTree.add(7,17);
		testTree.add(2,9);
		testTree.add(11,5);
		System.out.println(testTree.toString());
	}

}
