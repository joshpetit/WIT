package dev.joshpetit.wit.gui.launcher;

import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import dev.joshpetit.wit.core.commands.AppendCommand;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);

        CommandableTextArea area = new CommandableTextArea("Demo");
        AppendCommand com = new AppendCommand("upper", "lower");

        area.typingAppend(com);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(area);

        vbox.getChildren().add(hbox);
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("WIT - Blind Keyboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

     public static void main(String[] args) {
        launch(args);
    }
}
