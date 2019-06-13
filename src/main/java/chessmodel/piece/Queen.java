package chessmodel.piece;

import chessmodel.Board;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(int colour){
        super(colour);
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point p) {
        return null;
    }

    @Override
    public boolean canMove(Board board, Point p1, Point p2) {
        int xDiff = Math.abs(p2.x - p1.x);
        int yDiff = Math.abs(p2.y - p1.y);
        return (xDiff == yDiff && xDiff != 0 || xDiff == 0 ^ yDiff == 0) && emptyStraight(board, p1, p2);
    }
}
