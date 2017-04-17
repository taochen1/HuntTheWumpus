//Name:Tao Chen
//Description:this class will create the text view of the HuntTheWumpus game
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.HuntTheWumpusGame;
import model.Symbol;

@SuppressWarnings("serial")
public class TextView extends JPanel implements Observer {

	private HuntTheWumpusGame theGame;
	private JLabel[][] board = null;

	// constructor
	public TextView(HuntTheWumpusGame huntTheWumpusGame) {
		theGame = huntTheWumpusGame;
		setBackground(Color.GREEN);
		initializePanel();
	}

	// initialize the panel
	private void initializePanel() {
		int size = theGame.size();
		setLayout(new GridLayout(size, size, 5, 5));
		Symbol[][] gameBoard = theGame.getGameBoard();
		board = new JLabel[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = new JLabel("", SwingConstants.CENTER);
				board[i][j].setFont(new Font("monospaced", Font.PLAIN, 10));
				// display the X if not visible and character if visible
				if (gameBoard[i][j].isVisible()) {
					board[i][j].setText("[" + gameBoard[i][j].getSymbol() + "]");
				} else {

					board[i][j].setText("[" + "X" + "]");
				}
				add(board[i][j]);
			}
		}
	}

	// update the board
	@Override
	public void update(Observable arg0, Object arg1) {
		int size = theGame.size();
		Symbol[][] gameBoard = theGame.getGameBoard();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (gameBoard[i][j].isVisible()) {
					board[i][j].setText("[" + gameBoard[i][j].getSymbol() + "]");
				} else {
					board[i][j].setText("[" + "X" + "]");
				}
				add(board[i][j]);
			}
		}

	}

}
