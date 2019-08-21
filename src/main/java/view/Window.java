package view;

import chessmodel.Board;

import chessmodel.piece.Piece;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import listeners.PieceMover;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A class used for the main GUI window.
 * @author Owen Salvage
 */
public class Window extends Application implements Initializable {

    public static final int TILE_SIZE = 100;
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    @FXML private BorderPane root;
    @FXML private Pane centre;
    private Board board;

    public Window(){
        board = new Board();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("window.fxml"));
        loader.setController(this);

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PieceMover p = new PieceMover(board);
        centre.addEventHandler(MouseEvent.MOUSE_PRESSED, p);
        centre.addEventHandler(MouseEvent.MOUSE_RELEASED, p);
        centre.getChildren().add(tileGroup);
        centre.getChildren().add(pieceGroup);

        //draws tiles onto the board
        for (int x = 0; x < Board.WIDTH; x++){
            for (int y = 0; y < Board.HEIGHT; y++){
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                tileGroup.getChildren().add(tile);
            }
        }

        pieceGroup.getChildren().addAll(board.getImages());

        root.setPrefSize(Board.WIDTH * TILE_SIZE, Board.HEIGHT * TILE_SIZE );
    }

    public static Point toCoordinates(Point p){
        p.x = p.x / TILE_SIZE;
        p.y = p.y / TILE_SIZE;
        return p;
    }

}
