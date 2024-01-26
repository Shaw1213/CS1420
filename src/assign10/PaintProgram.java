package assign10;

/**
 * This class serves as the entry point for the paint program. 
 * 
 * @author Shawn Zhang
 * @version Nov 30 , 2023
 */
public class PaintProgram {
    
    /**
     * The main method to launch the paint program.
     * It creates an instance of PaintFrame and displays it.
     * 
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}
