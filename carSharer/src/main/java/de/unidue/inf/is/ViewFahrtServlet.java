package de.unidue.inf.is;

import de.unidue.inf.is.domain.Bewertung;
import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.domain.Reservieren;
import de.unidue.inf.is.stores.CSAppStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewFahrtServlet")
public final class ViewFahrtServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int fid =  Integer.parseInt(request.getParameter("fid"));
        request.setAttribute("fid1", fid);

        CSAppStore fahrt = new CSAppStore();
        Fahrt fahrt1 = fahrt.getFahrtInfo(fid);
        request.setAttribute("fahrt", fahrt1);

        CSAppStore bewertung = new CSAppStore();
        List<Bewertung> bewertung1 = bewertung.getListRating(fid);
        request.setAttribute("bewertung", bewertung1);  
        
       /* CSAppStore freiePlaetz = new CSAppStore();
        int freiePlaetze1 = freiePlaetz.getFreiePlaetzeFahrt(fid);
        request.setAttribute("freiePlaetz", freiePlaetze1);  */
        
        CSAppStore rating = new CSAppStore();
        double rating1= rating.getAverageRating(fid);
        request.setAttribute("rating", rating1);           
        

        request.getRequestDispatcher("viewFahrt.ftl").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	
    	          
    	int fid = Integer.parseInt(request.getParameter("fid"));
    	request.setAttribute("fid1", fid);
    	System.out.println("the id is: " + fid);
    	
    	
    	String anzPlaetze1 = request.getParameter("anzPlaetzeR");
    	System.out.println("it should be : " + anzPlaetze1);
    	int anzPlaetze = Integer.parseInt(anzPlaetze1);
    	
    	CSAppStore bid = new CSAppStore();
    	int benutzerId = bid.getBenutzerId(fid);
    	
    	CSAppStore kid = new CSAppStore();
    	int kundeId = bid.getKundeId();
    	
    	CSAppStore frei = new CSAppStore();
    	int freiePlaetze = frei.getFreiePlaetzeFahrt(fid);
    	
    	CSAppStore bewertung = new CSAppStore();
    	int beid = bewertung.getBewertungId(fid, benutzerId);
    	
    	CSAppStore reservieren = new CSAppStore();
    	String reservieren1 = request.getParameter("Fahrt reservieren");
    	
    	
    	CSAppStore fahrtLoeschen = new CSAppStore();
    	String fahrtLoeschen1 = request.getParameter("Fahrt loeschen");
    	
    	if(("fahrtReservieren").equals(reservieren1) && anzPlaetze <= freiePlaetze && anzPlaetze > 0 && kundeId!=benutzerId) {   //&& kundeId!=benutzerId
    		reservieren.makeReservation(kundeId,fid,anzPlaetze);              
    		//request.getRequestDispatcher("viewFahrt.ftl").forward(request, response);
    		doGet(request, response);
    		
    	} else if (("fahrtLoeschen").equals(fahrtLoeschen1)&& kundeId!=benutzerId) {
    		fahrtLoeschen.deleteReservieren(fid,benutzerId);
    		fahrtLoeschen.deleteBewertung(beid);
    		fahrtLoeschen.deleteFahrt(fid);
    		//response.sendRedirect("carSharer_Home");
    		request.getRequestDispatcher("carSharer_Home.ftl").forward(request, response);
    		
    	} else {
    		request.setAttribute("message", "Die Reservierung der Fahrt oder das Loeschen der Fahrt ist fehlgeschlagen! PLEASE TRY AGAIN !");
     		request.setAttribute("color", "color: red;");
     		request.setAttribute("targetAction", "/"); //this navigates to view_main
     		request.getRequestDispatcher("failReservationOrDelete.ftl").forward(request, response);
    	}
       

    }
}
