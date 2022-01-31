package de.unidue.inf.is.domain;

public class Schreiben {

	private int benutzer;
    private int fahrt;
    private int bewertung;
    private String name;
    private String comment;
    private int rating;
    
    public Schreiben() {}
    
    public Schreiben(int benutzer, int fahrt,int bewertung) {
    	this.benutzer = benutzer;
    	this.fahrt = fahrt;
        this.bewertung = bewertung;
    }
    
   /* public Schreiben(int benutzer, String name, int fahrt, int bewertung) {
        this.benutzer = benutzer;
        this.fahrt = fahrt;
        this.bewertung = bewertung;
        this.name = name;
    }  */

	public int getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(int benutzer) {
		this.benutzer = benutzer;
	}

	public int getFahrt() {
		return fahrt;
	}

	public void setFahrt(int fahrt) {
		this.fahrt = fahrt;
	}

	public int getBewertung() {
		return bewertung;
	}

	public void setBewertung(int bewertung) {
		this.bewertung = bewertung;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
    
    
}
