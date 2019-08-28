package chessmodel.piece;

import chessmodel.Board;
import chessmodel.Move;
import javafx.scene.image.Image;

import java.awt.Point;
import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(Colour colour){
        this(colour, null);
    }

    public Knight(Colour colour, Point pos){
        super(colour, pos, new Image("images/" + (colour == Colour.BLACK ? "Black" : "White") + "_Knight.png"));
    }

    @Override
    public ArrayList<Move> getMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int xdir = -2; xdir < 3; xdir++){
            for (int ydir = -2; ydir < 3; ydir++){
                Point pos = getPos();
                if (canMove(board, new Point(xdir + pos.x, pos.y + ydir))){
                    moves.add(new Move(this, new Point(xdir + pos.x, pos.y + ydir)));
                }
            }
        }

        return moves;
    }

    @Override
    public boolean canMove(Board board, Point p2) {
        if (getPos() != null) {
            Point p1 = getPos();
            int xDiff = Math.abs(p2.x - p1.x);
            int yDiff = Math.abs(p2.y - p1.y);

            return xDiff + yDiff == 3 && xDiff != 0 && yDiff != 0 && validEndPos(board, p2);
        }
        return false;
    }
}
