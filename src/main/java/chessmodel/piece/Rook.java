package chessmodel.piece;

import chessmodel.Board;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(int colour, Point pos){
        super(colour, pos);
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point p) {
        return null;
    }

    @Override
    public boolean canMove(Board board, Point p1, Point p2) {
        return p2.x - p1.x == 0 ^ p2.y - p1.y == 0 && emptyStraight(board, p1, p2);
    }

}
