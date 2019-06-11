package chessmodel;

import chessmodel.pieces.Piece;

import java.awt.*;

/**
 * A class representing the state of the board
 * @author Owen Salvage
 */
public class Board {

    private Piece[][] board;

    public Board(){
        board = new Piece[8][8];

    }

    /**
     * Checks if the coordinate is in range (between 0 and 7
     * @param pos the coordinate
     * @return true if it's in range, false otherwise.
     */
    public static boolean inRange(int pos){
        return pos >= 0 && pos < 8;
    }

    /**
     * Checks if the coordinates are in range (between 0 and 7
     * @param p the coordinates
     * @return true if it's in range, false otherwise.
     */
    public static boolean inRange(Point p){
        return inRange(p.x) && inRange(p.y);
    }

    /**
     * Returns true if the space specified is empty.
     * @param p the position in question
     * @return true if the space is empty, false otherwise.
     */
    private boolean isEmptySpace(Point p){
        return inRange(p.x) && inRange(p.y) && board[p.x][p.y] == null;
    }

    void movePiece(Point sP, Point eP){
        if (inRange(sP) && inRange(eP) && !isEmptySpace(sP) && board[sP.x][sP.y].canMove(board, sP, eP)){

            board[eP.x][eP.y] =  board[sP.x][sP.y];
            board[sP.x][sP.y] = null ;

        }
    }

}
