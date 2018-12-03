package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
		Pane root = loader.load();
		Scene scene = new Scene(root, 800,620);
		Main_Controller controller = loader.getController();
		if(controller.isOnline()) {
			primaryStage.setOnHidden(e -> controller.shutDown());
		}
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("BDA");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);	
	}
}
