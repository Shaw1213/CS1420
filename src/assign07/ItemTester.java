package assign07;

/**
 * This is a JUnit test class which check the normal and edge cases of 
 * all instance methods in the three subclasses Tool,Magic, and Armor.
 * 
 * @author Shawn
 * @version Oct 26, 2023
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ItemTester {

    // Testing Magic class
    // Normal Case
    @Test
    public void testMagicNormal() {
        Magic magic = new Magic("Fireball", 100, 50);
        assertEquals("Magic: Fireball - power = 100, cost = 50", magic.toString());
    }

    // Edge Case: Zero or negative values
    @Test
    public void testMagicEdge() {
        Magic magic = new Magic("Ice Spike", 0, -10);
        assertEquals("Magic: Ice Spike - power = 0, cost = -10", magic.toString());
    }

    // Test merge method for Magic
    @Test
    public void testMergeMagic() {
        Magic magic1 = new Magic("Fireball", 100, 50);
        Magic magic2 = new Magic("Ice Spike", 50, 25);
        magic1.merge(magic2);
        assertEquals("Magic: Fireball - power = 150, cost = 51", magic1.toString());
    }

    // Test compareTo method for Magic
    @Test
    public void testCompareToMagic() {
        Magic magic1 = new Magic("Fireball", 120, 50);
        Magic magic2 = new Magic("Ice Spike", 50, 25);
        assertTrue(magic1.compareTo(magic2) > 0);
    }

    // Testing Tool class
    // Normal Case
    @Test
    public void testToolNormal() {
        Tool tool = new Tool("Hammer", 75);
        assertEquals("Tool: Hammer – power = 75", tool.toString());
    }

    // Edge Case: Zero or negative value
    @Test
    public void testToolEdge() {
        Tool tool = new Tool("Wrench", -20);
        assertEquals("Tool: Wrench – power = -20", tool.toString());
    }

    // Test merge method for Tool
    @Test
    public void testMergeTool() {
        Tool tool1 = new Tool("Hammer", 50);
        Tool tool2 = new Tool("Wrench", 50);
        tool1.merge(tool2);
        assertEquals("Tool: Hammer – power = 100", tool1.toString());
    }

    // Test compareTo method for Tool
    @Test
    public void testCompareToTool() {
        Tool tool1 = new Tool("Hammer", 75);
        Tool tool2 = new Tool("Wrench", 25);
        assertTrue(tool1.compareTo(tool2) > 0);
    }

    
    // Testing Armor class
	 // Normal Case
	 @Test
	 public void testArmorNormal() {
	     Armor armor = new Armor("Plate Armor", 99, 89);
	     assertEquals("Armor: Plate Armor - defense = 99, condition = 89", armor.toString());
	 }
	
	 // Edge Case: Zero or negative values
	 @Test
	 public void testArmorEdge() {
     Armor armor = new Armor("Leather Armor", 0, -10);
	     assertEquals("Armor: Leather Armor - defense = 0, condition = -10", armor.toString());
	 }
	
	 // Test merge method for Armor
	 @Test
	 public void testMergeArmor() {
	     Armor armor1 = new Armor("Plate Armor", 100, 80);
	     Armor armor2 = new Armor("Chainmail", 90, 60);
	     armor1.merge(armor2);
	     assertEquals("Armor: Plate Armor - defense = 190, condition = 80", armor1.toString());
	 }
	
	 // Test compareTo method for Armor
	 @Test
	 public void testCompareToArmor() {
	     Armor armor1 = new Armor("Plate Armor", 100, 80);
	     Armor armor2 = new Armor("Chainmail", 50, 60);
	     assertTrue(armor1.compareTo(armor2) > 0);
 }


}
