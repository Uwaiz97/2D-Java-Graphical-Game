package ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class GameButtons extends Button{
	
	private final String FONT_PATH = "C:/Users/uwais/Downloads/uipack_fixed/Font/kenvector_future.ttf";
	private final String BUTTON_PRESSED = "-fx-backgroung-color: transparent; -fx-background-image: url('C:/Users/uwais/Downloads/uipack_fixed/PNG/yellow_button_pressed');";
	private final String BUTTON_FREE = "-fx-backgroung-color: transparent;-fx-background-image: url('C:/Users/uwais/Downloads/uipack_fixed/PNG/yellow_button01.png');";

	public GameButtons(String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_FREE);
		InitialiseListeners();
	}
	
	private void setButtonFont()
	{
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH),23));
		}catch(FileNotFoundException e) {
			setFont(Font.font("Verdana",23));
		}
	}
	
	private void setButtonPressed()
	{
		setStyle(BUTTON_PRESSED);
		setPrefHeight(45);
		setLayoutY(getLayoutY()+4);
	}
	
	private void setButtonReleased()
	{
		setStyle(BUTTON_FREE);
		setPrefHeight(49);
		setLayoutY(getLayoutY()-4);
	}
	
	private void InitialiseListeners()
	{
		setOnMousePressed(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY))
				{
					setButtonPressed();
				}
			}
	});
		setOnMouseReleased(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY))
				{
					setButtonReleased();
				}
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
				
			}	
			
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
				
			}	
			
		});
		
	}	
	

}
  