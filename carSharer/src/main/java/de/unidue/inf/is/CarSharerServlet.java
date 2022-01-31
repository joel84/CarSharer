package de.unidue.inf.is;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.stores.CSAppStore;
import de.unidue.inf.is.utils.DBUtil;



/**
 * Das könnte die Eintrittsseite sein.
 */
public final class CarSharerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
  
        boolean databaseExists = DBUtil.checkDatabaseExistsExternal();

        
        /*
        if (databaseExists) {
            request.setAttribute("db2exists", "vorhanden! Supi!");
        }
        else {
            request.setAttribute("db2exists", "nicht vorhanden :-(");
        }
        */
        
        
        CSAppStore projOffen = new CSAppStore();
        CSAppStore projZu = new CSAppStore();
        List<Fahrt> proj1 = projOffen.getOpenFahrt();
        request.setAttribute("projOffen", proj1);
        List<Fahrt> proj2 = projZu.getClosedFahrt();
        request.setAttribute("projZu", proj2); 

        request.getRequestDispatcher("/carSharer_Home.ftl").forward(request, response);   
    }

}