
package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;

	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests
		// test get length for all types of ships
		Submarine submarine = new Submarine();
		assertEquals(1, submarine.getLength());
		Destroyer destroyer = new Destroyer();
		assertEquals(2, destroyer.getLength());
		Cruiser cruiser = new Cruiser();
		assertEquals(3, cruiser.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		// test getRow for a new cruiser
		Ship cruiser = new Cruiser();
		row = 4;
		column = 5;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests
		// test getColumn for a new submarine
		Ship submarine = new Submarine();
		row = 6;
		column = 7;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		submarine.setBowColumn(column);
		assertEquals(column, submarine.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		// test hit this battleship again
		assertEquals(hits[2], ship.getHit()[2]);
		assertEquals(hits[3], ship.getHit()[3]);
	}
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		// test if the correct ship type is gotten
		Submarine submarine = new Submarine();
		Cruiser cruiser = new Cruiser();
		EmptySea emptySea = new EmptySea();
		Destroyer destroyer = new Destroyer();

		assertEquals("submarine", submarine.getShipType());
		assertEquals("cruiser", cruiser.getShipType());
		assertEquals("empty", emptySea.getShipType());
		assertEquals("destroyer", destroyer.getShipType());
	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		// put a new cruiser and test the horizontal status
		Ship cruiser = new Cruiser();
		row = 6;
		column = 7;
		horizontal = false;
		cruiser.placeShipAt(row,column,horizontal,ocean);
		assertFalse(cruiser.isHorizontal());
	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		// create a new submarine and test if the setRow is working well
		Submarine submarine = new Submarine();
		submarine.setBowRow(6);
		assertEquals(6, submarine.getBowRow());

		// create a new cruiser and test if the setRow is working well
		Cruiser cruiser = new Cruiser();
		cruiser.setBowRow(7);
		assertEquals(7, cruiser.getBowRow());



	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
		// create a new submarine and test if the setColumn is working well
		Submarine submarine = new Submarine();
		submarine.setBowColumn(6);
		assertEquals(6, submarine.getBowColumn());

		// create a new cruiser and test if the setColumn is working well
		Cruiser cruiser = new Cruiser();
		cruiser.setBowColumn(7);
		assertEquals(7, cruiser.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		Submarine submarine = new Submarine();
		submarine.setHorizontal(false);
		assertFalse(submarine.isHorizontal());

		Cruiser cruiser = new Cruiser();
		cruiser.setHorizontal(true);
		assertTrue(cruiser.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests
		// out of boundary
		assertTrue(battleship.okToPlaceShipAt(1, 1, true, ocean));
		Ship cruiser = new Cruiser();
		assertTrue(cruiser.okToPlaceShipAt(1, 6, true, ocean));
	}

	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests
		// test adjacency
		Battleship battleship3 = new Battleship();
		row = 1;
		column = 6;
		horizontal = true;
		boolean ok3 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3, "Not OK to place ship diagonally adjacent.");
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests
		// put a new submarine and test if it is placed in the correct position
		Ship submarine = new Submarine();
		row = 6;
		column = 7;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
		assertEquals(column, submarine.getBowColumn());
		assertTrue(submarine.isHorizontal());

		assertEquals("empty", ocean.getShipArray()[7][7].getShipType());
		assertEquals(submarine, ocean.getShipArray()[6][7]);

	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
		// put a new submarine and test the hit array
		Ship submarine = new Submarine();
		submarine.placeShipAt(6,7,true,ocean);
		ocean.shootAt(6,7);
		boolean[] hitArray1 = {true};
		assertArrayEquals(submarine.getHit(), hitArray1);

		// put a new destroyer and test the hit array
		Ship destroyer = new Destroyer();
		destroyer.placeShipAt(3,4, true, ocean);
		ocean.shootAt(3,4);
		ocean.shootAt(3,3);
		boolean[] hitArray2 = {true, true};
		assertArrayEquals(destroyer.getHit(), hitArray2);

	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		// put a new destroyer and test if it will sink after being shot
		Ship destroyer = new Destroyer();
		destroyer.placeShipAt(0, 1, true, ocean);
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(0, 1));
		assertTrue(destroyer.shootAt(0, 0));
		assertTrue(destroyer.isSunk());
		
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests
		// put a new submarine and test the output after shot
		Ship submarine = new Submarine();
		submarine.placeShipAt(0, 0, true, ocean);
		submarine.shootAt(0, 0);
		assertEquals("s", submarine.toString());

		// put a new destroyer and test the output after shot
		Ship destroyer = new Destroyer();
		destroyer.placeShipAt(6, 7, true, ocean);
		destroyer.shootAt(6, 7);
		assertEquals("x", destroyer.toString());

	}

}
