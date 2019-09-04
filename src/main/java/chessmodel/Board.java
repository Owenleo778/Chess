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
    private Piece pieceToRemove;

    public Board(){
        pieceToRemove = null;
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
        if (!isEmptySpace(p)) {
            Piece piece =  board[p.x][p.y];
            setPieceToRemove(piece);
            board[p.x][p.y].setPos(null);
            board[p.x][p.y] = null;
            piece.remove();
            if (piece.getColour() == Colour.BLACK){
                bPieces.remove(piece);
            } else {
                wPieces.remove(piece);
            }
        }
    }

    public Colour getColour(Point p) throws NullPointerException {
        return board[p.x][p.y].getColour();
    }

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


    //Currently can't move out of check by capturing the attacking piece

    /**
     * Moves the piece to the specified position if it's a legal move
     * @param p the piece to move
     * @param pos the position to move to
     * @return returns true if the piece was moved, false otherwise
     */
    public boolean movePiece(Piece p, Point pos){
        if (p.getColour() != turn || p.getPos() == null)
            return false;

        Point passantCheck = getEnPassant();
        if (p.canMove(this, pos) && !inCheckHere(p, pos)) {

            if (enPassant != null && enPassant.x == pos.x && enPassant.y == pos.y){ // removes other pawn in enpassant move
                int yadd = p.getColour() == Colour.BLACK ? Colour.WHITE.getValue() : Colour.BLACK.getValue();
                Point pawnPoint = new Point(pos.x, pos.y + yadd);
                removePiece(pawnPoint);
                if (passantCheck != null && passantCheck == getEnPassant())
                    setEnPassant(null);
            } else {
                removePiece(pos);
            }

            setPiecePosition(p, pos);
            return true;
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
     * Returns true if the piece's king would be in check if it was instead at position pos2
     * @param p the piece in question
     * @param pos2 the queried position
     * @return true if it would leave the king in check, false otherwise
     */
    public boolean inCheckHere(Piece p, Point pos2){
        //King king = colour == Colour.BLACK ? bKing : wKing;
        Point pos = p.getPos();
        p.setPos(pos2);
        board[pos.x][pos.y] = null;
        Piece otherP =  board[pos2.x][pos2.y];
        if (otherP != null)
            otherP.setPos(null);
        board[pos2.x][pos2.y] = p;
        boolean inCheck = inCheck(p.getColour());
        board[pos2.x][pos2.y] = otherP;
        p.setPos(pos);
        if (otherP != null)
            otherP.setPos(pos2);
        board[pos.x][pos.y] = p;
        return inCheck;
    }

    /**
     * Checks if the colour's king is in checkmate
     * @param colour the king to check
     * @return true if the king is in checkmate, false otherwise
     */
    public boolean verifyMate(Colour colour){
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
        return getAllMoves(k.getColour()).size() == 0 && inCheck(k.getColour());
    }

    /**
     * Returns a list of all possible moved for the specified colour
     * @param c the colour of the pieces
     * @return the list of possible moves
     */
    public ArrayList<Move> getAllMoves(Colour c){
        if (c == Colour.BLACK){
            return getAllMoves(bPieces);
        } else {
            return getAllMoves(wPieces);
        }
    }

    /**
     * Returns a list of all possible moved for the specified pieces
     * @param pieces the list of pieces
     * @return the list of possible moves
     */
    public ArrayList<Move> getAllMoves(ArrayList<Piece> pieces){
        ArrayList<Move> moves = new ArrayList<>();
        for (Piece p : pieces){
            ArrayList<Move> pMoves = p.getMoves(this);
            for (Move m : pMoves){
                if (!inCheckHere(m.getPiece(), m.getEndPoint()))
                    moves.add(m);
            }
        }

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

    public void setPieceToRemove(Piece p){
        pieceToRemove = p;
    }

    public Piece getPieceToRemove(){
        return pieceToRemove;
    }

    public Group getImages(){
        Group g = new Group();
        for (Piece p : bPieces)
            g.getChildren().add(p.getImage());
        for (Piece p : wPieces)
            g.getChildren().add(p.getImage());
        return g;
    }

    public void setTurn(Colour c){
        turn = c;
    }

    public Colour getTurn(){
        return turn;
    }
}
