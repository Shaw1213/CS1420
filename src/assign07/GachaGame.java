package assign07;

import java.util.Random;
import java.util.Scanner;

/**
 * This class handles the interactive mechanics of a gacha game, in which 
 * a player collects four types of items:
 * 1. Tools with a power number
 * 2. Armor with defense and condition numbers
 * 3. Magic with power and cost numbers
 * 
 * The goal is to get the best set of items, but there is a limit
 * to how many items a player's inventory can hold. To make more space,
 * and improve items further, existing items can be merged.
 * 
 * The game repeatedly asks for commands (get n, merge, show, exit)
 * and prints the resulting set of best items.
 * 
 * @author Prof. Heisler
 * @version Oct. 4, 2023
 */
public class GachaGame {
	/**
	 * The main method runs the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		Scanner input = new Scanner(System.in);
		boolean running = true;
		String command;
		
		// Start with an initial set of weak items
		inventory.add(new Tool("soggy banana", 0));
		inventory.add(new Armor("garbage bag", 0, 10));
		inventory.add(new Magic("sneeze", 0, 1000));
		System.out.println("                                 _m_   ");
		System.out.println("Hello collector of fine items! <(^e^)>o");
		System.out.println("                                 L L  |");
		System.out.println("You can hold up to " + Inventory.CAPACITY + " items.");
		System.out.println("I'll give you something to start with.");
		inventory.printBestItems();
		
		// Repeatedly ask for input and process until "exit" command
		while(running) {
			System.out.println("\nNext command? (get n (0<n<11), merge, show, exit)");
		
			// Wait for the next command
			command = input.next();
			
			if(command.equals("get")) 
				if(input.hasNextInt()) {
					int num = input.nextInt();
					if(0 < num && num < 11) {
						System.out.println("Getting "+num+" new items.");
						Item[] newItems = getNewItems(num);
						try {
							for(Item item : newItems) 
								inventory.add(item);
							inventory.printBestItems();
						}catch(RuntimeException e) {
							System.out.println("Ran out of space in inventory!   _m_    ");
							System.out.println("Not all items added.           \\(*.*)/ o");
							System.out.println("Try merging to free up space.    L L  / ");
						}
					}
					else 
						System.out.println("Can only get 1 to 10 items at a time. Try again.");
				}
				else {
					System.out.println("get needs a number between 1 and 10. Example: get 7");
					input.next();
				}
			else if(command.equals("merge")) {
				System.out.println("Merging items in inventory.");
				inventory.mergeAll();
				inventory.printBestItems();
			}
			else if(command.equals("show")) 
				inventory.printItems();
			else if(command.equals("exit")) {
				System.out.println("                      _m_   o");
				System.out.println("Thanks for playing. <('u')>/ ");
				System.out.println("  Goodbye!            L L    ");
				running = false;
			}
			else 
				System.out.println("Unknown command " + command + ". Try: get n (0<n<11), merge, show, exit");
		}
		
		input.close();
	}
	
	/**
	 * Creates and returns one new Item with a randomly chosen name and stats.
	 * 
	 * @return the new Item
	 */
	private static Item getRandomItem() {
		Random generator = new Random();
		int itemType = generator.nextInt(3);
		String itemName = makeItemName(itemType, generator);
		
		if(itemType==0) {
			int power = generator.nextInt(1000);
			return new Tool(itemName, power);
			
		}else if(itemType==1) {
			int defense = generator.nextInt(1000);
			int condition = 50 + generator.nextInt(50);
			return new Armor(itemName, defense, condition);
			
		}else{
			int power = generator.nextInt(1000);
			int cost = generator.nextInt(100) + 1;
			
			return new Magic(itemName, power, cost);
		}
	}
	
	/**
	 * Creates and returns an array of randomly generated Items.
	 * 
	 * @param howMany - number of Items to make
	 * @return the array of new Items
	 */
	private static Item[] getNewItems(int howMany) {
		Item[] items = new Item[howMany];
		
		for(int i=0; i<howMany; i++) {
			items[i] = getRandomItem();
		}
		return items;
	}
	
	/**
	 * A private helper method that makes an item name by randomly choosing a prefix, noun, and suffix.
	 * 
	 * @param itemType - 0 for tool, 1 for armor, 2 for magic
	 * @param generator - a random number generator
	 * @return a randomly-generated name for the item type given
	 */
	private static String makeItemName(int itemType, Random generator) {
		String[] toolNames = { "stick", "rock", "chain", "wrench", "scissors", "fish" };
		String[] armorNames = { "helmet", "boots", "gloves", "jacket", "mask", "goggles" };
		String[] magicNames = { "sparks", "ice", "rain", "flame", "quake", "blast" };
		
		String[] adjectives = { "mighty", "shining", "powerful", "quick", "appropriate" };
		String[] modifiers = { "the moon", "the ancients", "satisfaction", "doom", "time" };
		
		String name;
		if(itemType == 0)
			name = toolNames[generator.nextInt(toolNames.length)];
		else if(itemType == 1)
			name = armorNames[generator.nextInt(armorNames.length)];
		else if(itemType == 2)
			name = magicNames[generator.nextInt(magicNames.length)];
		else
			throw new IllegalArgumentException("Parameter itemType must be 0, 1, or 2");
		
		return adjectives[generator.nextInt(adjectives.length)] + " " + name +
				" of " + modifiers[generator.nextInt(modifiers.length)];
	}
}