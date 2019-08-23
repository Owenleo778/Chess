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
    public boolean canMove(Board board, Point p) {
        if (getPos() != null) {
            int xDiff = Math.abs(p.x - getPos().x);
            int yDiff = Math.abs(p.y - getPos().y);

            return (xDiff == 0 || xDiff == 1) && (yDiff == 0 || yDiff == 1) &&
                    !(xDiff == 0 && yDiff == 0) && validEndPos(board, p);
        }
        return false;

    }

    /**
     * Returns true if the point specified is a valid castle move
     * @param board the class holding information about the pieces
     * @param p the position to move to
     * @return true if it can make the move, false otherwise
     */
    public boolean castleMove(Board board, Point p){
        /*
            1. Neither the king nor the chosen rook has previously moved.
            2. There are no pieces between the king and the chosen rook.
            3. The king is not currently in check.
            4. The king does not pass through a square that is attacked by an enemy piece.
            5. The king does not end up in check. (True of any legal move.)     [this is covered in outer methods]
         */
        int xDiff = Math.abs(p.x - getPos().x);
        int yDiff = Math.abs(p.y - getPos().y);

        Point kP = getPos();

        if (!hasMoved() && yDiff == 0 && xDiff == 2 && !board.inCheck(getColour())) { // 1.a, 3

            int rookx = kP.x - p.x > 0 ? 0 : Board.WIDTH - 1;
            Piece rook = board.getPiece(new Point(rookx, p.y));
            if (rook.hasMoved() || !emptyStraight(board, kP, new Point(rookx == 0 ? 1: Board.WIDTH - 2, p.y))) // 1.b, 2
                return false;

            return !board.inCheckHere(getColour(), new Point(kP.x + (rookx == 0 ? -1: 1), kP.y)); //4

        }
        return false;
    }

}
