package it.polito.tdp.gosales.model;

import java.util.List;
import java.util.Set;

public class ProdottiPerVenditore {
	private int retailer;
	private Set<Integer> prodotti;
	private int anno;
	public ProdottiPerVenditore(int retailer, Set<Integer> prodotti, int anno) {
		super();
		this.retailer = retailer;
		this.prodotti = prodotti;
		this.anno = anno;
	}
	public int getRetailer() {
		return retailer;
	}
	public void setRetailer(int retailer) {
		this.retailer = retailer;
	}
	public Set<Integer> getProdotti() {
		return prodotti;
	}
	public void setProdotti(Set<Integer> prodotti) {
		this.prodotti = prodotti;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	@Override
	public String toString() {
		return "ProdottiPerVenditore [retailer=" + retailer + ", prodotti=" + prodotti + ", anno=" + anno + "]";
	}
	

}
