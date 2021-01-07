package dev.joshpetit.wit.gui.launcher;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import dev.joshpetit.wit.core.base.BasicTypingSystem;
import dev.joshpetit.wit.core.base.StringInterpreter;
import dev.joshpetit.wit.core.base.TypingSystem;
import dev.joshpetit.wit.core.commands.AppendCommand;

import javafx.application.Application;

import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import javafx.stage.Stage;

public class Main extends Application {
    CommandableTextArea area;
    BasicTypingSystem ts;
    StringInterpreter controller;
    Properties config;
    int index;
    Map<Integer, ButtonPane> indexedPanes;
    Map<String, ButtonPane> referencedPanes;

    //TODO: Set minimal height for panes
    public Pane createButtonDisplay() {
        indexedPanes = new HashMap<>();
        int rowSpacing = 30;
        int buttonSpacing = 11;
        int buttonHeight = 200;
        VBox column = new VBox();
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER);
        row.setSpacing(rowSpacing);
        column.setSpacing(buttonSpacing);

        HBox lr1 = new HBox();
        HBox lr2 = new HBox();
        HBox bottom = new HBox();
        bottom.setSpacing(rowSpacing);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPrefHeight(buttonHeight);

        lr1.setSpacing(buttonSpacing);
        lr1.setPrefHeight(buttonHeight);

        lr2.setSpacing(buttonSpacing);
        lr2.setPrefHeight(buttonHeight);

        for (int i = 0; i < 10; i++) {
            ButtonPane p = new ButtonPane("" + (i + 1));
            p.setPrefWidth(buttonHeight);
            p.setPrefHeight(50);
            p.setStyle("-fx-background-color: #ffffff;");
            if ( i < 5) {
                lr1.getChildren().add(p);
            } else {
                lr2.getChildren().add(p);
            }
            indexedPanes.put(i, p);
        }

		Map<Integer, String> map = ts.getStandardCompletions();
		for (int i = 0; i < 10; i++) {
			indexedPanes.get(i).setPotential(map.get(i));
		}

        row.getChildren().addAll(lr1, lr2);
        column.getChildren().addAll(row, bottom);
		Button button = new Button("Reset");
		button.setOnAction( (x) -> {
			if (referencedPanes != null) {
				referencedPanes.clear();
			}
			for (int i = 0; i < 10; i++) {
				indexedPanes.get(i).setKey("");
			}
			controller.resetMappings();
			index = 0;
			
			System.out.println(referencedPanes);
			System.out.println(indexedPanes);
		});
		bottom.getChildren().add(button);
        return column;
    }

    public void createInterpreter() {
        config = BasicTypingSystem.getDefaultProperties();
        area = new CommandableTextArea("Demo Text");
        area.getStyleClass().add("output");
        //area.setDisable(true);
        area.setOpacity(1);
        ts = new BasicTypingSystem(config, area);
        controller = new StringInterpreter(ts);

    }


    @Override
    public void start(Stage primaryStage) {
        createInterpreter();
        Pane row = createButtonDisplay();
        row.getStyleClass().add("button-pane");
        referencedPanes = new HashMap<>();
        BorderPane root = new BorderPane();
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (index != 10) {
					if (!referencedPanes.containsKey(code)) {
						referencedPanes.put(code, indexedPanes.get(index));
						indexedPanes.get(index).setKey(code);
						controller.addMapping(code, index);
						index++;
					}
                } else {
                    controller.input(code);
                }
                if (referencedPanes.containsKey(code)) {
                    referencedPanes.get(code).setStyle("-fx-background-color: green;");
					// Possibly use keyReleased for second presses
                    Map<Integer, String> map = ts.getStandardCompletions();
                    for (int i = 0; i < 10; i++) {
                        indexedPanes.get(i).setPotential(map.get(i));
                    }
                }
            }
        });
        root.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (referencedPanes.containsKey(code)) {
                    referencedPanes.get(code).setStyle("-fx-background-color: white;");
                }
            }
        });
        row.setPadding(new Insets(15, 12, 15, 12));
        root.setCenter(area);
        root.setBottom(row);
        Scene scene = new Scene(root, 700, 500);
        String css = Main.class.getResource("main.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setTitle("WIT - Blind Keyboard");
        primaryStage.setScene(scene);
        primaryStage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
