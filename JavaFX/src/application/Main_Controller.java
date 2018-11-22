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

import RedesSociais.EmailPost;
import RedesSociais.Facebook;
import RedesSociais.FacebookPost;
import RedesSociais.Gmail;
import RedesSociais.PostGeral;
import RedesSociais.TwitterMain;
import RedesSociais.TwitterPost;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * É o controlador da interface gráfica. Vão ser inicializados
 * os handlers dos eventos da interface gráfica
 * @author 
 *
 */

public class Main_Controller implements Initializable{
	
	
	private Facebook fb;
	private ArrayList<PostGeral> fb_posts;
	private Gmail gm;
	private ArrayList<PostGeral> gm_posts;
	private boolean fb_flag = false;
	private TwitterMain tw;
	private ArrayList<PostGeral> tw_posts;
	
	
    @FXML
    private TextArea area;

    @FXML
    private Button botao;
    
    @FXML
    private Button definicoes;
    
    @FXML
    private TextArea textAreaFacebook_list;
    
    @FXML
    private TextArea textAreaGmail_list;
    
    @FXML
    private TextArea textAreaTwitter_list;
    
    @FXML
    private ListView<String> listEmail;
    
    @FXML
    private ListView<String> listFacebook;
    
    @FXML
    private ListView<String> listTwitter;
    
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
    private TextField searchBarGmail;
    
    @FXML
    private TextField searchBarTwitter;
    
    
    @FXML
    private SplitMenuButton facebookSplitMenu;
    
    @FXML
    private SplitMenuButton emailSplitMenu;
    
