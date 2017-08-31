package app;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws IOException {
		Parent parent =(Parent) FXMLLoader.load(getClass().getResource("/app/view/LoginView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("QUIZ");
		stage.getIcons().add(new Image("http://www.myandroidsolutions.com/wp-content/uploads/2016/12/Quiz-Time.png"));
		stage.show();

	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
