package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Window extends Application implements Initializable {

    @FXML private BorderPane root;
    @FXML private Button button;
    @FXML private TextField textf;
    @FXML private Slider slider;

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
        button.setOnAction(e -> textf.setText("Hello World!"));
        slider.valueProperty().addListener(e -> textf.setFont(new Font(slider.getValue())));
    }
}
