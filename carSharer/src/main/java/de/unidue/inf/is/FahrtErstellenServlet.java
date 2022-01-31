package de.unidue.inf.is;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.domain.Transportmittel;
import de.unidue.inf.is.stores.CSAppStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;  

@WebServlet("/FahrtErstellenServlet")
public final class FahrtErstellenServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    String user = "dummy@dummy.com";
   
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
    	CSAppStore fahrt = new CSAppStore();
        int fid =fahrt.getNumberOfFahrten() + 1;
        request.setAttribute("fahrt", fid);
    	
        CSAppStore transportmittel1 = new CSAppStore();
        List<Transportmittel> transportmittelList = transportmittel1.getTransportmitteln();
        request.setAttribute("transportmittel1", transportmittelList);


        request.getRequestDispatcher("fahrtErstellen.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)         //read the input parameters from the html form
            throws ServletException, IOException {

    	int transportmittel = 0;
    	
    	String startort = request.getParameter("von");
        String zielort = request.getParameter("nach");
        //status is offen by default
        String maxPlaetze1 = request.getParameter("maxKap");
        int maxPlaetze = Integer.parseInt(maxPlaetze1);
        
        String fahrtkosten1 = request.getParameter("fahrtkosten");
        double fahrtkosten = Double.parseDouble(fahrtkosten1);
        
        String transportmittel1 = request.getParameter("transport");
        
        if((transportmittel1).equals("auto")) {
        	 transportmittel = 1;
        } else if((transportmittel1).equals("bus")) {
        	 transportmittel = 2;
        } else if((transportmittel1).equals("kleintransporter")) {
        	transportmittel = 3;
        }
        
        String fahrtdatumzeit1 = request.getParameter("fahrtDatum");
        String fahrtdatumzeit2 = request.getParameter("fahrtDatum2");
        String fahrtdatumzeit = fahrtdatumzeit1 + " " + fahrtdatumzeit2;
        
        
        String beschreibung = request.getParameter("beschreibung");


        CSAppStore newFahr = new CSAppStore();
        
        CSAppStore kid = new CSAppStore();
    	int kundeId = kid.getKundeId();
        
        //int fid = newFahr.getNumberOfFahrten() + 1;
        int anbieter = kundeId;
        
        if(!(maxPlaetze > 0 && maxPlaetze <= 10) || fahrtkosten < 0 ) {
        	request.setAttribute("message", "Fahrt wurde nicht erstellt! PLEASE TRY AGAIN !");
    		request.setAttribute("color", "color: red;");
    		request.setAttribute("targetAction", "/"); //this navigates to view_main
    		request.getRequestDispatcher("failNewDrive.ftl").forward(request, response);
        } else {
        	
        Fahrt fahrt2 = new Fahrt(startort, zielort, fahrtdatumzeit, maxPlaetze, fahrtkosten, anbieter, transportmittel, beschreibung);
        
        if(newFahr.addFahrt(fahrt2)) {
        	request.setAttribute("message", "Fahrt erfolgreich erstellt!");
    		request.setAttribute("color", "color: blue;");
    		request.setAttribute("targetAction", "/"); //this navigates to view_main
    		request.getRequestDispatcher("confirmNewDrive.ftl").forward(request, response);
        	//response.sendRedirect("carSharer_Home.ftl");
    		
        } else {
            doGet(request, response);
          }
        }

    }

}
