package chessmodel.piece;

import chessmodel.Board;
import javafx.scene.image.Image;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Represents the Pawn piece
 */
public class Pawn extends Piece {

    public Pawn(Colour colour){
        this(colour, null);
    }

    public Pawn(Colour colour, Point pos){
        super(colour, pos, new Image("images/" + (colour == Colour.BLACK ? "Black" : "White") + "_Pawn.png"));
    }


    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point p) {return null;}


    //Needs rework for En passant move \/ \/ \/ \/ \/ ------------------------------------------

    @Override
    public boolean canMove(Board board, Point p2) {
        Point p1 = getPos();
        if (board.isEmptySpace(p2) && p1.x == p2.x){
            if (p1.y + getColour().getValue() == p2.y){
                return true;
            } else if (board.isEmptySpace(p1.x, p1.y + getColour().getValue())) {
                return p1.y + getColour().getValue() * 2 == p2.y && getColour() == Colour.BLACK ? p1.y == 1 : p1.y == 6;
            }
        } else if (!board.isEmptySpace(p2)){
                return board.getPiece(p2).getColour() != getColour() && Math.abs(p1.x - p2.x) == 1 && p1.y + getColour().getValue() == p2.y;
        }
        return false;
    }

}
