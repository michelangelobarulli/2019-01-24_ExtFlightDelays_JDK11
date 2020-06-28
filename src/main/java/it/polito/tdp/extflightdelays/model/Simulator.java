package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulator {

	Integer T;
	Integer G;
	Graph<String, DefaultWeightedEdge> graph;
	Map<String, Integer> mapLocationTourists;
	
	public void init(Integer t, Integer g, Graph<String, DefaultWeightedEdge> graph, String partenza) {
		this.T = t;
		this.G = g;
		this.graph = graph;
		this.mapLocationTourists = new TreeMap<>();
		for(String state: this.graph.vertexSet()) {
			this.mapLocationTourists.put(state, 0);
		}
		System.out.println(this.mapLocationTourists.size());
		if(this.mapLocationTourists.containsKey(partenza)) {
			System.out.println(partenza);
		}
			
		this.mapLocationTourists.remove(partenza);
		this.mapLocationTourists.put(partenza, T);
	}
	
	public void run() {
		for(int i = 0; i<this.G; i++) {
			//OGNI GIORNO
			Map<String, Integer> tempMap = new TreeMap<>(this.mapLocationTourists);
			for(String s: this.mapLocationTourists.keySet()) {
				
				Integer turistiNelloStatoS = this.mapLocationTourists.get(s);
				System.out.println(turistiNelloStatoS+" "+s);
					for(int j = 0; j<turistiNelloStatoS; j++) {
						// per ogni turista nello stato calcoliamo la destinazione e lo mettiamo nella nuova mappa
						
						String dest = this.calcolaDestinazione(s);
						Integer tTempDest = tempMap.get(dest);
						tempMap.remove(dest);
						tempMap.put(dest, tTempDest+1);
						
						Integer tTempOrigin = tempMap.get(s);
						tempMap.remove(s);
						tempMap.put(s, tTempOrigin-1);
						
						System.out.println("Turista in "+s+" va in "+dest);
						
					}
				}
			this.mapLocationTourists = new TreeMap<>(tempMap);
		}
	}
	

	private String calcolaDestinazione(String s) {
		List<String> possibiliDestinazioni = new ArrayList<>();
		possibiliDestinazioni = Graphs.successorListOf(this.graph, s);
		Double pesoTotale = 0.0;
		for(String str: possibiliDestinazioni) {
			pesoTotale += this.graph.getEdgeWeight(this.graph.getEdge(s, str));
		}
		Double random = Math.random();
		Double cumulata = 0.0;
		for(String str: possibiliDestinazioni) {
			
			cumulata += this.graph.getEdgeWeight(this.graph.getEdge(s, str))/pesoTotale;
			System.out.println("Numero estratto: "+random+", cumulata = "+cumulata);
			if(random < cumulata) {
				System.out.println("Il turista va in "+str);
				return str;
			}
		}
		return null;
	}

	public Map<String, Integer> getMapResult() {
		// TODO Auto-generated method stub
		return this.mapLocationTourists;
	}
}
