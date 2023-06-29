package it.polito.tdp.gosales.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.gosales.dao.GOsalesDAO;

public class Model {
	private GOsalesDAO dao;
	private Graph<Retailers, DefaultWeightedEdge> grafo;
	private Map<Integer, DailySale> mappaVendite;
	private Map<Integer, Retailers> mappaVenditore;
	private List<Arco> archi;
	
	public Model() {
		this.dao= new GOsalesDAO();
		this.mappaVendite= new TreeMap<>();
		this.dao.getAllSales(mappaVendite);
		this.mappaVenditore=new TreeMap<>();
		this.dao.getAllRetailers(mappaVenditore);
	}
	public List<String> getAllNazioni(){
		return this.dao.getAllNazioni();
	}
	public void buildGraph(String country, int anno, int numProdComune) {
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.archi= new ArrayList<>();
		List<Retailers> vertici=this.dao.getVertici(country);
		Graphs.addAllVertices(this.grafo, vertici);
		List<ProdottiPerVenditore> listaPv = new ArrayList<>();
		for(Retailers r1: vertici) {
			listaPv.add(new ProdottiPerVenditore(r1.getCode(), this.dao.getDailySale(r1.getCode(), anno), anno));
		}
		for(int i=0; i<listaPv.size(); i++) {
			for(int j=0;j<listaPv.size(); j++) {
				Retailers r1=mappaVenditore.get(listaPv.get(i).getRetailer());
				Retailers r2= mappaVenditore.get(listaPv.get(j).getRetailer());
				    Set<Integer> prodotti1= new HashSet<>(listaPv.get(i).getProdotti());
				    Set<Integer> prodotti2= listaPv.get(j).getProdotti();
				    prodotti1.retainAll(prodotti2);
				    if(!r1.equals(r2) && prodotti1.size()>=numProdComune) {
				    	Arco arco =new Arco(r1, r2, prodotti1.size());
				    	archi.add(arco);
				    	Graphs.addEdgeWithVertices(this.grafo,r1,r2, prodotti1.size());
			    }
			}
		}
	}
	
	public int getNumeroVertici() {
		return this.grafo.vertexSet().size();
	}
	public int getNumeroArchi() {
		return this.grafo.edgeSet().size(); 
	}
	public List<Retailers> getVertici(){
		List<Retailers> venditori = new ArrayList<>(this.grafo.vertexSet());
		Collections.sort(venditori);
		return venditori;
	}
	public List<Arco> getArchi(){
		List<Arco> archi= new ArrayList<>(this.archi);
		Collections.sort(archi);
		return archi;
	}
	public int getDimConnessa(Retailers r) {
		ConnectivityInspector<Retailers, DefaultWeightedEdge> ispettore= new ConnectivityInspector<>(this.grafo);
		return ispettore.connectedSetOf(r).size();
	}
	public double getPesoConnessa(Retailers r) {
		ConnectivityInspector<Retailers, DefaultWeightedEdge> ispettore= new ConnectivityInspector<>(this.grafo);
		Set<Retailers> connessa= ispettore.connectedSetOf(r);
		double pesiTot=0.0;
		for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
			if(connessa.contains(grafo.getEdgeSource(e)) && connessa.contains(this.grafo.getEdgeTarget(e)))
			pesiTot+=this.grafo.getEdgeWeight(e);
		}
		return pesiTot;
	}
	
}
