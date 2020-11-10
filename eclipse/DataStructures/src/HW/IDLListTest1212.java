package HW;
import static org.junit.Assert.*;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


//import static org.junit.Assert.*;

public class IDLListTest1212 {
    IDLList<String> myIDLList = new IDLList<String>();


    @org.junit.Test
    public void append() {
        myIDLList.add("Sam");
        myIDLList.add("Bob");
        myIDLList.append("Jack");
        System.out.println(myIDLList.toString());
        System.out.println(myIDLList.getLast());
        assertEquals("Jack", myIDLList.getLast());
    }

    @org.junit.Test
    public void get() {
        IDLList<Integer> mpIDLList = new IDLList<Integer>();
        mpIDLList.add(23);
        mpIDLList.add(1, 43);
        mpIDLList.add(2, 53);
        System.out.println(mpIDLList.toString());
        System.out.println(mpIDLList.get(1));
        assertEquals((Object)43, mpIDLList.get(1));
     }


    @org.junit.Test
    public void getLast() {
        myIDLList.add("Sam"); // added item at head
        myIDLList.add("Bob"); // added item at head
        myIDLList.append("Jack");
        myIDLList.append("Frank");
        myIDLList.add(2, "Harry");  // added item ad middle
        myIDLList.add(4, "Ellen");  // added item at middle
        myIDLList.add(0, "Chris");  // added item at head
        myIDLList.add(myIDLList.size(), "Jeff");  // added item at tail
        System.out.println(myIDLList.toString());
        assertEquals("Jeff", myIDLList.getLast());
    }


    @org.junit.Test
    public void removeLast() {
        myIDLList.add("Sam");
        myIDLList.add("Bob");
        myIDLList.append("Jack");
        myIDLList.append("Frank");
        myIDLList.add(2, "Harry");  // added item ad middle
        myIDLList.add(4, "Ellen");  // added item at middle
        myIDLList.add(0, "Chris");  // added item at head
        myIDLList.add(myIDLList.size(), "Jeff");  // added item at tail
        assertEquals("Jeff", myIDLList.removeLast());
    }


    @org.junit.Test
    public void remove1() {
        myIDLList.add("Sam");
        myIDLList.add("Bob");
        myIDLList.append("Jack");
        myIDLList.append("Frank");
        myIDLList.add(2, "Harry");  // added item ad middle
        myIDLList.add(4, "Ellen");  // added item at middle
        myIDLList.add(0, "Chris");  // added item at head
        myIDLList.add(myIDLList.size(), "Jeff");  // added item at tail
        assertEquals(true, myIDLList.remove("Frank"));

    }
    
}