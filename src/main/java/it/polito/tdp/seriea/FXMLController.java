package it.polito.tdp.seriea;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.ClassificaFinale;
import it.polito.tdp.seriea.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Integer> boxSeason;

    @FXML
    private ChoiceBox<?> boxTeam;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCarica(ActionEvent event) {
    	txtResult.clear();
    	
    	Integer season = boxSeason.getValue();
    	if (season == null) {
    		txtResult.appendText("Inserire una stagione!");
    		return ;
    	}
    	
    	this.model.creaGrafo(season);
    	txtResult.appendText("Grafo creato!"+"\n");
    	txtResult.appendText("#Vertici: "+this.model.getVertex().size()+" #Archi: "+this.model.getEdge().size()+"\n\n");

    	txtResult.appendText("Classifica finale:"+"\n");
    	for (ClassificaFinale cf : this.model.classificaFinale()) {
    		txtResult.appendText(cf.toString()+"\n");
    	}
    }

    @FXML
    void handleDomino(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxSeason != null : "fx:id=\"boxSeason\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxTeam != null : "fx:id=\"boxTeam\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.boxSeason.getItems().addAll(this.model.stagioni());
	}
}
