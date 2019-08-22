package view;

import chessmodel.Board;

import chessmodel.piece.Colour;
import chessmodel.piece.King;
import chessmodel.piece.Piece;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import listeners.PieceMover;

import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A class used for the main GUI window.
 * @author Owen Salvage
 */
public class Window extends Application implements Initializable {

    public static final int TILE_SIZE = 100;
    private Group tileGroup;
    private Group pieceGroup;
    @FXML private BorderPane root;
    @FXML private Pane centre;
    @FXML private Label turn;
    private Board board;

    public Window(){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("window.fxml"));
        loader.setController(this);
        board = new Board();
        pieceGroup = board.getImages();

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tileGroup = new Group();
        PieceMover p = new PieceMover(this);

        centre.addEventHandler(MouseEvent.MOUSE_PRESSED, p);
        centre.addEventHandler(MouseEvent.MOUSE_RELEASED, p);
        centre.addEventHandler(MouseEvent.MOUSE_ENTERED, p);
        centre.getChildren().add(tileGroup);
        centre.getChildren().add(pieceGroup);

        //draws tiles onto the board
        for (int x = 0; x < Board.WIDTH; x++){
            for (int y = 0; y < Board.HEIGHT; y++){
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                tileGroup.getChildren().add(tile);
            }
        }
        root.setPrefSize(Board.WIDTH * TILE_SIZE, Board.HEIGHT * TILE_SIZE );
    }

    /**
     * Converts pixel coordinates (e.g mouse position) to board coordinates (e.g position of a piece)
     * @param p the pixel coordinates
     * @return the board coordinates
     */
    public static Point toCoordinates(Point p){
        p.x = p.x / TILE_SIZE;
        p.y = p.y / TILE_SIZE;
        return p;
    }

    /**
     * Removes the specified piece from being shown in the GUI
     * @param p the piece to remove
     */
    public void removePiece(Piece p){
        pieceGroup.getChildren().remove(p.getImage());
    }

    public Board getBoard(){
        return board;
    }

    public void nextTurn(){
        if (turn.getText().equals("White's Turn")){
            turn.setText("Black's Turn");
        } else {
            turn.setText("White's Turn");
        }
    }

    public void setOpenHand(){
        centre.setCursor(Cursor.OPEN_HAND);
    }

    public void setClosedHand(){
        centre.setCursor(Cursor.CLOSED_HAND);
    }

}
