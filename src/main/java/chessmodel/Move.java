package chessmodel;

import java.awt.*;

/**
 * A class representing a chess move.
 * @author Owen Salvage
 */
public class Move {

    Point p1;
    Point p2;

    public Move(Point p1, Point p2){
        if (!Board.inRange(p1) || !Board.inRange(p2)){
            System.err.println("Not in range");
            System.exit(0);
        }
        this.p1 = p1;
        this.p2 = p2;
    }
}
