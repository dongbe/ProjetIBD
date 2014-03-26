package accesBD;

import exceptions.CategorieException;
import exceptions.ExceptionConnexion;
import modele.Categorie;
import modele.Utilisateur;

import java.sql.*;
import java.util.Vector;

public class BDCategories {

    public BDCategories() {

    }

    /**
     * retourne la liste des catégories définies dans la bd
     *
     * @param Utilisateur
     * @return Vector<Categorie>
     * @throws CategorieException
     * @throws ExceptionConnexion
     */
    public static Vector<Categorie> getCategorie(Utilisateur user) throws CategorieException, ExceptionConnexion {
        Vector<Categorie> res = new Vector<Categorie>();
        String requete;
        Statement stmt;
        ResultSet rs;
        Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());

        requete = "select nomc, prix from LesCategories order by nomc";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(requete);
            while (rs.next()) {
                res.addElement(new Categorie(rs.getString(1), rs.getFloat(2)));
            }
        } catch (SQLException e) {
            throw new CategorieException(" Problème dans l'interrogation des catégories.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        }
        BDConnexion.FermerTout(conn, stmt, rs);
        return res;
    }

    public static void addCategories(Utilisateur user, Categorie c) throws CategorieException, ExceptionConnexion {

        PreparedStatement stmt;
        Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());

        try {
            stmt = conn.prepareStatement("insert into LesCategories values (?,?) ");
            Statement requete = conn.createStatement();
            stmt.setString(1, c.getCategorie());
            stmt.setFloat(2, c.getPrix());
            stmt.executeUpdate();
            requete.executeUpdate("commit");
            BDConnexion.FermerTout(conn, stmt, null);
            BDConnexion.FermerTout(conn, requete, null);
        } catch (SQLException e) {

        }


    }

}
