package assign11;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * A Tester for ReviewPredictor.
 * 
 * @author Shawn Zhang
 * @version Dec, 7 , 2023
 */

public class ReviewPredictorTester {

    private ReviewPredictor predictor;

    @Before
    public void setUp() throws FileNotFoundException {
        predictor = new ReviewPredictor(new File("src/assign11/MovieReviews.txt"));
    }

    @Test
    public void testPredictScoreForKnownWords() {
        assertEquals(2.69, predictor.predictScore("a pretty excellent film"), 0.01);
    }

    @Test
    public void testPredictScoreForUnknownWords() {
        assertEquals(-1.0, predictor.predictScore("unknownword"), 0.01);
    }

    @Test
    public void testCheckPredictionAccuracy() {
        assertEquals(0.31000000000000005, predictor.checkPrediction("a pretty excellent film", 3.0), 0.01);
    }

    @Test
    public void testBestWordsWithValidK() {
        int k = 3;
        assertNotNull(predictor.bestWords(k));
        assertTrue(predictor.bestWords(k).size() <= k);
    }

    @Test
    public void testBestWordsWithInvalidK() {
        assertNull(predictor.bestWords(-1));
    }
}
