/*
 * @(#)ProgrammeServlet.java	1.0 2007/10/31
 * 
 * Copyright (c) 2007 Sara Bouchenak.
 */
package servlets;

import affichage.StylePage;
import exceptions.ExceptionConnexion;
import exceptions.ExceptionSpectacle;
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

        StylePage.Header(out);

        try {
            Utilisateur user = Utilitaires.Identification();
            if (user != null) {
                Utilitaires.AfficherSpectacle(user, out);
            }
        } catch (ExceptionConnexion exceptionConnexion) {
            exceptionConnexion.printStackTrace();
        } catch (ExceptionUtilisateur exceptionUtilisateur) {
            exceptionUtilisateur.printStackTrace();
        } catch (ExceptionSpectacle throwables) {
            throwables.printStackTrace();
        }
       StylePage.Footer(out);

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
