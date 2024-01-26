package assign09;

/**
 * SlidePuzzleFrame creates the main window for a slide puzzle game.
 * It contains a grid of TileButtons, a shuffle button, and a solution button.
 * 
 * @author Shawn Zhang
 * @version November 17, 2023
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SlidePuzzleFrame extends JFrame implements ActionListener {
    private TileButton[][] tiles;
    private TileButton emptyTile;
    private JButton shuffleButton;
    private JButton solutionButton;

    /**
     * Constructor for SlidePuzzleFrame.
     * It sets up the frame and initializes all components.
     */
    public SlidePuzzleFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("Slide Puzzle Game");

        tiles = new TileButton[4][4];
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 4, 0, 0));
        gridPanel.setPreferredSize(new Dimension(720, 720));
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                String filename = "src/assign09/tile_" + row + "_" + col + ".png";
                TileButton btn = new TileButton(filename, row, col, row * 4 + col);
                btn.addActionListener(this);
                tiles[row][col] = btn;
                gridPanel.add(btn);
            }
        }
        emptyTile = tiles[0][0];
        emptyTile.setVisible(false);

        shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener(this);
        solutionButton = new JButton("Solve");
        solutionButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(shuffleButton);
        buttonPanel.add(solutionButton);

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack(); 
        setLocationRelativeTo(null); 
        shuffle();
    }

    /**
     * Checks if the specified tile is adjacent to the empty tile.
     * @param tile The tile to check.
     * @return true if the tile is adjacent to the empty tile, false otherwise.
     */
    private boolean adjacentToEmpty(TileButton tile) {
        return Math.abs(tile.getRow() - emptyTile.getRow()) +
               Math.abs(tile.getColumn() - emptyTile.getColumn()) == 1;
    }

    /**
     * Shuffles the tiles on the grid to start the game with a random puzzle.
     */
    public void shuffle() {
        Random rng = new Random();
        for (int i = 0; i < 100; i++) {
            int[][] moves = {
                {emptyTile.getRow() - 1, emptyTile.getColumn()}, // Up
                {emptyTile.getRow() + 1, emptyTile.getColumn()}, // Down
                {emptyTile.getRow(), emptyTile.getColumn() - 1}, // Left
                {emptyTile.getRow(), emptyTile.getColumn() + 1}  // Right
            };

            while (true) {
                int direction = rng.nextInt(4);
                int newRow = moves[direction][0];
                int newCol = moves[direction][1];

                if (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4) {
                    tiles[newRow][newCol].swap(emptyTile);
                    TileButton temp = emptyTile;
                    emptyTile = tiles[newRow][newCol];
                    emptyTile.setVisible(false);
                    temp.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Solves the puzzle by setting each tile to its original image,
     * effectively resetting the puzzle to its solved state.
     */
    public void solve() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int imageID = row * 4 + col;
                String filename = "src/assign09/tile_" + row + "_" + col + ".png";
                tiles[row][col].setIcon(new ImageIcon(filename));

                if (row == 0 && col == 0) {
                    emptyTile = tiles[row][col];
                    emptyTile.setVisible(false);
                } else {
                    tiles[row][col].setVisible(true);
                }
            }
        }
        revalidate();
        repaint();
    }

    /**
     * Checks if the puzzle is solved.
     * @return true if the puzzle is solved, false otherwise.
     */
    public boolean isSolved() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                String expectedFilename = "src/assign09/tile_" + row + "_" + col + ".png";
                ImageIcon expectedIcon = new ImageIcon(expectedFilename);
                ImageIcon actualIcon = (ImageIcon) tiles[row][col].getIcon();

                // Compare the descriptions of the icons (assuming the description is the filename)
                if (!expectedIcon.getDescription().equals(actualIcon.getDescription())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Handles actions performed on the GUI.
     * @param event The ActionEvent triggered by the components.
     */
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == shuffleButton) {
            shuffle();
        } else if (source == solutionButton) {
            solve();
        } else if (source instanceof TileButton) {
            TileButton clickedTile = (TileButton) source;
            if (adjacentToEmpty(clickedTile)) {
                clickedTile.swap(emptyTile);
                TileButton temp = emptyTile;
                emptyTile = clickedTile;
                clickedTile = temp;
                emptyTile.setVisible(false);
                clickedTile.setVisible(true);

                if (isSolved()) {
                    JOptionPane.showMessageDialog(this, "Congratulations! You solved the puzzle!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please click a tile adjacent to the empty space.");
            }
        }
    }
}
