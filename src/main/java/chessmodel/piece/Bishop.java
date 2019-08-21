package chessmodel.piece;

import chessmodel.Board;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(Colour colour){
        this(colour, null);
    }

    public Bishop(Colour colour, Point pos){
        super(colour, pos, new Image("images/" + (colour == Colour.BLACK ? "Black" : "White") + "_Bishop.png"));
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point p) { return null; }



    @Override
    public boolean canMove(Board board, Point p2) {
        Point p1 = getPos();
        int xDiff = p2.x - p1.x;
        int yDiff = p2.y - p1.y;
        return Math.abs(xDiff) == Math.abs(yDiff) && xDiff != 0 &&  emptyStraight(board, p1, p2);
    }

}
