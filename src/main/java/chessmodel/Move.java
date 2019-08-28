package chessmodel;

import chessmodel.piece.Piece;

import java.awt.*;

/**
 * A class that represents a chess move.
 * @author Owen Salvage
 */
public class Move {

    private Piece p;
    private Point pos;

    public Move(Piece p, Point pos){
        this.p = p;
        this.pos = pos;
    }

    public Point getEndPoint(){
        return pos;
    }

    public Piece getPiece(){
        return p;
    }

}
