package assign07;
/**
 * This is a class reoersenbts item in a gacha game.
 * it serves as a base class for different iteams: Tool, Armor, Magic.
 * 
 * @author Shawn
 * @version Oct 26, 2023
 */

public abstract class Item implements Comparable<Item>{
	private String name;
	
	/**
	 * Constricts an item with name.
	 * @param name the name of the item.
	 */
	public Item(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name of the item.
	 * @return the name of the item.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Merges this item with another item.
	 * @param other the item to merge with this.
	 */
	public abstract void merge(Item other);
	
}
