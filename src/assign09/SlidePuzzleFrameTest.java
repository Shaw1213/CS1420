package assign09;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the SlidePuzzleFrame class.
 * 
 * @author Prof. Heisler and Shawn Zhang
 * @version November 17, 2023
 */
public class SlidePuzzleFrameTest {  
	
	@Test
	public void testStartInUnsolvedState() { 
		SlidePuzzleFrame frame = new SlidePuzzleFrame();
		assertFalse(frame.isSolved());
	}
	
	@Test
    public void testInitialState() {
		SlidePuzzleFrame frame = new SlidePuzzleFrame();
        assertFalse(frame.isSolved());
    }

    @Test
    public void testShuffle() {
    	SlidePuzzleFrame frame = new SlidePuzzleFrame();
        frame.shuffle();
        assertFalse(frame.isSolved());
    }

    @Test
    public void testSolve() {
    	SlidePuzzleFrame frame = new SlidePuzzleFrame();
        frame.solve();
        assertTrue(frame.isSolved());
    }
}