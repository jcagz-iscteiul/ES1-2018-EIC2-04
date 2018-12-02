package application;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.restfb.types.Post;

import RedesSociais.Destaques;
import RedesSociais.EmailPost;
import RedesSociais.Facebook;
import RedesSociais.FacebookPost;
import RedesSociais.Gmail;
import RedesSociais.PostGeral;
import RedesSociais.TwitterObject;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import twitter4j.TwitterException;

/**
 * É o controlador da interface gráfica. Vão ser inicializados os handlers dos
 * eventos da interface gráfica
 * 
 * @author
 *
 */

public class Main_Controller implements Initializable {

	private Facebook fb;
	private ArrayList<PostGeral> fb_posts;
	private Gmail gm;
	private ArrayList<PostGeral> gm_posts;
	private boolean fb_flag = false;
	private TwitterObject tw;
	private ArrayList<PostGeral> tw_posts;
	private ArrayList<PostGeral> destaques;

	@FXML
	private TextArea area;

	@FXML
	private Button botao;
	
	@FXML
	private Destaques destaquesObject;
	
	@FXML
	private Button botaoSendEmail;
	
	@FXML 
	private Button botaoSendTwitter;
	
	@FXML
	private Button botaoRefreshDestaques;
	
	@FXML
	private Button botaoRefreshFacebook;
	
	@FXML
	private Button botaoRefreshTwitter;
	
	@FXML
	private Button botaoRefreshEmail;
	
	@FXML
	private TextField emailTo;
	
	@FXML
	private TextField assunto;
	
	@FXML
	private TextArea conteudoEmail;
	
	@FXML
	private TextArea conteudoTwitter;
	
	@FXML
	private Button definicoes;

	@FXML
	private TextArea textAreaFacebook_list;

	@FXML
	private TextArea textAreaGmail_list;

	@FXML
	private TextArea textAreaTwitter_list;
	
	@FXML
	private TextArea textDestaques_list;

	@FXML
	private ListView<String> listEmail;

	@FXML
	private ListView<String> listFacebook;

	@FXML
	private ListView<String> listTwitter;
	
	@FXML
	private ListView<String> listDestaques;

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabEmail;

	@FXML
	private Tab tabFacebook;

	@FXML
	private Tab tabTwitter;
	
	@FXML
	private Tab tabDestaques;

	@FXML
	private TextField searchBarFacebook;
	
	@FXML
	private ToggleButton toggleFb;
	
	@FXML
	private ToggleButton toggleGmail;
	
	@FXML
	private ToggleButton toggleTwitter;
	
	@FXML
	private ToggleButton toggleDestaques;

	@FXML
	private TextField searchBarGmail;

	@FXML
	private TextField searchBarTwitter;
	
	@FXML
	private TextField searchBarDestaques;

	@FXML
	private SplitMenuButton facebookSplitMenu;

	@FXML
	private SplitMenuButton emailSplitMenu;

	@FXML
	private SplitMenuButton twitterSplitMenu;

	/**
	 * É o construtor da classe Main_Controller. Os atributos fb e fb_posts são
	 * inicializados através da criação do objeto Facebook
	 */
	public Main_Controller() {
		fb = new Facebook();
		fb_posts = fb.getLista_posts();
		this.tw = TwitterObject.getInstance();
		this.tw_posts = tw.getLista_posts();
		this.gm = new Gmail();
		this.gm_posts = gm.getLista_posts();

		this.destaquesObject = new Destaques(gm_posts, fb_posts, tw_posts);
		this.destaques = destaquesObject.getDestaques();
		
		
	}

	/**
	 * Atualiza a list view da interface gráfica com uma nova lista de posts que são
	 * das últimas 24 horas
	 * 
	 * @param event
	 */
	
	
	@FXML
	public void filtragem24_destaques(ActionEvent event) {
		listDestaques.getItems().clear();
		textDestaques_list.clear();
		for (PostGeral post : destaquesObject.vinteQuatroHoras(destaquesObject.getDestaques())) {
			listDestaques.getItems().add(post.createTitulo());
		}
	}
	
