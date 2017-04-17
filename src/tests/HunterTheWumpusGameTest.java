//Name:Tao Chen
//Description:this class will test HunterTheWumpusGame class
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Direction;
import model.HuntTheWumpusGame;
import model.Hunter;
import model.Symbol;

public class HunterTheWumpusGameTest {
	// set up the static game for testing
	// [O] [ ] [ ] [B] [B] [W] [B] [B] [ ] [ ]
	// [ ] [ ] [ ] [ ] [B] [B] [G] [ ] [ ] [ ]
	// [ ] [ ] [ ] [ ] [ ] [G] [P] [S] [ ] [ ]
	// [ ] [ ] [S] [ ] [ ] [ ] [S] [ ] [ ] [ ]
	// [ ] [S] [P] [S] [ ] [ ] [ ] [ ] [ ] [ ]
	// [ ] [ ] [S] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
	// [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
	// [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [S] [ ]
	// [ ] [ ] [ ] [ ] [ ] [B] [ ] [S] [P] [S]
	// [ ] [ ] [ ] [ ] [B] [B] [B] [ ] [S] [ ]
	public static HuntTheWumpusGame setStaticGame() {
		HuntTheWumpusGame game = new HuntTheWumpusGame();
		Symbol[][] board = new Symbol[10][10];
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				board[row][col] = new Symbol();
			}
		}
		board[0][3].setSymbol('B');
		board[0][4].setSymbol('B');
		board[0][6].setSymbol('B');
		board[0][7].setSymbol('B');
		board[1][4].setSymbol('B');
		board[1][5].setSymbol('B');
		board[8][5].setSymbol('B');
		board[9][4].setSymbol('B');
		board[9][5].setSymbol('B');
		board[9][6].setSymbol('B');

		board[0][5].setSymbol('W');

		board[1][6].setSymbol('G');
		board[2][5].setSymbol('G');

		board[2][6].setSymbol('P');
		board[4][2].setSymbol('P');
		board[8][8].setSymbol('P');

		board[2][7].setSymbol('S');
		board[3][2].setSymbol('S');
		board[3][6].setSymbol('S');
		board[4][1].setSymbol('S');
		board[4][3].setSymbol('S');
		board[5][2].setSymbol('S');
		board[7][8].setSymbol('S');
		board[8][7].setSymbol('S');
		board[8][9].setSymbol('S');
		board[9][8].setSymbol('S');

		board[0][0].setSymbol('O');
		board[0][0].setVisible();

		game.setStaticBoard(board);
		Hunter hunter = new Hunter(0, 0, 10);
		game.setHunter(hunter);
		return game;
	}

	@Test
	public void testbeginning() {
		HuntTheWumpusGame theGame = setStaticGame();
		assertEquals(10, theGame.size());
		assertEquals(0, theGame.getHunter().getCurrentRow());
		assertEquals(0, theGame.getHunter().getCurrentColumn());
		// print the static game board if indeed
		// Symbol[][] board = theGame.getGameBoard();
		// for (int row = 0; row < 10; row++) {
		// for (int col = 0; col < 10; col++) {
		// System.out.print("[" + board[row][col].getSymbol() + "] ");
		// }
		// System.out.println();
		// }
	}

	// test hunter walk into the Wumpus
	@Test
	public void testGame1() {
		HuntTheWumpusGame theGame = setStaticGame();
		theGame.getHunter().move(Direction.EAST);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().move(Direction.EAST);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().move(Direction.EAST);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().move(Direction.EAST);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().move(Direction.EAST);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		assertEquals('W', theGame.getOldSybmol());
		assertTrue(theGame.isGameOver());
	}

	// test hunter walk into the SlimePit
	@Test
	public void testGame2() {
		HuntTheWumpusGame theGame = setStaticGame();
		theGame.getHunter().move(Direction.EAST);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().move(Direction.EAST);
		assertFalse(theGame.isGameOver());
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().move(Direction.SOUTH);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().move(Direction.SOUTH);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().move(Direction.SOUTH);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().move(Direction.SOUTH);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		assertEquals('P', theGame.getOldSybmol());
		assertFalse(theGame.checkIsShoot());
		assertTrue(theGame.isGameOver());
	}

	// test hunter fail to shoot the Wumpus
	@Test
	public void testGame3() {
		HuntTheWumpusGame theGame = setStaticGame();
		theGame.getHunter().shoot(Direction.SOUTH);
		assertTrue(theGame.checkIsShoot());
		assertFalse(theGame.isShootWumpus());
		assertTrue(theGame.isGameOver());
	}

	// test hunter shoot the Wumpus successfully
	@Test
	public void testGame4() {
		HuntTheWumpusGame theGame = setStaticGame();
		theGame.getHunter().move(Direction.EAST);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().move(Direction.EAST);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().move(Direction.EAST);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().move(Direction.EAST);
		theGame.getMoveAndCheck(theGame.getHunter().getCurrentRow(), theGame.getHunter().getCurrentColumn());
		theGame.getHunter().shoot(Direction.EAST);
		assertTrue(theGame.checkIsShoot());
		assertTrue(theGame.isShootWumpus());
		assertTrue(theGame.isGameOver());
	}
}
