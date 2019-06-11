package chessmodel;

import chessmodel.pieces.*;

import java.awt.*;

/**
 * A class representing the state of the board. x = 0, y = 0 -> top left corner
 * @author Owen Salvage
 */
public class Board {

    private Piece[][] board;
    private int turn;

    public Board(){
        board = new Piece[8][8];
        turn = Piece.WHITE;

        board[1][1] = new Bishop(1);
        board[0][0] =  new Pawn(-1);
        //board[2][2] =  new Pawn(-1);

        System.out.println(board[1][1].canMove(this, new Point(1, 1), new Point(0, 0)));
    }

    public static void main(String[] args) {
        Board b = new Board();
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
     * @param x the x position in question
     * @param y the y position in question
     * @return true if the space is empty, false otherwise.
     */
    public boolean isEmptySpace(int x, int y){
        return board[x][y] == null;
    }

    /**
     * Returns true if the space specified is empty.
     * @param p the position in question
     * @return true if the space is empty, false otherwise.
     */
    public boolean isEmptySpace(Point p){
        return isEmptySpace(p.x, p.y);
    }

    public int getColour(Point p) throws NullPointerException {
        return board[p.x][p.y].getColour();
    }


    //ADD VERIFICATION NOT LEAVING IN CHECKMATE
    boolean movePiece(Point sP, Point eP){
        if (inRange(sP) && inRange(eP) && !isEmptySpace(sP) && board[sP.x][sP.y].getColour() == turn){

            if(board[sP.x][sP.y].canMove(this, sP, eP)) {
                board[eP.x][eP.y] = board[sP.x][sP.y];
                board[sP.x][sP.y] = null;
                return true;
            }
        }
        return false;
    }

}
