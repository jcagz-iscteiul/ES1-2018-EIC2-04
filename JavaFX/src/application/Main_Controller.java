package application;

import java.util.ArrayList;
import java.util.List;

import com.restfb.types.Post;

import RedesSociais.Facebook;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

public class Main_Controller {

	
	private Facebook fb;
	private ArrayList<Post> p;
	
    @FXML
    private TextArea area;

    @FXML
    private Button botao;
    
    @FXML
    private ListView<String> lista;
    
    @FXML
    private Tab tab1;
    
    @FXML
    private TabPane tabPane;
    
    
    public Main_Controller() {
		fb = new Facebook();
		p =(ArrayList<Post>) fb.getPosts();
		
	}
    
    
    

    @FXML
    void printa(ActionEvent event) {
    	System.out.println("printa");
    	area.appendText("benfica");
    	
    	for(Post pl : p) {
			lista.getItems().add(pl.getMessage());
		}
    	
    }
    
    
    
    
   

}
