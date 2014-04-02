package accesBD;

import exceptions.ExceptionConnexion;
import exceptions.ExceptionSpectacle;
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
     * @param user
     * @return Vector<Categorie>
     * @throws exceptions.ExceptionSpectacle
     * @throws ExceptionConnexion
     */
    public static Vector<Categorie> getCategorie(Utilisateur user, String nomC) throws ExceptionSpectacle, ExceptionConnexion {
        Vector<Categorie> res = new Vector<Categorie>();
        String requete;
        ResultSet rs;
        Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());

        try {
            if(nomC!=null){
                Statement stmt = conn.createStatement();
                requete = "select nomc, prix from LesCategories order by nomc";
                rs = stmt.executeQuery(requete);
                while (rs.next()) {
                    res.addElement(new Categorie(rs.getString(1), rs.getFloat(2)));
                }
                BDConnexion.FermerTout(conn, stmt, rs);
            }else{
                PreparedStatement stmt = conn.prepareStatement("select * from LesCategories where nomC=?");
                stmt.setString(1, nomC);
                rs= stmt.executeQuery();
                while (rs.next()) {
                    res.addElement(new Categorie(rs.getString(1), rs.getFloat(2)));
                }
                BDConnexion.FermerTout(conn, stmt, rs);
            }
        } catch (SQLException e) {
            throw new ExceptionSpectacle(" Problème dans l'interrogation des catégories.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        }

        return res;
    }

    public static void addCategories(Utilisateur user, Categorie c) throws ExceptionSpectacle, ExceptionConnexion {

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
