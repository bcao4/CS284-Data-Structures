package classes;

import java.util.Arrays;

import com.sun.org.apache.bcel.internal.generic.SWAP;

public class Sort {

	private static int[] merge(int[] a, int[] b) {
		   int[] c = new int[a.length+b.length];
		   int ia=0;
		   int ib=0;
		   int ic=0;
		   
		   while (ia<a.length && ib<b.length) {
			   if (a[ia]<b[ib]) {
				   c[ic] = a[ia];
				   ia++;
			   } else {
				   c[ic] = b[ib];
				   ib++;
			   }
			   ic++;
		   }
		   
		   while (ia<a.length) {
			   c[ic]=a[ia];
			   ia++;
			   ic++;
		   }
		   
		   while (ib<b.length) {
			   c[ic]=b[ib];
			   ib++;
			   ic++;
		   }
		   
		   
		   
		   return c;
	}
	
	public static <E extends Comparable<E>> void qs(E[] a) {
		qs(a, 0, a.length - 1);
	}
	
	private static <E extends Comparable<E>> void swap(E[] a, int i, int j) {
		E temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	private static <E extends Comparable<E>> int partition(E[] a, int first, int last) {
		int up = first;
		int down = last;
		int pivot = first;
		
		do {
			while(up < down && a[up].compareTo(a[pivot]) <= 0 ) { up++; }
			while(a[down].compareTo(a[pivot])>0) {down--;}
			if(up<down) {swap(a, up, down);}
		} while (up < down);
		
		swap(a, pivot, down);
		return down;
	}

	private static <E extends Comparable<E>> void qs(E[] a, int first, int last) {
		if (first < last) { // There is data to be sorted. 
			int pivIndex = partition(a, first, last); 
			qs(a, first, pivIndex - 1); 
			qs(a, pivIndex + 1, last);
		}
		}
	
	public static <E extends Comparable<E>> int partition2(E[] a, int first, int last) {

        if (a.length<=1) {
               return 0;
        }
        int pivot = first;
        int down = last;
        int up = first+1;
        do {

               while (a[down].compareTo(a[pivot])>=0) {
                      down--;
               }

               swap(a, pivot, down);
               while (a[up].compareTo(a[pivot])<0) {
                      up++;
               }
        } while (up<down);
        swap(a, pivot, down);
        int x = (a.length/2)-1;
        swap(a, x-1, x+1);
        partition(a, 0, x-2);
        partition(a, x+2, a.length-1);
        return down;

  }
	
//	public static <E extends Comparable<E>> int partition3(E[] a, int first, int last) {
//		int pivot = first; 
//		int oppEnd = last;
//		int incr = -1;
//		
//		while (pivot != oppEnd) {
//			if (incr == -1 ? a[oppEnd].compareTo(a[pivot]) > 0:(a[oppEnd].compareTo(a[pivot]))) {
//				oppEnd = oppEnd + incr;			
//			} else {
//				swap(a.pivot, oppEnd);
//				
//			}
//		}
//	}

	
	public static void main(String[] args) {
		Integer[] a = {10, 7, 6 , 5};
		System.out.println(Arrays.toString(a));
		
	}
}
