package de.unidue.inf.is.domain;

import java.sql.Clob;
import java.sql.Timestamp;

public class Fahrt {

	private int id;
	private String startort;
	private String zielort;
	private String fahrtdatumzeit;
	private int maxPlaetze;
	private double fahrtkosten;
	private String status;
	private int anbieter;
	private int transportmittel;
	private String beschreibung;
	private String icon;
	private String benutzername;
	private int freiePlaetze;
	private String email;
	
	
    public Fahrt () {}
    
	public Fahrt(int id, String startort, String zielort, String fahrtdatumzeit, int maxPlaetze, double fahrtkosten,
			     int anbieter, int transportmittel, String beschreibung) {
		
		this.id = id;
		this.startort = startort;
		this.zielort = zielort;
		this.fahrtdatumzeit = fahrtdatumzeit;
		this.maxPlaetze = maxPlaetze;
		this.fahrtkosten = fahrtkosten;
		this.anbieter = anbieter;
		this.transportmittel = transportmittel;
		this.beschreibung = beschreibung;
	}
	
	public Fahrt( String startort, String zielort, String fahrtdatumzeit, int maxPlaetze, double fahrtkosten,
		     int anbieter, int transportmittel, String beschreibung) {
	
	
	this.startort = startort;
	this.zielort = zielort;
	this.fahrtdatumzeit = fahrtdatumzeit;
	this.maxPlaetze = maxPlaetze;
	this.fahrtkosten = fahrtkosten;
	this.anbieter = anbieter;
	this.transportmittel = transportmittel;
	this.beschreibung = beschreibung;
}
	
	public Fahrt(String icon,String startort,String zielort,String fahrtdatumzeit,int freiePlaetze,double fahrtkosten,String email,int transportmittel,String status,String beschreibung) {
	
	this.icon = icon;
	this.startort = startort;
	this.zielort = zielort;
	this.fahrtdatumzeit = fahrtdatumzeit;
	this.freiePlaetze = freiePlaetze;
	this.fahrtkosten = fahrtkosten;
	this.email = email;
	this.transportmittel = transportmittel;
    this.status = status;
    this.beschreibung = beschreibung;
}
	
	public Fahrt(String icon,String startort,String zielort,String fahrtdatumzeit,int freiePlaetze,double fahrtkosten,String email,int transportmittel,String status) {
		
	this.icon = icon;
	this.startort = startort;
	this.zielort = zielort;
	this.fahrtdatumzeit = fahrtdatumzeit;
	this.freiePlaetze = freiePlaetze;
	this.fahrtkosten = fahrtkosten;
	this.email = email;
	this.transportmittel = transportmittel;
    this.status = status;
}

	public Fahrt(int id, String startort, String zielort, String fahrtdatumzeit, int maxPlaetze, double fahrtkosten,
			     int anbieter, int transportmittel) {
		
		this.id = id;
		this.startort = startort;
		this.zielort = zielort;
		this.fahrtdatumzeit = fahrtdatumzeit;
		this.maxPlaetze = maxPlaetze;
		this.fahrtkosten = fahrtkosten;
		this.anbieter = anbieter;
		this.transportmittel = transportmittel;
	}
	
	public Fahrt(int id, String startort, String zielort, String status, String icon,  String benutzername, int maxPlaetze) {
	
	this.id = id;
	this.startort = startort;
	this.zielort = zielort;
	this.status = status;
	this.icon = icon;
	this.benutzername = benutzername;
	this.maxPlaetze = maxPlaetze;
}
	

	public Fahrt(String icon, int id, String startort, String zielort, String status) {
		// TODO Auto-generated constructor stub
		this.icon = icon;
		this.id = id;
		this.startort = startort;
		this.zielort = zielort;
		this.status = status;
	}
	
	public Fahrt(String icon, int id, String startort, String zielort, double fahrtkosten, int freiePlaetze) {
		// TODO Auto-generated constructor stub
		this.icon = icon;
		this.id = id;
		this.startort = startort;
		this.zielort = zielort;
		this.fahrtkosten = fahrtkosten;
		this.freiePlaetze = freiePlaetze;
	}
	
	public Fahrt(String icon,String startort, String zielort, double fahrtkosten) {
		// TODO Auto-generated constructor stub
		this.icon = icon;
		this.startort = startort;
		this.zielort = zielort;
		this.fahrtkosten = fahrtkosten;
	}
	
	public Fahrt(String icon, String startort, String zielort, String status) {
		// TODO Auto-generated constructor stub
		this.icon = icon;
		this.startort = startort;
		this.zielort = zielort;
		this.status = status;
	}		
	
	public Fahrt(int id, String startort, String zielort) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.startort = startort;
		this.zielort = zielort;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStartort() {
		return startort;
	}

	public void setStartort(String startort) {
		this.startort = startort;
	}

	public String getZielort() {
		return zielort;
	}

	public void setZielort(String zielort) {
		this.zielort = zielort;
	}

	public String getFahrtdatumzeit() {
		return fahrtdatumzeit;
	}

	public void setFahrtdatumzeit(String fahrtdatumzeit) {
		this.fahrtdatumzeit = fahrtdatumzeit;
	}

	public int getMaxPlaetze() {
		return maxPlaetze;
	}

	public void setMaxPlaetze(int maxPlaetze) {
		this.maxPlaetze = maxPlaetze;
	}

	public double getFahrtkosten() {
		return fahrtkosten;
	}

	public void setFahrtkosten(double fahrtkosten) {
		this.fahrtkosten = fahrtkosten;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAnbieter() {
		return anbieter;
	}

	public void setAnbieter(int anbieter) {
		this.anbieter = anbieter;
	}

	public int getTransportmittel() {
		return transportmittel;
	}

	public void setTransportmittel(int transportmittel) {
		this.transportmittel = transportmittel;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public void printFahrt(Fahrt f1) {
		System.out.println(f1.icon);
		System.out.println(f1.startort);
		System.out.println(f1.zielort);
		System.out.println(f1.fahrtkosten);
		System.out.println(f1.freiePlaetze);
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getFreiePlaetze() {
		return freiePlaetze;
	}

	public void setFreiePlaetze(int freiePlaetze) {
		this.freiePlaetze = freiePlaetze;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
