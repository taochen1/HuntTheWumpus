//Name:Tao Chen
//Description:this class will be the game board type and store one character and one boolean information
package model;

public class Symbol {
	private boolean visible;
	private char character;

	// constructor
	public Symbol() {
		visible = false;
		character = ' ';
	}

	// change the character
	public void setSymbol(char symbol) {
		character = symbol;
	}

	// get the character
	public char getSymbol() {
		return character;
	}

	// check visible
	public boolean isVisible() {
		return visible;
	}

	// change to visible
	public void setVisible() {
		visible = true;
	}
}
