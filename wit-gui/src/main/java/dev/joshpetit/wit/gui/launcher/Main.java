package dev.joshpetit.wit.gui.launcher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("WIT - Blind Keyboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

     public static void main(String[] args) {
        launch(args);
    }
}
