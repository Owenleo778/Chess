package chessmodel.piece;

import chessmodel.Board;
import javafx.scene.image.Image;

import java.awt.Point;
import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(Colour colour){
        this(colour, null);
    }

    public Queen(Colour colour, Point pos){
        super(colour, pos,  new Image("images/" + (colour == Colour.BLACK ? "Black" : "White") + "_Queen.png"));
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
