package chessmodel.piece;

import chessmodel.Board;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(Colour colour){
        this(colour, null);
    }

    public Knight(Colour colour, Point pos){
        super(colour, pos, new Image("images/" + (colour == Colour.BLACK ? "Black" : "White") + "_Knight.png"));
    }

    @Override
    public ArrayList<Point> getMoves(Piece[][] board, Point p) { return null; }

    @Override
    public boolean canMove(Board board, Point p1, Point p2) {
        int xDiff = Math.abs(p2.x - p1.x);
        int yDiff = Math.abs(p2.y - p1.y);

        return xDiff + yDiff == 3 && xDiff != 0 && yDiff != 0 &&  validEndPos(board, p2);
    }
}
