package dev.joshpetit.wit.gui.launcher;

import java.io.IOException;

import dev.joshpetit.wit.core.base.BasicTypingSystem;
import dev.joshpetit.wit.core.base.StringInterpreter;
import dev.joshpetit.wit.core.base.TypingSystem;
import dev.joshpetit.wit.core.commands.AppendCommand;

import javafx.application.Application;

import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.Properties;
import javafx.stage.Stage;

public class Main extends Application {
    CommandableTextArea area;
    TypingSystem ts;
    StringInterpreter controller;
    Properties config;

    public void createInterpreter() {
        try {
            config = BasicTypingSystem.getDefaultProperties();
            area = new CommandableTextArea("Demo");
            ts = new BasicTypingSystem(config, area);
            controller = new StringInterpreter(ts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        createInterpreter();

        Pane root = new Pane();
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
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
