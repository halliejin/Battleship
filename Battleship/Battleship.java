
package battleship;

public class Battleship extends Ship{
    public Battleship(){
        // set ship length to 4
        super(4);
    }

    @Override
    public String getShipType(){
        return "battleship";
    }
}
