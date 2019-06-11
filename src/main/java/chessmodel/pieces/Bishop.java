package chessmodel.pieces;

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
        int xDiff = p2.x - p1.x ;
        int yDiff = p2.y - p1.y;
        if (Math.abs(xDiff) == Math.abs(yDiff) && xDiff != 0){
            int xDir = xDiff / Math.abs(xDiff);
            int yDir = yDiff / Math.abs(yDiff);
            for (int i = 1 ; i <  Math.abs(xDiff); i++){ // Checks all spaces in between are empty
                if (!board.isEmptySpace(p1.x + i * xDir, p1.y + i * yDir))
                    return false;
            }
            return board.getColour(p2) != getColour();
        }

        return false;
    }

}
