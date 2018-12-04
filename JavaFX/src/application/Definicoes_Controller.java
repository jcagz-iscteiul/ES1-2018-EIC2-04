package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import RedesSociais.Facebook;
import RedesSociais.Gmail;
import RedesSociais.TwitterObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import xml.XML;

public class Definicoes_Controller implements Initializable {
	private XML xml;

	@FXML
	private TextArea facebookAccessToken;

	@FXML
	private Button getFacebookAccessToken;

	@FXML
	private Button setFacebookAccessToken;

	@FXML
	private TextArea twitterAccessToken;

	@FXML
	private Button getTwitterAccessToken;

	@FXML
	private Button setTwitterAccessToken;

	@FXML
	private TextArea gmailEmail;

	@FXML
	private Button getGmailEmail;

	@FXML
	private Button setGmailEmail;

	@FXML
	private TextArea gmailPassword;

	@FXML
	private Button getGmailPassword;

	@FXML
	private Button setGmailPassword;
	
	@FXML
	private CheckBox filtroPesquisaDestaques;
	
	@FXML
	private CheckBox filtroRecenteDestaques;
	
	@FXML
	private CheckBox filtroSplitMenuDestaques;
	
	@FXML
	private CheckBox filtroRecenteFacebook;
	
	@FXML
	private CheckBox filtroPesquisaFacebook;
	
	@FXML
	private CheckBox filtroSplitMenuFacebook;
	
	@FXML
	private CheckBox filtroPesquisaGmail;
	
	@FXML
	private CheckBox filtroRecenteGmail;
	
	@FXML
	private CheckBox filtroSplitMenuGmail;
	
	@FXML
	private CheckBox filtroPesquisaTwitter;
	
	@FXML
	private CheckBox filtroRecenteTwitter;
	
	@FXML
	private CheckBox filtroSplitMenuTwitter;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Definicoes: Controlador ativo.");
		xml = new XML();
		setCheckBoxesCheckedPredefined();
		
		filtroPesquisaDestaques.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue){
	            	Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getPesquisarDestaques());
	            	Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getSearchBarDestaques());
	            }else{
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getPesquisarDestaques());
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getSearchBarDestaques());
	            }
	        }
	    });
		
		filtroSplitMenuDestaques.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue){
	        		Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getDestaquesSplitMenu());
	            }else{
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getDestaquesSplitMenu());
	            }
	        }
	    });
		
		filtroRecenteDestaques.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue){
	        		Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getToggleDestaques());
	            }else{
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getToggleDestaques());
	            }
	        }
	    });
		
		filtroPesquisaFacebook.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue){
	            	Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getPesquisarFacebook());
	            	Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getSearchBarFacebook());
	            }else{
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getPesquisarFacebook());
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getSearchBarFacebook());
	            }
	        }
	    });
		
		filtroSplitMenuFacebook.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue){
	        		Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getFacebookSplitMenu());
	            }else{
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getFacebookSplitMenu());
	            }
	        }
	    });
		
		filtroRecenteFacebook.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue){
	            	Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getToggleFb());
	            }else{
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getToggleFb());
	            }
	        }
	    });
		
		filtroPesquisaGmail.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue){
	            	Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getPesquisarGmail());
	            	Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getSearchBarGmail());
	            }else{
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getPesquisarGmail());
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getSearchBarGmail());
	            }
	        }
	    });
		
		filtroSplitMenuGmail.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue){
	        		Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getEmailSplitMenu());
	            }else{
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getEmailSplitMenu());
	            }
	        }
	    });
		
		filtroRecenteGmail.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue){
	        		Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getToggleGmail());
	            }else{
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getToggleGmail());
	            }
	        }
	    });
		
		filtroPesquisaTwitter.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue){
	            	Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getPesquisarTwitter());
	            	Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getSearchBarTwitter());
	            }else{
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getPesquisarTwitter());
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getSearchBarTwitter());
	            }
	        }
	    });
		
		filtroSplitMenuTwitter.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue){
	        		Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getTwitterSplitMenu());
	            }else{
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getTwitterSplitMenu());
	            }
	        }
	    });
		
		filtroRecenteTwitter.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue){
	        		Main.getMyControllerHandle().setVisible(
	        				Main.getMyControllerHandle().getToggleTwitter());
	            }else{
	            	Main.getMyControllerHandle().setInvisible(
	            			Main.getMyControllerHandle().getToggleTwitter());
	            }
	        }
	    });
		
	}

	// Facebook

	@FXML
	void getFacebookAccessTokenInfo(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {
		facebookAccessToken.appendText(xml.getFacebookAccessToken());
	}

	// Facebook Token
	// EAAb1xNqnPAcBAFpC6yZCPtFvTZB22CV8RjitcWBHrUfWfMys0cFpaFcjbLtIggy3qqfC4Hgl1GnEVnh4I34xBzb2L0hsuQOZBB44rRkn8LphPLdvSulL0hF7pUXG2f5Cm6ZCwuLT1sPNIZBZAJkLQdkLNZAtOxddLJlpQ8rls0nZBmaLORsMZClHx
	@FXML
	void setFacebookAccessTokenInfo(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {
		xml.setFacebookAccessToken(facebookAccessToken.getText().toString());
	}

	// Twitter

	@FXML
	void getTwitterAccessTokenInfo(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {
		twitterAccessToken.appendText(xml.getTwitterAccessToken());
	}

	// Twitter Token
	// 1047106477910085633-GWY5PA98YwxX66JAHnQJb6wQV6hde4
	@FXML
	void setTwitterAccessTokenInfo(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {
		xml.setTwitterAccessToken(twitterAccessToken.getText().toString());
	}

	// Gmail

	@FXML
	void getGmailEmailInfo(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {
		gmailEmail.appendText(xml.getGmailEmail());
	}

	// Gmail Email
	// eic2.04.lei@gmail.com
	@FXML
	void setGmailEmailInfo(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {
		xml.setGmailEmail(gmailEmail.getText().toString());
	}
	

	@FXML
	void getGmailPasswordInfo(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {
		gmailPassword.appendText(xml.getGmailPassword());
	}

	// Gmail Password
	// ISCTE2018lei
	@FXML
	void setGmailPasswordInfo(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {
		xml.setGmailPassword(gmailPassword.getText().toString());
	}
	
	public void setCheckBoxesCheckedPredefined() {
		filtroPesquisaDestaques.setSelected(true);
		filtroSplitMenuDestaques.setSelected(true);
		filtroRecenteDestaques.setSelected(true);
		filtroPesquisaFacebook.setSelected(true);
		filtroSplitMenuFacebook.setSelected(true);
		filtroRecenteFacebook.setSelected(true);
		filtroPesquisaGmail.setSelected(true);
		filtroSplitMenuGmail.setSelected(true);
		filtroRecenteGmail.setSelected(true);
		filtroPesquisaTwitter.setSelected(true);
		filtroSplitMenuTwitter.setSelected(true);
		filtroRecenteTwitter.setSelected(true);
	}
}
