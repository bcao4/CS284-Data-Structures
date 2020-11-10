package Huffman;

import static org.junit.Assert.*;

import org.junit.Test;

public class HuffmanTest {

	@Test
	public void test() {
		String s = "HuffmanTree Test";
		HuffmanTree t = new HuffmanTree(s);
		System.out.println(s.toString());
		System.out.println(t.toString());
		Boolean coding[] = new Boolean[]{true, false, true};
		assertEquals("101", t.bitsToString(coding));
	}
	
	@Test
	public void test2() {
		String s = "HuffmanTree Test";
		HuffmanTree t = new HuffmanTree(s);
		Boolean[] str = t.encode(s);
		assertEquals(s, t.decode(str));
	}
	
	@Test
	public void test3() {
		String s = "HuffmanTree Test";
		HuffmanTree t = new HuffmanTree(s);
		Boolean[] effStr = t.efficientEncode(s);
		assertEquals(s, t.decode(effStr));
	}	

}