	@FXML
	public void filtragem24_facebook(ActionEvent event) {
		listFacebook.getItems().clear();
		textAreaFacebook_list.clear();
		for (PostGeral post : fb.vinteQuatroHoras(fb.getLista_posts())) {
			listFacebook.getItems().add(post.getTitulo());
		}
	}

	@FXML
	public void filtragem24_gmail(ActionEvent event) {
		System.out.println("evento das 24h");
		listEmail.getItems().clear();
		textAreaGmail_list.clear();

		for (PostGeral post : gm.vinteQuatroHoras(gm.getLista_posts())) {
			listEmail.getItems().add(((EmailPost) post).emailPostPreview());
		}

	}
	
	@FXML
	public void filtragem24_twitter(ActionEvent event) {
		System.out.println("evento das 24h");
		listTwitter.getItems().clear();
		textAreaTwitter_list.clear();

		for (PostGeral post : tw.vinteQuatroHoras(tw.getLista_posts())) {
			listTwitter.getItems().add((tw.createPostPreview((TwitterPost) post)));
			
		}
	}

	@FXML
	public void toggleButtonFbEvent(ActionEvent event) {
			
		fb.viraLista();	
		
		this.fb_posts = fb.getLista_posts();
		
		int index = listFacebook.getSelectionModel().getSelectedIndex();
		listFacebook.getSelectionModel().clearSelection(index);
		listFacebook.getItems().clear();
	
		textAreaFacebook_list.clear();
		
		for (PostGeral post : fb_posts) {
			listFacebook.getItems().add(((FacebookPost) post).getTitulo());
		}
	}
	
	
	@FXML
	public void toggleButtonGmailEvent(ActionEvent event) {
		
		gm.viraLista();
		
		this.gm_posts = gm.getLista_posts();
		
		int index = listEmail.getSelectionModel().getSelectedIndex();
		listEmail.getSelectionModel().clearSelection(index);
		listEmail.getItems().clear();
	
		textAreaGmail_list.clear();
		
		for (PostGeral post : gm_posts) {
			listEmail.getItems().add(((EmailPost) post).emailPostPreview());
		}
	}
	
	
	
	
	@FXML
	public void toggleButtonTwitterEvent(ActionEvent event) {
		
		tw.viraLista();
		
		this.tw_posts = tw.getLista_posts();
		
		int index = listTwitter.getSelectionModel().getSelectedIndex();
		listTwitter.getSelectionModel().clearSelection(index);
		listTwitter.getItems().clear();
	
		textAreaTwitter_list.clear();
		
		for (PostGeral post : tw_posts) {
			listTwitter.getItems().add(tw.createPostPreview((TwitterPost) post));
		}
	}
	
	
	@FXML
	public void toggleButtonDestaquesEvent(ActionEvent event) {
		
		destaquesObject.viraLista();
		
		this.destaques = destaquesObject.getDestaques();
		
		int index = listDestaques.getSelectionModel().getSelectedIndex();
		listDestaques.getSelectionModel().clearSelection(index);
		listDestaques.getItems().clear();
	
		textDestaques_list.clear();
		
		for (PostGeral post : destaques) {
			listDestaques.getItems().add(post.createTitulo());
		}
	}
	
	
	
	
	@FXML
	public void sendEmailEvent(ActionEvent event) {
		
		gm.sendEmail(emailTo.getText(), assunto.getText(), conteudoEmail.getText());
		emailTo.clear();
		assunto.clear();
		conteudoEmail.clear();
		System.out.println("Email enviado com sucesso(acho eu, mano confirma no email)");
		
	}
	
