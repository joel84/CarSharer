package de.unidue.inf.is.domain;

import java.sql.Timestamp;

public class Fahrerlaubnis {

	private String inhaber;
	private int nummer;
	private Timestamp ablaufDatum;
	
	public Fahrerlaubnis () {}

	public Fahrerlaubnis(String inhaber, int nummer, Timestamp ablaufDatum) {
		
		this.inhaber = inhaber;
		this.nummer = nummer;
		this.ablaufDatum = ablaufDatum;
	}

	public String getInhaber() {
		return inhaber;
	}

	public void setInhaber(String inhaber) {
		this.inhaber = inhaber;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public Timestamp getAblaufDatum() {
		return ablaufDatum;
	}

	public void setAblaufDatum(Timestamp ablaufDatum) {
		this.ablaufDatum = ablaufDatum;
	}
	
	
}
