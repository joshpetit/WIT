package dev.joshpetit.wit.gui.launcher;
import javafx.geometry.Pos;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ButtonPane extends BorderPane {
	Text numText;
	Text keyText;
	Text potentialText;

	public ButtonPane() {
		this("", "", "");
	}

	public ButtonPane(String numText) {
		this(numText, "", "");
	}

	public ButtonPane(String numText, String keyText, String potentialText) {
		super();
		int fontSize = 20;
		this.numText = new Text(numText);
		this.keyText = new Text(keyText);
		this.potentialText = new Text(potentialText);

		setTop(this.numText);
		setAlignment(this.numText, Pos.CENTER);

		setCenter(this.potentialText);
		setAlignment(this.potentialText, Pos.CENTER);

		setBottom(this.keyText);
		setAlignment(this.keyText, Pos.CENTER);
		this.keyText.setFont(new Font(fontSize));

	}

	public void setNum(String text) {
		this.numText.setText(text);
	}

	public void setPotential(String text) {
		this.potentialText.setText(text);
	}

	public void setKey(String text) {
		this.keyText.setText(text);
	}
}
