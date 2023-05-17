
package battleship;

public abstract class Ship {
    // The row that contains the bow (front part of the ship)
    private int bowRow;
    // The column that contains the bow (front part of the ship)
    private int bowColumn;
    // The length of the ship
    private int length;
    // A boolean that represents whether the ship is going to be placed horizontally or vertically
    private boolean horizontal;
    // An array of booleans that indicate whether that part of the ship has been hit or not
    private boolean[] hit;

    // This constructor sets the length property of the particular ship and initializes the hit array based on that length
    public Ship(int length) {
        this.length = length;
        this.hit = new boolean[length];
    }

    // getters and setters
    // Returns the ship length
    public int getLength() {
        return length;
    }

    // Returns the row corresponding to the position of the bow
    public int getBowRow() {
        return bowRow;
    }

    // Sets the value of bowRow
    public void setBowRow(int bowRow) {
        if (bowRow < 0 || bowRow > 9) {
            System.out.println("Bow row should be in range (0, 9)");
            return;
        }
        this.bowRow = bowRow;
    }

    // Returns the bow column location
    public int getBowColumn() {
        return bowColumn;
    }

    // Sets the value of bowColumn
    public void setBowColumn(int bowColumn) {
        if (bowColumn < 0 || bowColumn > 9) {
            System.out.println("Bow column should be in range (0, 9)");
            return;
        }
        this.bowColumn = bowColumn;
    }

    // Returns whether the ship is horizontal or not
    public boolean isHorizontal() {
        return horizontal;
    }

    // Sets the value of the instance variable horizontal
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    // Returns the hit array
    public boolean[] getHit() {
        return hit;
    }


    // Abstract Methods

    /**
     * Returns the type of ship as a String.
     * Every specific type of Ship (e.g.BattleShip, Cruiser, etc.) has to
     * override and implement this method and return the corresponding ship type.
     */

    public abstract String getShipType();

    // Other Methods

    /**
     * Based on the given row, column, and orientation, returns true if it is okay to put a
     * ship of this length with its bow in this location; false otherwise. The ship must not
     * overlap another ship, or touch another ship (vertically, horizontally, or diagonally),
     * and it must not ”stick out” beyond the array. Does not actually change either the
     * ship or the Ocean - it just says if it is legal to do so.
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        // if horizontal
        if (horizontal && column - this.getLength() > -2 && column < 10) {
            int len = this.getLength();
            // check the adjacency
            while (len > 0) {
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = column - 1; j <= column + 1; j++) {
                        if (i > -1 && i < 10 && j > -1 &&  j < 10) {
                            if (ocean.isOccupied(i, j)) return false;

                        }
                    }
                }
                column--;
                len--;
            }
            return true;
        }

        // if not horizontal
        if (!horizontal && row - this.getLength() > -2 && row < 10) {
            int len = this.getLength();
            // check the adjacency
            while (len > 0) {
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = column - 1; j <= column + 1; j++) {
                        if (i > -1 && i < 10 && j > -1 && j < 10) {
                            if (ocean.isOccupied(i, j)) return false;
                        }
                    }
                }
                row--;
                len--;
            }
            return true;
        }
        return false;
    };


    /**
     * “Puts” the ship in the ocean. This involves giving values to the bowRow,
     * bowColumn, and horizontal instance variables in the ship, and it also involves
     * putting a reference to the ship in each of 1 or more locations (up to 4) in the ships
     * array in the Ocean object. (Note: This will be as many as four identical
     * references; you can’t refer to a ”part” of a ship, only to the whole ship.)
     */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        // set parameters according to input
        this.setHorizontal(horizontal);
        this.setBowColumn(column);
        this.setBowRow(row);

        // not horizontal
        if (!horizontal) {
            for (int i = 0; i < this.getLength(); ++i) {
                ocean.setShipArray(this, row-i, column);
            }
        }
        // horizontal
        else {
            for (int i = 0; i < this.getLength(); ++i) {
                ocean.setShipArray(this, row, column-i);
            }
        }

    }

    /**
     * If a part of the ship occupies the given row and column, and the ship hasn’t been
     * sunk, mark that part of the ship as “hit” (in the hit array, index 0 indicates the
     * bow) and return true; otherwise return false.
     */
    boolean shootAt(int row, int column) {
        for (int i = 0; i < length; i++) {
            if ((horizontal && bowRow == row && bowColumn - i == column)
                    || (!horizontal && bowColumn == column && bowRow - i == row)) {
                hit[i] = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if every part of the ship has been hit, false otherwise
     */
    boolean isSunk(){
        for (int i = 0; i < getLength(); ++i) {
            if (!hit[i])
                return false;
        }
        return true;
    }

    /** Returns a single-character String to use in the Ocean's print method.
     * This method should return "s" if the ship has been sunk and "x" if it
     * has not been sunk. This method can be used to print out locations in
     * the ocean that have been shot at; it should not be used to print locations
     * that have not been shot at. Since toString behaves exactly the same for
     * all ship types, it is placed here in the Ship class.
     */
    @Override
    public String toString(){
        if (isSunk()) return "s";
        else return "x";
    }

}
