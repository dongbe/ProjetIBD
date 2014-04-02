package utils;

import accesBD.*;
import exceptions.ExceptionConnexion;
import exceptions.ExceptionRepresentation;
import exceptions.ExceptionSpectacle;
import exceptions.ExceptionUtilisateur;
import modele.*;

import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.Vector;

/**
 * les operations de l'application
 *
 * @author fauvet
 */
public class Utilitaires {

    public Utilitaires() {
    }

    /**
     * Affiche les categories du theatre avec pour chacune son prix
     *
     * @param user l'utilisateur identifie
     * @throws exceptions.ExceptionConnexion
     * @throws java.io.IOException
     */
    public static void AfficherCategories(Utilisateur user, ServletOutputStream out) throws IOException {
        Vector<Categorie> res = new Vector<Categorie>();

        try {
            String nomC=nomZone(user,null);
            res = BDCategories.getCategorie(user,nomC);
            if (res.isEmpty()) {
                out.println(" Liste vide ");
            } else {
                for (int i = 0; i < res.size(); i++) {
                    out.println(res.elementAt(i).getCategorie() + " (prix : "
                            + res.elementAt(i).getPrix() + ")");
                }
            }
        } catch (ExceptionSpectacle e) {
            out.println(" Erreur dans l'affichage des categories : "
                    + e.getMessage());
        } catch (ExceptionConnexion e) {
            out.println(" Erreur de connexion : "
                    + e.getMessage());
        }

    }

