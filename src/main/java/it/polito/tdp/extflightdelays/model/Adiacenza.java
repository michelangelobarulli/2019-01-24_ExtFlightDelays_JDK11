package it.polito.tdp.extflightdelays.model;

public class Adiacenza implements Comparable<Adiacenza>{

	private String st1;
	private String st2;
	private Double weight;
	public Adiacenza(String st1, String st2, Double weight) {
		super();
		this.st1 = st1;
		this.st2 = st2;
		this.weight = weight;
	}
	public String getSt1() {
		return st1;
	}
	public void setSt1(String st1) {
		this.st1 = st1;
	}
	public String getSt2() {
		return st2;
	}
	public void setSt2(String st2) {
		this.st2 = st2;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	@Override
	public int compareTo(Adiacenza o) {
		
		return -this.weight.compareTo(o.getWeight());
	}
	@Override
	public String toString() {
		return "Partenza: " + st1 + "arrivo: " + st2 + " numero velivoli: " + weight;
	}
	
}
