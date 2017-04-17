//Name:Tao Chen
//Description:this class will test Symbol class
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Symbol;

public class SymbolTest {

	@Test
	public void test() {
		Symbol symbol = new Symbol();
		assertFalse(symbol.isVisible());
		assertEquals(' ',symbol.getSymbol());
		symbol.setVisible();
		symbol.setSymbol('O');
		assertTrue(symbol.isVisible());
		assertEquals('O',symbol.getSymbol());
	}

}
