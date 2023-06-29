package it.polito.tdp.gosales.model;

import java.util.Objects;

public class Arco implements Comparable<Arco>{
	private Retailers r1;
	private Retailers r2;
	private int peso;
	public Arco(Retailers r1, Retailers r2, int peso) {
		super();
		this.r1 = r1;
		this.r2 = r2;
		this.peso = peso;
	}
	public Retailers getR1() {
		return r1;
	}
	public void setR1(Retailers r1) {
		this.r1 = r1;
	}
	public Retailers getR2() {
		return r2;
	}
	public void setR2(Retailers r2) {
		this.r2 = r2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		return Objects.hash(peso, r1, r2);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		return peso == other.peso && Objects.equals(r1, other.r1) && Objects.equals(r2, other.r2);
	}
	@Override
	public String toString() {
		return "r1=" + r1 + ", r2=" + r2 + ", peso=" + peso;
	}
	@Override
	public int compareTo(Arco o) {
		return this.peso-o.getPeso();
	}
	
}

