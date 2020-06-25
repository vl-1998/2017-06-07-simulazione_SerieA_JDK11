package it.polito.tdp.seriea.model;

public class ClassificaFinale implements Comparable <ClassificaFinale>{
	private String squadra;
	private Integer punteggio;

	/**
	 * @param squadra
	 * @param punteggio
	 */
	public ClassificaFinale(String squadra, Integer punteggio) {
		super();
		this.squadra = squadra;
		this.punteggio = punteggio;
	}

	public String getSquadra() {
		return squadra;
	}

	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}

	public Integer getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(Integer punteggio) {
		this.punteggio = punteggio;
	}
	
	
	
	@Override
	public int compareTo(ClassificaFinale o) {
		// TODO Auto-generated method stub
		return -this.punteggio-o.getPunteggio();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((squadra == null) ? 0 : squadra.hashCode());
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
		ClassificaFinale other = (ClassificaFinale) obj;
		if (squadra == null) {
			if (other.squadra != null)
				return false;
		} else if (!squadra.equals(other.squadra))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return squadra + ", punti=" + punteggio ;
	}
	
	

}
