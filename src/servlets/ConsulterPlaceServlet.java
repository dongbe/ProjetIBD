package servlets;


import affichage.StylePage;
import exceptions.ExceptionConnexion;
import exceptions.ExceptionUtilisateur;
import modele.Utilisateur;
import utils.Formulaires;
import utils.Utilitaires;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

import static utils.Utilitaires.Identification;
/**
 * NouvelleRepresentation Servlet.
 *
 * This servlet dynamically adds a new date a show.
 *
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 31/10/2007
 */

public class ConsulterPlaceServlet extends HttpServlet {

   /**
    * HTTP GET request entry point.
    *
    * @param req	an HttpServletRequest object that contains the request 
    *			the client has made of the servlet
    * @param res	an HttpServletResponse object that contains the response 
    *			the servlet sends to the client
    *
    * @throws ServletException   if the request for the GET could not be handled
    * @throws IOException	 if an input or output error is detected 
    *				 when the servlet handles the GET request
    */
    public void doGet(HttpServletRequest req, HttpServletResponse res)	throws ServletException, IOException{
       String numS,dateS, heureS;
        Utilisateur user=null;
       ServletOutputStream out = res.getOutputStream();

	    res.setContentType("text/html");

        StylePage.Header(out);

        try {
            user = Identification();
            numS = req.getParameter("spectacle");
            dateS = req.getParameter("date");
            heureS = req.getParameter("heure");

            out.println("<h2>Consulter Places disponibles</h2>");
            Formulaires.ConsulterPlaceForm(user,out);
            if (numS == null && dateS == null && heureS == null) {


            } else if(numS != null && dateS == null && heureS == null) {
                out.println("<h1>Les Representations</h1>");
                out.println("<div id=\"cible\">");
                Utilitaires.AfficherRepresentations(user,out,numS);
                out.println("</div>");
            }
            else{
                out.println("<h1>Les Representations</h1>");
                out.println("<div id=\"cible\">");
                Utilitaires.AfficherRepresentations(user,out,numS);
                out.println("</div>");
                out.println("<h1>Reservez!</h1>");
                out.println("<div>");
                Utilitaires.AfficherPlace(user,out,numS,dateS,heureS);
                out.println("</div>");
                out.println("<div>");
                out.println("</div>");
            }
        } catch (ExceptionConnexion exceptionConnexion) {
            out.println(" Message " + exceptionConnexion.getMessage());
        } catch (ExceptionUtilisateur exceptionUtilisateur) {
            out.println(" Message " + exceptionUtilisateur.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StylePage.Footer(out);
    }

   /**
    * HTTP POST request entry point.
    *
    * @param req	an HttpServletRequest object that contains the request 
    *			the client has made of the servlet
    * @param res	an HttpServletResponse object that contains the response 
    *			the servlet sends to the client
    *
    * @throws ServletException   if the request for the POST could not be handled
    * @throws IOException	   if an input or output error is detected 
    *					   when the servlet handles the POST request
    */

    public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	  doGet(req, res);
    }


   /**
    * Returns information about this servlet.
    *
    * @return String information about this servlet
    */

    public String getServletInfo() {
        return "Ajoute une reprentation �une date donn閑 pour un spectacle existant";
    }

}