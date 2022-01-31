package de.unidue.inf.is;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.stores.CSAppStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchFahrtServlet")
public final class SearchFahrtServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String start = request.getParameter("start");
        String ziel = request.getParameter("ziel");
        String fahrtDatum = request.getParameter("fahrtDatum");

        CSAppStore fahrt = new CSAppStore();
        List<Fahrt> fahrt1 = fahrt.getFahrten(start,ziel,fahrtDatum);
        request.setAttribute("fahrt", fahrt1);




        request.getRequestDispatcher("searchFahrt.ftl").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)         //read the input parameters from the html form
            throws ServletException, IOException {

    	
    	 String start = request.getParameter("start");
         String ziel = request.getParameter("ziel");
         String fahrtDatum = request.getParameter("fahrtDatum");

        // System.out.println("it is:   , " + start+ " , " + ziel+ " , " + fahrtDatum);
         
         CSAppStore fahrt = new CSAppStore();
         List<Fahrt> fahrt1 = fahrt.getFahrten(start,ziel,fahrtDatum);
         
         if(start.isEmpty() || ziel.isEmpty() || fahrtDatum.isEmpty()) {
         	request.setAttribute("message", "Die Suchanfrage ist fehlgeschlagen! PLEASE TRY AGAIN !");
     		request.setAttribute("color", "color: red;");
     		request.setAttribute("targetAction", "/"); //this navigates to view_main
     		request.getRequestDispatcher("failSearchAvailableDrive.ftl").forward(request, response);
         } else { 
         
         request.setAttribute("fahrt", fahrt1);
        
         request.getRequestDispatcher("searchFahrt.ftl").forward(request, response);
         }
    }

}
