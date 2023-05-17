
package battleship;

import java.util.Random;

public class Ocean {
    // Used to quickly determine which ship is in any given location
    private Ship[][]ships = new Ship[10][10];
    // The total number of shots fired by the user
    private int shotsFired;

    /** The number of times a shot hit a ship. If the user shoots the same part of a ship
     * more than once, every hit is counted, even though additional “hits” don’t do the
     * user any good.
     */
    private int hitCount;
    // The number of ships sunk (10 ships in all)
    private int shipsSunk;
    // set up a table to record the positions being fired
    private boolean[][] fired = new boolean[10][10];

    public Ocean(){
        // initialize variables
        this.shotsFired = 0;
        this.hitCount = 0;
        this.shipsSunk = 0;
        // initialize the ocean
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 10; ++j){
                EmptySea emptySea = new EmptySea();
                emptySea.placeShipAt(i, j, true, this);
            }
        }
    }

    /**
     * Place all ten ships randomly on the (initially empty) ocean. Place larger ships
     * before smaller ones, or you may end up with no legal place to put a large ship.
     * You will want to use the Random class in the java.util package, so look that
     * up in the Java API.
     */
    void placeAllShipsRandomly(){
        // import the random class
        Random random = new Random();

        // place 1 battleship first
        Battleship battleship = new Battleship();
        // initialize the location-related variables
        int row = 0;
        int column = 0;
        boolean okToPlace = false;
        boolean horizontal = true;

        while (!okToPlace){
            row = random.nextInt(10);
            column = random.nextInt(10);
            horizontal = random.nextBoolean();
            // check if this battleship can be placed to this random position
            okToPlace = battleship.okToPlaceShipAt(row, column, horizontal, this);
        }
        // place the battleship
        battleship.placeShipAt(row, column, horizontal, this);


        // place 2 cruisers
        // similar to the battleship placement
        for (int i = 0; i < 2; ++i){
            Cruiser cruiser = new Cruiser();
            okToPlace = false;
//            random = new Random();

            while (!okToPlace){
                row = random.nextInt(10);
                column = random.nextInt(10);
                horizontal = random.nextBoolean();
                okToPlace = cruiser.okToPlaceShipAt(row,column,horizontal,this);
            }
            cruiser.placeShipAt(row,column,horizontal,this);
        }


        // place 3 destroyers
        // similar to previous parts
        for (int i = 0; i < 3; ++i){
            Destroyer destroyer = new Destroyer();
            okToPlace = false;
//            random = new Random();

            while (!okToPlace){
                row = random.nextInt(10);
                column = random.nextInt(10);
                horizontal = random.nextBoolean();
                okToPlace = destroyer.okToPlaceShipAt(row,column,horizontal,this);
            }
            destroyer.placeShipAt(row,column,horizontal,this);
        }



        // place 4 submarines
        // similar to previous parts
        for (int i = 0; i < 4; ++i){
            Submarine submarine = new Submarine();
            okToPlace = false;
//            random = new Random();

            while (!okToPlace){
                row = random.nextInt(10);
                column = random.nextInt(10);
                horizontal = random.nextBoolean();
                okToPlace = submarine.okToPlaceShipAt(row,column,horizontal,this);
            }
            submarine.placeShipAt(row,column,horizontal,this);
        }
    }


    /**
     * Returns true if the given location contains a ship, false if it does not
     */
    boolean isOccupied (int row, int column){
        return !this.ships[row][column].getShipType().equals("empty");
    }


    /**
     * Returns true if the given location contains a ”real” ship, still afloat, (not an
     * EmptySea), false if it does not. In addition, this method updates the number of
     * shots that have been fired, and the number of hits.
     */
    boolean shootAt (int row, int column){
        // add the shotsFired by 1
        this.shotsFired++;
        // record the position being fired
        this.fired[row][column] = true;
        // check if this position still holds a ship that is not sunk
        if (this.ships[row][column].shootAt(row,column)){
            this.hitCount++;
            // check if this ship is sunk
            if (this.ships[row][column].isSunk()){
                // add the counter by 1
                this.shipsSunk++;
            }
            // shoot
            return true;
        }
        // miss
        else return false;
    }



    /**
     * Returns the number of shots fired (in the game)
     */
    int getShotsFired() {
        return this.shotsFired;
    }

    /**
     * Returns the number of hits recorded (in the game). All hits are counted, not just
     * the first time a given square is hit.
     */
    int getHitCount(){
        return this.hitCount;
    }

    /**
     * Returns the number of ships sunk (in the game)
     */
    int getShipsSunk(){
        return this.shipsSunk;
    }

    /**
     * Returns true if all ships have been sunk, otherwise false
     */
    boolean isGameOver(){
        return this.getShipsSunk() == 10;
    }


    /**
     * Returns the 10x10 array of Ships. The methods in the Ship class that take an
     * Ocean parameter need to be able to look at the contents of this array; the
     * placeShipAt() method even needs to modify it. While it is undesirable to
     * allow methods in one class to directly access instance variables in another class,
     * sometimes there is just no good alternative.
     */
    Ship[][] getShipArray(){
        return this.ships;
    }

    void setShipArray (Ship ship, int row, int column){
        this.ships[row][column] = ship;
    }

    boolean[][] getFired(){ return this.fired;}


    /**
     * Prints the Ocean. To aid the user, row numbers should be displayed along the
     * left edge of the array, and column numbers should be displayed along the top.
     * Numbers should be 0 to 9, not 1 to 10.
     */
    void print(){
        // get the list from fired
        boolean[][] fired = this.getFired();

        // first line
        System.out.println("   0 1 2 3 4 5 6 7 8 9");
        System.out.println("   -------------------");

        // fill in the chart
        for (int i = 0; i < 10; ++i){
            System.out.print(i + "  ");
            for (int j = 0; j < 10; ++j){
                if (!fired[i][j]) {
                    System.out.print(". ");;
                }
                else{
                    System.out.print(ships[i][j].toString() + " ");;
                }
            }
            System.out.println("  ");
        }
    }


    /**
     * Like the print() method, this method prints the Ocean with row numbers
     * displayed along the left edge of the array, and column numbers displayed along
     * the top. Numbers should be 0 to 9, not 1 to 10. The top left corner square
     * should be 0, 0.
     * USED FOR DEBUGGING PURPOSES ONLY.
     */
    void printWithShips(){
        // first line
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        System.out.println("  -------------------");

        for (int i = 0; i < 10; ++i){
            System.out.print(i + " ");
            for (int j = 0; j < 10; ++j){
                Ship ship = ships[i][j];
                String shipLetter = "";
                if (ship.getShipType().equals("battleship")) {
                    shipLetter = "b";
                } else if (ship.getShipType().equals("cruiser")) {
                    shipLetter = "c";
                } else if (ship.getShipType().equals("destroyer")) {
                    shipLetter = "d";
                } else if (ship.getShipType().equals("submarine")) {
                    shipLetter = "s";
                }

                if (ship.getShipType().equals("empty")) {
                    System.out.print("  ");
                } else {
                    System.out.print(shipLetter + " ");
                }
            }
            System.out.println("  ");
        }

    }


}
