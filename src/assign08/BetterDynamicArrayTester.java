package assign08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the BetterDynamicArray class. 
 * 
 * @author Prof. Parker and Shawn Zhang
 * @version  November 9, 2023
 */
public class BetterDynamicArrayTester {   
    
	private static BetterDynamicArray threeInts;
	
	// This code executes before each test.
	// You can reference threeStrings in your tests without having to create it.
	@BeforeEach
	public void setup(){
		threeInts = new BetterDynamicArray();
		threeInts.append(9);
		threeInts.append(0);
		threeInts.append(4);
	}
       
    @Test
    public void testConstructor() {
        BetterDynamicArray array = new BetterDynamicArray();
        assertEquals(0, array.size(), "Constructor did not yield a 0-sized dynamic array");
        assertEquals("[] backing array length: 10", array.toString(), 
        		"Constructor did not yield the correct dynamic array (via toString)");
    }
        
    @Test
    public void testConstructorCreatesDistinctArrays() {
        BetterDynamicArray array = new BetterDynamicArray();
        BetterDynamicArray otherArray = new BetterDynamicArray();
        otherArray.append(-5);
        assertEquals(0, array.size(), 
        		"Appending an element to one DynamicArray object changed the size of a different DynamicArray object");
    }
    
    @Test
    public void testAppendSimple() {
        String expected = "[9, 0, 4] backing array length: 10";
        assertEquals(expected, threeInts.toString(), "Failed appending 3 elements to empty dynamic array");
        assertEquals(3, threeInts.size(), "Incorrect size after appending 3 elements to dynamic array");
    }
    
    @Test
    public void testAppendLarger() {
        // Appending >= 10 elements tests the double-length growth of a dynamic array.
        int[] largeArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BetterDynamicArray array = new BetterDynamicArray();
        for(int elem : largeArray) 
            array.append(elem);
        String expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10] backing array length: 20";
        assertEquals(expected, array.toString(), "Failed appending 11 elements to dynamic array");
        assertEquals(11, array.size(), "Incorrect size after appending 11 elements to dynamic array");
    }
    
    @Test
    public void testInsertFront() {
        threeInts.insert(0, 5);
        String expected = "[5, 9, 0, 4] backing array length: 10";
        assertEquals(expected, threeInts.toString(), "Failed inserting an element to the front");
        assertEquals(4, threeInts.size(), "Incorrect size after inserting element to the front");
    }
    
    @Test
    public void testInsertMiddle() {
    	threeInts.insert(1, 50);
        String expected = "[9, 50, 0, 4] backing array length: 10";
        assertEquals(expected, threeInts.toString(), "Failed inserting an element to the middle");
        assertEquals(4, threeInts.size(), "Incorrect size after inserting element to the middle");
    }
    
    @Test
    public void testInsertEnd() {
    	threeInts.insert(3, 99);
        String expected = "[9, 0, 4, 99] backing array length: 10";
        assertEquals(expected, threeInts.toString(), "Failed inserting an element to the end");
        assertEquals(4, threeInts.size(), "Incorrect size after inserting element to the end");
    }
    
    @Test
    public void testInsertInvalidLowIndex() {
    	// This assertion checks that calling insert with an index that is too low throws the
    	// IndexIndexOutOfBoundsException.
    	assertThrows(IndexOutOfBoundsException.class, () -> { threeInts.insert(-1, 33); },
    			"Failed to throw exception when inserting with too-low index");
    }
    
    @Test
    public void testInsertInvalidHighIndex() {
    	assertThrows(IndexOutOfBoundsException.class, () -> { threeInts.insert(4, -4); }, 
    			"Failed to throw exception when inserting with too-high index");
    }
    
    @Test
    public void testGetElement(){
        assertEquals(9, threeInts.getElement(0), "Failed getting element from front");
        assertEquals(0, threeInts.getElement(1), "Failed getting element from middle");
        assertEquals(4, threeInts.getElement(2), "Failed getting element from end");
        assertEquals(3, threeInts.size(), "Calling getElement changed the size of dynamic array");
    }
    
    @Test
    public void testGetElementInvalidLow() {
    	assertThrows(IndexOutOfBoundsException.class, () -> { threeInts.getElement(-1); }, 
    			"Failed to throw exception when getting element with too-low index");
    }
    
    @Test
    public void testGetElementInvalidHigh() {
    	assertThrows(IndexOutOfBoundsException.class, () -> { threeInts.getElement(3); },
    			"Failed to throw exception when getting element with too-high index");
    }
    
    @Test
    public void testDoublingIsFaster() {
        double DynamicArrayTime = DynamicArrayTimer.appendToBetterDynamicArray(10000);
        double regularDynamicArrayTime = DynamicArrayTimer.appendToDynamicArray(10000);
        assertTrue(DynamicArrayTime < regularDynamicArrayTime,
                "The time to add 10k items to a doubling dynamic array should be faster, " +
                "but it is not with the current implementation");
    }
    @Test
    public void testSetElement() {
        threeInts.setElement(0, 8);
        assertEquals(8, threeInts.getElement(0), "Failed to set the first element correctly");
        
        threeInts.setElement(1, 5);
        assertEquals(5, threeInts.getElement(1), "Failed to set the middle element correctly");
        
        threeInts.setElement(2, 7); 
        assertEquals(7, threeInts.getElement(2), "Failed to set the last element correctly");
    }

    @Test
    public void testSetElementInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> threeInts.setElement(-1, 10),
            "Failed to throw exception for too-low index in setElement");
        
        assertThrows(IndexOutOfBoundsException.class, () -> threeInts.setElement(3, 10),
            "Failed to throw exception for too-high index in setElement");
    }

    @Test
    public void testDelete() {
        threeInts.delete(0);
        assertEquals("[0, 4] backing array length: 10", threeInts.toString(), 
            "Failed deleting first element");

        threeInts.delete(1);
        assertEquals("[0] backing array length: 10", threeInts.toString(),
            "Failed deleting last element");
    }

    @Test
    public void testDeleteInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> threeInts.delete(-1),
            "Failed to throw exception for too-low index in delete");
        
        assertThrows(IndexOutOfBoundsException.class, () -> threeInts.delete(3),
            "Failed to throw exception for too-high index in delete");
    }

    @Test
    public void testSort() {
        threeInts.append(2);
        threeInts.append(1);
        threeInts.sort();
        assertEquals("[0, 1, 2, 4, 9] backing array length: 10", threeInts.toString(), 
            "Failed to sort the array correctly");
    }

    @Test
    public void testOperationsOnLargerArray() {
        BetterDynamicArray largeArray = new BetterDynamicArray();
        for(int i = 0; i < 20; i++) {
            largeArray.append(i);
        }
        assertEquals(20, largeArray.size(), "Incorrect size after appending 20 elements");
        
        largeArray.setElement(10, 100);
        assertEquals(100, largeArray.getElement(10), "Failed to set element in expanded array");
        
        largeArray.delete(5);
        assertEquals(19, largeArray.size(), "Incorrect size after deleting an element from expanded array");
        
        largeArray.sort();
        
    }
}