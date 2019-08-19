package view;

import chessmodel.piece.Piece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A class to represent a tile in the game, holds the piece that's on it.
 * @author Owen Salvage
 */
public class Tile extends Rectangle {

    private Piece piece;

    public Tile(boolean dark, int x, int y){
        setFill(dark ? Color.valueOf("#654321") : Color.valueOf("#ebebeb")); // brown : white
        setWidth(Window.TILE_SIZE);
        setHeight(Window.TILE_SIZE);
        relocate(x * Window.TILE_SIZE, y * Window.TILE_SIZE); // position of Tile
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece p){
        piece = p;
    }

    /**
     * Determines if there is a piece on this tile
     * @return true if there isn't, false otherwise
     */
    public boolean isEmpty(){
        return piece == null;
    }

}
