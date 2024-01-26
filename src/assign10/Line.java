package assign10;

import java.awt.*;

/**
 * Represents a line, which is a Shape2D.
 *
 * @author Prof. Heisler, Shawn Zhang
 * @version Nov. 6, 2023
 */
public class Line extends Shape2D {
    private int endX;
    private int endY;

    /**
     * Construct a Line with the given end positions, color, and filled property.
     *
     * @param startX - x position of start point
     * @param startY - y position of start point
     * @param endX   - x position of end point
     * @param endY   - y position of end point
     * @param color
     * @param filled - has no effect on a Line object, but is needed by Shape2D
     */
    public Line(int startX, int startY, int endX, int endY, Color color, boolean filled) {
        super(startX, startY, color, filled);
        this.endX = endX;
        this.endY = endY;
    }


    @Override
    public void resize(int sizeX, int sizeY) {
        this.endX = sizeX;
        this.endY = sizeY;
    }

    @Override
    public void draw(Graphics g, double zoom) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g.setColor(getColor());
        g.drawLine((int) (getX() * zoom), (int) (getY() * zoom)
                , (int) (endX * zoom), (int) (endY * zoom));
    }
}
