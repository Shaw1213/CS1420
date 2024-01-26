package assign09;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

/**
 * This class contains tests for the TileButton class.
 * 
 * @author Prof. Heisler and Shawn Zhang
 * @version November 17, 2023
 */
public class TileButtonTest {
	private TileButton button1;
	private TileButton button2;
	// This code executes before each test.
	// You can reference button1 and button2 in your tests without having to create them.
	@Before
	public void setup(){
		button1 = new TileButton("src/assign09/tile_1_2.png", 1, 2, 6);
		button2 = new TileButton("src/assign09/tile_2_2.png", 2, 2, 10);
	}
		
	@Test
	public void testGetRow() {
		assertEquals(1, button1.getRow());
	}
	
	@Test
	public void testGetImageID() {
		assertEquals(6, button1.getImageID());
	}	
	
	@Test
	public void testSwap() {
		button1.swap(button2);
		assertEquals(10, button1.getImageID());
	}	
	
	@Test
    public void testGetColumn() {
        assertEquals(2, button1.getColumn());
    }

    @Test
    public void testSwapRowAndColumnUnchanged() {
        int originalRow1 = button1.getRow();
        int originalColumn1 = button1.getColumn();
        int originalRow2 = button2.getRow();
        int originalColumn2 = button2.getColumn();

        button1.swap(button2);

        assertEquals(originalRow1, button1.getRow());
        assertEquals(originalColumn1, button1.getColumn());
        assertEquals(originalRow2, button2.getRow());
        assertEquals(originalColumn2, button2.getColumn());
    }

    @Test
    public void testIconSwap() {
        ImageIcon originalIcon1 = (ImageIcon) button1.getIcon();
        ImageIcon originalIcon2 = (ImageIcon) button2.getIcon();

        button1.swap(button2);

        assertSame(originalIcon1, button2.getIcon());
        assertSame(originalIcon2, button1.getIcon());
    }
}