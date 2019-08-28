package chessmodel.piece;

import chessmodel.Board;
import chessmodel.Move;
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
    public ArrayList<Move> getMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int xdir = -1; xdir < 2; xdir++){
            for (int ydir = -1; ydir < 2; ydir++) {
                if (xdir == 0 || ydir == 0) {
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
            return p2.x - p1.x == 0 ^ p2.y - p1.y == 0 && emptyStraight(board, p1, p2);
        }
        return false;
    }

}
