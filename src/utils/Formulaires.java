package utils;

import exceptions.ExceptionConnexion;
import exceptions.ExceptionSpectacle;
import modele.Utilisateur;

import javax.servlet.ServletOutputStream;

import java.io.IOException;

import static utils.Utilitaires.AfficherSpectacles;
import static utils.Utilitaires.prixZone;

/**
 * Created by donatien on 02/04/14.
 */
public class Formulaires {

    public Formulaires(){

    }
    public static void ConsulterPlaceForm(Utilisateur user, ServletOutputStream out) throws IOException {

        out.println("Veuillez saisir les informations relatives &agrave; la nouvelle repr&eacute;sentation :");
        out.println("<P>");
        out.print("<form name=\"form1\" action=\"");
        out.print("ConsulterPlaceServlet\" ");
        out.println("method=POST>");
        out.println("Num&eacute;ro de spectacle :");
        out.println("<select name=\"spectacle\" id=\"spec\" onchange=\"dateShow()\">");
        try {

            if (user != null) {
                AfficherSpectacles(user, out);
            }
        } catch (ExceptionConnexion exceptionConnexion) {
            exceptionConnexion.printStackTrace();
        } catch (ExceptionSpectacle throwables) {
            throwables.printStackTrace();
        }
        out.println("</select>");
        out.println("<br>");
        out.println("<input type=submit>");
        out.println("</form>");
    }
    public static void PrintTicket(Utilisateur user, ServletOutputStream out, String noPlace,
                                   String noZone, String noRang, String numRep, String dateRep,
                                   String heureRep, String prix) throws IOException, ExceptionConnexion, ExceptionSpectacle {
        prix=prixZone(user,noZone);
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
        out.print("<form action=\"ReserverServlet\"");
        out.println("method=POST>");
        out.println("<input type=submit>");
        out.println("</form>");
    }
}
