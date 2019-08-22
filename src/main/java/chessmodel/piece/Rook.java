package chessmodel.piece;

import chessmodel.Board;
import javafx.scene.image.Image;

import java.awt.Point;
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(Colour colour){
        this(colour, null);
    }

    public Rook(Colour colour, Point pos){
        super(colour, pos, new Image("images/" + (colour == Colour.BLACK ? "Black" : "White") + "_Rook.png"));
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point p) {
        return null;
    }

    @Override
    public boolean canMove(Board board, Point p2) {
        if (getPos() != null) {
            Point p1 = getPos();
            return p2.x - p1.x == 0 ^ p2.y - p1.y == 0 && emptyStraight(board, p1, p2);
        }
        return false;
    }

}
