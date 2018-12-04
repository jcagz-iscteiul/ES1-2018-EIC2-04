package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	static Main_Controller myControllerHandle;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
	    Parent root = loader.load();
	    myControllerHandle = (Main_Controller) loader.getController();
		
		Scene scene = new Scene(root, 800,620);
		if(myControllerHandle.isOnline()) {
			primaryStage.setOnHidden(e -> myControllerHandle.shutDown());
		}
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image icon = new Image(getClass().getResourceAsStream("/images/ES.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("BDA");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static Main_Controller getMyControllerHandle() {
		return myControllerHandle;
	}

	public static void main(String[] args) {
		launch(args);	
	}
}
