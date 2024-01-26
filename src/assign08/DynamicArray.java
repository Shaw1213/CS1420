package assign08;

import java.util.Arrays;

/**
 * This class represents a dynamic array of integers.
 * 
 * A dynamic array behaves like a regular array that uses [], except it can grow
 * and shrink, as needed. Like a regular array, it is indexed beginning with 0.
 * When a DynamicArray object is created, it is empty.
 * 
 * @author Prof. Parker
 * @version October 25, 2023
 * 
 * DO NOT MODIFY THIS FILE
 */
public class DynamicArray {

	private int[] elements; // the backing array

	/**
	 * Creates an empty dynamic array.
	 */
	public DynamicArray() {
		elements = new int[0];
	}

	/**
	 * Appends the given integer to end of this dynamic array.
	 * 
	 * @param value - the integer to append
	 */
	public void append(int value) {
		insert(elements.length, value);
	}

	/**
	 * Inserts a given integer into this dynamic array at a given index.
	 * 
	 * @param index - the index at which to insert
	 * @param value - the integer to insert
	 * @throws IndexOutOfBoundsException if the given index is out of bounds
	 */
	public void insert(int index, int value) {
		// Ensure the index is valid.
		if(index < 0 || index > elements.length)
			throw new IndexOutOfBoundsException("Index " + index + 
					"is invalid for adding to this array with length " + elements.length);

		// Adding a new element requires more space, so make a new array.
		int[] largerArray = new int[elements.length + 1];

		// Copy elements from the backing array to the new array, up to the given index.
		for(int i = 0; i < index; i++) 
			largerArray[i] = elements[i];
		
		// Insert the new element at index.
		largerArray[index] = value;

		// Copy the remaining elements from the backing array to the new array, but 
		// shifted up one array slot.
		for(int i = index; i < elements.length; i++) 
			largerArray[i + 1] = elements[i];
	
		// Set the backing array reference to the new array.
		elements = largerArray;
	}

	/**
	 * Gets the integer stored in this dynamic array at the given index.
	 * 
	 * @param index - the index of the element to get
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException if the given index is out of bounds
	 */
	public int getElement(int index) {
		if(index < 0 || index >= elements.length)
			throw new IndexOutOfBoundsException(
					"Index " + index + "is invalid for this array with length " + elements.length);

		return elements[index];
	}

	/**
	 * Returns the number of elements in this dynamic array.
	 * 
	 * @return the number of elements
	 */
	public int size() {
		return elements.length;
	}

	/**
	 * Sets (i.e., changes) the integer stored in this dynamic array at the given index
	 * to the given integer.
	 * 
	 * @param index - the index of the element to set
	 * @param value - the new integer value for setting the element
	 * @throws IndexOutOfBoundsException if the given index is out of bounds
	 */
	public void setElement(int index, int value) {
		if(index < 0 || index >= elements.length)
			throw new IndexOutOfBoundsException(
					"Index " + index + "is invalid for this array with length " + elements.length);

		elements[index] = value;
	}

	/**
	 * Deletes the integer at the given index from this dynamic array. 
	 * 
	 * @param index - the index of the element to delete
	 * @throws IndexOutOfBoundsException if the given index is out of bounds
	 */
	public void delete(int index) {
		if(index < 0 || index >= elements.length)
			throw new IndexOutOfBoundsException(
					"Index " + index + "is invalid for this array with length " + elements.length);

		// Deleting an element requires less space, so make a new array.
		int[] smallerArray = new int[elements.length - 1];

		// Copy elements from the backing array to the new array, up to the given index.
		for(int i = 0; i < index; i++) 
			smallerArray[i] = elements[i];

		// Copy the elements from the backing array to the new array, but 
		// shifted down one array slot.  This overwrites the deleted element.
		for(int i = index + 1; i < elements.length; i++) 
			smallerArray[i - 1] = elements[i];
		
		// Set the backing array reference to the new array.
		elements = smallerArray;
	}
	
	/**
	 * Sorts the elements of this dynamic array from smallest to largest.
	 */
	public void sort() {
		Arrays.sort(elements);
	}

	/**
	 * Generates a textual representation of this dynamic array.
	 * 
	 * @return the textual representation
	 */
	public String toString() {
		String result = "[";
		if(elements.length > 0) 
			result += elements[0];
		for(int i = 1; i < elements.length; i++) 
			result += ", " + elements[i];
		return result + "]";
	}
}