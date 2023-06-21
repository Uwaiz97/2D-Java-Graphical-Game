package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class InformationLBL extends Label {
	
	public final static String FONT_PATH = "C:/Users/uwais/Downloads/uipack_fixed/Font/kenvector_future.ttf";
	public final static String BACKGROUND_IMAGE = "C:/Users/uwais/Downloads/uipack_fixed/PNG/yellow_button13.png";

	public InformationLBL(String text){
		setPrefWidth(130);
		setPrefHeight(50);
		setLabelFont();
		
		BackgroundImage background = new BackgroundImage(new Image(BACKGROUND_IMAGE,130,50,false,true), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
		setBackground(new Background(background));
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10,10,10,10));
		setText(text);
	}
	
	private void setLabelFont() {
		
		try{
			setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)),15));
		}catch(FileNotFoundException ex) {
			setFont(Font.font("Calibri",15));
		}
		
	}


}
