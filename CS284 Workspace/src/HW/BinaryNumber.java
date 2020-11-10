package HW;

//Brandon Cao
//CS284B
//I pledge my honor that I have abided by the Stevens Honor System

import java.util.Arrays;

public class BinaryNumber {

    //Data fields
    private int data[];
    private boolean overflow;

    //Constructor

    /* Creates a binary number consisting only of zeros. */
    public BinaryNumber(int length) {
        data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = 0;
        }
    }

    /* Creates a binary number given a string. */
    public BinaryNumber(String str) {
        data = new int [str.length()];
        for (int i = 0; i < str.length(); i++) {
            data[i] = Character.getNumericValue(str.charAt(i));
        }
    }

    //Operations

    /* Getter. Determines the length of a binary number. */

     public int getLength() {
         return data.length;
     }

     /* Obtains a digit of a binary number given an index.
     The starting index is 0. If the index is out of bounds,
     then a message should be printed on the screen indicating this fact. */

     public int getDigit(int index) {
         if (index >= data.length || index < 0) {
             System.out.println("Index is out of bounds!");
             return -1;
         }
        else {
            return data[index];
        }
     }

    /* Private method that makes room for a new digit. */

    private void reallocate(int amount) {
        this.data = Arrays.copyOf(this.data, this.data.length + amount);
    }

     /* Shifts all digits in a binary number any number of places to
    the right, as indicated by a parameter amountToShift. The new
    digit should be 0. */

    public void shiftR(int amount) {
        int original_num = this.getLength();
        reallocate(amount);
        for (int i = original_num - 1; i >= 0; i--) {
            data[i + amount] = data[i];
            data[i] = 0;
        }

    }

    /* Adds two binary numbers, one is the binary number that receives the message
    and the other is given as a parameter. If the lengths of the binary numbers do
    not coincide, then a message should be printed on the screen indicating this fact. */

    public void add(BinaryNumber aBinaryNumber) {
        int carried = 0;
        int i = 0;
        if (this.getLength() != aBinaryNumber.getLength()) {
            System.out.println("Lengths of binary numbers do not match!");
        }
        else {
            for (i = 0; i < this.getLength(); i++) {
                if (this.data[i] + aBinaryNumber.data[i] + carried == 0) {
                    data[i] = 0;
                    carried = 0;
                }
                else if (this.data[i] + aBinaryNumber.data[i] + carried == 1) {
                    data[i] = 1;
                    carried = 0;
                }
                else if (this.data[i] + aBinaryNumber.data[i] + carried == 2) {
                    data[i] = 0;
                    carried = 1;
                }
                else {
                    data[i] = 1;
                    carried = 1;
                }
            }
            if ((i == 31) && carried == 1) {
                overflow = true;
            }
            if (carried == 1) {
                data= Arrays.copyOf(this.data, this.getLength() + 1);
                data[data.length - 1] = 1;
            }
        }

    }

    /* Transforms a binary number to a String */

    public String toString() {
        String s = "";
        if (overflow == true) {
            return "Overflow";
        }
        else {
            for (int i = 0; i < this.getLength(); i++) {
                s += data[i];
            }
            return s;
        }
    }

     /*  Transforms a binary number to its decimal notation. */

    public int toDecimal () {
        int total = 0;
        for (int i = 0; i < this.getLength(); i++) {
            total = (int) (total + this.getDigit(i) * Math.pow(2, i));
        }
        return total;
    }


    /* Clears the overflow flag */

    public void clearOverflow() {
        overflow = false;
    }



}

