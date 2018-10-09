package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Main_Controller {

    @FXML
    private TextArea area;

    @FXML
    private Button botao;

    @FXML
    void printa(ActionEvent event) {
    	System.out.println("printa");
    	area.appendText("benfica");
    }

}
