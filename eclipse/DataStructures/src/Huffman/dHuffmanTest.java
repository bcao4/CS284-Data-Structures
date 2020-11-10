package Huffman;

import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.junit.Test;

public class dHuffmanTest {

	@Test
	public void test() {
		String s = "Some string you want to encode";
		System.out.println(">>>> Generate Huffman tree from string: \"" + s +"\"");
		System.out.println(s.toString());
		HuffmanTree t = new HuffmanTree(s); // Creates specific Huffman Tree for "s"
  	    
		System.out.println(">>>> Test method toString() on Huffman Tree");
		System.out.println(t.toString());
		
		System.out.println(">>>> Test method bitsTotring()");
		Boolean coding[] = new Boolean[]{true, false, true};
		System.out.println(t.bitsToString(coding));
		assertEquals("Expect: 101", "101", t.bitsToString(coding));
		
		System.out.println(">>>> Test method encode()");
		System.out.println(">>>> Encoding string: \"" + s + "\"");
		Boolean[] encodeStr = t.encode(s);
		System.out.println(">>>> Test method decode()");
		assertEquals(s, t.decode(encodeStr));
		
		System.out.println(">>>> Test method efficientEncode()");
		Boolean[] efficientEncodeStr = t.efficientEncode(s);
		assertEquals(s, t.decode(efficientEncodeStr));
	}
}
/*	@Test
	public void testErrorHandle() {
		String s = "Some string you want to encode";
		HuffmanTree t = new HuffmanTree(s); // Creates specific Huffman Tree for "s"
		System.out.println("");
		System.out.println(">>>> Test invalid input <<<<");
		
		String invalidString = "";
		
		try {
		    HuffmanTree invalidTree = new HuffmanTree(invalidString);
		    System.out.println(invalidTree.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(">>>> Catch to build a tree from an empty string");
		}
		
		try {
		    Boolean[] encodeStr = t.encode(invalidString);
		} catch (IllegalArgumentException e) {
			System.out.println(">>>> Catch encode an empty string using method encode()");
		}
		
		try {
		    Boolean[] encodeStr = t.efficientEncode(invalidString);
		} catch (IllegalArgumentException e) {
			System.out.println(">>>> Catch encode an empty string using method efficientEncode()");
		}
		
		Boolean coding[] = new Boolean[]{};
		String bitString = t.bitsToString(coding);
		System.out.println(">>>> bitString: " + bitString);
		assertNotEquals("Expect empty", "1", t.bitsToString(coding));
		
		Boolean[] invalidEncodeStr = new Boolean[]{true, false, true, true, true, false};
		try {
		   assertEquals(s, t.decode(invalidEncodeStr));
		} catch (IllegalArgumentException e) {
			System.out.println(">>>> Catch invalid input string to decode");
		}
	}

}*/
