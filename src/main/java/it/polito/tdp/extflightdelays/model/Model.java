package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	ExtFlightDelaysDAO dao;
	Graph<String, DefaultWeightedEdge> graph;
	List<Adiacenza> edges;
	
	public Model() {
		this.dao = new ExtFlightDelaysDAO();
	}
	
	public List<String> creaGrafo() {
		this.graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		List<String> vertices = this.dao.loadAllStates();
		Graphs.addAllVertices(this.graph, vertices);
		this.edges = this.dao.getAllEdges();
		for(Adiacenza a: edges) {
			Graphs.addEdgeWithVertices(this.graph, a.getSt1(), a.getSt2(), a.getWeight());
		}
		return vertices;
	}
	
	public List<Adiacenza> getVelivoli(String statoPartenza){
		List<Adiacenza> result = new ArrayList<>();
		for(Adiacenza a: this.edges) {
			if(a.getSt1().compareTo(statoPartenza)==0) {
				result.add(a);
			}
		}
		Collections.sort(result);
		return result;
	}
	
	public Map<String, Integer> simula (Integer T, Integer G, String statoPartenza){
		Simulator sim = new Simulator();
		sim.init(T, G, graph, statoPartenza);
		sim.run();
		return sim.getMapResult();
	}
	
	public Integer getVertexSetSize() {
		return this.graph.vertexSet().size();
	}
	public Integer getEdgeSetSize() {
		return this.graph.edgeSet().size();
	}
}
