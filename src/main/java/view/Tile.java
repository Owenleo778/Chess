package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A class to represent a tile in the game, holds the piece that's on it.
 * @author Owen Salvage
 */
public class Tile extends Rectangle {


    public Tile(boolean light, int x, int y){
        setFill(light ? Color.valueOf("#ebebeb") : Color.valueOf("#654321")); // white : brown
        setWidth(Window.TILE_SIZE);
        setHeight(Window.TILE_SIZE);
        relocate(x * Window.TILE_SIZE, y * Window.TILE_SIZE); // position of Tile
    }

}
