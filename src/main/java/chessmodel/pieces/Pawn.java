package chessmodel.pieces;

import chessmodel.Board;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents the Pawn piece
 */
public class Pawn extends Piece {

    public Pawn(int colour){
        super(colour);
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point p) {return null;}


    //Needs rework for En passant move \/ \/ \/ \/ \/ ------------------------------------------

    @Override
    public boolean canMove(Board board, Point p1, Point p2) {
        boolean emptySpace = board.isEmptySpace(p2);
        if (emptySpace && p1.x == p2.x){
            if (p1.y + getColour() == p2.y){
                return true;
            } else {
                return p1.y + getColour() * 2 == p2.y && board.isEmptySpace(p1.x, p1.y + getColour());
            }
        } else {
                return !emptySpace && board.getColour(p2) != getColour() && Math.abs(p1.x - p2.x) == 1 && p1.y + getColour() == p2.y;
        }
    }
}
