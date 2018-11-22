package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import RedesSociais.Facebook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Definicoes_Controller implements Initializable{
	private Facebook fb;

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
    private TextArea gmailAccessToken;
    
    @FXML
    private Button getGmailAccessToken;
    
    @FXML
    private Button setGmailAccessToken;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Definicoes: Controlador ativo.");
		try {
			fb = new Facebook();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @FXML
    void getFacebookAccessTokenInfo(ActionEvent event) throws ParserConfigurationException, SAXException, IOException{
    	facebookAccessToken.appendText(fb.getXml().getFacebookAccessToken());
    }
    
    //Facebook Token
    //EAAb1xNqnPAcBAFpC6yZCPtFvTZB22CV8RjitcWBHrUfWfMys0cFpaFcjbLtIggy3qqfC4Hgl1GnEVnh4I34xBzb2L0hsuQOZBB44rRkn8LphPLdvSulL0hF7pUXG2f5Cm6ZCwuLT1sPNIZBZAJkLQdkLNZAtOxddLJlpQ8rls0nZBmaLORsMZClHx
    @FXML
    void setFacebookAccessTokenInfo(ActionEvent event) throws ParserConfigurationException, SAXException, IOException{
    	fb.getXml().setFacebookAccessToken(facebookAccessToken.getText().toString());
    }
}
