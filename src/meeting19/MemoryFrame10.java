package meeting19;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/** 
 * This class implements a Memory Game GUI.
 * 
 * This is the eighth version of the GUI built in class, 
 * with buttons included, listening for when each button 
 * is selected, detection of when matches are made,
 * disabling buttons when they should not be selected by user,
 * displaying messages with game updates, detection of 
 * when the game is won/over, a menu of options for 
 * restarting the game (or a new game) with tool tips, and 
 * a menu option for customizing the integers used as secret values 
 * (via file).
 * 
 * @author Prof. Parker
 * @version November 6, 2023
 */
public class MemoryFrame10 extends JFrame implements ActionListener {
	
	// all buttons
	private MemoryButton[] buttons;
	
	// buttons on game board currently selected
	private ArrayList<MemoryButton> buttonsSelected; 
	
	// helpful message for user
	private JLabel msgLabel;
	
	// counter for matches made so far
	private int matchCount;

	public MemoryFrame10() {
		
		// Exit on close
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Create a container to hold and organized the 16 buttons.
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		
		// Create 16 buttons.  For each button, 
		//    --construct the button and hide a secret value
		//    --add the button to the container (so it can be seen)
		//    --add a listener for the button (what to do when user selects)
		
		ArrayList<Integer> secretValues = generateSecretValues(8);
		buttons = new MemoryButton[16];
		for(int i = 0; i < 16; i++) {
				buttons[i] = new MemoryButton(secretValues.remove(0));
				buttons[i].setFont(new Font("Dialog", Font.PLAIN, 100));
				panel.add(buttons[i]);
				buttons[i].addActionListener(this);
		}
		
		// Keep track of buttons selected by the user, none yet.
		buttonsSelected = new ArrayList<MemoryButton>();  
		
		// Create label for displaying helpful messages
		msgLabel = new JLabel("Let's play MEMORY!", JLabel.CENTER);
		msgLabel.setFont(new Font("Dialog", Font.PLAIN, 32));
		
		// Add a panel to contain the sub-panel of buttons and the label
		JPanel outerPanel = new JPanel();
		outerPanel.setLayout(new BorderLayout());
		outerPanel.add(panel, BorderLayout.CENTER);
		outerPanel.add(msgLabel, BorderLayout.SOUTH);
		panel = outerPanel;   // to avoid changing code below that sets up JFrame
		
		// Initialize match counter
		matchCount = 0;
		
	// Add a Restart menu with two options
		JMenuBar menubar = new JMenuBar();
		JMenu restartMenu = new JMenu("Restart");
		JMenuItem resetItem = new JMenuItem("Restart same game");
		resetItem.setToolTipText("This option resets to 0 matches but does not change secret values.");
		JMenuItem newGameItem = new JMenuItem("Start a new game");
		newGameItem.setToolTipText("This option resets to 0 matches and changes secret values.");
		resetItem.addActionListener(this);
		newGameItem.addActionListener(this);
		restartMenu.add(newGameItem);
	    menubar.add(restartMenu);
		this.setJMenuBar(menubar);
		
		// Add a Customize menu with one option
		JMenu customizeMenu = new JMenu("Customize");
		JMenuItem customTextItem = new JMenuItem("Get secret values from file");
		customTextItem.setToolTipText("This option sets the secret value text from file.");
		customTextItem.addActionListener(this);
		customizeMenu.add(customTextItem);
		menubar.add(customizeMenu);
		
		// Set up the JFrame.
		setTitle("Memory");
		setContentPane(panel);
		setPreferredSize(new Dimension(600, 600));
		pack();
	}
	
	/**
	 * This is the method that is invoked when a button or menu item is selected.
	 * 
	 * @param -- an object the represents the event
	 */
	public void actionPerformed(ActionEvent e) {	
		
		Object eventSource = e.getSource();
		
		if(eventSource instanceof MemoryButton)
			handleButtonEvent((MemoryButton)eventSource);
		
		// Else must be one of three JMenuItem objects
		else if(((JMenuItem)eventSource).getText().equals("Restart same game")) 
			resetGame(false, null);
	
		else if(((JMenuItem)eventSource).getText().equals("Start a new game")) 
			resetGame(true, generateSecretValues(8));
		
		else { // Else must be customize option
			JFileChooser chooser = new JFileChooser();
			// Narrows file selection to only text files
			chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
			chooser.setDialogTitle("Select the file that contains eight integers to use as text for secret values.");

			// If a file is successfully chosen, start a new game with user-provided integers
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
				resetGame(true, processUserProvidedValues(chooser.getSelectedFile()));
			// Else, show a message and continue the current game.
			else 
				JOptionPane.showMessageDialog(this, "Open file cancelled");
		}
	}
	
