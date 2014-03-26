/*
 * @(#)ProgrammeServlet.java	1.0 2007/10/31
 * 
 * Copyright (c) 2007 Sara Bouchenak.
 */
package servlets;

import exceptions.ExceptionConnexion;
import exceptions.ExceptionUtilisateur;
import modele.Utilisateur;
import utils.Utilitaires;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Proramme Servlet.
 * <p/>
 * This servlet dynamically returns the theater program.
 *
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 31/10/2007
 */

public class ProgrammeServlet extends HttpServlet {

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
        ServletOutputStream out = res.getOutputStream();

        res.setContentType("text/html");

        out.println("<HEAD><TITLE> Programme de la saison </TITLE></HEAD>");
        out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
        out.println("<font color=\"#FFFFFF\"><h1> Programme de la saison </h1>");

        try {
            Utilitaires.AfficherRepresentations();
        } catch (ExceptionConnexion exceptionConnexion) {
            exceptionConnexion.printStackTrace();
        } catch (ExceptionUtilisateur exceptionUtilisateur) {
            exceptionUtilisateur.printStackTrace();
        }
       /* try {
            Utilisateur user = Utilitaires.Identification();
            if (user != null) {
                Utilitaires.AfficherRepresentations(user,out);
            }
        } catch (ExceptionConnexion exceptionConnexion) {
            exceptionConnexion.printStackTrace();
        } catch (ExceptionUtilisateur exceptionUtilisateur) {
            exceptionUtilisateur.printStackTrace();
        }*/
        // TO DO
        // R�cup�ration de la liste de tous les spectacles de la saison.
        // Puis construction dynamique d'une page web d�crivant ces spectacles.
        out.println("<p><i><font color=\"#FFFFFF\">A compl&eacute;ter</i></p>");
        out.println("<p><i><font color=\"#FFFFFF\">...</i></p>");
        out.println("<hr><p><font color=\"#FFFFFF\"><a href=\"/index.jsp\">Accueil</a></p>");
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
        return "Retourne le programme du th�atre";
    }

}
