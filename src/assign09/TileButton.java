package assign09;
/**
 * This class represents a tile in a slide puzzle game. It extends JButton, 
 * allowing each tile to be interacted with like a button.
 * Each tile displays an image and has a specific location within the grid of the puzzle.
 * 
 * @author Shawn Zhang
 * @version November 17, 2023
 */

import javax.swing.*;

public class TileButton extends JButton {
    private final int row;
    private final int column;
    private int imageID;

    /**
     * Constructs a TileButton with specified image, row, column, and image ID.
     *
     * @param filename The path to the image file.
     * @param row The row position of the tile in the grid.
     * @param column The column position of the tile in the grid.
     * @param imageID The ID of the image displayed on the tile.
     */
    public TileButton(String filename, int row, int column, int imageID) {
        super(new ImageIcon(filename));
        this.row = row;
        this.column = column;
        this.imageID = imageID;
    }

    /**
     * Gets the row position of the tile.
     *
     * @return The row position.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column position of the tile.
     *
     * @return The column position.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gets the image ID of the tile.
     *
     * @return The image ID.
     */
    public int getImageID() {
        return imageID;
    }

    /**
     * Swaps the icon and image ID of this tile with another tile.
     *
     * @param other The other TileButton with which to swap.
     */
    public void swap(TileButton other) {
        Icon thisIcon = this.getIcon();
        int thisId = this.imageID;
        
        this.setIcon(other.getIcon());
        this.imageID = other.getImageID();
        
        other.setIcon(thisIcon);
        other.imageID = thisId;
    }
}