    public static void AjouterCategories(Utilisateur user, ServletOutputStream out) {
        Vector<Categorie> res = new Vector<Categorie>();
        String nomCat;
        String pxCat;
        try {
            out.println("Ajout de nouvelles categories : En cours ....");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AjoutCategorieDialog ajout_box = new AjoutCategorieDialog(
                new Frame(""));
        ajout_box.setVisible(true);
        nomCat = ajout_box.getNomCat();
        pxCat = ajout_box.getPxCat();
        Categorie nouvo = new Categorie(nomCat, Float.parseFloat(pxCat));
        try {
            BDCategories.addCategories(user, nouvo);
            out.println("Une nouvelle categorie inseree");
        } catch (ExceptionSpectacle e) {
            e.printStackTrace();
        } catch (ExceptionConnexion exceptionConnexion) {
            exceptionConnexion.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * effectue la connexion pour l'utilisateur
     *
     * @return l'oid de l'objet utilisateur
     * @throws exceptions.ExceptionUtilisateur
     */
    public static Utilisateur Identification() throws ExceptionConnexion, ExceptionUtilisateur, IOException {
        Utilisateur user = null;
        String login;
        String passwd;
		login = "gbed";//p.getProperty("user");
		passwd ="bd2013"; //p.getProperty("mdp");

		/* test de la connexion */
        Connection conn = BDConnexion.getConnexion(login, passwd);
        if (conn != null) {

            BDConnexion.FermerTout(conn, null, null);
            user = new Utilisateur(login, passwd);
        } else {
            throw new ExceptionConnexion("Connexion impossible\n");
        }
        return user;
    }
    /**
     * Affiche la table de representation
     *
     * @throws exceptions.ExceptionUtilisateur
     * @throws exceptions.ExceptionConnexion
     */
    public static void AfficherRepresentations(Utilisateur user,ServletOutputStream out, String numS)
            throws ExceptionConnexion, ExceptionUtilisateur{
        Vector<Representation> res = new Vector<Representation>();

        try{

            res = BDRepresentation.getRepresentations(user,numS);
                if (res.isEmpty()) {
                    out.println("il n'y a plus de representations");
                } else {
                    out.println("<TABLE BORDER=\"1\"><TR>");
                    out.println(" <TH> Numero </TH>");
                    out.println(" <TH> Numero spectacle </TH>");
                    out.println(" <TH> Date </TH>");
                    out.println(" <TH> Heure</TH>");
                    out.println("</TR>");
                    for (int i = 0; i < res.size(); i++) {
                        out.println("<TR>");
                        out.println(" <TH>" + (i + 1) + "</TH>");
                        out.println("<TD>" + res.elementAt(i).getNumRep() + " </TD><TD> "
                                + res.elementAt(i).getDateRep() + "</TD>" +
                                "<TD><a href=\"/servlet/ConsulterPlaceServlet?spectacle="+res.elementAt(i).getNumRep()+
                                "&date="+res.elementAt(i).getDateRep()+"&heure="+res.elementAt(i).getHeurRep()+
                                "\">"+res.elementAt(i).getHeurRep()+"</a></TD>");
                        out.println("</TR>");
                    }
                    out.println("</TABLE>");
                }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExceptionRepresentation e) {
            e.printStackTrace();
        }
    }

    public static void AjouterRepresentation(Utilisateur user, Representation r)
            throws ExceptionRepresentation, ExceptionConnexion {

     BDRepresentation.addRepresentations(user, r);

     }

    /**
     * retourne la liste des spectacles dans un combobox
     * @param user
     * @param out
     * @throws ExceptionSpectacle
     * @throws ExceptionConnexion
     */
    public static void AfficherSpectacles(Utilisateur user, ServletOutputStream out) throws ExceptionSpectacle, ExceptionConnexion {
        Vector<Spectacle> res = new Vector<Spectacle>();

        res = BDSpectacle.getSpectacle(user);
        if(res.isEmpty()){

        }
        else{
                try {
                    out.println("<option value=\" \" selected=\"selected\"></option>");
                    for(int i=0; i<res.size(); i++){
                    out.println("<option value="+res.elementAt(i).getNumS()+">"+res.elementAt(i).getNomSpec()+"</option>");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Retourne la liste de spectacles
     * @param user
     * @param out
     * @throws ExceptionSpectacle
     * @throws ExceptionConnexion
     * @throws IOException
     */
    public static void AfficherSpectacle(Utilisateur user, ServletOutputStream out) throws ExceptionSpectacle, ExceptionConnexion, IOException {
        Vector<Spectacle> res = new Vector<Spectacle>();

        res = BDSpectacle.getSpectacle(user);
        if (res.isEmpty()) {
            out.println("il n'y a plus de representations");
        } else {
            out.println("<TABLE BORDER=\"1\"><TR>");
            out.println(" <TH> Numero </TH>");
            out.println(" <TH> Numero spectacle </TH>");
            out.println(" <TH> Spectacle </TH>");
            out.println(" <TH> Consulter diffusions</TH>");
            out.println("</TR>");
            for (int i = 0; i < res.size(); i++) {
                out.println("<TR>");
                out.println(" <TH>" + (i + 1) + "</TH>");
                out.println("<TD>" + res.elementAt(i).getNumS() + " </TD><TD> "
                        + res.elementAt(i).getNomSpec() + "</TD>" +
                        "<TD><a href=\"/servlet/ConsulterRepresentationServlet?numS="+res.elementAt(i).getNumS()+"\">les dates</a></TD>");
                out.println("</TR>");

            }
            out.println("</TABLE>");
        }
    }

    /**
     *
     * @param user
     * @param out
     * @throws ParseException
     * @throws ExceptionConnexion
     * @throws IOException
     */
    public static void AfficherPlace(Utilisateur user, ServletOutputStream out, String numS, String date, String heureS) throws ParseException, ExceptionConnexion, IOException {
        Vector<Place> res = new Vector<Place>();
        res= BDPlace.getPlace(user,null,null,null);
        if (res.isEmpty()) {
            out.println("il n'y a plus de place");
        } else {
            int val;
            out.println("<TABLE BORDER=\"1\">");
            for (int i = 0; i < res.size(); i++) {
                out.println("<TR>");
                out.println(" <TH>" + res.elementAt(i).getNorang() + "</TH>");
                out.println("<TD>"+zoneColor(res.elementAt(i).getNumz())+res.elementAt(i).getNoplace() + "</font></TD>");
                val= res.elementAt(i).getNorang();
                while(i+1<res.size() && res.elementAt(i+1).getNorang()==val){
                    out.println("<TD><a href=\"/servlet/ReserverServlet?num="+numS+
                            "&date="+date+
                            "&zone="+res.elementAt(i).getNumz()+
                            "&place="+res.elementAt(i+1).getNoplace()+
                            "&rang="+val+
                            "&heure="+heureS+"\"> "+ zoneColor(res.elementAt(i).getNumz())+res.elementAt(i+1).getNoplace() +"</font></a></TD>");
                    i++;
                }
                out.println("</TR>");
            }
            out.println("</TABLE>");
        }
    }
    public static String zoneColor(int z){
        String colorFont=null;
        switch (z){
            case 1:
               colorFont="<font color=\"10B215\">";break;
            case 2:
                colorFont="<font color=\"1048B2\">";break;
            case 3:
                colorFont="<font color=\"B21D10\">";break;
            case 4:
                colorFont="<font color=\"F7EF15\">";break;
            case 5:
                colorFont="<font color=\"F79D15\">";break;
            case 6:
                colorFont="<font color=\"F715E4\">";break;
        }
        return colorFont;
    }
    public static String nomZone(Utilisateur user, String zone) throws ExceptionConnexion {
        Vector<Zone> rs = new Vector<Zone>();
        rs=BDZone.getZone(user,zone);
        String nomC=rs.elementAt(0).getNomZone();
        return nomC;
    }
    public static String prixZone(Utilisateur user, String zone) throws ExceptionConnexion, ExceptionSpectacle {
        Vector<Categorie> rs = new Vector<Categorie>();
        rs=BDCategories.getCategorie(user,zone);
        float prix=rs.elementAt(0).getPrix();
        return String.valueOf(prix);
    }
}