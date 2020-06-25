package it.polito.tdp.seriea.model;

public class Coppia {
	private String squadra1;
	private String squadra2;
	/**
	 * @param squadra1
	 * @param squadra2
	 */
	public Coppia(String squadra1, String squadra2) {
		super();
		this.squadra1 = squadra1;
		this.squadra2 = squadra2;
	}
	public String getSquadra1() {
		return squadra1;
	}
	public void setSquadra1(String squadra1) {
		this.squadra1 = squadra1;
	}
	public String getSquadra2() {
		return squadra2;
	}
	public void setSquadra2(String squadra2) {
		this.squadra2 = squadra2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((squadra1 == null) ? 0 : squadra1.hashCode());
		result = prime * result + ((squadra2 == null) ? 0 : squadra2.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coppia other = (Coppia) obj;
		if (squadra1 == null) {
			if (other.squadra1 != null)
				return false;
		} else if (!squadra1.equals(other.squadra1))
			return false;
		if (squadra2 == null) {
			if (other.squadra2 != null)
				return false;
		} else if (!squadra2.equals(other.squadra2))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return  squadra1 + " -> " + squadra2 ;
	}
	
	

}
