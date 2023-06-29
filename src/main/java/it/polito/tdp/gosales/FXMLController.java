package it.polito.tdp.gosales;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.gosales.model.Arco;
import it.polito.tdp.gosales.model.Model;
import it.polito.tdp.gosales.model.Retailers;
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
    private Button btnAnalizzaComponente;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnSimula;

    @FXML
    private ComboBox<Integer> cmbAnno;

    @FXML
    private ComboBox<String> cmbNazione;

    @FXML
    private ComboBox<?> cmbProdotto;

    @FXML
    private ComboBox<Retailers> cmbRivenditore;

    @FXML
    private TextArea txtArchi;

    @FXML
    private TextField txtN;

    @FXML
    private TextField txtNProdotti;

    @FXML
    private TextField txtQ;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextArea txtVertici;

    @FXML
    void doAnalizzaComponente(ActionEvent event) {
    	Retailers r=this.cmbRivenditore.getValue();
    	txtResult.appendText("La componente connessa ha dimensione :");
    	txtResult.appendText(this.model.getDimConnessa(r)+"\n");
    	txtResult.appendText("con peso : "+this.model.getPesoConnessa(r));
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	String country= this.cmbNazione.getValue();
    	if(country==null) {
    		txtResult.setText("DEVI INSERIRE UNA NAZIONE");
    		return;
    	}
    	Integer anno= this.cmbAnno.getValue();
    	if(anno==null) {
    		txtResult.setText("DEVI INSERIRE UN ANNO");
    		return;
    	}
    	int numeroProdotti=0;
    	try {
    		 numeroProdotti= Integer.parseInt(this.txtNProdotti.getText());
    	}catch(NumberFormatException e) {
    		txtResult.setText("Devi inserire un numero in numero prodotti");
    		return;
    	}
    	if(numeroProdotti<0) {
    		this.txtResult.setText("N prodotti in comune deve essere maggiore di zero");
    	}
    	this.model.buildGraph(country, anno, numeroProdotti);
    	txtResult.setText("Il grafo è stato creato correttamente \n");
    	txtResult.appendText("Il grafo ha: "+this.model.getNumeroVertici()+" vertici \n");
    	txtResult.appendText("Il grafo ha: "+this.model.getNumeroArchi() +" archi \n");
    	txtResult.appendText("Lista vertici: \n");
    	List<Retailers> vertici = this.model.getVertici();
    	this.cmbRivenditore.getItems().addAll(vertici);
    	for(Retailers r: vertici) {
    		txtVertici.appendText(r.getName()+"\n");
    	}
    	List<Arco> archi= this.model.getArchi();
    	for(Arco a: archi) {
    		txtArchi.appendText(a+"\n");
    	}
    }

    @FXML
    void doSimulazione(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnAnalizzaComponente != null : "fx:id=\"btnAnalizzaComponente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbNazione != null : "fx:id=\"cmbNazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProdotto != null : "fx:id=\"cmbProdotto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbRivenditore != null : "fx:id=\"cmbRivenditore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArchi != null : "fx:id=\"txtArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNProdotti != null : "fx:id=\"txtNProdotti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtQ != null : "fx:id=\"txtQ\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVertici != null : "fx:id=\"txtVertici\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	for(int i=2015; i<2019;i++) {
    		this.cmbAnno.getItems().add(i);
    	}
    	List<String> nazioni= this.model.getAllNazioni();
    	this.cmbNazione.getItems().addAll(nazioni);
    }

}