    @FXML
    private SplitMenuButton twitterSplitMenu;
    /**
     * É o construtor da classe Main_Controller. Os atributos fb e fb_posts são inicializados através 
     * da criação do objeto Facebook
     */
    public Main_Controller() {
		try {
			fb = new Facebook();
			fb_posts = fb.getPosts();
			this.gm = new Gmail();
			this.gm_posts = gm.getEmails();
			this.tw = TwitterMain.getInstance();
			this.tw_posts = tw.getTw_tweet();
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
    
    
    /**
     * Atualiza a list view da interface gráfica com uma nova lista
     * de posts que são das últimas 24 horas
     * @param event
     */
    @FXML
    public void filtragem24_facebook(ActionEvent event) {
    	listFacebook.getItems().clear();
    	textAreaFacebook_list.clear();
    	for(PostGeral post : fb.vinteQuatroHoras(fb.getPosts())){
    		listFacebook.getItems().add(post.getTitulo());
    	}
    }
    
    
    
    @FXML
    public void filtragem24_gmail(ActionEvent event) {
    	System.out.println("evento das 24h");
    	listEmail.getItems().clear();
    	textAreaGmail_list.clear();
    	
    	
    	for(PostGeral post : gm.vinteQuatroHoras(gm.getEmails())){
    		listEmail.getItems().add(((EmailPost)post).emailPostPreview());
    	}
    	
    }
    
    
    @FXML
    void printa(ActionEvent event) {
    	System.out.println("printa");
    	area.appendText("benfica");	
    }
    
    /**
     * Vai atualizar a list view da interface gráfica com uma nova 
     * lista de posts com a palavra que o utilizador introduziu na
     * barra de pesquisa
     * @param event
     */
    @FXML
    public void searchButton(ActionEvent event) {
    	//Vai buscar a palavra que o utilizador escreveu
    	String palavra = searchBarFacebook.getText();
    	System.out.println("Palavra a procura: " + palavra);
    	int index = listFacebook.getSelectionModel().getSelectedIndex();
    	listFacebook.getSelectionModel().clearSelection(index);
    	listFacebook.getItems().clear();
    	//Vai buscar a lista nova
    	ArrayList<PostGeral> listaFacebook = fb.getPosts();
    	ArrayList<PostGeral> lista = fb.palavraChave(palavra, listaFacebook);
    	textAreaFacebook_list.clear();
    	this.fb_posts = lista;
    	for(PostGeral fbPost: lista) {
    		System.out.println(((FacebookPost) fbPost).getPostPreview());
    		listFacebook.getItems().add(((FacebookPost) fbPost).getPostPreview());
    	}
    	
    }
    
    @FXML
    public void searchButtonGmail(ActionEvent event) {
    	String palavra = searchBarGmail.getText();
    	System.out.println("Palavra a procurar: " + palavra);
    	int index = listEmail.getSelectionModel().getSelectedIndex();
    	listEmail.getSelectionModel().clearSelection(index);
    	listEmail.getItems().clear();
    	ArrayList<PostGeral> listaEmail = gm.getEmails();
    	ArrayList<PostGeral> lista = gm.palavraChave(palavra, listaEmail);
    	textAreaGmail_list.clear();
    	this.gm_posts = lista;
    	for(PostGeral post: lista) {
    		System.out.println(((EmailPost)post).emailPostPreview());
    		listEmail.getItems().add(((EmailPost)post).emailPostPreview());
    	}
    }
    
    @FXML
    void openSettingsScene(ActionEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Definicoes.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Definicoes");
    		stage.setScene(new Scene(root1));
    		stage.show();
    		}
    		catch(Exception e){
    		System.out.println("Cant load new window");
    		}
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Main:Controler ativo");
		
		if(tabPane.getSelectionModel().getSelectedItem().equals(tabEmail)) {
			System.out.println("Ja estava selecionado o email");
			System.out.println("Tamanho da lista posts gmail: " + gm_posts.size());
			for(PostGeral post: gm_posts) {
				listEmail.getItems().add(((EmailPost) post).emailPostPreview());
			}
		}
		
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

		    @Override
		    public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
		        if(newTab == tabFacebook && !fb_flag) {
		        	System.out.println("tab facebook aqui");
		        	
		        	for(PostGeral post : fb_posts) {
		        		listFacebook.getItems().add(((FacebookPost) post).getPostPreview());
		    		}
		        	
		        	fb_flag=true;
		        	
		        }
		        if(newTab == tabTwitter) {
		        	System.out.println("tab twitter aqui");
		        	
		        	for(PostGeral post : tw_posts) {
		        		listTwitter.getItems().add(tw.createPostPreview((TwitterPost) post));
		        	}
		        }
		        if(newTab == tabEmail) {
		        	System.out.println("tab email aqui");
		        }
		        }
		});
		
		this.textAreaFacebook_list.setWrapText(true);
		listFacebook.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    
			private int currentSelection = -1;
			
			@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	
				int i = listFacebook.getSelectionModel().getSelectedIndex();
				if(i != currentSelection) {
					currentSelection = i;	
					String selectedItem = listFacebook.getSelectionModel().getSelectedItem();
					System.out.println(selectedItem);
					FacebookPost post = fb.getPostEspecifico(selectedItem);
					textAreaFacebook_list.clear();
					textAreaFacebook_list.appendText(post.getFullPost());
				}
				currentSelection = -1;
		    }
		    
		});
		
		listEmail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			private int currentSelection = -1;
			
			
			@Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					
					int i = listEmail.getSelectionModel().getSelectedIndex();
					if(i != currentSelection) {
						currentSelection = i;
						String selectedItem = listEmail.getSelectionModel().getSelectedItem();
						System.out.println("Selected Item: " + selectedItem);
						EmailPost post = gm.getPostEspecifico(selectedItem);
						textAreaGmail_list.clear();
						textAreaGmail_list.appendText(post.getConteudo());
						
					}
					currentSelection = -1;
				
			    }
		});
		
		
		
			listTwitter.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			private int currentSelection = -1;	
			@Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					
					int i = listTwitter.getSelectionModel().getSelectedIndex();
					if(i != currentSelection) {
						currentSelection = i;
						String selectedItem = listTwitter.getSelectionModel().getSelectedItem();
						System.out.println("Selected Item: " + selectedItem);
						TwitterPost tweet = tw.getPostEspecifico(selectedItem); //Está a vir null
						textAreaTwitter_list.clear();
						textAreaTwitter_list.appendText(tweet.getConteudo());
					}
					currentSelection = -1;
			    }
		});
	}
}
