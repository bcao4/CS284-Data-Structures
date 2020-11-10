package classes;

public class PalindromeChecker {
	// Data fields
	private String inputString;
	private StackInterface<Character> charStack;
	
	// Constructor
	public PalindromeChecker(String str) {
		inputString = str;
		charStack = new StackNode<Character>();
		fillstack();
	}
	
	// Methods
	private void fillstack() {
		for (int i=0; i<inputString.length(); i++) {
			charStack.push(inputString.charAt(i));
		}
	}
	
	private String buildReverse() {
		StringBuilder s = new StringBuilder();
		
		while (!charStack.empty()) {
			s.append(charStack.pop());
		}
		
		return s.toString();
	}
	
	public boolean isPalindrome() {
		return inputString.replaceAll("\\s+","").equalsIgnoreCase
				(this.buildReverse().replaceAll("\\s",""));
	}
	
	public static void main(String[] args) {
		PalindromeChecker p1 = new PalindromeChecker("kayak");
		System.out.println(p1.isPalindrome());
		
		PalindromeChecker p2 = new PalindromeChecker("kayaK");
		System.out.println(p2.isPalindrome());
		
		PalindromeChecker p3 = new PalindromeChecker("k ayaK");
		System.out.println(p3.isPalindrome());
		
		
		
	}
}
