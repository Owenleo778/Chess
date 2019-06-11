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
     * @param board the position of all pieces in play
     * @param p the position of the piece
     * @return the list of possible moves
     */
    public abstract ArrayList<Move> getMoves(Piece[][] board, Point p);

    /**
     * Returns true if this piece can move in the specified way
     * @param board the position of all pieces in play
     * @param p1 the start position
     * @param p2 the end position
     * @return true
     */
    public abstract boolean canMove (Piece[][] board, Point p1, Point p2);

    public int getColour(){
        return colour;
    }


}
