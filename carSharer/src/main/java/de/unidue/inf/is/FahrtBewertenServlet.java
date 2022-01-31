package de.unidue.inf.is;

import de.unidue.inf.is.domain.Bewertung;
import de.unidue.inf.is.domain.Schreiben;
import de.unidue.inf.is.stores.CSAppStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;  

@WebServlet("/FahrtBewertenServlet")
public final class FahrtBewertenServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    String user = "dummy@dummy.com";
    


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	 int fid =  Integer.parseInt(request.getParameter("fid"));
         request.setAttribute("fid1", fid);
        
        
    	 request.getRequestDispatcher("fahrtBewerten.ftl").forward(request, response);
        


    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	
    	int fid =  Integer.parseInt(request.getParameter("fid"));
        request.setAttribute("fid1", fid);
    	/*int fid =  Integer.parseInt(request.getParameter("fid"));
    	int beid =  Integer.parseInt(request.getParameter("beid"));
    	int bid =  Integer.parseInt(request.getParameter("bid")); */
    	
    	String textnachricht = request.getParameter("textnachricht");
    	String rating1 = request.getParameter("grade");
        int rating = Integer.parseInt(rating1);
        String erstellungsdatum = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.println("now the date is: " + erstellungsdatum);
        
        CSAppStore bid = new CSAppStore();
    	int benutzerId = bid.getBenutzerId(fid);
        
        CSAppStore kid = new CSAppStore();
    	int kundeId = kid.getKundeId();
    	
    	CSAppStore bewertung = new CSAppStore();
    	int beid = bewertung.getBewertungId(fid, kundeId);
        
        
        CSAppStore newBewertung = new CSAppStore();
        
        CSAppStore newSchreiben = new CSAppStore();
        
     /*   int fid =  Integer.parseInt(request.getParameter("fid"));
        request.setAttribute("fid1", fid);
        int bid = newBewertung.getBenutzerId(fid);
        int beid = newBewertung.getNumberOfBewertung() + 1;  */  //newBewertung.updateSchreiben(schreiben2)
        
        
       
        if(!(rating > 0 && rating <= 5) || textnachricht.isEmpty() || kundeId==benutzerId) {
        	request.setAttribute("message", "Bewertung wurde nicht erstellt! PLEASE TRY AGAIN !");
    		request.setAttribute("color", "color: red;");
    		request.setAttribute("targetAction", "/"); //this navigates to view_main
    		request.getRequestDispatcher("failNewBewertung.ftl").forward(request, response);
        } else {
        	Bewertung bewertung2 = new Bewertung(rating,textnachricht,erstellungsdatum);
        	//Schreiben schreiben2 = new Schreiben(kundeId,fid,beid); 
        	          
        	
        	 if(newBewertung.makeBewertung(bewertung2)) {
            
        	       // int bid = newBewertung.getBenutzerId(fid);
        	       // int beid = newBewertung.getNumberOfBewertung() + 1;
        	        Schreiben schreiben2 = new Schreiben(kundeId,fid,beid); 
        	        newBewertung.updateSchreiben(schreiben2);
        	        String f = String.valueOf(fid);
        	        String help = "viewFahrt?fid=" + f;
        		  //request.setAttribute("message", "Bewertung erfolgreich erstellt!");
         		 // request.setAttribute("color", "color: blue;");
         		 // request.setAttribute("targetAction", "/"); //this navigates to view_main
         		 // request.getRequestDispatcher("confirmNewBewertung.ftl").forward(request, response);
             	response.sendRedirect(help);
         		//request.getRequestDispatcher("viewFahrt.ftl").forward(request, response);
         		
             } else {
                 //doGet(request, response);
            	 request.setAttribute("message", "Bewertung wurde nicht erstellt! PLEASE TRY AGAIN !");
         		request.setAttribute("color", "color: red;");
         		request.setAttribute("targetAction", "/"); //this navigates to view_main
         		request.getRequestDispatcher("failNewBewertung.ftl").forward(request, response);
               }
        }

    }
    

}
