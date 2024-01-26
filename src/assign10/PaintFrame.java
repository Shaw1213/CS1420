package assign10;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PaintFrame is the main window of the paint program.
 * 
 * @author Shawn Zhang
 * @version Nov 30 , 2023
 */

public class PaintFrame extends JFrame implements ActionListener {

	JMenuItem clear, save, close;
	JButton oval, rectangle, line, filled, color;
	JPanel paintPanel, buttonPanel;

	/**
     * Constructor for PaintFrame. It sets up the GUI.
     * @throws HeadlessException if Graphics Environment is not available.
     */
	
	public PaintFrame() throws HeadlessException {
		super("Paint Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 500));

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		clear = new JMenuItem("Clear");
		save = new JMenuItem("Save");
		close = new JMenuItem("Close");
		file.add(save);
		file.add(clear);
		file.add(close);
		menuBar.add(file);
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);

		JPanel mainPanel = new JPanel();
		paintPanel = new PaintPanel();
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 1));

		color = new JButton();
		color.setIcon(new ImageIcon("src/assign10/colorIcon.png"));
		color.setBackground(Color.WHITE);
		color.setPreferredSize(new Dimension(70, 70));

		filled = new JButton();
		filled.setIcon(new ImageIcon("src/assign10/fillIcon.png"));
		filled.setBackground(Color.WHITE);
		filled.setPreferredSize(new Dimension(70, 70));

		line = new JButton();
		line.setIcon(new ImageIcon("src/assign10/lineIcon.png"));
		line.setBackground(Color.WHITE);
		line.setPreferredSize(new Dimension(70, 70));

		rectangle = new JButton();
		rectangle.setIcon(new ImageIcon("src/assign10/rectIcon.png"));
		rectangle.setBackground(Color.WHITE);
		rectangle.setPreferredSize(new Dimension(70, 70));

		oval = new JButton();
		oval.setIcon(new ImageIcon("src/assign10/ovalIcon.png"));
		oval.setBackground(Color.WHITE);
		oval.setPreferredSize(new Dimension(70, 70));

		JPanel p1 = new JPanel();
		p1.add(color);
		JPanel p2 = new JPanel();
		p2.add(filled);
		JPanel p3 = new JPanel();
		p3.add(line);
		JPanel p4 = new JPanel();
		p4.add(rectangle);
		JPanel p5 = new JPanel();
		p5.add(oval);

		buttonPanel.add(p1);
		buttonPanel.add(p2);
		buttonPanel.add(p3);
		buttonPanel.add(p4);
		buttonPanel.add(p5);

		mainPanel.add(paintPanel);
		mainPanel.add(buttonPanel);
		add(mainPanel);

		clear.addActionListener(this);
		clear.setToolTipText("Clear the paint panel");
		save.addActionListener(this);
		save.setToolTipText("Save the paint panel");
		close.addActionListener(this);
		close.setToolTipText("Close the application");
		oval.addActionListener(this);
		oval.setToolTipText("Draw an oval");
		rectangle.addActionListener(this);
		rectangle.setToolTipText("Draw a rectangle");
		line.addActionListener(this);
		line.setToolTipText("Draw a line");
		filled.addActionListener(this);
		filled.setToolTipText("Fill or unFill the shape");
		color.addActionListener(this);
		color.setToolTipText("Choose a color");
		pack();
	}

	/**
     * Handles action events triggered by menu items and buttons in the frame.
     * @param e The ActionEvent object containing details about the event.
     */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == color) {
			Color c = JColorChooser.showDialog(this, "Choose a color", Color.BLACK);
			if (c != null) {
				color.setBackground(c);
				((PaintPanel) paintPanel).setColor(c);
			}
		} else if (e.getSource() == filled) {
			((PaintPanel) paintPanel).setFilled();
		} else if (e.getSource() == line) {
			((PaintPanel) paintPanel).setShape(Shape.LINE);
		} else if (e.getSource() == rectangle) {
			((PaintPanel) paintPanel).setShape(Shape.RECTANGLE);
		} else if (e.getSource() == oval) {
			((PaintPanel) paintPanel).setShape(Shape.OVAL);
		} else if (e.getSource() == clear) {
			((PaintPanel) paintPanel).clear();
		} else if (e.getSource() == save) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");
			int userSelection = fileChooser.showSaveDialog(this);
			fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png", "gif"));
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				((PaintPanel) paintPanel).save(fileChooser.getSelectedFile());
			}

		} else if (e.getSource() == close) {
			this.dispose();
		}

	}

}
