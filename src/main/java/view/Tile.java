package view;

import chessmodel.piece.Piece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Tile extends Rectangle {

    private Piece piece;

    public Tile(boolean dark, int x, int y){
        setFill(dark ? Color.valueOf("#654321") : Color.valueOf("#ebebeb")); // brown : white
        setWidth(Window.TILE_SIZE);
        setHeight(Window.TILE_SIZE);
        relocate(x * Window.TILE_SIZE, y * Window.TILE_SIZE); // position of Tile
    }

}
