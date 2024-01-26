package meeting19;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** 
 * This class implements a Memory Game GUI.
 * 
 * This is the seventh version of the GUI built in class, 
 * with buttons included, listening for when each button 
 * is selected, detection of when matches are made,
 * disabling buttons when they should not be selected by user,
 * displaying messages with game updates, and detection of 
 * when the game is won/over.
 * 
 * @author Prof. Parker
 * @version November 6, 2023
 */
public class MemoryFrame7 extends JFrame implements ActionListener {
	
	// buttons on game board currently selected
	private ArrayList<MemoryButton> buttonsSelected; 
	
	// helpful message for user
	private JLabel msgLabel;
	
	// counter for matches made so far
	private int matchCount;

	/**
	 * Creates a Memory Game GUI.
	 */
	public MemoryFrame7() {
		
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
	
		for(int i = 0; i < 16; i++) {
				MemoryButton button = new MemoryButton(secretValues.remove(0));
				button.setFont(new Font("Dialog", Font.PLAIN, 100));
				panel.add(button);
				button.addActionListener(this);
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
		
		// Set up the JFrame.
		setTitle("Memory");
		setContentPane(panel);
		setPreferredSize(new Dimension(600, 600));
		pack();
	}
	
	/**
	 * This is the method that is invoked when a button is selected.
	 * 
	 * @param -- an object the represents the event of the button 
	 *           being selected
	 */
	public void actionPerformed(ActionEvent e) {	
		
		// If two buttons were saved from last round, their values need to be hidden.
		if(buttonsSelected.size() == 2) {
			buttonsSelected.get(0).hideValue();
			buttonsSelected.get(1).hideValue();
			buttonsSelected.clear();
		}
		
		// Get the button just selected by user.
		MemoryButton button = (MemoryButton)e.getSource();
		
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
	 * Generates a list of integers 1 to limit, two of each, 
	 * randomly shuffled.
	 * 
	 * (This is a helper method for the constructor -- make private.)
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