package accesBD;

import exceptions.ExceptionConnexion;
import exceptions.ExceptionSpectacle;
import modele.Spectacle;
import modele.Utilisateur;

import java.sql.*;
import java.util.Vector;

/**
 * Created by donatien on 30/03/14.
 */
public class BDSpectacle {

    public BDSpectacle() {

    }

    /**
     * retourne la liste des spectacles définies dans la bd
     *
     * @param user l'utilisateur identifie
     * @return Vector<Spectacle>
     * @throws exceptions.ExceptionSpectacle
     * @throws exceptions.ExceptionConnexion
     */
    public static Vector<Spectacle> getSpectacle(Utilisateur user) throws ExceptionSpectacle, ExceptionConnexion {
        Vector<Spectacle> res = new Vector<Spectacle>();
        String requete;
        Statement stmt;
        ResultSet rs;
        Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());

        requete = "select * from LesSpectacles";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(requete);
            while (rs.next()) {
                res.addElement(new Spectacle(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new ExceptionSpectacle(" Problème dans l'interrogation des spectacles.."
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
     * @param s nouveau Spectacle
     * @throws exceptions.ExceptionSpectacle
     * @throws exceptions.ExceptionConnexion
     */
    public static void addSpectacle(Utilisateur user, Spectacle s) throws ExceptionSpectacle, ExceptionConnexion {

        PreparedStatement stmt;
        Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());

        try {
            stmt = conn.prepareStatement( "insert into LesSpectacles values (?,?) ");
            stmt.setInt(1, s.getNumS());
            stmt.setString(2, s.getNomSpec());
            stmt.executeUpdate();
            BDConnexion.commit(conn);
            BDConnexion.FermerTout(conn, stmt, null);
        }
        catch (SQLException e){
            throw new ExceptionSpectacle(" Problème dans l'insertion d'un spectacle.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        }


    }
}
