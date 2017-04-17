//Name:Tao Chen
//Description:this class will create the hunter model and can move and shoot four directions
package model;

import model.Direction;

public class Hunter {
	private int currentRow, currentCol;
	private int shootRow, shootCol;

	private int size;

	// constructor
	public Hunter(int beginRow, int beginCol, int boardSize) {
		size = boardSize;
		currentRow = beginRow;
		currentCol = beginCol;
		shootRow = currentRow;
		shootCol = currentCol;
	}

	// move indeed direction
	public void move(Direction direction) {
		if (direction == Direction.NORTH)
			currentRow--;
		if (direction == Direction.EAST)
			currentCol++;
		if (direction == Direction.SOUTH)
			currentRow++;
		if (direction == Direction.WEST)
			currentCol--;

		// Allow wrap around
		if (currentCol < 0)
			currentCol = size - 1;
		if (currentCol > size - 1)
			currentCol = 0;

		if (currentRow < 0)
			currentRow = size - 1;
		if (currentRow > size - 1)
			currentRow = 0;

		shootRow = currentRow;
		shootCol = currentCol;
	}

	// shoot indeed direction
	public void shoot(Direction direction) {
		if (direction == Direction.NORTH) {
			shootRow = currentRow - 1;
			shootCol = currentCol;
		}
		if (direction == Direction.EAST) {
			shootCol = currentCol + 1;
			shootRow = currentRow;
		}
		if (direction == Direction.SOUTH) {
			shootRow = currentRow + 1;
			shootCol = currentCol;
		}
		if (direction == Direction.WEST) {
			shootCol = currentCol - 1;
			shootRow = currentRow;
		}

		// Allow wrap around
		if (shootCol < 0)
			shootCol = size - 1;
		if (shootCol > size - 1)
			shootCol = 0;

		if (shootRow < 0)
			shootRow = size - 1;
		if (shootRow > size - 1)
			shootRow = 0;

	}

	// return the row of hunter location
	public int getCurrentRow() {
		return currentRow;
	}

	// return the column of hunter location
	public int getCurrentColumn() {
		return currentCol;
	}

	// return the row of shooting location
	public int getShootRow() {
		return shootRow;
	}

	// return the column of shooting location
	public int getShootColumn() {
		return shootCol;
	}
}
