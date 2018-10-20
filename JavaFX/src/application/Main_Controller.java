package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    
    @FXML
    private TextField searchBarFacebook;
    
    @FXML
    private SplitMenuButton facebookSplitMenu;
    
    
    
    public Main_Controller() {
		try {
			fb = new Facebook();
			fb_posts = fb.getPosts();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
    
    
    
    @FXML
    public void filtragem24_facebook(ActionEvent event) {
    	listFacebook.getItems().clear();
    	
    	for(FacebookPost post : fb.vinteQuatroHoras(fb_posts)){
    		listFacebook.getItems().add(post.getPostPreview());
    	}
    }
    
    

    @FXML
    void printa(ActionEvent event) {
    	System.out.println("printa");
    	area.appendText("benfica");	
    }
    
    
    @FXML
    public void searchButton(ActionEvent ae) {
    	//Vai buscar a palavra que o utilizador escreveu
    	String palavra = searchBarFacebook.getText();
    	System.out.println("Palavra a procura: " + palavra);
    	int index = listFacebook.getSelectionModel().getSelectedIndex();
    	listFacebook.getSelectionModel().clearSelection(index);
    	listFacebook.getItems().clear();
    	//Vai buscar a lista nova
    	ArrayList<FacebookPost> listaFacebook = fb.getPosts();
    	ArrayList<FacebookPost> lista = fb.palavraChave(palavra, listaFacebook);
  
    	this.fb_posts = lista;
    	for(FacebookPost fbPost: lista) {
    		System.out.println(fbPost.getPostPreview());
    		listFacebook.getItems().add(fbPost.getPostPreview());
    	}
    	
    }
    
//    @FXML
//    public void listEmailClick(ActionEvent event) {
//    	System.out.println("Clickei na lista Email!");
//    }
    
    
   


//    ObservableList<String> lista = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Main:Controler ativo");
		
		
		
		
		if(tabPane.getSelectionModel().getSelectedItem().equals(tabEmail)) {
			System.out.println("twitter ja estava selecionado o email");
		}
		
		listEmail.getItems().add("Item1");
		listEmail.getItems().add("Item2");
		listEmail.getItems().add("Item3");
		listEmail.getItems().add("Item4");
		listEmail.getItems().add("Item5");
		listEmail.getItems().add("Item6");
		
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

		    @Override
		    public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
		        if(newTab == tabFacebook && !fb_flag) {
		        	System.out.println("tab facebook aqui");
		        	
		        	
		        	
		        	for(FacebookPost post : fb_posts) {
//		        		lista.add(post.getPostPreview());
		        		listFacebook.getItems().add(post.getPostPreview());
		    			
		    		}
		        	

//		        	listFacebook.setItems(lista);
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
		    
			private int currentSelection = -1;
			
			@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	
		    	
		    	
//		        for(FacebookPost post : fb.getPosts()) {
////		        	System.out.println(post.getPostPreview());
//		        	if(newValue.equals(post.getPostPreview())) {
//		        		System.out.println(post.getFullPost());
//		        		textAreaFacebook_list.clear();
//		        		textAreaFacebook_list.appendText(post.getFullPost());
//		        		
//		        	}
//		        	
//		        }
				
				int i = listFacebook.getSelectionModel().getSelectedIndex();
				if(i != currentSelection) {
					currentSelection = i;	
					String selectedItem = listFacebook.getSelectionModel().getSelectedItem();
					System.out.println(selectedItem);
					FacebookPost post = fb.getPostEspecifico(selectedItem);
					textAreaFacebook_list.clear();
					textAreaFacebook_list.appendText(post.getFullPost());
					//Ir buscar o post atraves do titulo (com o objeto fb)
//					for(FacebookPost post: fb.getPosts()) {
//						if(newValue.equals(post.getFullPost())) {
//							textAreaFacebook_list.clear();
//							textAreaFacebook_list.appendText(post.getFullPost());
//						}
//					}
				}
				currentSelection = -1;
		    }
		    
		});
		
		listEmail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			@Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        
					for(String item: listEmail.getItems()) {
						if(newValue.equals(item)) {
							System.out.println("Clickei neste item: " + item);
						}
						
					}
			    }
		});
		
		
		
	}
    
    
    
    
    
    
    
   

}
