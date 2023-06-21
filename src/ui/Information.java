package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class Information extends Label{
	
	private final static String FONT_PATH = "C:/Users/uwais/Downloads/uipack_fixed/Font/kenvector_future.ttf";
	
	private final static String BACKGROUND_IMAGE = "C:/Users/uwais/Downloads/uipack_fixed/PNG/yellow_button13.png";

	public Information(String text) {
		setPrefWidth(380);
		setPrefHeight(49);
		setText(text);
		setWrapText(true);
		setLabelFont();
		setAlignment(Pos.CENTER);
		
		BackgroundImage background = new BackgroundImage(new Image(BACKGROUND_IMAGE,380,49,false,true), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
		
		setBackground(new Background(background));
	}

	private void setLabelFont() {
		
		try{
			setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)),23));
		}catch(FileNotFoundException ex) {
			setFont(Font.font("Calibri",23));
		}
		
	}

}