	/**
	 * Handles the event of a MemoryButton being selected by the user.
	 * 
	 * @param -- an object the represents the event of the button 
	 *           being selected
	 */
	private void handleButtonEvent(MemoryButton button) {
		
		// If two buttons were saved from last round, their values need to be hidden.
		if(buttonsSelected.size() == 2) {
			buttonsSelected.get(0).hideValue();
			buttonsSelected.get(1).hideValue();
			buttonsSelected.clear();
		}
		
		// Disable the button selected by the user
		button.setEnabled(false);
		
		// Show the value for the button.
		button.showValue();
		
		// Keep track of this button for future rounds.
		buttonsSelected.add(button);
 
		// If this is the second button of a pair, check for a match.
		if(buttonsSelected.size() == 2)
			if(button.getValue() == buttonsSelected.get(0).getValue()) {
				
				// We are finished with these buttons, do not save for future round.
				buttonsSelected.clear();
								
				// Check if game is won
				matchCount++;
				if(matchCount == 8)
					msgLabel.setText("You found all matches! Game over.");
				else
					msgLabel.setText("MATCH! " + matchCount + " found so far.");
			}
			else {  // Else enable both buttons
				buttonsSelected.get(0).setEnabled(true);
				buttonsSelected.get(1).setEnabled(true);	
				
				// No match message
				msgLabel.setText(buttonsSelected.get(0).getValue() + " and " + 
						buttonsSelected.get(1).getValue() + " do not match. Try again.");				
			}
		// This is the first button in a pair
		else
			msgLabel.setText("Select another button.");
	}
	
	/**
	 * Resets the buttons, match counter, and label for a game.
	 * 
	 * @param updateSecretValues - indicates whether to reset the secret value
	 * @param secretValues - list of secret values to use
	 */
	private void resetGame(boolean updateSecretValues, ArrayList<Integer> secretValues) {
		matchCount = 0;
		for(int i = 0; i < 16; i++) {
			if(updateSecretValues)
				buttons[i].resetValue(secretValues.remove(0));
			buttons[i].hideValue();
			buttons[i].setEnabled(true);
		}
		buttonsSelected.clear();
		msgLabel.setText("Let's play MEMORY!");
	}
	
	/**
	 * Using integer values in a file, produces a shuffled list of eight pairs.
	 * 
	 * If the file is not formatted as expected (at least eight integers), 
	 * simply returns a shuffled list of pairs 1 to 8.
	 * 
	 * @param filename - name (and path) of file containing the integers
	 * @return a shuffled list of eight pairs of integers
	 */
	private ArrayList<Integer> processUserProvidedValues(File filename) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(filename);
		}
		catch(FileNotFoundException e) {
			// do nothing -- file from JFileChooser, must exist
		}
		
		for(int i = 0; i < 8; i++) {
			if(fileIn.hasNextInt()) {
				// add user-provided integer as a pair
				int value = fileIn.nextInt();
				list.add(value);
				list.add(value);
			}
			else
				// if file does not contain at least 8 integers,
				// simply generate list of pairs 1 to 8
				return generateSecretValues(8);
		}	
		Collections.shuffle(list);
		return list;
	}
	
	/** 
	 * Generates a list of integers 1 to limit, two of each, randomly shuffled.
	 * 
	 * @param limit -- the largest integer generated
	 * @returns an ArrayList of integers
	 */
	private ArrayList<Integer> generateSecretValues(int limit) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i <= limit; i++) {
			list.add(i);
			list.add(i);
		}	
		Collections.shuffle(list);
		return list;
	}
	
	// Required by a serializable class (ignore for now)
	private static final long serialVersionUID = 1L;
}