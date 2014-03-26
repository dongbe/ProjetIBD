package utils;

import accesBD.BDCategories;
import accesBD.BDConnexion;
import accesBD.BDRepresentations;
import exceptions.CategorieException;
import exceptions.ExceptionConnexion;
import exceptions.ExceptionUtilisateur;
import exceptions.RepresentationsException;
import modele.Categorie;
import modele.Representations;
import modele.Utilisateur;

import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

/**
 * les operations de l'application
 *
 * @author fauvet
 */
public class Utilitaires {

    public Utilitaires() {
    }

    public static ServletOutputStream out;

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

            res = BDCategories.getCategorie(user);
            if (res.isEmpty()) {
                out.println(" Liste vide ");
            } else {
                for (int i = 0; i < res.size(); i++) {
                    out.println(res.elementAt(i).getCategorie() + " (prix : "
                            + res.elementAt(i).getPrix() + ")");
                }
            }
            out.println("===================");
        } catch (CategorieException e) {
            out.println(" Erreur dans l'affichage des categories : "
                    + e.getMessage());
        } catch (ExceptionConnexion e) {
            out.println(" Erreur dans l'affichage des categories : "
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
        } catch (CategorieException e) {
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
        //lecture des parametres de connexion dans connection.conf
		Properties p = new Properties();
		InputStream is = null;
		is = new FileInputStream(utils.Constantes.Config);
		p.load(is);
		login = p.getProperty("user");
		passwd = p.getProperty("mdp");
		if (login == null || login.equals("MYUSERNAME")) {
			UserNamePasswordDialog login_dialog = new UserNamePasswordDialog(
					new Frame(""));
			login_dialog.setVisible(true);
			login = login_dialog.getUid();
			passwd = login_dialog.getPwd();
		}
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
    public static void AfficherRepresentations() throws ExceptionConnexion, ExceptionUtilisateur {
        Vector<Representations> res = new Vector<Representations>();

        try {
            Utilisateur user = Utilitaires.Identification();
            if (user != null) {
                res = BDRepresentations.getRepresentations(user);
                if (res.isEmpty()) {
                    out.println("il n'y a plus de representations");
                } else {

                    out.println("<TABLE BORDER=\"1\"><TR>");
                    out.println(" <TH> Numero </TH>");
                    out.println(" <TH> Numero spectacle </TH>");
                    out.println(" <TH> Date </TH>");
                    out.println("</TR>");
                    for (int i = 0; i < res.size(); i++) {
                        out.println("<TR>");
                        out.println(" <TH>" + (i + 1) + "</TH>");
                        out.println("<TD>" + res.elementAt(i).getNomSpec() + " </TD><TD> "
                                + res.elementAt(i).getDateSpec() + "</TD>");
                        out.println("</TR>");
                    }
                    out.println("</TABLE>");
                }
            }else {
                out.println("<font color=\"#FFFFFF\"><h1> Aucun programme disponible </h1>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RepresentationsException e) {
            e.printStackTrace();
        }
    }
    public static void AjouterRepresentations(int n,Date date) throws ExceptionConnexion, ExceptionUtilisateur {
        String gy;
        Date dateSpec = null;
        int nomSpec = n;
        dateSpec = date;
        Representations nouvo = new Representations(nomSpec, dateSpec);
        try {
            BDRepresentations.addRepresentations(user, nouvo);
            out.println("Une nouvelle categorie inseree");
        } catch (CategorieException e) {
            e.printStackTrace();
        } catch (ExceptionConnexion exceptionConnexion) {
            exceptionConnexion.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}