package de.unidue.inf.is;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.Benutzer;
import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.stores.CSAppStore;

public final class BenutzerProfilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        CSAppStore userInfo = new CSAppStore();
        Benutzer user = userInfo.getErstellerInfo();
        request.setAttribute("userInfo", user);

        CSAppStore anz = new CSAppStore();
        int anzErstellte = anz.getNumberCreatedFahrt();
        request.setAttribute("anz", anzErstellte);

        CSAppStore unter = new CSAppStore();
        int anzUnterstuetzte = unter.getNumberReservedFahrt();
        request.setAttribute("unter", anzUnterstuetzte);


        CSAppStore erstProj = new CSAppStore();
        CSAppStore untProj = new CSAppStore();
        List<Fahrt> proj1 = erstProj.getCreatedFahrt();
        request.setAttribute("erstProj", proj1);
        List<Fahrt> proj2 = untProj.getReservedFahrt();
        request.setAttribute("untProj", proj2);

        request.getRequestDispatcher("/myProfile.ftl").forward(request, response);
    }

}

