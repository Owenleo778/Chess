package chessmodel.pieces;

import chessmodel.Move;

import java.awt.*;
import java.util.ArrayList;

/**
 * A class that represents a chess piece
 * @author Owen Salvage
 */
public abstract class Piece {

    public final static int BLACK = 1;
    public final static int WHITE = -1;
    private int colour;

    public Piece(int colour){
        if (colour != BLACK && colour != WHITE){
            System.err.println("Pieces must be black or white");
            System.exit(0);
        }
        this.colour = colour;
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
