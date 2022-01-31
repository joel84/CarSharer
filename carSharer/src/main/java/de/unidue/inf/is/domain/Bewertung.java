package de.unidue.inf.is.domain;

public class Bewertung {

    private int beid;
    private String textnachricht;
    private String erstellungsdatum;
    private int rating;
    private String email;
    private String beschreibung;

    public Bewertung () {}
    
    public Bewertung(int beid, String email, int rating, String beschreibung) {
        this.beid = beid;
        this.email = email;
        this.beschreibung = beschreibung;
        this.rating = rating;
    }

    public Bewertung(String textnachricht, int rating, String email) {
        this.email = email;
        this.textnachricht = textnachricht;
        this.rating = rating;
    }

    public Bewertung(int rating, String textnachricht,String erstellungsdatum) {
        
        this.textnachricht = textnachricht;
        this.rating = rating;
        this.erstellungsdatum = erstellungsdatum;
    }
    
  public Bewertung(String email,String textnachricht, int rating) {
        
        this.email = email;
        this.textnachricht = textnachricht;
        this.rating = rating;
    }

    public int getBeid() {
        return beid;
    }

    public void setBeid(int beid) {
        this.beid = beid;
    }

    public String getTextnachricht() {
        return textnachricht;
    }

    public void setTextnachricht(String textnachricht) {
        this.textnachricht = textnachricht;
    }

    public String getErstellungsdatum() {
        return erstellungsdatum;
    }

    public void setErstellungsdatum(String erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }

    public int getRating() {
        return rating;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	

	public void setRating(int rating) {
		this.rating = rating;
	}

	

    
}
