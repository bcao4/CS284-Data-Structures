package HW;

//import java.util.Arrays;

public class App {
	public static void main(String[] args) {
		BinaryNumber binaryNum1 = new BinaryNumber(6);
		BinaryNumber binaryNum2 = new BinaryNumber("11011");
		BinaryNumber binaryNum3 = new BinaryNumber("11110");
		System.out.println("hello world");
		System.out.println("Add two number: " + binaryNum2.toString() + " + " + binaryNum3.toString() );
		binaryNum2.add(binaryNum3);
		System.out.format("Sum: Binary: %s, Decimal: %d\n", binaryNum2.toString(), binaryNum2.toDecimal());


	}
}
