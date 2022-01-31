package de.unidue.inf.is.domain;

public class Reservieren {

	private int kunde;
    private int fahrt;
    private int anzPlaetze;
    private String benutzername;
    
    public Reservieren() {}

	public Reservieren(int kunde, int fahrt, int anzPlaetze, String benutzername) {
		
		this.kunde = kunde;
		this.fahrt = fahrt;
		this.anzPlaetze = anzPlaetze;
		this.benutzername = benutzername;
	}
	
    public Reservieren(int kunde, int fahrt, int anzPlaetze) {
		
		this.kunde = kunde;
		this.fahrt = fahrt;
		this.anzPlaetze = anzPlaetze;
	}
	
	public Reservieren(int kunde) {
		
		this.kunde = kunde;
	}

	public int getKunde() {
		return kunde;
	}

	public void setKunde(int kunde) {
		this.kunde = kunde;
	}

	public int getFahrt() {
		return fahrt;
	}

	public void setFahrt(int fahrt) {
		this.fahrt = fahrt;
	}

	public int getAnzPlaetze() {
		return anzPlaetze;
	}

	public void setAnzPlaetze(int anzPlaetze) {
		this.anzPlaetze = anzPlaetze;
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}
    
    
}
