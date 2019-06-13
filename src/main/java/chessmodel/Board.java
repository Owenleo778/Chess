package chessmodel;

import chessmodel.piece.*;

import java.awt.*;

/**
 * A class representing the state of the board. x = 0, y = 0 -> top left corner
 * @author Owen Salvage
 */
public class Board {

    private Piece[][] board;
    private int turn;
    private Point bKing;
    private Point wKing;

    public Board(){
        board = new Piece[8][8];
        turn = Piece.WHITE;

        board[1][1] = new King(1);
        board[0][0] = new Queen(-1);
        board[2][3] = new Knight(-1);

        //System.out.println(board[1][1].canMove(this, new Point(1, 1), new Point(2, 3)));
        System.out.println(verifyCheck(new Point(1, 1)));
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
    public boolean movePiece(Point sP, Point eP){
        if (inRange(sP) && inRange(eP) && !isEmptySpace(sP) && board[sP.x][sP.y].getColour() == turn){

            if(board[sP.x][sP.y].canMove(this, sP, eP)) {
                board[eP.x][eP.y] = board[sP.x][sP.y];
                board[sP.x][sP.y] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the colour's king is in check
     * @param colour the king to check
     * @return true if the king is in check, false otherwise
     */
    private boolean verifyCheck(int colour){
        if (colour == Piece.BLACK){
            return verifyCheck(bKing);
        } else if (colour == Piece.WHITE){
            return verifyCheck(wKing);
        }
        return false;
    }

    /**
     * Checks if the king is in check
     * @param p the position of the king
     * @return true if the king is in check, false otherwise
     */
    private boolean verifyCheck(Point p){

        //Checks in the 8 directions round if any piece is checking the king
        for (int xDir = -1; xDir < 2; xDir++){ // Loops through the x directions
            for (int yDir = -1; yDir < 2; yDir++){ // Loops through the y directions
                if (xDir != 0 || yDir != 0){
                    int i = 1;
                    Point p2 = new Point(p.x + xDir, p.y + yDir);
                    boolean validPos = inRange(p2);
                    while (validPos) {
                        //System.out.println("Checking: " + p2.x + ", " + p2.y);
                        if (!isEmptySpace(p2) && board[p2.x][p2.y].canMove(this, p2, p)){
                            return true;
                        } else if (!isEmptySpace(p2) &&getColour(p2) == getColour(p)){
                            break;
                        }

                        i++;
                        p2.x = p.x + i * xDir;
                        p2.y = p.y + i * yDir;
                        validPos = inRange(p.x + i *xDir) && inRange(p.y + i * yDir);
                    }
                }
            }
        }
        //System.out.println();
        // Checks for Knights
        for (int xDir = -2; xDir < 3; xDir++) { // Loops through the x directions
            for (int yDir = -2; yDir < 3; yDir++) { // Loops through the y directions
                if (Math.abs(xDir) + Math.abs(yDir) == 3){
                    Point p2 = new Point(p.x + xDir, p.y + yDir);
                    //System.out.println("Checking: " + p2.x + ", " + p2.y);
                    if (inRange(p2) && !isEmptySpace(p2) && board[p2.x][p2.y].canMove(this, p2, p))
                        return true;
                }
            }
        }

        return false;
    }

}
