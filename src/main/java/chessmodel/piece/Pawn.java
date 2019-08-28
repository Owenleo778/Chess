package chessmodel.piece;

import chessmodel.Board;
import chessmodel.Move;
import javafx.scene.image.Image;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Represents the Pawn piece
 * @author Owen Salvage
 */
public class Pawn extends Piece {

    public Pawn(Colour colour){
        this(colour, null);
    }

    public Pawn(Colour colour, Point pos){
        super(colour, pos, new Image("images/" + (colour == Colour.BLACK ? "Black" : "White") + "_Pawn.png"));
    }

    @Override
    public ArrayList<Move> getMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int xdir = -1; xdir < 2; xdir++){
            for (int ydir = 1; ydir < 3; ydir++){
                Point pos = getPos();
                if (canMove(board, new Point(xdir + pos.x, pos.y + ydir * getColour().getValue()))){
                    moves.add(new Move(this, new Point(xdir + pos.x, pos.y + ydir * getColour().getValue())));
                }
            }
        }

        return moves;
    }

    @Override
    public boolean canMove(Board board, Point p2) {
        if (getPos() != null) {
            Point p1 = getPos();
            if (board.isEmptySpace(p2) && p1.x == p2.x) {
                if (p1.y + getColour().getValue() == p2.y) {
                    return true;
                } else if (board.isEmptySpace(p1.x, p1.y + getColour().getValue())) {

                    if ( p1.y + getColour().getValue() * 2 == p2.y && !hasMoved()){
                        board.setEnPassant(new Point(p1.x, p1.y + getColour().getValue()));
                        return true;
                    }
                }
            } else if (Math.abs(p1.x - p2.x) == 1) {
                if (!board.isEmptySpace(p2)) {
                    return board.getPiece(p2).getColour() != getColour() && p1.y + getColour().getValue() == p2.y;
                } else if ( board.canEnPassant()){
                    return board.getEnPassant() == p2;
                }
            }
        }
        return false;
    }

}
