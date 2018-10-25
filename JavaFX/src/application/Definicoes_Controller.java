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
import xml.XML;

public class Definicoes_Controller implements Initializable{
	private Facebook fb;

    @FXML
    private TextArea accessToken;
    
    @FXML
    private Button getAccessToken;
    
    @FXML
    private Button setAccessToken;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Definicoes:Controler ativo");
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
    void getAccessTokenInfo(ActionEvent event) throws ParserConfigurationException, SAXException, IOException{
    	accessToken.appendText(fb.getXml().getAcessToken());
    }
    
    //Facebook Token
    //EAAb1xNqnPAcBAFpC6yZCPtFvTZB22CV8RjitcWBHrUfWfMys0cFpaFcjbLtIggy3qqfC4Hgl1GnEVnh4I34xBzb2L0hsuQOZBB44rRkn8LphPLdvSulL0hF7pUXG2f5Cm6ZCwuLT1sPNIZBZAJkLQdkLNZAtOxddLJlpQ8rls0nZBmaLORsMZClHx
    @FXML
    void setAccessTokenInfo(ActionEvent event) throws ParserConfigurationException, SAXException, IOException{
    	fb.getXml().setAcessToken(accessToken.getText().toString());
    }
}
