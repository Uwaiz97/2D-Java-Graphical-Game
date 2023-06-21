package ui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CharacterChooser extends VBox {
	
	private ImageView circleImage;
	private ImageView characterImage;
	
	private String notChosen = "C:/Users/uwais/Downloads/uipack_fixed/PNG/grey_box.png";
	private String chosen = "C:/Users/uwais/Downloads/uipack_fixed/PNG/grey_boxCross.png";
	
	private Characters character;
	
	private boolean isChosen;

	public CharacterChooser(Characters character) {
		circleImage = new ImageView(notChosen);
		characterImage = new ImageView(character.getURL());
		this.character = character;
		isChosen = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().addAll(circleImage,characterImage);
		
	}
	
	public Characters getCharacter()
	{
		return character;
	}
	
	public boolean getIsChosen()
	{
		return isChosen;
	}
	
	public void setIsChosen(boolean isChosen)
	{
		this.isChosen = isChosen;
		String ImageToSet = this.isChosen ? chosen : notChosen;
		circleImage.setImage(new Image(ImageToSet));
		
	}
}
