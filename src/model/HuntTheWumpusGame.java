//Name: Tao Chen
//Description: this class will create the main model of the HuntTheWumpus game
package model;

import java.util.Observable;
import java.util.Random;

public class HuntTheWumpusGame extends Observable {
	private Symbol[][] board;
	private int size;
	private Hunter hunter;
	private int currentRow, currentCol;
	private char oldSymbol;
	private boolean gameOver;
	Random rand = new Random();

	// constructor
	public HuntTheWumpusGame() {
		size = 10;
		oldSymbol = ' ';
		gameOver = false;
		initializeBoard();

	}

	// initialize the game board and place all characters randomly
	private void initializeBoard() {
		board = new Symbol[size][size];
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				board[row][col] = new Symbol();
			}
		}

		int WRow = rand.nextInt(size);
		int WCol = rand.nextInt(size);
		board[WRow][WCol].setSymbol('W');
		for (int a = WRow - 1; a <= WRow + 1; a++) {
			for (int b = WCol - 1; b <= WCol + 1; b++) {
				if (!(a == WRow && b == WCol)) {
					board[wrapAroundIndex(a)][wrapAroundIndex(b)].setSymbol('B');
				}
			}
		}
		board[wrapAroundIndex(WRow - 2)][WCol].setSymbol('B');
		board[wrapAroundIndex(WRow + 2)][WCol].setSymbol('B');
		board[WRow][wrapAroundIndex(WCol - 2)].setSymbol('B');
		board[WRow][wrapAroundIndex(WCol + 2)].setSymbol('B');

		int numOfSlimePit = rand.nextInt(3) + 3;
		int i = 0;
		while (numOfSlimePit != i) {
			int PRow = rand.nextInt(size);
			int PCol = rand.nextInt(size);
			if (board[PRow][PCol].getSymbol() == ' ') {
				board[PRow][PCol].setSymbol('P');
				placeGoopAndSlime(wrapAroundIndex(PRow - 1), PCol);
				placeGoopAndSlime(wrapAroundIndex(PRow + 1), PCol);
				placeGoopAndSlime(PRow, wrapAroundIndex(PCol - 1));
				placeGoopAndSlime(PRow, wrapAroundIndex(PCol + 1));
				i++;
			}
		}
		placeHunter();

	}

	// make hunter can move or shoot around
	private int wrapAroundIndex(int index) {
		if (index < 0)
			index += size;
		if (index > size - 1)
			index = index - size;
		return index;
	}

	// place slime and goop character
	private void placeGoopAndSlime(int row, int col) {
		if (board[row][col].getSymbol() == ' ') {
			board[row][col].setSymbol('S');
		} else if (board[row][col].getSymbol() == 'B') {
			board[row][col].setSymbol('G');
		}
	}

	// place the hunter
	private void placeHunter() {
		int HRow = rand.nextInt(size);
		int HCol = rand.nextInt(size);
		while (true) {
			if (board[HRow][HCol].getSymbol() == ' ') {
				board[HRow][HCol].setSymbol('O');
				board[HRow][HCol].setVisible();
				break;
			} else {
				HRow = rand.nextInt(size);
				HCol = rand.nextInt(size);
			}
		}
		currentRow = HRow;
		currentCol = HCol;
		hunter = new Hunter(currentRow, currentCol, size);
	}

	// return the size of the game
	public int size() {
		return size;
	}

	// set up static board for test
	public void setStaticBoard(Symbol[][] staticBoard) {
		board = staticBoard;
	}

	// set up static beginning location hunter for test
	public void setHunter(Hunter staticHunter) {
		hunter = staticHunter;
	}

	// return the board 2D array
	public Symbol[][] getGameBoard() {
		return board;
	}

	// return the hunter
	public Hunter getHunter() {
		return hunter;
	}

	// check issue when hunter move
	public void getMoveAndCheck(int row, int col) {
		board[currentRow][currentCol].setSymbol(oldSymbol);
		oldSymbol = board[row][col].getSymbol();
		board[row][col].setSymbol('O');
		board[row][col].setVisible();
		if (oldSymbol == 'W' || oldSymbol == 'P') {
			gameOver();
		}
		currentRow = row;
		currentCol = col;
		setChanged();
		notifyObservers();
	}

	// check if hunter shoot or not
	public boolean checkIsShoot() {
		if (currentRow == hunter.getShootRow() && currentCol == hunter.getShootColumn()) {
			return false;
		} else {
			return true;
		}
	}

	// check if shoot the Wumpus or not
	public boolean isShootWumpus() {
		gameOver();
		setChanged();
		notifyObservers();
		if (board[hunter.getShootRow()][hunter.getShootColumn()].getSymbol() == 'W') {
			return true;
		} else {
			return false;
		}
	}

	// set every index to visible if game over
	private void gameOver() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j].setVisible();
			}
		}
		gameOver = true;
	}

	// check if game over or not
	public char getOldSybmol() {
		return oldSymbol;
	}

	public boolean isGameOver() {
		return gameOver;
	}
}
