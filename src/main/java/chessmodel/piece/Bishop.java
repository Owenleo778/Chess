package chessmodel.piece;

import chessmodel.Board;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(int colour){
        super(colour);
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point p) { return null; }

    @Override
    public boolean canMove(Board board, Point p1, Point p2) {
        int xDiff = p2.x - p1.x;
        int yDiff = p2.y - p1.y;
        return Math.abs(xDiff) == Math.abs(yDiff) && xDiff != 0 &&  emptyStraight(board, p1, p2);
    }

}
