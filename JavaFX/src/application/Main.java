package application;
	
import java.io.IOException;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Pane root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root, 600,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
}
	
	public static void main(String[] args) {
		

		
		
//		GregorianCalendar mn = new GregorianCalendar(2018, 10, 16, 21, 36);
//		java.util.Date min = mn.getTime();
//		
//		System.out.println("date minima: " + min.toString());
//		
//		
//		GregorianCalendar me = new GregorianCalendar(2018, 10, 16, 21, 36);
//		java.util.Date meio = me.getTime();
//		
//		System.out.println("date media: " + meio.toString());
//		System.out.println("date menos um dia: ");
//		
//		
//		Calendar calendar = Calendar.getInstance(); // this would default to now
//		System.out.println("calendario hoje: " + calendar.getTime().toString());
//		calendar.add(Calendar.DAY_OF_MONTH, -1);
//		System.out.println("calendario ontem: " + calendar.getTime().toString());
//		
//		
//		
//		GregorianCalendar mx = new GregorianCalendar(2018, 10, 16, 21, 59);
//		java.util.Date max = mx.getTime();
//		
//		System.out.println("date maxima: " + max.toString());
//		
//		
//		if(meio.compareTo(min) * meio.compareTo(max)<=0) {
//			System.out.println("a data está entre as outras duas");
//		}
//		else {
//			System.out.println("a data NAO esta entre as outras duas");
//		}
		
		
		launch(args);
		
		
	}
	
}
