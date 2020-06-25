package it.polito.tdp.seriea.model;

public class Arco {
	private String teamA;
	private String teamB;
	private Integer peso;
	/**
	 * @param teamA
	 * @param teamB
	 * @param peso
	 */
	public Arco(String teamA, String teamB, Integer peso) {
		super();
		this.teamA = teamA;
		this.teamB = teamB;
		this.peso = peso;
	}
	public String getTeamA() {
		return teamA;
	}
	public void setTeamA(String teamA) {
		this.teamA = teamA;
	}
	public String getTeamB() {
		return teamB;
	}
	public void setTeamB(String teamB) {
		this.teamB = teamB;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	
}
