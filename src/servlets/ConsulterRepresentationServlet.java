package servlets;

import affichage.StylePage;
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
 * NouvelleRepresentation Servlet.
 *
 * This servlet dynamically adds a new date a show.
 *
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 31/10/2007
 */

public class ConsulterRepresentationServlet extends HttpServlet {

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
    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
    	String numS;
        ServletOutputStream out = res.getOutputStream();   

	  res.setContentType("text/html");

	 StylePage.Header(out);

	  numS= req.getParameter("numS");

	  if (numS == null) {
          	out.println("Veuillez saisir les informations relatives &agrave; la nouvelle repr&eacute;sentation :");
          	out.println("<P>");
          	out.print("<form action=\"");
          	out.print("ConsulterRepresentationServlet\" ");
          	out.println("method=POST>");
          	out.println("Num&eacute;ro de spectacle :");
          	out.println("<input type=text size=20 name=numS>");
          	out.println("<br>");
          	out.println("<input type=submit>");
          	out.println("</form>");
	  } else {
          Utilisateur user = null;
          try {
              user = Utilitaires.Identification();
          } catch (ExceptionConnexion exceptionConnexion) {
              out.println(" Message " + exceptionConnexion.getMessage());
          } catch (ExceptionUtilisateur exceptionUtilisateur) {
              out.println(" Message " + exceptionUtilisateur.getMessage());
          }
          if(user!=null){
              try {
                  Utilitaires.AfficherRepresentations(user,out,numS);
              } catch (ExceptionConnexion exceptionConnexion) {
                  out.println(" Message " + exceptionConnexion.getMessage());
              } catch (ExceptionUtilisateur exceptionUtilisateur) {
                  out.println(" Message " + exceptionUtilisateur.getMessage());
              }
          }else{
              out.println(" reseau indisponible - veuillez reessayer plus tard");
          }
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

