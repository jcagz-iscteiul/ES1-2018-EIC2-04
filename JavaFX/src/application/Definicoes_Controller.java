package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import RedesSociais.Facebook;
import RedesSociais.Gmail;
import RedesSociais.TwitterObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Definicoes: Controlador ativo.");
		xml = new XML();
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

}
