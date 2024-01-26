package assign05;
/**
 * This class define a 2D circle with a center position and radius.
 *  
 *  @author Shawn
 *  @version Oct 5, 2023
 */

public class Circle {
	
	private int x;
    private int y;
    private int radius;
	
    /**
     * Initializes the circle with default center (0,0) and radius of 1.
     */
    public Circle() {
    	this.x = 0;
        this.y = 0;
        this.radius = 1;
    }
    
    /**
     * Initializes the circle with a center and a radius. 
     * 
     * @param positionX The x coordinate of the circle's center position.
     * @param positionY The y coordinate of the circle's center position.
     * @param radius The radius of the circle.
     */
    public Circle(int positionX, int positionY, int radius) {
    	this.x = positionX;
        this.y = positionY;
        this.radius = radius;
    }
    
    /**
     * Gets the x coordinate of the circle's center position.
     * 
     * @return The x coordinate.
     */
    public int getX() {
        return x;
    }
    
    /**
     * Gets the y coordinate of the circle's center position.
     *
     * @return The y coordinate.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Gets the radius of the circle.
     *
     * @return The radius.
     */
    public int getRadius() {
        return radius;
    }
    
    /**
     * Moves the circle to a new center position.
     *
     * @param newX The new x coordinate of the circle's center position.
     * @param newY The new y coordinate of the circle's center position.
     */
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
    
    /**
     * Scales the circle's radius by a factor.
     *
     * @param factor Factor to scale the radius.
     */
    public void scale(double factor) {
        this.radius = (int) (radius * factor);
    }
    
    /**
     * Determines if the area of the circle is larger than the other circle.
     * 
     * @param other
     * @return True if this circle is larger, false if not.
     */
    public boolean largerThan(Circle other) {
    	return this.radius > other.getRadius();
    }
    
    /**
     * Creates a bounding rectangle that surrounds the circle.
     * 
     * @return A new rectangle that counds the circle.
     */
    public Rectangle boundingRectangle() {
    	int newX = x - radius;
        int newY = y - radius;
        int sideLength = 2 * radius;
        return new Rectangle(newX, newY, sideLength, sideLength);
    }
    
    /**
     *Creates a bounding rectangle that includes this and another circle.
     * 
     * @param other The other circle to construct.
     * @return A new rectangle that bound this and the other circle. 
     */
    public Rectangle boundingRectangle(Circle other) {
    	int newX = Math.min(this.x - this.radius, other.getX() - other.getRadius());
        int newY = Math.min(this.y - this.radius, other.getY() - other.getRadius());
        int newWidth = Math.max(this.x + this.radius, other.getX() + other.getRadius()) - newX;
        int newHeight = Math.max(this.y + this.radius, other.getY() + other.getRadius()) - newY;
        return new Rectangle(newX, newY, newWidth, newHeight);
    }

    /**
     * Provides a string of this circle.
     *
     * @return A string describing the circle's radius and center position.
     */
    public String toString() {
        return "r = " + radius + " circle at (" + x + ", " + y + ")";
    }
    

}
