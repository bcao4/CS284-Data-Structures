package classes;

import com.sun.xml.internal.bind.v2.model.core.ID;

public class Recurrsion {
	
	public static double fib(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return fib(n - 1) + fib(n - 2);
		}
	}
	
	public static double fact(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n * fact(n-1);
		}
	}
	
	public static double ffib(int n, double old, double current) {
		if (n<=1) {
			return current;
		} else {
			return ffib(n-1, current, old + current);
		}
	}
	
	public static double iterative_ffib(int n) {
		double old = 1;
		double current = 1;
		int i = n;
		
		while(i>1) {
			current = current+old;
			old = current-old;
			i--;
		}
		
		return current;
		
	}
	
	public static void main(String[] args) {
		System.out.println(fib(10));
		System.out.println(fact(5));
		System.out.println(ffib(200, 1, 1));
		System.out.println(iterative_ffib(200));
	}

}
