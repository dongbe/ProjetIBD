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

import static utils.Utilitaires.Identification;
import static utils.Utilitaires.prixZone;

/**
 * NouvelleRepresentation Servlet.
 *
 * This servlet dynamically adds a new date a show.
 *
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 31/10/2007
 */

public class ReserverServlet extends HttpServlet {

   /**
    * HTTP GET request entry point.
    *
    * @param req	an HttpServletRequest object that contains the request 
    *			the client has made of the servlet
    * @param res	an HttpServletResponse object that contains the response 
    *			the servlet sends to the client
    *
    * @throws javax.servlet.ServletException   if the request for the GET could not be handled
    * @throws java.io.IOException	 if an input or output error is detected
    *				 when the servlet handles the GET request
    */

    public void doGet (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
    	String numRep,dateRep,noZone,noPlace,noRang,heureRep,prix;
        Utilisateur user=null;
        ServletOutputStream out = res.getOutputStream();

	  res.setContentType("text/html");
      StylePage.Header(out);
        try {
            user = Identification();
            numRep= req.getParameter("num");
            dateRep =req.getParameter("date");
            noZone=req.getParameter("zone");
            noPlace=req.getParameter("place");
            noRang=req.getParameter("rang");
            heureRep=req.getParameter("heure");
            prix=req.getParameter("prix");
            if(noZone==null || numRep==null || dateRep==null || noPlace==null || noRang==null ){
                req.getRequestDispatcher("/servlet/ConsulterPlaceServlet").forward(req, res);
                out.println("Precisez la representation");
            } else if (prix==null){
                prix=prixZone(user,noZone);
                out.print("<form action=\"ReserverServlet?prix="+prix+"\"");
                out.println("method=POST>");
                out.println("Informations sur la place choisie:");
                out.println("<br>");
                out.println("Place :" + noPlace);
                out.println("<br>");
                out.println("Rang :" + noRang);
                out.println("<br>");
                out.println("Zone :" + noZone + " (" + Utilitaires.nomZone(user,noZone)+")");
                out.println("<br>");
                out.println("Prix :"+prix);
                out.println("<br>");
                out.println("Representation :"+numRep);
                out.println("<br>");
                out.println("Date :"+dateRep);
                out.println("<br>");
                out.println("Heure :"+ heureRep);
                out.println("<br>");
                out.println("<input type=submit>");
                out.println("</form>");
            }
            else{

                out.println("Informations sur la place choisie:");
                out.println("<P>");
                out.println("<Place :" + noPlace);
                out.println("Rang :" + noRang);
                out.println("Zone :" + noZone + " (" + Utilitaires.nomZone(user, noZone) + ")");
                out.println("Prix :" + prix);
                out.println("Representation :"+numRep);
                out.println("Date :"+dateRep);
                out.println("Heure :"+ heureRep);
                out.println("<br>");
                out.print("<form action=\"ReserverServlet\"");
                out.println("method=POST>");
                out.println("Nom :");
                out.println("<input type=text size=20 name=nom>");
                out.println("<br>");
                out.println("Prenom :");
                out.println("<input type=text size=20 name=prenom>");
                out.println("<br>");
                out.println("<input type=submit>");
                out.println("</form>");

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