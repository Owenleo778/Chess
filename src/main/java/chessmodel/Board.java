package chessmodel;

import chessmodel.piece.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

/**
 * A class representing the state of the board. x = 0, y = 0 -> top left corner
 * @author Owen Salvage
 */
public class Board {

    private Piece[][] board;
    private int turn;
    private King bKing;
    private King wKing;
    private ArrayList<Piece> bPieces;
    private ArrayList<Piece> wPieces;

    public Board(){
        board = new Piece[Window.WIDTH][Window.HEIGHT];
        turn = Piece.WHITE;

        wPieces = new ArrayList<>(16);
        bPieces = new ArrayList<>(16);

        addPawns();
        addRooks();
        addKnights();
        addBishops();
        addQueens();
        addKings();

        /*
        bKing = new King(Piece.BLACK, new Point(1,1));
        board[1][1] = bKing;
        wPieces.add(new Queen(Piece.WHITE, new Point(0, 0)));
        wPieces.add(new Knight(Piece.WHITE, new Point(2, 3)));

        board[0][0] = wPieces.get(0);
        board[2][3] = wPieces.get(1);

        //System.out.println(board[1][1].canMove(this, new Point(1, 1), new Point(2, 3)));
        System.out.println(inCheck(Piece.BLACK));
         */

        /*
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (board[x][y] != null) {
                    System.out.print(" X ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        */

    }

    private void addRooks(){
        Rook p = new Rook(Piece.BLACK);
        setPiecePosition(p, new Point(0,0));
        bPieces.add(p);
        p =  new Rook(Piece.BLACK);
        setPiecePosition(p, new Point(7,0));
        bPieces.add(p);

        p = new Rook(Piece.WHITE);
        setPiecePosition(p, new Point(0,7));
        wPieces.add(p);
        p =  new Rook(Piece.WHITE);
        setPiecePosition(p, new Point(7,7));
        wPieces.add(p);
    }

    private void addKnights(){
        Knight p = new Knight(Piece.BLACK);
        setPiecePosition(p, new Point(1,0));
        bPieces.add(p);
        p =  new Knight(Piece.BLACK);
        setPiecePosition(p, new Point(6,0));
        bPieces.add(p);

        p = new Knight(Piece.WHITE);
        setPiecePosition(p, new Point(1,7));
        wPieces.add(p);
        p =  new Knight(Piece.WHITE);
        setPiecePosition(p, new Point(6,7));
        wPieces.add(p);
    }

    private void addBishops(){
        Bishop p = new Bishop(Piece.BLACK);
        setPiecePosition(p, new Point(2,0));
        bPieces.add(p);
        p =  new Bishop(Piece.BLACK);
        setPiecePosition(p, new Point(5,0));
        bPieces.add(p);

        p = new Bishop(Piece.WHITE);
        setPiecePosition(p, new Point(2,7));
        wPieces.add(p);
        p =  new Bishop(Piece.WHITE);
        setPiecePosition(p, new Point(5,7));
        wPieces.add(p);
    }

    private void addQueens(){
        Queen p = new Queen(Piece.BLACK);
        setPiecePosition(p, new Point(3,0));
        bPieces.add(p);

        p = new Queen(Piece.WHITE);
        setPiecePosition(p, new Point(3,7));
        wPieces.add(p);
    }

    private void addKings(){
        bKing = new King(Piece.BLACK);
        wKing = new King(Piece.WHITE);
        bPieces.add(bKing);
        wPieces.add(wKing);
        setPiecePosition(bKing, new Point(4, 0));
        setPiecePosition(wKing, new Point(4, 7));
    }

    private void addPawns(){
        for (int x = 0; x < 8; x++){
            Pawn pawn = new Pawn(Piece.BLACK);
            setPiecePosition(pawn, new Point(x, 1));
            bPieces.add(pawn);

            pawn = new Pawn(Piece.WHITE);
            setPiecePosition(pawn, new Point(x, 6));
            wPieces.add(pawn);
        }
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

    /*
    //ADD VERIFICATION NOT LEAVING IN CHECKMATE
    public boolean setPiecePosition(Point sP, Point eP){
        if (inRange(sP) && inRange(eP) && !isEmptySpace(sP) && board[sP.x][sP.y].getColour() == turn){

            if(board[sP.x][sP.y].canMove(this, sP, eP)) {
                Piece p = getPiece(sP);
                Piece p2 = getPiece(eP);
                setPiecePosition(p, eP);
                if (inCheck(turn)) {
                    setPiecePosition(p, sP);
                    setPiecePosition(p2, eP);
                    return false;
                }
                return true;
            }
        }
        return false;
    }
     */



    //change to set piece position, make move puece a different function that checks more


    //ADD VERIFICATION NOT LEAVING IN CHECKMATE
    /**
     * Sets the piece to the specified point
     * @param p the piece to move
     * @param pos the point to move to
     */
    private void setPiecePosition(Piece p, Point pos){
            if (p.getPos() != null) {
                board[p.getPos().x][p.getPos().y] = null;
            }
            board[pos.x][pos.y] = p;
            p.setPos(pos);
    }

    /**
     * Moves the pieve to the specified position if it's a legal move
     * @param p the piece to move
     * @param pos the position to move to
     * @return returns true if the piece was moved, false otherwise
     */
    private boolean movePiece(Piece p, Point pos){
        if (p.getPos() == null)
            return false;
        if (p.canMove(this, pos)){
            setPiecePosition(p, pos);
        }
        return true;
    }



    private Piece getPiece(Point p){
        return board[p.x][p.y];
    }

    /**
     * Checks if the colour's king is in check
     * @param colour the king to check
     * @return true if the king is in check, false otherwise
     */
    private boolean inCheck(int colour){
        if (colour == Piece.BLACK){
            return inCheck(bKing.getPos(), wPieces);
        } else if (colour == Piece.WHITE){
            return inCheck(wKing.getPos(), bPieces);
        }
        return false;
    }

    /**
     * Checks if the king is in check
     * @param k the position of the king
     * @param pieces the list of opponents pieces
     * @return true if the king is in check, false otherwise
     */
    private boolean inCheck(Point k, ArrayList<Piece> pieces){

        for (Piece p : pieces){
            if (p.canMove(this, k))
                return true;
        }


        return false;
    }

    /*
    private boolean inCheck(Point p){

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

     */

    /**
     * Checks if the colour's king is in checkmate
     * @param colour the king to check
     * @return true if the king is in checkmate, false otherwise
     */
    private boolean verifyMate(int colour){
        if (colour == Piece.BLACK){
            return verifyMate(bKing.getPos());
        } else if (colour == Piece.WHITE){
            return verifyMate(wKing.getPos());
        }
        return false;
    }

    /**
     * Checks if the king is in checkmate
     * @param p the position of the king
     * @return true if the king is in checkmate, false otherwise
     */
    private boolean verifyMate(Point p){
        // Check if in check first?
        return false;
    }

}
