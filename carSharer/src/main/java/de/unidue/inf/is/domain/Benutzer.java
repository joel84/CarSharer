package de.unidue.inf.is.domain;

import java.sql.Clob;

public class Benutzer {


	
    private String email;
    private String name;
    private int id;
    private Clob comment;
    private String text;
    private String anbieter;
    private double fahrtkosten;



    public Benutzer() {
    }


    public Benutzer(String email, String name, int id) {
        this.email = email;
        this.name = name;
        this.id = id;
    }
    
    public Benutzer(String email, String name) {
        this.name = name;
        this.email = email;
    }

    public Benutzer(String name, double fahrtkosten) {
        this.name = name;
        this.fahrtkosten = fahrtkosten;
    }

    public Benutzer(String name, Clob comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public Clob getComment() {
        return comment;
    }

    public void setComment(Clob comment) {
        this.comment = comment;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


	public double getFahrtkosten() {
		return fahrtkosten;
	}


	public void setFahrtkosten(double fahrtkosten) {
		this.fahrtkosten = fahrtkosten;
	}
    
    
}
