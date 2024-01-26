package assign10;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PaintPanel is the main drawing area of the paint program.
 * 
 * @author Shawn Zhang
 * @version Nov 30 , 2023
 */

public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private Color color;
    private boolean filled;
    private Shape selectedShape;
    private List<Shape2D> shapes;
    private double zoom = 1.0;
    private Shape2D currentShape;

    /**
     * Constructor for PaintPanel. Initializes the panel and sets up listeners.
     */
    
    public PaintPanel() {
        super();

        setPreferredSize(new Dimension(400, 400));
        setBackground(new Color(255, 255, 255));

        selectedShape = Shape.LINE;
        color = Color.BLACK;
        filled = false;
        shapes = new ArrayList<>();

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (currentShape == null) {
            currentShape = getNewShape((int) (e.getX() / zoom), (int) (e.getY() / zoom));
            shapes.add(currentShape);
        }


        drawShapes(getGraphics());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentShape != null) {
            currentShape.resize((int) (e.getX() / zoom), (int) (e.getY() / zoom));
            currentShape = null;
        }

        drawShapes(getGraphics());

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        System.out.println("Mouse dragged at " + e.getX() + ", " + e.getY());
        if (currentShape != null) {
            currentShape.resize((int) (e.getX() / zoom), (int) (e.getY() / zoom));
        }
        drawShapes(getGraphics());

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoom -= e.getWheelRotation() * 0.2;
        if (zoom < 0.1) {
            zoom = 0.1;
        }
        drawShapes(getGraphics());

    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void setFilled() {
        this.filled = !this.filled;
    }

    public void setShape(Shape shape) {
        this.selectedShape = shape;

    }

    private void drawShapes(Graphics g) {
        for (Shape2D shape : shapes) {
            shape.draw(g, zoom);
        }
    }

    /**
     * Method to paint the component. It is called automatically by the Swing framework.
     * @param g The Graphics object used for drawing.
     */
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        repaint();
        drawShapes(g);
    }

    private Shape2D getNewShape(int x, int y) {
        switch (selectedShape) {
            case RECTANGLE:
                return new Rectangle(x, y, 0, 0, color, filled);
            case OVAL:
                return new Oval(x, y, 0, 0, color, filled);
            default:
                return new Line(x, y, x, y, color, filled);
        }
    }

    public void clear() {
        shapes.clear();
        repaint();
    }

    public void save(File selectedFile) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paintAll(g2);
        g2.dispose();
        String name = selectedFile.getName();
        String[] split = name.split("\\.");
        try {
            ImageIO.write(image, split[split.length-1], selectedFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
