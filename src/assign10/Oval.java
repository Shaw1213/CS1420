package assign10;

import java.awt.*;

/**
 * Represents an oval, which is a Shape2D.
 *
 * @author Prof. Heisler, Shawn Zhang
 * @version Nov. 6, 2023
 */
public class Oval extends Shape2D{
	private int width;
	private int height;

	/**
	 * Construct an Oval with the given position, size, color, and filled property.
	 *
	 * @param posX - x position
	 * @param posY - y position
	 * @param width - diameter in x direction
	 * @param height - diameter in y direction
	 * @param color
	 * @param filled - true if it will be drawn filled
	 */
	public Oval(int posX, int posY, int width, int height, Color color, boolean filled) {
		super(posX, posY, color, filled);
		this.width = width;
		this.height = height;
	}


    @Override
    public void resize(int sizeX, int sizeY) {
        this.width = sizeX - getX();
        this.height = sizeY - getY();
        if (width < 0) {
            width = 0;
        }
        if (height < 0) {
            height = 0;
        }

    }

    @Override
    public void draw(Graphics g, double zoom) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g.setColor(getColor());
        if (isFilled()) {
            g.fillOval((int) (getX() * zoom), (int) (getY() * zoom)
                    , (int) (width * zoom), (int) (height * zoom));
        } else {
            g.drawOval((int) (getX() * zoom), (int) (getY() * zoom)
                    , (int) (width * zoom), (int) (height * zoom));
        }
    }
}
