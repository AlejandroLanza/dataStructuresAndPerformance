/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		// test short list, first contents, then out of bounds
//		System.out.println("Shortlist size: " + shortList.get(0));
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		assertEquals("Check my own", "B", shortList.get(1));
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		a = list1.remove(1);
		assertEquals("Remove: last element can be removed and is correct ", 42, a);
		
		
		// TODO: Add more tests here
		try {
			int b = list1.remove(3);
			fail("Check out of bounds");
		}catch(IndexOutOfBoundsException e){}
		
		try {
			int b = list1.remove(-1);
			fail("Check out of bounds negative");
		}catch(IndexOutOfBoundsException e){}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		shortList.add("C");
//		assertEquals("Add to end, check size is correct ", 3, shortList.size());
		assertEquals("Add to end, check 3rd char is C ", "C", shortList.get(2));
		assertEquals("Add to end, check 2nd char is still B ", "B", shortList.get(1));
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Shortlist size test", 2, shortList.size());
		assertEquals("Emptylist size test", 0, emptyList.size());
		assertEquals("Longerlist size test", 10, longerList.size());
		assertEquals("List1 size test", 3, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
//		shortList(A,B)
//		shortList.add(0, “zero”);
		shortList.add(0, "zero");
//		shortList(zero,A,B)
		assertEquals("Added zero at pos 0", "zero", shortList.get(0));
		assertEquals("Bumped A to pos 1", "A", shortList.get(1));
		
		shortList.add(2, "C");		
//		shortList(zero,A,C,B)
		assertEquals("Added C to pos 2", "C", shortList.get(2));
		assertEquals("Bumped B to pos 3", "B", shortList.get(3));

		shortList.add(4,"D");
		assertEquals("Added D to pos 4", "D", shortList.get(4));
		
		// test nullpointer
		try {
			shortList.add(3, null);
			fail("Check null element");
		}
		catch(NullPointerException e){}
		
		// test out of bounds
		try {
			shortList.add(10, "Z");
			fail("Check out of bounds add");
		}
		catch(IndexOutOfBoundsException e) {
		}
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		
		// test replacing first element
	    int a = list1.set(0, 33);
	    assertEquals("Replaced element 0 (65) with 33", (Integer)33, list1.get(0));
	    assertEquals("Replaced element 0 (65) with 33", 65, a);

	    // test nullPointer and outOfBounds
	    try {
	    	a = list1.set(-1, 33);
	    	fail("Check out of bounds negative for set");
	    }catch(IndexOutOfBoundsException e) {}

	    try {
	    	a = list1.set(3, 33);
	    	fail("Check out of bounds over for set");
	    }catch(IndexOutOfBoundsException e) {}
	    
	    // test setting the last element
	    a = list1.set(2, 100);
	    assertEquals("Replaced element 2 (42) with 100", (Integer)100, list1.get(2));
	    assertEquals("A should be 42", 42, a);

	}
	
	
	// TODO: Optionally add more test methods.
	
}
