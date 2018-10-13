package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import com.restfb.types.Post;

import RedesSociais.Facebook;
import RedesSociais.FacebookPost;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class Main_Controller implements Initializable{

	
	private Facebook fb;
	private ArrayList<FacebookPost> fb_posts;
	private boolean fb_flag = false;
	
	
    @FXML
    private TextArea area;

    @FXML
    private Button botao;
    
    @FXML
    private TextArea textAreaFacebook_list;
    
    @FXML
    private ListView<String> listEmail;
    
    @FXML
    private ListView<String> listFacebook;

    
    @FXML
    private TabPane tabPane;
    
    @FXML
    private Tab tabEmail;
    
    @FXML
    private Tab tabFacebook;
    
    @FXML
    private Tab tabTwitter;
    
    
    
    public Main_Controller() {
		fb = new Facebook();
		fb_posts = fb.getPosts();
		
		
	}
    
    
    

    @FXML
    void printa(ActionEvent event) {
    	System.out.println("printa");
    	area.appendText("benfica");
    	
    }
    
    
   


    ObservableList<String> lista = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Main:Controler ativo");
		
		
		
		
		if(tabPane.getSelectionModel().getSelectedItem().equals(tabEmail)) {
			System.out.println("twitter ja estava selecionado o email");
		}
		
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

		    @Override
		    public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
		        if(newTab == tabFacebook && !fb_flag) {
		        	System.out.println("tab facebook aqui");
		        	
		        	
		        	
		        	for(FacebookPost post : fb_posts) {
		        		lista.add(post.getPostPreview());
		    			
		    		}
		        	

		        	listFacebook.setItems(lista);
		        	fb_flag=true;
		        	
		        }
		        if(newTab == tabTwitter) {
		        	System.out.println("tab twitter aqui");
		        }
		        if(newTab == tabEmail) {
		        	System.out.println("tab email aqui");
		        }
		        }
		    });
		
		
		
		
		listFacebook.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        
		        for(FacebookPost post : fb_posts) {
		        	if(newValue.equals(post.getPostPreview())) {
		        		System.out.println(post.getFullPost());
		        		textAreaFacebook_list.clear();
		        		textAreaFacebook_list.appendText(post.getFullPost());
		        		
		        	}
		        	
		        }
		    }
		});
		
		
	}
    
    
    
    
    
    
    
   

}
