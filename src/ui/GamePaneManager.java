package ui;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GamePaneManager {
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	private final static int WIDTH = 600;
	private final static int HEIGHT = 700;
	
	private Stage menuStage;
	private ImageView character;
	
	private boolean PressedLeft;
	private boolean PressedRight;
	private int angle;
	private AnimationTimer gameTimer;
	
	private GridPane gridPaneOne;
	private GridPane gridPaneTwo;
	private final static String BACKGROUND_IMAGE_URL = "C:/Users/uwais/Downloads/uipack_fixed/PNG/background.png";
	private final static String PREDATOR_1_URL = "C:/Users/uwais/Downloads/kenney_animalpackredux/PNG/Round (outline)/bear.png";
	private final static String PREDATOR_2_URL = "C:/Users/uwais/Downloads/kenney_animalpackredux/PNG/Round (outline)/crocodile.png";

	private ImageView[] Predator1;
	private ImageView[] Predator2;
	
	Random randPosition;
	
	private ImageView fruit;
	private InformationLBL PointslbL;
	private ImageView[] life;
	private int playerLife;
	private int Points;
	private final static String FRUIT_IMAGE_URL = "C:/Users/uwais/Downloads/uipack_fixed/PNG/banana.png";
	
	private final static int FRUIT_RADIUS = 12;
	private final static int PREDATOR_RADIUS = 27 ;
	private final static int CHARACTER_RADIUS = 20;

	public GamePaneManager() {
		Initialise();
		createListeners();
		randPosition = new Random();
		
	}

	private void Initialise() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane,WIDTH,HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
		menuStage = new Stage();
		
	}
	
	private void createListeners()
	{
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()== KeyCode.LEFT)
				{
					PressedLeft = true;
					
				}else if(event.getCode()== KeyCode.RIGHT)
				{
					PressedRight = true;
				}
				
			} 
			
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()== KeyCode.LEFT)
				{
					PressedLeft = false;
					
				}else if(event.getCode()== KeyCode.RIGHT)
				{
					PressedRight = false;
					
				}
				
			} 
			
		});
	}
	
	public void createNewGame(Stage menuStage, Characters character)
	{
		this.menuStage = menuStage;
		menuStage.hide();
		createBackground();
		createNewCharacter(character);
		createGameObjects(character);
		createGameLoop();
		gameStage.show();
	}
	
	private void createNewCharacter(Characters CharacterPicked)
	{
		character = new ImageView(CharacterPicked.getURL());
		character.setLayoutX(WIDTH/2);
		character.setLayoutY(HEIGHT - 90);
		
		gamePane.getChildren().add(character);
	}
	
	private void createGameObjects(Characters chosenCharacter)
	{
		playerLife = 2;
		fruit = new ImageView(FRUIT_IMAGE_URL);
		setNewPosition(fruit);
		gamePane.getChildren().add(fruit);
		PointslbL = new InformationLBL("Points :00");
		PointslbL.setLayoutX(460);
		PointslbL.setLayoutY(20);
		gamePane.getChildren().add(PointslbL);
		life = new ImageView[3];
		
		for(int l = 0; l < life.length;l++)
		{	
			life[l] = new ImageView(chosenCharacter.getLifeURL());
			life[l].setLayoutX(455+(l*50));
			life[l].setLayoutY(80);
			gamePane.getChildren().add(life[l]);
		}
		Predator1 = new ImageView[3];
		for(int p = 0; p < Predator1.length;p++)
		{
			Predator1[p] = new ImageView(PREDATOR_1_URL);
			setNewPosition(Predator1[p]);
			gamePane.getChildren().add(Predator1[p]);
		}
		
		Predator2 = new ImageView[3];
		for(int p = 0; p < Predator2.length;p++)
		{
			Predator2[p] = new ImageView(PREDATOR_2_URL);
			setNewPosition(Predator2[p]);
			gamePane.getChildren().add(Predator2[p]);
		}
		
	}
	
	private void moveGameObjects() {
		
		for(int i = 0; i < Predator1.length; i++)
		{
			Predator1[i].setLayoutY(Predator1[i].getLayoutY()+7);
			Predator1[i].setRotate(Predator1[i].getRotate()+4);
		}
		
		for(int i = 0; i < Predator2.length; i++)
		{
			Predator2[i].setLayoutY(Predator2[i].getLayoutY()+7);
			Predator2[i].setRotate(Predator2[i].getRotate()+4);
		}
	}
	
	private void checkObjectPosition()
	{
		if(fruit.getLayoutY()>1200)
		{
			setNewPosition(fruit);
		}
		for(int p = 0; p < Predator1.length; p++)
		{
			if(Predator1[p].getLayoutX()> 900)
			{
				setNewPosition(Predator1[p]);
			}
		}
		for(int p = 0; p < Predator2.length; p++)
		{
			if(Predator2[p].getLayoutX()> 900)
			{
				setNewPosition(Predator2[p]);
			}
		}
	}
	private void setNewPosition(ImageView image)
	{
		image.setLayoutX(randPosition.nextInt(370));
		image.setLayoutY(-(randPosition.nextInt(3200)+600));
	}
	
	private void createGameLoop()
	{
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				moveBackground();
				moveGameObjects();
				checkObjectPosition();
				checkObjectCollision();
				moveCharacter();
				
			}
			
		};
		gameTimer.start();
	}
	
	private void createBackground()
	{
		gridPaneOne = new GridPane();
		gridPaneTwo = new GridPane();
		
		for(int n = 0; n < 24; n++)
		{
			ImageView BackgroundOne = new ImageView(BACKGROUND_IMAGE_URL);
			ImageView BackgroundTwo = new ImageView(BACKGROUND_IMAGE_URL);
			
			GridPane.setConstraints(BackgroundOne, n%6, n/6);
			GridPane.setConstraints(BackgroundTwo, n%6, n/6);
			
			gridPaneOne.getChildren().add(BackgroundOne);
			gridPaneTwo.getChildren().add(BackgroundTwo);
		}
		
		gridPaneTwo.setLayoutY(-1400);
		
		gamePane.getChildren().addAll(gridPaneOne,gridPaneTwo); 
	}
	
	private void moveBackground()
	{
		gridPaneOne.setLayoutY(gridPaneOne.getLayoutY()+1);
		gridPaneTwo.setLayoutY(gridPaneTwo.getLayoutY()+1);
		
		if(gridPaneOne.getLayoutY() >= 1024)
		{
			gridPaneOne.setLayoutY(-1024);
		}
		
		if(gridPaneTwo.getLayoutY() >= 1024)
		{
			gridPaneTwo.setLayoutY(-1024);
		}
	}
	private void moveCharacter()
	{
		if(PressedRight && !PressedLeft)
		{
			if(angle < 30)
			{
				angle += 5;
			}
			character.setRotate(angle);
			
			if(character.getLayoutX() < 458)
			{
				character.setLayoutX(character.getLayoutX()+3);
			}
		}
		if(PressedLeft && !PressedRight)
		{
			if(angle > -30)
			{
				angle -= 5;
			}
			character.setRotate(angle);
			
			if(character.getLayoutX() > -2)
			{
				character.setLayoutX(character.getLayoutX()-3);
			}
		}
		if(PressedRight && PressedLeft)
		{
			if(angle < 0)
			{
				angle += 5;
			}else if(angle > 0)
			{
				angle -= 5;
			}
			character.setRotate(angle);
		}
		if(!PressedRight && !PressedLeft)
		{
			if(angle < 0)
			{
				angle += 5;
			}else if(angle > 0)
			{
				angle -= 5;
			}
			character.setRotate(angle);
		}
	}
	private void checkObjectCollision()
	{
		if(CHARACTER_RADIUS+FRUIT_RADIUS > Distance(character.getLayoutX()+49,fruit.getLayoutX()+15,character.getLayoutY()+37,fruit.getLayoutY()+15))
		{
			setNewPosition(fruit);
			String textToSet = "POINTS :";
			if(Points < 10)
			{
				textToSet = textToSet + "0";
			}
			PointslbL.setText(textToSet + Points);
		}
		
		for(int p = 0; p < Predator1.length;p++)
		{
			if(PREDATOR_RADIUS +CHARACTER_RADIUS > Distance(character.getLayoutX()+49,Predator1[p].getLayoutX()+20
					,character.getLayoutY()+37,Predator1[p].getLayoutY()+20))
			{
				DecreaseLife();
				setNewPosition(Predator1[p]);
			}
			
		}
		
		for(int p = 0; p < Predator2.length;p++)
		{
			if(PREDATOR_RADIUS +CHARACTER_RADIUS > Distance(character.getLayoutX()+49,Predator2[p].getLayoutX()+20
					,character.getLayoutY()+37,Predator2[p].getLayoutY()+20))
			{
				DecreaseLife();
				setNewPosition(Predator2[p]);
			}
			
		}
	}
	private void DecreaseLife()
	{
		gamePane.getChildren().remove(life[playerLife]);
		playerLife--;
		if(playerLife < 0)
		{
			gameStage.close();
			gameTimer.stop();
			menuStage.show();
		}
	}
	
	private double Distance(double x1,double x2,double y1,double y2)
	{
		return Math.sqrt(Math.pow(x1-x2,2)+ Math.pow(y1-y2,2));
	}
	

}
