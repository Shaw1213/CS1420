package assign07;

import java.util.Arrays;

/**
 * An inventory to hold up to a set number of Items for the gacha game.
 * New Items can be added and the best or all items can be displayed.
 * It includes a method to merge Items of the same type.
 * 
 * @author Prof. Heisler, Shawn
 * @version Oct. 26, 2023
 */
public class Inventory {
	private Item[] items;
	private int size;
	
	// A number repersenting the maximum capacity.  
	public static final int CAPACITY = 20;
	
	/**
	 * The constructor allocates space to hold CAPACITY Items.
	 * Initially, there are no Items in the inventory, and the size is zero.
	 */
	public Inventory() {
		items = new Item[CAPACITY];
        size = 0;
	}
	
	/**
	 * Gets the number of Items in the inventory.
	 * Note that this is not the same as the length of the backing array.
	 * 
	 * @return the number of Items
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Attempts to add an Item to the inventory. If there is no space,
	 * an exception is thrown and the inventory is not modified.
	 * If there is enough space, the new Item is added to the inventory
	 * and the size is increased by one.
	 * 
	 * @param newItem - to be added
	 * @throws RuntimeException if there is no space in the inventory
	 */
	public void add(Item newItem) {
		if (size >= CAPACITY) {
			throw new RuntimeException("No space in the inventory");
		}
		items[size] = newItem;
		size++;
	}
	
	/////////////////////////////////////////////////////
	// Everything below is complete.
	// Do not modify code below this line.
	/////////////////////////////////////////////////////
	
	/**
	 * Prints a description of the best item of each type.
	 */
	public void printBestItems() {
		Item[] bestItems = getBestItems();
		System.out.println("Best equipment:");
		System.out.println(" - " + bestItems[0]);
		System.out.println(" - " + bestItems[1]);
		System.out.println(" - " + bestItems[2]);
	}
	
	/**
	 * Prints a description of every item in the inventory sorted in
	 * ascending order.
	 */
	public void printItems() {
		Arrays.sort(items, 0, size);
		System.out.println("Inventory:");
		for(int index = 0; index < size; index++) 
			System.out.println(" - " + items[index]);
	}
	
	/**
	 * Merges all items of each type into three remaining items.
	 * The result is one Tool, one Armor, and one Magic.
	 * The size of the inventory will be three after this operation.
	 */
	public void mergeAll() {
		Item[] bestItems = getBestItems(); // sorting done here
		int nextToMerge = size - 2;
		// merge tools
		while(items[nextToMerge] instanceof Tool) {
			bestItems[0].merge(items[nextToMerge]);
			nextToMerge--;
		}
		// merge armor
		nextToMerge--;
		while(items[nextToMerge] instanceof Armor) {
			bestItems[1].merge(items[nextToMerge]);
			nextToMerge--;
		}
		// merge magic
		nextToMerge--;
		while(nextToMerge >= 0) {
			bestItems[2].merge(items[nextToMerge]);
			nextToMerge--;
		}
		
		// Clear inventory except for best items
		items[0] = bestItems[0];
		items[1] = bestItems[1];
		items[2] = bestItems[2];
		size = 3;
	}
	
	/**
	 * Gets the best items of each type in the inventory.
	 * 
	 * @return the best items of each type
	 */
	public Item[] getBestItems() {
		Arrays.sort(items, 0, size);
		Item[] bestItems = new Item[3];
		bestItems[0] = items[size - 1]; // best tool
		int armorIndex = size - 1;
		int magicIndex = size - 1;
		for(int index = 0; index < size; index++)
			if(items[index] instanceof Tool) {
				armorIndex--;
				magicIndex--;
			} 
			else if(items[index] instanceof Armor)
				magicIndex--;
		bestItems[1] = items[armorIndex];
		bestItems[2] = items[magicIndex];
		return bestItems;
	}
}
