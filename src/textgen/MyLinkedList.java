package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.head = new LLNode<E>(null);
		this.tail = new LLNode<E>(null);
		this.head.next = this.tail;
		this.tail.prev = this.head;
		this.size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		LLNode<E> penultimateNode = tail.prev;
		LLNode<E> newNode = new LLNode<E>(element, penultimateNode, tail);
		penultimateNode.next = newNode;
		tail.prev = newNode;
		size++;
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
//		System.out.println("here");
		if (index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid position selected");
			
		}
		LLNode<E> myNode = head;
		for (int i = 0; i <= index; i++) {
			myNode = myNode.next;
		}
		return myNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		
		// throw nullpointerexception if element is null
		if (element == null) {
			throw new NullPointerException("Cannot add null element");
		}
		
		// throw out of bounds when applicable
		if (index > this.size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid position selected");
		}
		
		LLNode<E> nodeToReplace = head;
		for (int i = 0; i <= index; i++) {
			nodeToReplace = nodeToReplace.next;
		}
		LLNode<E> newNode = new LLNode<E>(element, nodeToReplace.prev, nodeToReplace);
		nodeToReplace.prev.next = newNode;
		nodeToReplace.prev = newNode;
		size ++;		
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid position selected");
		}
		LLNode<E> nodeToRemove = head;
		for (int i = 0; i <= index; i++) {
			nodeToRemove = nodeToRemove.next;
		}
		nodeToRemove.prev.next = nodeToRemove.next;
		nodeToRemove.next.prev = nodeToRemove.prev;
		
		size --;	
		
		return nodeToRemove.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		// throw NullPointerException if element is null
		if (element == null) {
			throw new NullPointerException("Cannot add null element");
		}
		
		// throw out of bounds when applicable
		if (index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid position selected");
		}
				
		LLNode<E> nodeToReplace = head;
		for (int i = 0; i <= index; i++) {
			nodeToReplace = nodeToReplace.next;
		}
		
		E replacedItem = nodeToReplace.data;
		nodeToReplace.data = element;
		return replacedItem;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
//		this.prev = new LLNode<E>(null);
//		this.next = new LLNode<E>(null);
	}
	
	public LLNode(E e, LLNode<E> prev, LLNode<E> next) {
		this.data = e;
		this.prev = prev;
		this.next = next;
		
	}

}
