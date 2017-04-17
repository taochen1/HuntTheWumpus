//Name: Tao Chen
//Description: this class will create the graphical view for HuntTheWumpus game
package view;

import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.HuntTheWumpusGame;
import model.Symbol;

@SuppressWarnings("serial")
public class GraphicalView extends JPanel implements Observer {
	public static final int GRID_SIZE = 30;
	private HuntTheWumpusGame theGame;
	private Image hunter, ground, blood, goop, slime, slimePit, wumpus;

	// constructor
	public GraphicalView(HuntTheWumpusGame huntTheWumpusGame) {
		theGame = huntTheWumpusGame;
		initializePanel();
	}

	// initialize the panel
	private void initializePanel() {
		try {
			hunter = ImageIO.read(new File("images/TheHunter.png"));
			ground = ImageIO.read(new File("images/Ground.png"));
			blood = ImageIO.read(new File("images/Blood.png"));
			goop = ImageIO.read(new File("images/Goop.png"));
			slime = ImageIO.read(new File("images/Slime.png"));
			slimePit = ImageIO.read(new File("images/SlimePit.png"));
			wumpus = ImageIO.read(new File("images/Wumpus.png"));
		} catch (Exception e) {
			System.out.println("Error reading files");
			return;
		}
		repaint();
	}

	// paint whole view again to update
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

	// paint the whole view and indeed character according the model
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int size = theGame.size();
		Symbol[][] gameBoard = theGame.getGameBoard();
		Graphics2D g2 = (Graphics2D) g;
		// draw all characters except hunter not matter visible
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				g2.drawImage(ground, c * GRID_SIZE, r * GRID_SIZE, 35, 35, null);
				if (gameBoard[r][c].getSymbol() != 'O') {
					Image character = getImage(gameBoard[r][c].getSymbol());
					g2.drawImage(character, c * GRID_SIZE, r * GRID_SIZE, 35, 35, null);
				}
			}
		}
		// draw hunter if visible, draw black rectangle if not visible
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (gameBoard[r][c].isVisible()) {
					if (gameBoard[r][c].getSymbol() == 'O') {
						Image oldSymbol = getImage(theGame.getOldSybmol());
						g2.drawImage(oldSymbol, c * GRID_SIZE, r * GRID_SIZE, 35, 35, null);
						g2.drawImage(hunter, c * GRID_SIZE, r * GRID_SIZE, 30, 30, null);
					}
				} else {
					g2.setPaint(Color.BLACK);
					g2.fill(new Rectangle2D.Double(c * GRID_SIZE, r * GRID_SIZE, 35, 35));
				}
			}
		}
	}

	// return the specific Image variable
	private Image getImage(char character) {
		if (character == 'W') {
			return wumpus;
		} else if (character == 'O') {
			return hunter;
		} else if (character == 'G') {
			return goop;
		} else if (character == 'S') {
			return slime;
		} else if (character == 'P') {
			return slimePit;
		} else if (character == 'B') {
			return blood;
		} else {
			return ground;
		}
	}

}
