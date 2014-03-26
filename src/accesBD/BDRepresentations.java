package accesBD;

/**
 * Created by donatien on 21/03/14.
 */

import exceptions.ExceptionConnexion;
import exceptions.RepresentationsException;
import modele.Representations;
import modele.Utilisateur;

import java.sql.*;
import java.util.Vector;

public class BDRepresentations {

    public BDRepresentations() {

    }

    /**
     * retourne la liste des representations définies dans la bd
     *
     * @param user l'utilisateur identifie
     * @return Vector<Representations>
     * @throws exceptions.RepresentationsException
     * @throws exceptions.ExceptionConnexion
     */
    public static Vector<Representations> getRepresentations(Utilisateur user) throws RepresentationsException, ExceptionConnexion {
        Vector<Representations> res = new Vector<Representations>();
        String requete;
        Statement stmt;
        ResultSet rs;
        Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());

        requete = "select nomS, dateRep from LesSpectacles" +
                " LEFT JOIN LesRepresentations on LesSpectacles.numS=LesRepresentations.numS" +
                " where LesRepresentations.numS is NOT NULL " +
                "order by dateRep";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(requete);
            while (rs.next()) {
                res.addElement(new Representations(rs.getString(1), rs.getDate(2)));
            }
        } catch (SQLException e) {
            throw new RepresentationsException(" Problème dans l'interrogation des catégories.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        }
        BDConnexion.FermerTout(conn, stmt, rs);
        return res;
    }

    /**
     * ajoute une nouvelle representation dans la bd
     *
     * @param user l'utilisateur identifie
     * @param c nouvelle Representation
     * @throws exceptions.RepresentationsException
     * @throws exceptions.ExceptionConnexion
     */
    public static void addRepresentations(Utilisateur user, Representations c) throws RepresentationsException, ExceptionConnexion {

        PreparedStatement stmt;
        Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());

        try {
            stmt = conn.prepareStatement( "insert into LesRepresentations values (?,?) ");
            stmt.setString(1,c.getNomSpec());
            stmt.setDate(2, (Date) c.getDateSpec());
            stmt.executeUpdate();
            BDConnexion.commit(conn);
            BDConnexion.FermerTout(conn, stmt, null);
        }
        catch (SQLException e){

        }


    }

}

