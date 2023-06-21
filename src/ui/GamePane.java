package ui;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GamePane extends StackPane {
	
	private final int WIDTH = 1024;
	private final int HEIGHT = 768;
	private AnchorPane mainPane = new AnchorPane();
	private Stage mainStage = new Stage();
	
	private final static int MENU_BUTTON_START_X = 100;
	private final static int MENU_BUTTON_START_Y = 150;
	
	private GameSubScene startScene;
	private GameSubScene scoreScene;
	private GameSubScene creditsScene;
	private GameSubScene helpScene;
	private GameSubScene scene2hide;
	
	List<GameButtons> menuButtons;
	List<CharacterChooser> characterList;
	
	private Characters chosenCharacter;
 
	

	public GamePane() {
		// TODO Auto-generated constructor stub
		menuButtons = new ArrayList<>();
		Scene mainScene = new Scene(mainPane,WIDTH,HEIGHT);
		mainStage.setScene(mainScene);
		setBackground();
		createButtons();
		createSubScenes();
	}
	
	public Stage getStage()
	{
		return mainStage;
	} 
	
	private void addButtons(GameButtons button)
	{
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY(MENU_BUTTON_START_Y + (menuButtons.size()*100));
		menuButtons.add(button);
		mainPane.getChildren().add(button);
		
	}
	
	private void createButtons()
	{
		createPlayButton();
		createScoreButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
	}
	
	private void ShowSubscene(GameSubScene subscene)
	{
		if(scene2hide != null)
		{
			scene2hide.moveSubscene();
		}
		
		subscene.moveSubscene();
		scene2hide = subscene;
	}
	
	private void createSubScenes()
	{	
		scoreScene = new GameSubScene();
		mainPane.getChildren().add(scoreScene);
		
		creditsScene = new GameSubScene();
		mainPane.getChildren().add(creditsScene);
		
		helpScene = new GameSubScene();
		mainPane.getChildren().add(helpScene);
		
		createCharacterChooserScene();
	}
	
	private void createCharacterChooserScene()
	{
		startScene = new GameSubScene();
		mainPane.getChildren().add(startScene);
		
		Information chooseCharacterLabel = new Information("Pick Character");
		chooseCharacterLabel.setLayoutX(110);
		chooseCharacterLabel.setLayoutY(25);
		
		startScene.getPane().getChildren().add(chooseCharacterLabel);
		startScene.getPane().getChildren().add(createCharacterPicker());
		startScene.getPane().getChildren().add(createStartButton());
	}
	
	private HBox createCharacterPicker()
	{
		HBox hBox = new HBox();
		hBox.setSpacing(20);
		characterList = new ArrayList<>();
		for(Characters c : Characters.values())
		{
			CharacterChooser chooseCharacter = new CharacterChooser(c);
			characterList.add(chooseCharacter);
			hBox.getChildren().add(chooseCharacter);
			
			chooseCharacter.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for(CharacterChooser cc : characterList)
					{
						cc.setIsChosen(false);
					}
					chooseCharacter.setIsChosen(true);
					chosenCharacter = chooseCharacter.getCharacter();
				}
			});
		}
		hBox.setLayoutX(300-(118*2));
		hBox.setLayoutY(100);
		return hBox;
	}
	
	private GameButtons createStartButton()
	{
		GameButtons startButton = new GameButtons("START");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(chosenCharacter != null)
				{
					GamePaneManager gameManager = new GamePaneManager();
					gameManager.createNewGame(mainStage,chosenCharacter);
				}
				
			}
			
		});
		
		return startButton;
	}
	
	private void createPlayButton()
	{
		GameButtons playButton = new GameButtons("PLAY");
		addButtons(playButton);
		
		playButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				ShowSubscene(startScene);
				
			}
			
		});
	}
	
	private void createScoreButton()
	{
		GameButtons ScoreButton = new GameButtons("SCORE");
		addButtons(ScoreButton);
		
		ScoreButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ShowSubscene(scoreScene);
				
			}
		});
	}
	
	private void createHelpButton()
	{
		GameButtons HelpButton = new GameButtons("HELP");
		addButtons(HelpButton);
		
		HelpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ShowSubscene(helpScene);
				
			}
		});
	}
	
	private void createCreditsButton()
	{
		GameButtons CreditsButton = new GameButtons("CREDITS");
		addButtons(CreditsButton);
		
		CreditsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ShowSubscene(creditsScene);				
			}
			
		});
	}
	
	private void createExitButton()
	{
		GameButtons ExitButton = new GameButtons("EXIT");
		addButtons(ExitButton);
		
		ExitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
				
			}
		});
	}
	
	private void setBackground() {
		Image backgroundImage = new Image("C:/Users/uwais/Downloads/uipack_fixed/PNG/background.png",256,256,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,null);
		mainPane.setBackground(new Background(background));   
	}
	
	
}
