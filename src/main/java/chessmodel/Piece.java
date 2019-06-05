package chessmodel;

import java.awt.*;
import java.util.ArrayList;

/**
 * A class that represents a chess piece
 * @author Owen Salvage
 */
public abstract class Piece {

    private String colour;

    public Piece(String colour){
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "A " + colour + " piece.";
    }

    /**
     * Returns a list of all possible moves
     * @param p the position of the piece
     * @return the list of possible moves
     */
    public abstract ArrayList<Move> getMoves(Point p);

    /**
     * Returns true if this piece can move in the specified way
     * @param m the move
     * @return true
     */
    public abstract boolean canMove (Move m);


}
