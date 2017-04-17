//Name: Tao Chen
//Description:this class will control the HuntTheWumpus game and display the whole game to let user play
package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import model.Direction;
import model.HuntTheWumpusGame;
import model.Hunter;
import view.GraphicalView;
import view.TextView;

@SuppressWarnings("serial")
public class HuntTheWumpusGUI extends JFrame {
	// main function to make game visible
	public static void main(String[] args) {
		HuntTheWumpusGUI game = new HuntTheWumpusGUI();
		game.setVisible(true);
	}

	private HuntTheWumpusGame theGame;
	private Hunter theHunter;
	public static final int width = 450;
	public static final int height = 350;

	// include four direction moving and shooting button and message label
	private JPanel controlPanel;
	private JPanel movePanel;// four direction moving button
	private JPanel shootPanel;// four direction shooting button
	private JLabel move = new JLabel("move");
	private JLabel shoot = new JLabel("shoot");
	private JLabel message = new JLabel("Game Start", SwingConstants.CENTER);
	private JButton moveUp = new JButton("\u2191");
	private JButton moveDown = new JButton("\u2193");
	private JButton moveLeft = new JButton("\u2190");
	private JButton moveRight = new JButton("\u2192");
	private JButton shootUp = new JButton("\u2191");
	private JButton shootDown = new JButton("\u2193");
	private JButton shootLeft = new JButton("\u2190");
	private JButton shootRight = new JButton("\u2192");

	// include two different views of game board
	private JTabbedPane viewPanels;
	private JPanel textView;
	private JPanel graphicalView;

	// constructor
	public HuntTheWumpusGUI() {
		initializeGameForTheFirstTime();
		setupLayout();
		addObservers();
	}

	// set up the whole layout for the game and listener for all buttons
	private void setupLayout() {
		textView = new TextView(theGame);
		graphicalView = new GraphicalView(theGame);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width + 200, height + 200);
		setLocation(0, 0);
		setTitle("Hunt The Wumpus");
		viewPanels = new JTabbedPane();
		controlPanel = new JPanel();
		movePanel = new JPanel();
		shootPanel = new JPanel();
		this.setLayout(null);

		controlPanel.setSize((width - 50) / 2, height + 100);
		controlPanel.setLocation(25, 25);
		controlPanel.setLayout(null);

		viewPanels.setSize(height, height);
		viewPanels.setLocation(250, 25);
		movePanel.setSize((width - 50) / 2, (height - 50) / 3);
		movePanel.setLocation(0, 0);
		shootPanel.setSize((width - 50) / 2, (height - 50) / 3);
		shootPanel.setLocation(0, 150);
		viewPanels.add(graphicalView, "Graphical View");
		viewPanels.add(textView, "Text View");
		ButtonListener buttonListener = new ButtonListener();

		moveUp.addActionListener(buttonListener);
		moveDown.addActionListener(buttonListener);
		moveLeft.addActionListener(buttonListener);
		moveRight.addActionListener(buttonListener);
		shootUp.addActionListener(buttonListener);
		shootDown.addActionListener(buttonListener);
		shootLeft.addActionListener(buttonListener);
		shootRight.addActionListener(buttonListener);

		movePanel.setLayout(new BorderLayout());
		movePanel.add(move, BorderLayout.SOUTH);
		movePanel.add(moveUp, BorderLayout.NORTH);
		movePanel.add(moveLeft, BorderLayout.WEST);
		movePanel.add(moveRight, BorderLayout.EAST);
		movePanel.add(moveDown, BorderLayout.CENTER);

		shootPanel.setLayout(new BorderLayout());
		shootPanel.add(shoot, BorderLayout.SOUTH);
		shootPanel.add(shootUp, BorderLayout.NORTH);
		shootPanel.add(shootLeft, BorderLayout.WEST);
		shootPanel.add(shootRight, BorderLayout.EAST);
		shootPanel.add(shootDown, BorderLayout.CENTER);

		message.setSize((width - 50) / 2, 100);
		message.setLocation(0, 250);

		controlPanel.add(movePanel);
		controlPanel.add(shootPanel);
		controlPanel.add(message);
		add(controlPanel);
		add(viewPanels);
	}

	// initialize the game at the beginning
	private void initializeGameForTheFirstTime() {
		theGame = new HuntTheWumpusGame();
		theHunter = theGame.getHunter();
	}

	// make two view can switch to each other
	private void addObservers() {
		theGame.addObserver((Observer) textView);
		theGame.addObserver((Observer) graphicalView);
	}

	// Shows all hazards revealed at end of game
	private void endGame() {
		moveUp.setEnabled(false);
		moveDown.setEnabled(false);
		moveLeft.setEnabled(false);
		moveRight.setEnabled(false);
		shootUp.setEnabled(false);
		shootDown.setEnabled(false);
		shootLeft.setEnabled(false);
		shootRight.setEnabled(false);
	}

	// private class for listener
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton buttonClicked = (JButton) arg0.getSource();
			if (buttonClicked == moveUp) {
				theHunter.move(Direction.NORTH);

			}
			if (buttonClicked == moveDown) {
				theHunter.move(Direction.SOUTH);
			}
			if (buttonClicked == moveLeft) {
				theHunter.move(Direction.WEST);
			}
			if (buttonClicked == moveRight) {
				theHunter.move(Direction.EAST);
			}

			if (buttonClicked == shootUp) {
				theHunter.shoot(Direction.NORTH);
			}
			if (buttonClicked == shootDown) {
				theHunter.shoot(Direction.SOUTH);
			}
			if (buttonClicked == shootLeft) {
				theHunter.shoot(Direction.WEST);
			}
			if (buttonClicked == shootRight) {
				theHunter.shoot(Direction.EAST);
			}
			// update hunter's info to the game model
			theGame.getMoveAndCheck(theHunter.getCurrentRow(), theHunter.getCurrentColumn());
			if (theGame.getOldSybmol() == 'P') {
				message.setText("<html>You walked into a bottomless pit<br>You lost</html>");
				endGame();
			} else if (theGame.getOldSybmol() == 'S') {
				message.setText("You feel a breeze");
			} else if (theGame.getOldSybmol() == 'B') {
				message.setText("You smell a wumpus");
			} else if (theGame.getOldSybmol() == 'G') {
				message.setText("<html>You smell a wumpus <br>and feel a breeze</html>");
			} else if (theGame.getOldSybmol() == 'W') {
				message.setText("<html>You walked into a wumpus<br>You lost</html>");
				endGame();
			} else {
				message.setText("");
			}
			// check if hunter shoot or not
			if (theGame.checkIsShoot()) {
				//check if shoot the Wumpus or not
				if (theGame.isShootWumpus()) {
					message.setText("<html>You shoot a wumpus<br>You win</html>");
				} else {
					message.setText("<html>You fail to shoot wumpus<br>You lost</html>");
				}
				endGame();
			}
		}
	}
}
