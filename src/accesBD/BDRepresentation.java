package accesBD;

/**
 * Created by donatien on 21/03/14.
 */

import exceptions.ExceptionConnexion;
import exceptions.ExceptionRepresentation;
import modele.Representation;
import modele.Utilisateur;

import java.sql.*;
import java.text.ParseException;
import java.util.Vector;

import static java.lang.Integer.parseInt;

public class BDRepresentation {

    public BDRepresentation() {

    }

    /**
     * retourne la liste des representations définies dans la bd
     *
     * @param user l'utilisateur identifie
     * @return Vector<Representation>
     * @throws exceptions.ExceptionRepresentation
     * @throws exceptions.ExceptionConnexion
     */
    public static Vector<Representation> getRepresentations(Utilisateur user,String numS) throws ExceptionRepresentation, ExceptionConnexion {
        Vector<Representation> res = new Vector<Representation>();
        String requete;
        ResultSet rs;
        Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());

        try {
            if (numS == null){
                requete = "select * from LesRepresentations";
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(requete);
                while (rs.next()) {
                    res.addElement(new Representation(rs.getInt(1), rs.getDate(2),rs.getString(3)));
                }
                BDConnexion.FermerTout(conn, stmt, rs);
            }else {
                PreparedStatement stmt = conn.prepareStatement("select * from LesRepresentations where numS=?");
                stmt.setInt(1, parseInt(numS));
                rs= stmt.executeQuery();
                while (rs.next()) {
                    res.addElement(new Representation(rs.getInt(1), rs.getDate(2),rs.getString(3)));
                }
                BDConnexion.FermerTout(conn, stmt, rs);
            }

        } catch (SQLException e) {
            throw new ExceptionRepresentation(" Problème dans l'interrogation des representations.."
                    + "Code Oracle " + e.getErrorCode()
                    + "Message " + e.getMessage());
        }
        return res;
    }

    /**
     * ajoute une nouvelle representation dans la bd
     *
     * @param user l'utilisateur identifie
     * @param c nouvelle Representation
     * @throws exceptions.ExceptionRepresentation
     * @throws exceptions.ExceptionConnexion
     */
    public static void addRepresentations(Utilisateur user, Representation c) throws ExceptionRepresentation, ExceptionConnexion {

        PreparedStatement stmt;
        Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());

        try {
            stmt = conn.prepareStatement( "insert into LesRepresentations values (?,?,?) ");
            stmt.setInt(1, c.getNumRep());
            stmt.setDate(2, (Date) c.getDateRep());
            stmt.setString(3, c.getHeurRep());
            stmt.executeUpdate();
            BDConnexion.commit(conn);
            BDConnexion.FermerTout(conn, stmt, null);
        }
        catch (SQLException e){

        }


    }
    public static Vector<Representation> ConsulterRepresentation (Utilisateur user,String numS)
            throws ParseException, ExceptionConnexion{
        Vector<Representation> ress=new Vector<Representation>();
        String requete;
        Statement stmt = null;
        ResultSet rs = null;
        int nums = parseInt(numS);
        Connection conn =BDConnexion.getConnexion(user.getLogin(), user.getmdp());
        requete="select * from LESREPRESENTATIONS where nums="+nums;

        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(requete);
            while(rs.next()){
                ress.addElement(new Representation (rs.getInt(1),rs.getDate(2),rs.getString(3)));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BDConnexion.FermerTout(conn, stmt, rs);
        return ress;
    }

}

