package dev.joshpetit.wit.gui.launcher;
import javafx.geometry.Insets;

import java.io.IOException;

import dev.joshpetit.wit.core.base.BasicTypingSystem;
import dev.joshpetit.wit.core.base.StringInterpreter;
import dev.joshpetit.wit.core.base.TypingSystem;
import dev.joshpetit.wit.core.commands.AppendCommand;

import javafx.application.Application;

import javafx.geometry.Pos;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.Properties;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    CommandableTextArea area;
    TypingSystem ts;
    StringInterpreter controller;
    Properties config;
    int index;

    public Pane createButtonDisplay() {
        HBox row = new HBox();
        row.setSpacing(11);
        row.setPrefHeight(200);
        for (int i = 0; i < 11; i++) {
            Pane p = new Pane();
            p.setPrefWidth(200);
            p.setPrefHeight(100);
            p.setStyle("-fx-background-color: #ffffff;");
            row.getChildren().add(p);
            System.out.println("add");
        }
        return row;
    }

    public void createInterpreter() {
        try {
            config = BasicTypingSystem.getDefaultProperties();
            area = new CommandableTextArea("Demo Text");
            //area.setDisable(true);
            area.setOpacity(1);
            ts = new BasicTypingSystem(config, area);
            controller = new StringInterpreter(ts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage primaryStage) {
        createInterpreter();
        BorderPane root = new BorderPane();
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (index != 10) {
                    controller.addMapping(e.getCode().toString(), index);
                    index++;
                } else {
                    controller.input(e.getCode().toString());
                }
            }
        });
        Pane row = createButtonDisplay();
        row.setStyle("-fx-background-color: #336699;");
        row.setPadding(new Insets(15, 12, 15, 12));
        root.setCenter(area);
        root.setBottom(row);
        Scene scene = new Scene(root, 700, 500);

        primaryStage.setTitle("WIT - Blind Keyboard");
        primaryStage.setScene(scene);
        primaryStage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
