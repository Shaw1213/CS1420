package assign05;

/**
 * This class define a new data type representing a rectangle.
 *  
 *  @author Shawn
 *  @version Oct 5, 2023
 */
public class Rectangle {
	private int x;
    private int y;
    private int width;
    private int height;
    
    /**
     * Default constructor that initilizes the rectangle
     * with (0,0) and width and height of 1.
     */
    public Rectangle() {
    	this.x = 0;
        this.y = 0;
        this.width = 1;
        this.height = 1;
    }

    /**
     * A constructor that initializes the rectangle with 
     * positions, width and height.
     * 
     * @param positionX The x coordinate of the rectangle.
     * @param positionY The y coordinate of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(int positionX, int positionY, int width, int height) {
    	this.x = positionX;
        this.y = positionY;
        this.width = width;
        this.height = height;
    }
    
    /**
     * This method returns the value of the 
     * x coordinate of the position of this Rectangle.
     * 
     * @return The x coordinate.
     */
    public int getX() {
    	return x;
    }
    
    /**
     * This method returns the value of the 
     * y coordinate of the position of this Rectangle.
     * 
     * @return The y coordinate.
     */
    public int getY() {
        return y;
    }
    
    /**
     * This method returns the width of this Rectangle, 
     * which is the size in the x direction.
     * 
     * @return The width of this Rectangle. 
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * This method returns the height of this Rectangle, 
     * which is the size in the y direction.
     * 
     * @return The height of this Rectangle. 
     */
    public int getHeight() {
        return height;
    }

    /**
     * This method changes the position of 
     * this Rectangle to be (newX, newY).
     * 
     * @param newX The new x coordinate of the rectangle.
     * @param newY The new y coordinate of the rectangle.
     */
    public void move(int newX, int newY) {
    	this.x = newX;
        this.y = newY;
    }
    
    /**
     * Scales the rectangle's width and height with the new factor. 
     * 
     * @param xFactor Factor to scale the width.
     * @param yFactor Factor to scale the height.
     */
    public void scale(double xFactor, double yFactor) {
    	this.width = (int) (width * xFactor);
        this.height = (int) (height * yFactor);
    }
    
    /**
     * Determines if the area of this rectangle 
     * is larger of another rectangle.
     * 
     * @param other The other rectangle to compare with.
     * @return True if this rectangle is larger, false if not.
     */
    public boolean largerThan(Rectangle other) {
        return this.width * this.height > other.getWidth() * other.getHeight();
    }
    
    /**
     * Creates a rectangle that includes this and another rectangle.
     * 
     * @param other The other rectangle to construct.
     * @return A new rectangle that bounds this and the other rectangle.
     */
    public Rectangle boundingRectangle(Rectangle other) {
    	int newX = Math.min(this.x, other.getX());
        int newY = Math.min(this.y, other.getY());
        int newWidth = Math.max(this.x + this.width, other.getX() + other.getWidth()) - newX;
        int newHeight = Math.max(this.y + this.height, other.getY() + other.getHeight()) - newY;
        return new Rectangle(newX, newY, newWidth, newHeight);
    }

    /**
     * This method returns a String representing this Rectangle.
     * 
     * @return A String representing this Rectangle
     */
    public String toString() {
        return width + " x " + height + " rectangle at (" + x + ", " + y + ")";
    }
}
    

