package chessmodel.piece;

import chessmodel.Board;
import chessmodel.Move;
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
    public ArrayList<Move> getMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int xdir = -1; xdir < 2; xdir++){
            for (int ydir = -1; ydir < 2; ydir++) {
                if (!(xdir == 0 && ydir == 0)) {
                    int x = xdir;
                    int y = ydir;
                    Point pos = getPos();

                    //Loops until can't move in that direction, adding moves each time
                    while (canMove(board, new Point(x + pos.x, pos.y + y))) {
                        moves.add(new Move(this, new Point(x + pos.x, pos.y + y)));
                        x += xdir;
                        y += ydir;
                    }
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
            return (xDiff == yDiff && xDiff != 0 || xDiff == 0 ^ yDiff == 0) && emptyStraight(board, p1, p2);
        }
        return false;
    }
}
