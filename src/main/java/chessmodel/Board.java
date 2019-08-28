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
    private Point enPassant;

    public Board(){
        enPassant = null;
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
     * Moves a piece to the specified position, returns true if it was legal, false otherwise
     * (doesn't move piece if the move is illegal).
     * @param m the move
     * @return true if moved, false otherwise
     */
    public boolean movePiece(Move m){
        return movePiece(m.getPiece(), m.getEndPoint());
    }

    /**
     * Moves the piece to the specified position if it's a legal move
     * @param p the piece to move
     * @param pos the position to move to
     * @return returns true if the piece was moved, false otherwise
     */
    public boolean movePiece(Piece p, Point pos){
        if (p.getColour() == turn) {
            if (p.getPos() == null)
                return false;
            Point passentCheck = getEnPassant();
            if (p.canMove(this, pos)) {
                Piece p2 = getPiece(pos);
                if (p2 != null)
                    p2.setPos(null);
                Point pos2 = p.getPos();

                if (!verifyCheck(p, pos, pos2)) {
                    if (passentCheck != null && passentCheck == getEnPassant())
                        setEnPassant(null);
                    removePiece(pos);
                    return true;
                } else if (p2 != null) {
                    setPiecePosition(p2, pos);
                }

            } else if (p instanceof chessmodel.piece.King && ((King) p).castleMove(this, pos)){
                Point kingPos = p.getPos();
                if ((!verifyCheck(p, pos, kingPos))){
                    Point rookInit = new Point(kingPos.x - pos.x > 0 ? 0: WIDTH - 1, kingPos.y);
                    Point rookEnd = new Point(kingPos.x + (rookInit.x == 0 ? -1: 1), kingPos.y);
                    setPiecePosition(getPiece(rookInit), rookEnd);
                    getPiece(rookEnd).setMoved(true);
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Verifies that the piece in position pos will not leave their king in check, reverts if it does
     * @param p piece to check it's move
     * @param pos the end pos
     * @param initPos the initial pos
     * @return true if it's in check
     */
    private boolean verifyCheck(Piece p, Point pos, Point initPos){
        setPiecePosition(p, pos);
        if (!inCheck(turn)) {
            turn = turn == Colour.BLACK ? Colour.WHITE : Colour.BLACK;
            p.setMoved(true);
            return false;
        }
        setPiecePosition(p, initPos);
        return true;
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
    public boolean inCheck(Colour colour){
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
     * Checks if the specifed coloured king would be in check, if it was in point p instead
     * @param p the point to check
     * @return true if it would be, false otherwise
     */
    public boolean inCheckHere(Colour colour, Point p){
        if (!isEmptySpace(p))
            System.exit(0);

        King king = colour == Colour.BLACK ? bKing : wKing;
        Point kingPos = king.getPos();
        king.setPos(p);
        board[p.x][p.y] = king;
        boolean inCheck = inCheck(colour);
        board[p.x][p.y] = null;
        king.setPos(kingPos);
        return inCheck;
    }



    /**
     * Checks if the colour's king is in checkmate
     * @param colour the king to check
     * @return true if the king is in checkmate, false otherwise
     */
    private boolean verifyMate(Colour colour){
        if (inCheck(colour)) {
            if (colour == Colour.BLACK) {
                return verifyMate(bKing, wPieces);
            } else if (colour == Colour.WHITE) {
                return verifyMate(wKing, bPieces);
            }
        }
        return false;
    }

    /**
     * Checks if the king is in checkmate
     * @param k the king
     * @return true if the king is in checkmate, false otherwise
     */
    private boolean verifyMate(King k, ArrayList<Piece> pieces){


        return false;
    }

    /**
     * Returns a list of all possible moved for the specified colour
     * @param c the colour of the pieces
     * @return the list of possible moves
     */
    public ArrayList<Move> getAllMoves(Colour c){
        ArrayList<Move> moves = new ArrayList<>();

        return moves;
    }

    public void setEnPassant(Point p){
        enPassant = p;
    }

    public Point getEnPassant(){
        return enPassant;
    }

    public boolean canEnPassant(){
        return enPassant != null;
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