	@FXML
	public void sendTweetEvent(ActionEvent event) {
		
		try {
			tw.fazertweet(conteudoTwitter.getText());
			System.out.println("Foi feito o tweet: " + conteudoTwitter.getText());
			conteudoTwitter.clear();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void buttonRefreshDestaques(ActionEvent event) {
		//Os atributos ficam atualizados
		listDestaques.getItems().clear();
		textDestaques_list.clear();
		destaques.clear();
		fb.refrescarConteudo();
		gm.refrescarConteudo();
		tw.refrescarConteudo();

		this.fb_posts = fb.getLista_posts();
		this.gm_posts = gm.getLista_posts();
		this.tw_posts = tw.getLista_posts();
		this.destaquesObject = new Destaques(gm_posts, fb_posts, tw_posts);
		this.destaques = destaquesObject.getDestaques();
		
		//Transpor a lista para a Interface Gráfica
		for(PostGeral post: destaques) {
//			System.out.println("Estou a percorrer a lista dos Destaques");
			listDestaques.getItems().add(post.createTitulo());
		}
		
	}
	
	@FXML
	public void buttonRefreshFacebook(ActionEvent event) {
		listFacebook.getItems().clear();
		textAreaFacebook_list.clear();
		fb.refrescarConteudo();
		this.fb_posts = fb.getLista_posts();
		
		for(PostGeral post: fb_posts) {
			listFacebook.getItems().add(((FacebookPost)post).getTitulo());
		}
	}
	
	@FXML
	public void buttonRefreshTwitter(ActionEvent event) {
		listTwitter.getItems().clear();
		textAreaTwitter_list.clear();
		tw.refrescarConteudo();
		this.tw_posts = tw.getLista_posts();
		
		for(PostGeral post: tw_posts) {
//			System.out.println("Estou no buttonRefreshTwitter");
			listTwitter.getItems().add(tw.createPostPreview((TwitterPost)post));
//			System.out.println("ID: " + post.getId());
//			System.out.println("Conteudo: " + post.getConteudo() + "\n");
		}
	}
	
	@FXML
	public void buttonRefreshEmail(ActionEvent event) {
		listEmail.getItems().clear();
		textAreaGmail_list.clear();
		gm.refrescarConteudo();
		this.gm_posts.clear();
		
		for(PostGeral post: gm_posts) {
			listEmail.getItems().add(((EmailPost)post).emailPostPreview());
		}
	}
	

	/**
	 * Vai atualizar a list view da interface gráfica com uma nova lista de posts
	 * com a palavra que o utilizador introduziu na barra de pesquisa
	 * 
	 * @param event
	 */
	
	
	@FXML
	public void searchButtonDestaques(ActionEvent event) {
		// Vai buscar a palavra que o utilizador escreveu
		String palavra = searchBarDestaques.getText();
		System.out.println("Palavra a procura: " + palavra);
		int index = listDestaques.getSelectionModel().getSelectedIndex();
		listDestaques.getSelectionModel().clearSelection(index);
		listDestaques.getItems().clear();
		// Vai buscar a lista nova
		ArrayList<PostGeral> listaDestaques = destaquesObject.getDestaques();
		ArrayList<PostGeral> lista = destaquesObject.palavraChave(palavra, listaDestaques);
		textDestaques_list.clear();
		this.destaques = lista;
		for (PostGeral post : lista) {
			System.out.println( post.createTitulo());
			listDestaques.getItems().add(post.createTitulo());
		}

	}
	
	@FXML
	public void searchButton(ActionEvent event) {
		// Vai buscar a palavra que o utilizador escreveu
		String palavra = searchBarFacebook.getText();
		System.out.println("Palavra a procura: " + palavra);
		int index = listFacebook.getSelectionModel().getSelectedIndex();
		listFacebook.getSelectionModel().clearSelection(index);
		listFacebook.getItems().clear();
		// Vai buscar a lista nova
		ArrayList<PostGeral> listaFacebook = fb.getLista_posts();
		ArrayList<PostGeral> lista = fb.palavraChave(palavra, listaFacebook);
		textAreaFacebook_list.clear();
		this.fb_posts = lista;
		for (PostGeral post : lista) {
			System.out.println(((FacebookPost) post).getTitulo());
			listFacebook.getItems().add(post.getTitulo());
		}

	}

	@FXML
	public void searchButtonGmail(ActionEvent event) {
		String palavra = searchBarGmail.getText();
		System.out.println("Palavra a procurar: " + palavra);
		int index = listEmail.getSelectionModel().getSelectedIndex();
		listEmail.getSelectionModel().clearSelection(index);
		listEmail.getItems().clear();
		ArrayList<PostGeral> listaEmail = gm.getLista_posts();
		ArrayList<PostGeral> lista = gm.palavraChave(palavra, listaEmail);
		textAreaGmail_list.clear();
		this.gm_posts = lista;
		for (PostGeral post : lista) {
			System.out.println(((EmailPost) post).emailPostPreview());
			listEmail.getItems().add(((EmailPost) post).emailPostPreview());
		}
	}
	
	@FXML
	public void searchButtonTwitter(ActionEvent event) {
		String palavra = searchBarTwitter.getText();
		System.out.println("Palavra a procurar: " + palavra);
		int index = listTwitter.getSelectionModel().getSelectedIndex();
		listTwitter.getSelectionModel().clearSelection(index);
		listTwitter.getItems().clear();
		ArrayList<PostGeral> listaTweet = tw.getLista_posts();
		ArrayList<PostGeral> lista = tw.palavraChave(palavra, listaTweet);
		textAreaTwitter_list.clear();
		this.tw_posts = lista;
		for (PostGeral post : lista) {
			System.out.println(((TwitterPost) post).getConteudo());
			listTwitter.getItems().add(tw.createPostPreview((TwitterPost) post));
		}
	}

	@FXML
	void openSettingsScene(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Definicoes.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Definicoes");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			System.out.println("Cant load new window");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Main: Controlador ativo");

		if (tabPane.getSelectionModel().getSelectedItem().equals(tabDestaques)) {
			System.out.println("Ja estava selecionado o tab de destaques");
			
		
			
			for (PostGeral destaque : destaques) {
				listDestaques.getItems().add(destaque.createTitulo());
			}
			
		}

		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
				if (newTab == tabFacebook && !fb_flag) {
					System.out.println("- Aberta tab Facebook");

					for (PostGeral post : fb_posts) {
						listFacebook.getItems().add(((FacebookPost) post).getTitulo());
					}

					fb_flag = true;

				}
				if (newTab == tabTwitter) {
					System.out.println("- Aberta tab Twitter");

					for (PostGeral post : tw_posts) {
						listTwitter.getItems().add(tw.createPostPreview((TwitterPost) post));
					}
				}
				if (newTab == tabEmail) {
					System.out.println("- Aberta tab Email");
					gm.viraLista();
					
					for (PostGeral post : gm_posts) {
						listEmail.getItems().add(((EmailPost) post).emailPostPreview());
					}
					
					
				}
			}
		});

		this.textAreaFacebook_list.setWrapText(true);
		listFacebook.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			private int currentSelection = -1;

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				int i = listFacebook.getSelectionModel().getSelectedIndex();
				if (i != currentSelection) {
					currentSelection = i;
					String selectedItem = listFacebook.getSelectionModel().getSelectedItem();
					System.out.println(selectedItem);
					FacebookPost post = fb.getPostEspecifico(selectedItem);
					textAreaFacebook_list.clear();
					textAreaFacebook_list.appendText(post.getConteudo());
				}
				currentSelection = -1;
			}

		});
		
		this.textAreaGmail_list.setWrapText(true);
		listEmail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			private int currentSelection = -1;

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				int i = listEmail.getSelectionModel().getSelectedIndex();
				if (i != currentSelection) {
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
		
		this.textAreaTwitter_list.setWrapText(true);
		listTwitter.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			private int currentSelection = -1;

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				int i = listTwitter.getSelectionModel().getSelectedIndex();
				if (i != currentSelection) {
					currentSelection = i;
					String selectedItem = listTwitter.getSelectionModel().getSelectedItem();
					System.out.println("Selected Item: " + selectedItem);
					TwitterPost tweet = tw.getPostEspecifico(selectedItem);
					textAreaTwitter_list.clear();
					textAreaTwitter_list.appendText(tweet.getConteudo());
				}
				currentSelection = -1;
			}
		});
		
		this.textDestaques_list.setWrapText(true);
		listDestaques.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			private int currentSelection = -1;

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				int i = listDestaques.getSelectionModel().getSelectedIndex();
				if (i != currentSelection) {
					currentSelection = i;
					String selectedItem = listDestaques.getSelectionModel().getSelectedItem();
					System.out.println("Selected Item: " + selectedItem);
					PostGeral pg = destaquesObject.getPostEspecifico(selectedItem);
					textDestaques_list.clear();
					textDestaques_list.appendText(pg.getConteudo());
				}
				currentSelection = -1;
			}
		});
		
		}
}
