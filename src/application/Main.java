package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import ui.GamePane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GamePane mainPane = new GamePane();
			primaryStage = mainPane.getStage();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
