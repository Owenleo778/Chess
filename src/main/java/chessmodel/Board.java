package chessmodel;

import chessmodel.piece.*;
import javafx.scene.Group;

import java.awt.*;
import java.util.ArrayList;

/**
 * A class representing the state of the board. x = 0, y = 0 -> top left corner
 * @author Owen Salvage
 */
public class Board {

    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private Piece[][] board;
    private Colour turn;
    private King bKing;
    private King wKing;
    private ArrayList<Piece> bPieces;
    private ArrayList<Piece> wPieces;

    public Board(){
        board = new Piece[WIDTH][HEIGHT];
        turn = Colour.WHITE;

        wPieces = new ArrayList<>(16);
        bPieces = new ArrayList<>(16);

        addPawns();
        addRooks();
        addKnights();
        addBishops();
        addQueens();
        addKings();

    }

    private void addRooks(){
        Rook p = new Rook(Colour.BLACK);
        setPiecePosition(p, new Point(0,0));
        bPieces.add(p);
        p =  new Rook(Colour.BLACK);
        setPiecePosition(p, new Point(7,0));
        bPieces.add(p);

        p = new Rook(Colour.WHITE);
        setPiecePosition(p, new Point(0,7));
        wPieces.add(p);
        p =  new Rook(Colour.WHITE);
        setPiecePosition(p, new Point(7,7));
        wPieces.add(p);
    }

    private void addKnights(){
        Knight p = new Knight(Colour.BLACK);
        setPiecePosition(p, new Point(1,0));
        bPieces.add(p);
        p =  new Knight(Colour.BLACK);
        setPiecePosition(p, new Point(6,0));
        bPieces.add(p);

        p = new Knight(Colour.WHITE);
        setPiecePosition(p, new Point(1,7));
        wPieces.add(p);
        p =  new Knight(Colour.WHITE);
        setPiecePosition(p, new Point(6,7));
        wPieces.add(p);
    }

    private void addBishops(){
        Bishop p = new Bishop(Colour.BLACK);
        setPiecePosition(p, new Point(2,0));
        bPieces.add(p);
        p =  new Bishop(Colour.BLACK);
        setPiecePosition(p, new Point(5,0));
        bPieces.add(p);

        p = new Bishop(Colour.WHITE);
        setPiecePosition(p, new Point(2,7));
        wPieces.add(p);
        p =  new Bishop(Colour.WHITE);
        setPiecePosition(p, new Point(5,7));
        wPieces.add(p);
    }

    private void addQueens(){
        Queen p = new Queen(Colour.BLACK);
        setPiecePosition(p, new Point(3,0));
        bPieces.add(p);

        p = new Queen(Colour.WHITE);
        setPiecePosition(p, new Point(3,7));
        wPieces.add(p);
    }

    private void addKings(){
        bKing = new King(Colour.BLACK);
        wKing = new King(Colour.WHITE);
        bPieces.add(bKing);
        wPieces.add(wKing);
        setPiecePosition(bKing, new Point(4, 0));
        setPiecePosition(wKing, new Point(4, 7));
    }

    private void addPawns(){
        for (int x = 0; x < 8; x++){
            Pawn pawn = new Pawn(Colour.BLACK);
            setPiecePosition(pawn, new Point(x, 1));
            bPieces.add(pawn);

            pawn = new Pawn(Colour.WHITE);
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

    /**
     * Sets the piece at the specified position to be removed
     * @param p the position to check
     */
    public void removePiece(Point p){
        if (!isEmptySpace(p))
            board[p.x][p.y].remove();

        /*
        ArrayList<Piece> pieces = new ArrayList<>();
        for (Piece piece : bPieces) {
            if (!piece.isRemoved())
                pieces.add(piece);
        }
        bPieces = pieces;

        pieces = new ArrayList<>();
        for (Piece piece : wPieces) {
            if (!piece.isRemoved())
                pieces.add(piece);
        }
        wPieces = pieces;
         */

    }

    public Colour getColour(Point p) throws NullPointerException {
        return board[p.x][p.y].getColour();
    }


    //change to set piece position, make move piece a different function that checks more


    //ADD VERIFICATION NOT LEAVING IN CHECKMATE
    /**
     * Sets the piece to the specified point
     * @param p the piece to move
     * @param pos the point to move to
     */
    private void setPiecePosition(Piece p, Point pos){
            if (p.getPos() != null)
                board[p.getPos().x][p.getPos().y] = null;
            board[pos.x][pos.y] = p;
            p.setPos(pos);
    }

    /**
     * Moves the pieve to the specified position if it's a legal move
     * @param p the piece to move
     * @param pos the position to move to
     * @return returns true if the piece was moved, false otherwise
     */
    public boolean movePiece(Piece p, Point pos){
        if (p.getColour() == turn) {
            if (p.getPos() == null)
                return false;
            if (p.canMove(this, pos)) {
                Piece p2 = getPiece(pos);
                Point pos2 = p.getPos();
                setPiecePosition(p, pos);
                if (!inCheck(turn)) {
                    turn = turn == Colour.BLACK ? Colour.WHITE : Colour.BLACK;
                    removePiece(pos);
                    return true;
                } else {
                    setPiecePosition(p, pos2);
                    if (p2 != null)
                        setPiecePosition(p2, pos);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Returns the piece at the given position
     * @param p the position to check
     * @return the piece found, null if no piece is found
     */
    public Piece getPiece(Point p){
        return inRange(p) ?  board[p.x][p.y] :  null;
    }

    /**
     * Checks if the colour's king is in check
     * @param colour the king to check
     * @return true if the king is in check, false otherwise
     */
    private boolean inCheck(Colour colour){
        if (colour == Colour.BLACK){
            return inCheck(bKing.getPos(), wPieces);
        } else if (colour == Colour.WHITE){
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

    /**
     * Checks if the colour's king is in checkmate
     * @param colour the king to check
     * @return true if the king is in checkmate, false otherwise
     */
    private boolean verifyMate(Colour colour){
        if (colour == Colour.BLACK){
            return verifyMate(bKing.getPos());
        } else if (colour == Colour.WHITE){
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

    public Group getImages(){
        Group g = new Group();
        for (Piece p : bPieces)
            g.getChildren().add(p.getImage());
        for (Piece p : wPieces)
            g.getChildren().add(p.getImage());
        return g;
    }

}
