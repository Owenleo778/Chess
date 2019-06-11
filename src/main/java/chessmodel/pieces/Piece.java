package chessmodel.pieces;

import chessmodel.Board;

import java.awt.*;
import java.util.ArrayList;

/**
 * A class that represents a chess piece. The colour is represented by an integer, so that it can also be used
 * for moving purposes e.g using pos.y + colour to determine how a pawn would move.
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
    public abstract ArrayList<Point> getMoves(Piece[][] board, Point p);

    /**
     * Returns true if this piece can move in the specified way. Is only called after both points are verified
     * @param board the class holding information about the pieces
     * @param p1 the start position
     * @param p2 the end position
     * @return true
     */
    public abstract boolean canMove (Board board, Point p1, Point p2);

    /**
     * Returns true if the point supplied is empty or is occupied by an enemy piece.
     * SHOULD NOT BE USED FOR THE PAWN CLASS WHEN MOVING FORWARD.
     * @param board the board class containing the information of the other pieces
     * @param p the point to check
     * @return true if it can safely move there, false otherwise
     */
    protected boolean validEndPos(Board board, Point p){
        return board.isEmptySpace(p) || board.getColour(p) != getColour();
    }

    /**
     * Returns the colour of the piece
     * @return the colour
     */
    public int getColour(){
        return colour;
    }


}
