
package battleship;

import java.util.Scanner;


public class BattleshipGame {

    private Ocean ocean;
    private boolean gameOver;
    private Scanner scanner;

    public BattleshipGame() {
        this.ocean = new Ocean();
        this.ocean.placeAllShipsRandomly();
        this.gameOver = false;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        BattleshipGame game = new BattleshipGame();
        game.run();
    }

    public void run() {
        // welcome message
        printWelcome();


        while (!gameOver) {
            // print map
            ocean.print();

            // debug
            //ocean.printWithShips();

            // ask the player to shoot
            printShootMessage();

            boolean flag = false;
            // get the coordinate
            while (!flag) {
                try {
                    String input = scanner.next().strip();
                    String[] coor = input.split(",");
                    // check if the input is valid
                    if (coor.length != 2) {
                        System.out.println("Invalid input format. To shoot, enter the position as row,column: ");
                        continue;
                    }

                    int row = Integer.parseInt(coor[0]);
                    int col = Integer.parseInt(coor[1]);

                    // check the boundary
                    if (row < 0 || row > 9 || col < 0 || col > 9 ) {
                        System.out.println("Out of boundary! To shoot, enter the position as row,column: ");
                        continue;
                    }


                    boolean hit = ocean.shootAt(row, col);

                    if (hit) {
                        Ship ship = ocean.getShipArray()[row][col];
                        System.out.println("You hit a ship!");

                        // check if the ship has been sunk
                        if (ship.isSunk()) {
                            System.out.println("You sunk a " + ship.getShipType() + "!");
                        }
                    }

                    else {
                        System.out.println("You missed.");
                    }
                    System.out.println("Shots taken: " + ocean.getShotsFired());
                    flag = true;
                }

                catch (Exception e) {
                    System.out.println("Invalid input format. To shoot, enter the position as row,column: ");
                }
            }

            gameOver = ocean.isGameOver();
        }

        System.out.println("Congratulations! You sunk all the ships in " + ocean.getShotsFired() + " shots.");
        scanner.close();
    }

    public void printWelcome(){
        System.out.println("Welcome to the Battleship game!");
        System.out.println("The computer will place all ships randomly in a 10 by 10 map.");
        System.out.println("Sink all ships to win the game!");
        System.out.println("Now let's get started!");
        System.out.println();
    }

    public void printShootMessage(){
        System.out.println("To shoot, enter the position as row,column: ");
    }
}