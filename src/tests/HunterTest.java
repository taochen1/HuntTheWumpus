//Name:Tao Chen
//Description:this class will test Hunter class
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Direction;
import model.Hunter;

public class HunterTest {

	@Test
	public void testMove() {
		Hunter hunter = new Hunter(1, 1, 10);
		hunter.move(Direction.NORTH);
		assertEquals(0, hunter.getCurrentRow());
		assertEquals(1, hunter.getCurrentColumn());
		assertEquals(0, hunter.getShootRow());
		assertEquals(1, hunter.getShootColumn());
		hunter.move(Direction.SOUTH);
		assertEquals(1, hunter.getCurrentRow());
		assertEquals(1, hunter.getCurrentColumn());
		assertEquals(1, hunter.getShootRow());
		assertEquals(1, hunter.getShootColumn());
		hunter.move(Direction.WEST);
		assertEquals(1, hunter.getCurrentRow());
		assertEquals(0, hunter.getCurrentColumn());
		assertEquals(1, hunter.getShootRow());
		assertEquals(0, hunter.getShootColumn());
		hunter.move(Direction.EAST);
		assertEquals(1, hunter.getCurrentRow());
		assertEquals(1, hunter.getCurrentColumn());
		assertEquals(1, hunter.getShootRow());
		assertEquals(1, hunter.getShootColumn());
	}

	@Test
	public void testMoveBorder() {
		Hunter hunter = new Hunter(0, 0, 10);
		hunter.move(Direction.NORTH);
		assertEquals(9, hunter.getCurrentRow());
		assertEquals(0, hunter.getCurrentColumn());
		assertEquals(9, hunter.getShootRow());
		assertEquals(0, hunter.getShootColumn());
		hunter.move(Direction.SOUTH);
		hunter.move(Direction.WEST);
		assertEquals(0, hunter.getCurrentRow());
		assertEquals(9, hunter.getCurrentColumn());
		assertEquals(0, hunter.getShootRow());
		assertEquals(9, hunter.getShootColumn());
		hunter = new Hunter(9, 9, 10);
		hunter.move(Direction.EAST);
		assertEquals(9, hunter.getCurrentRow());
		assertEquals(0, hunter.getCurrentColumn());
		assertEquals(9, hunter.getShootRow());
		assertEquals(0, hunter.getShootColumn());
	}

	@Test
	public void testShoot() {
		Hunter hunter = new Hunter(1, 1, 10);
		hunter.shoot(Direction.NORTH);
		assertEquals(1, hunter.getCurrentRow());
		assertEquals(1, hunter.getCurrentColumn());
		assertEquals(0, hunter.getShootRow());
		assertEquals(1, hunter.getShootColumn());
		hunter.shoot(Direction.SOUTH);
		assertEquals(1, hunter.getCurrentRow());
		assertEquals(1, hunter.getCurrentColumn());
		assertEquals(2, hunter.getShootRow());
		assertEquals(1, hunter.getShootColumn());
		hunter.shoot(Direction.WEST);
		assertEquals(1, hunter.getCurrentRow());
		assertEquals(1, hunter.getCurrentColumn());
		assertEquals(1, hunter.getShootRow());
		assertEquals(0, hunter.getShootColumn());
		hunter.shoot(Direction.EAST);
		assertEquals(1, hunter.getCurrentRow());
		assertEquals(1, hunter.getCurrentColumn());
		assertEquals(1, hunter.getShootRow());
		assertEquals(2, hunter.getShootColumn());
	}

	@Test
	public void testShootBorder() {
		Hunter hunter = new Hunter(0, 0, 10);
		hunter.shoot(Direction.NORTH);
		assertEquals(0, hunter.getCurrentRow());
		assertEquals(0, hunter.getCurrentColumn());
		assertEquals(9, hunter.getShootRow());
		assertEquals(0, hunter.getShootColumn());
		hunter.shoot(Direction.WEST);
		assertEquals(0, hunter.getCurrentRow());
		assertEquals(0, hunter.getCurrentColumn());
		assertEquals(0, hunter.getShootRow());
		assertEquals(9, hunter.getShootColumn());
		hunter = new Hunter(9, 9, 10);
		hunter.shoot(Direction.EAST);
		assertEquals(9, hunter.getCurrentRow());
		assertEquals(9, hunter.getCurrentColumn());
		assertEquals(9, hunter.getShootRow());
		assertEquals(0, hunter.getShootColumn());
		hunter.shoot(Direction.SOUTH);
		assertEquals(9, hunter.getCurrentRow());
		assertEquals(9, hunter.getCurrentColumn());
		assertEquals(0, hunter.getShootRow());
		assertEquals(9, hunter.getShootColumn());
	}
}
