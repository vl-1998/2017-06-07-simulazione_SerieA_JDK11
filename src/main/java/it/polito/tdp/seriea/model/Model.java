package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	private SerieADAO dao;
	private Graph <String, DefaultWeightedEdge> grafo;
	private List<Coppia> bestPercorso;
	
	
	public Model () {
		this.dao = new SerieADAO ();
	}
	
	public List<Integer> stagioni(){
		List<Integer> res = new ArrayList<>();
		for (Season s : this.dao.listSeasons()) {
			res.add(s.getSeason());
		}
		Collections.sort(res);
		return res;
	}
	
	public void creaGrafo(Integer stagione) {
		this.grafo = new SimpleDirectedWeightedGraph <>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, dao.getVertex(stagione));
		
		for (Arco a : this.dao.getArchi(stagione)) {
			if (this.grafo.vertexSet().contains(a.getTeamA()) && this.grafo.vertexSet().contains(a.getTeamB())) {
				Graphs.addEdgeWithVertices(this.grafo, a.getTeamA(), a.getTeamB(), a.getPeso());
			}
		}
	}
	
	public List<String> getVertex(){
		List<String> vertici = new ArrayList<>(this.grafo.vertexSet());
		return vertici;
	}
	
	public List<DefaultWeightedEdge> getEdge(){
		List<DefaultWeightedEdge> archi = new ArrayList<>(this.grafo.edgeSet());
		return archi;
	}
	
	public List<Arco> getArchi(Integer season){
		return this.dao.getArchi(season);
	}
	
	
	public List<ClassificaFinale> classificaFinale(){
		List<ClassificaFinale> result = new ArrayList<>();
		List<String> squadre = new ArrayList<>();
		
		for (String s : this.grafo.vertexSet()) {
			if (!squadre.contains(s)) {
				squadre.add(s);
				Integer punteggio = 0;
				
				List<DefaultWeightedEdge> archiUscenti = new ArrayList<>(this.grafo.outgoingEdgesOf(s));
				
				for (DefaultWeightedEdge dwe : archiUscenti) {
					if (this.grafo.getEdgeWeight(dwe)==+1) {
						punteggio = punteggio +3;
					} else if (this.grafo.getEdgeWeight(dwe)==-1) {
						punteggio = punteggio +0;
					} else if (this.grafo.getEdgeWeight(dwe)==0) {
						punteggio = punteggio +1;
					}
				}
				ClassificaFinale cf = new ClassificaFinale(s,punteggio);
				result.add(cf);
			}
		}
		
		Collections.sort(result);
		return result;
	}
	
	
	public List<Coppia> calcolaPercorso(String s){
		this.bestPercorso = new ArrayList<>();
		List<Coppia> parziale = new ArrayList<>();
		
		cerca(parziale, s);
		
		return bestPercorso;
	}

	private void cerca(List<Coppia> parziale, String s) {
		List<String> successivi = Graphs.successorListOf(this.grafo, s);
		
		if (!parziale.isEmpty()) {
			if (parziale.size()>bestPercorso.size()) {
				this.bestPercorso = new ArrayList<>(parziale);
			} else {
				return;
			}
		}
		
		
		
		for (String p :successivi) {
			Coppia c = new Coppia (s,p);
			if (!parziale.contains(c)) {
				if (this.grafo.getEdgeWeight(this.grafo.getEdge(s, p))==1) {
					parziale.add(c);
					cerca(parziale,p);
					parziale.remove(parziale.size()-1);
				}
			}
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
