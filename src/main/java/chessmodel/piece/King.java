package chessmodel.piece;

import chessmodel.Board;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class King extends Piece {

    public King(Colour colour){
        this(colour, null);
    }

    public King(Colour colour, Point pos){
        super(colour, pos, new Image("images/" + (colour == Colour.BLACK ? "Black" : "White") + "_King.png"));
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point p) { return null; }

    @Override
    public boolean canMove(Board board, Point p2) {
        Point p1 = getPos();
        int xDiff = Math.abs(p2.x - p1.x);
        int yDiff = Math.abs(p2.y - p1.y);

        return (xDiff == 0 || xDiff == 1) && (yDiff == 0 || yDiff == 1) &&
                !(xDiff == 0 && yDiff == 0) && validEndPos(board, p2);

    }

}
