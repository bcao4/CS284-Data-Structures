package HW;


public class hwtest {
	public static void testIDLListAddAppendMethods(IDLList<String> myIDLList) {
        myIDLList.add("Sam");
        myIDLList.add("Bob");
        myIDLList.append("Jack");	
        myIDLList.append("Frank");
        myIDLList.add(2, "Harry");  // added item ad middle
        myIDLList.add(4, "Ellen");  // added item at middle
        myIDLList.add(0, "Chris");  // added item at head
        myIDLList.add(myIDLList.size(), "Jeff");  // added item at tail
	}
	
	public static void testIDLListMethods(IDLList<String> myDLList) {
        System.out.println("Data at Head : " + myDLList.getHead());
	    System.out.println("Data at Last : " + myDLList.getLast());
	    System.out.println("DLList size: " + myDLList.size());
	    
	    System.out.println("Before removing - DLList toString: ");
	    System.out.println(myDLList.toString());
	    System.out.println("Data remove at head: " + myDLList.remove());
	    System.out.println("Data remove at last: " + myDLList.removeLast());    
	    System.out.println("Data remove at index 3: " + myDLList.removeAt(3));
	    System.out.println("Data remove elem Bob: " + myDLList.remove("Bob"));
	    
	    System.out.println("After removing - DLList toString: ");
	    System.out.println(myDLList.toString());
	    for( int i = 0; i < myDLList.size(); i++) {
	        System.out.println("Index data: " + myDLList.get(i));
	    }
	}
	
	public static void testCase1_EmptyList() {
		System.out.println(">>>> Test Empty IDLLIst");
		IDLList<String> myIDLList = new IDLList<String>();
		testIDLListMethods(myIDLList);
	}
	
	public static void testCase2_AddAndAppend() {
		System.out.println(">>>> Test Add and Append IDLLIst");
		IDLList<String> myIDLList = new IDLList<String>();
		testIDLListAddAppendMethods(myIDLList);
		testIDLListMethods(myIDLList);
	}
	
	public static void testCase3_ErrorConditionCheck() {
		IDLList<String> myIDLList = new IDLList<String>();
		
		System.out.println(">>>> Test Error Condition");
		testIDLListAddAppendMethods(myIDLList);
		System.out.println(myIDLList.toString());

//		myIDLList.add(100, "Error");
//		myIDLList.get(100);
		if( myIDLList.remove("Steve") == false) {
			System.out.println("Cannot find elem \"Steve\"");
		}
			
	}
	
    public static void main(String[] args) {
  //      testCase1_EmptyList();
        testCase2_AddAndAppend();
  //      testCase3_ErrorConditionCheck();
    }
}
