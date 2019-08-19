package view;

import chessmodel.Board;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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

    public Window(){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("window.fxml"));
        loader.setController(this);

        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(this.root);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        centre.getChildren().add(tileGroup);
        centre.getChildren().add(pieceGroup);
        //draws tiles onto the board
        for (int x = 0; x < Board.WIDTH; x++){
            for (int y = 0; y < Board.HEIGHT; y++){
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                tileGroup.getChildren().add(tile);
            }
        }


        /*Circle c = new Circle(50, Color.BLUE);
        c.relocate(200, 200);
        centre.getChildren().add(c);*/


        root.setPrefSize(Board.WIDTH * TILE_SIZE, Board.HEIGHT * TILE_SIZE );
        //root.autosize();
    }
}
