package assign08;

/**
 * This class represents a better dynamic array of integers, doubling the length of
 * the backing array when more space is needed and never shrinking.
 * 
 * @author Prof. Parker and Shawn Zhang
 * @version November 9, 2023
 */
public class BetterDynamicArray {

	private int[] elements; // the backing array
	private int elementCount; // the number of elements

	/**
	 * Creates a dynamic array with space for ten elements, but zero spaces occupied.
	 * 
	 * 
	 */
	public BetterDynamicArray() {
		elements = new int[10];
		elementCount = 0;
	}

	/**
	 * Appends the given integer to end of this dynamic array.
	 * 
	 * @param value - the integer to append
	 */
	public void append(int value) {
		 insert(elementCount, value);
	}

	/**
	 * Inserts a given integer into this dynamic array at a given index.
	 * 
	 * @param index - the index at which to insert
	 * @param value - the integer to insert
	 * @throws IndexOutOfBoundsException if the given index is out of bounds
	 */
	public void insert(int index, int value) {	
		if (index < 0 || index > elementCount) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + elementCount);
        }
		
		if(elementCount == elements.length) 
			doubleBackingArray();

		for (int i = elementCount; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = value;
        elementCount++;
	}
	
	/**
	 * Creates a new array with twice the length as the backing array.
	 * Copies all elements from the backing array to the new array.
	 * Sets the backing array reference to the new array.
	 */
	private void doubleBackingArray() {
		int[] largerArray = new int[elements.length * 2];
		for(int i = 0; i < elements.length; i++) 
			largerArray[i] = elements[i];			
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
		if (index < 0 || index >= elementCount) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + elementCount);
        }

        return elements[index];
	}

	/**
	 * Returns the number of elements in this dynamic array.
	 * 
	 * @return the number of elements
	 */
	public int size() {

		 return elementCount;
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
		if (index < 0 || index >= elementCount) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + elementCount);
        }

        elements[index] = value;
	}

	/**
	 * Deletes the integer at the given index from this dynamic array. 
	 * 
	 * @param index - the index of the element to delete
	 * @throws IndexOutOfBoundsException if the given index is out of bounds
	 */
	public void delete(int index) {
		
		if (index < 0 || index >= elementCount) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + elementCount);
        }

		for (int i = index; i < elementCount - 1; i++) {
            elements[i] = elements[i + 1];
        }
		
		elementCount--;
		
		elements[elementCount] = 0;//keeps out unnecessary refresh.
	}
	
	/**
	 * Sorts the elements of this dynamic array from smallest to largest.
	 */
	public void sort() {
        java.util.Arrays.sort(elements, 0, elementCount);
	}

	/**
	 * Generates a textual representation of this dynamic array.
	 * 
	 * @return the textual representation
	 * 
	 * DO NOT MODIFY THIS METHOD
	 */
	public String toString() {
		String result = "[";
		if(size() > 0) 
			result += getElement(0);
		
		for(int i = 1; i < size(); i++) 
			result += ", " + getElement(i);
		
		return result + "] backing array length: " + elements.length;
	}
}