package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Adiacenza;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<String> cmbBoxStati;

    @FXML
    private Button btnVisualizzaVelivoli;

    @FXML
    private TextField txtT;

    @FXML
    private TextField txtG;

    @FXML
    private Button btnSimula;
    
    String stato;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	this.txtResult.clear();
    	List<String> stati = this.model.creaGrafo();
    	this.cmbBoxStati.getItems().addAll(stati);
    	this.txtResult.appendText("Grafo creato con "+this.model.getVertexSetSize()+" vertici e "+this.model.getEdgeSetSize()+" archi.\n\n");
    }

    @FXML
    void doSimula(ActionEvent event) {
    	Integer i;
    	try {
    		i = Integer.parseInt(this.txtT.getText());
    	} catch (NumberFormatException e) {
    		this.txtResult.appendText("Inserire intero valido.");
    		return;
    	}
    	Integer j;
    	try {
    		j = Integer.parseInt(this.txtG.getText());
    	} catch (NumberFormatException e) {
    		this.txtResult.appendText("Inserire intero valido.");
    		return;
    	}
    	stato = this.cmbBoxStati.getValue();
    	if(stato == null) {
    		this.txtResult.appendText("Selezionare uno stato!");
    		return;
    	}
    	Map<String, Integer> statiPersone = this.model.simula(i, j, this.stato);
    	for(String s: statiPersone.keySet()) {
    		this.txtResult.appendText("Stato: "+s+" numero persone: "+statiPersone.get(s)+"\n");
    	}
    }

    @FXML
    void doVisualizzaVelivoli(ActionEvent event) {
    	stato = this.cmbBoxStati.getValue();
    	if(stato == null) {
    		this.txtResult.appendText("Selezionare uno stato!");
    		return;
    	}
    	List<Adiacenza> result = this.model.getVelivoli(stato);
    	for(Adiacenza a: result) {
    		this.txtResult.appendText(a.toString()+"\n");
    	}
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert cmbBoxStati != null : "fx:id=\"cmbBoxStati\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnVisualizzaVelivoli != null : "fx:id=\"btnVisualizzaVelivoli\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtT != null : "fx:id=\"txtT\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtG != null : "fx:id=\"txtG\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
