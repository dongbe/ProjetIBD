package servlets;
/*
 * @(#)NouvelleRepresentationServlet.java	1.0 2007/10/31
 * 
 * Copyright (c) 2007 Sara Bouchenak.
 */

import exceptions.ExceptionConnexion;
import exceptions.ExceptionRepresentation;
import exceptions.ExceptionSpectacle;
import exceptions.ExceptionUtilisateur;
import modele.Representation;
import modele.Utilisateur;
import utils.Utilitaires;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.Integer.parseInt;

/**
 * NouvelleRepresentation Servlet.
 * <p/>
 * This servlet dynamically adds a new date a show.
 *
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 31/10/2007
 */

public class NouvelleRepresentationServlet extends HttpServlet {

    /**
     * HTTP GET request entry point.
     *
     * @param req an HttpServletRequest object that contains the request
     *            the client has made of the servlet
     * @param res an HttpServletResponse object that contains the response
     *            the servlet sends to the client
     * @throws javax.servlet.ServletException if the request for the GET could not be handled
     * @throws java.io.IOException            if an input or output error is detected
     *                                        when the servlet handles the GET request
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String numS, dateS, heureS;
        Utilisateur user = null;

        ServletOutputStream out = res.getOutputStream();

        res.setContentType("text/html");

        out.println("<HEAD><TITLE> Ajouter une nouvelle representation </TITLE><LINK href=\"/stylesheets/aind_delhi_style1.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\t<LINK href=\"/stylesheets/aind_delhi_MainStyleS.css\" rel=\"stylesheet\" type=\"text/css\"></HEAD>");
        out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
        out.println("<div class=frame0 >");
        out.println("<h1> Ajouter une nouvelle repr&eacute;sentation </h1>");

        try {
            user = Utilitaires.Identification();
            numS = req.getParameter("spectacles");
            dateS = req.getParameter("date");
            heureS = req.getParameter("heure");

            if (numS == null || dateS == null || heureS == null) {
                out.println("Veuillez saisir les informations relatives &agrave; la nouvelle repr&eacute;sentation :");
                out.println("<P>");
                out.print("<form action=\"");
                out.print("NouvelleRepresentationServlet\" ");
                out.println("method=POST>");
                out.println("Num&eacute;ro de spectacle :");
                out.println("<select name=\"spectacles\">");
                try {

                    if (user != null) {
                        Utilitaires.AfficherSpectacles(user, out);
                    }
                } catch (ExceptionConnexion exceptionConnexion) {
                    exceptionConnexion.printStackTrace();
                } catch (ExceptionSpectacle throwables) {
                    throwables.printStackTrace();
                }

                out.println("</select>");
                out.println("<br>");
                out.println("Date de la repr&eacute;sentation :");
                out.println("<input type=text size=20 name=date>");
                out.println("<br>");
                out.println("Heure de d&eacute;but de la repr&eacute;sentation :");
                out.println("<input type=text size=20 name=heure>");
                out.println("<br>");
                out.println("<input type=submit>");
                out.println("</form>");
            } else {
                SimpleDateFormat ff=new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date date2;
                try {
                    date2 = ff.parse(dateS);
                    Representation representation=new Representation(parseInt(numS),new java.sql.Date(date2.getTime()),heureS);
                    Utilitaires.AjouterRepresentation(user, representation);
                    out.println("Representation ajoutee avec succes");
                } catch (ParseException e) {
                   out.println(" Message " + e.getMessage());
                } catch (ExceptionRepresentation e) {
                    out.println(" Message " + e.getMessage());
                }

            }
        } catch (ExceptionConnexion exceptionConnexion) {
            out.println(" Message " + exceptionConnexion.getMessage());
        } catch (ExceptionUtilisateur exceptionUtilisateur) {
            out.println(" Message " + exceptionUtilisateur.getMessage());
        }



        out.println("<hr><p><font color=\"#FFFFFF\"><a href=\"/admin/admin.html\">Page d'administration</a></p>");
        out.println("<hr><p><font color=\"#FFFFFF\"><a href=\"/index.html\">Page d'accueil</a></p>");
        out.println("</div>");
        out.println("</BODY>");
        out.close();

    }

    /**
     * HTTP POST request entry point.
     *
     * @param req an HttpServletRequest object that contains the request
     *            the client has made of the servlet
     * @param res an HttpServletResponse object that contains the response
     *            the servlet sends to the client
     * @throws javax.servlet.ServletException if the request for the POST could not be handled
     * @throws java.io.IOException            if an input or output error is detected
     *                                        when the servlet handles the POST request
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }


    /**
     * Returns information about this servlet.
     *
     * @return String information about this servlet
     */

    public String getServletInfo() {
        return "Ajoute une representation a une date donnee pour un spectacle existant";
    }

}
