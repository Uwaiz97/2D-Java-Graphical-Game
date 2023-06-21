package ui;

public enum Characters {
	MONKEY("C:/Users/uwais/Downloads/kenney_animalpackredux/PNG/Round (outline)/gorilla.png", "C:/Users/uwais/Downloads/uipack_fixed/PNG/red_circle.png"),
	CHICK("C:/Users/uwais/Downloads/kenney_animalpackredux/PNG/Round (outline)/chick.png", "C:/Users/uwais/Downloads/uipack_fixed/PNG/yellow_circle.png"),
	SLOTH("C:/Users/uwais/Downloads/kenney_animalpackredux/PNG/Round (outline)/sloth.png", "C:/Users/uwais/Downloads/uipack_fixed/PNG/green_circle.png");
	
	private String CharacterURL;
	private String LifeURL;
	
	private Characters(String url, String lifeURL)
	{
		this.CharacterURL = url;
		this.LifeURL = lifeURL;
		
	}
	
	public String getURL()
	{
		return this.CharacterURL;
	}
	
	public String getLifeURL()
	{
		return this.LifeURL;
	}
}	
