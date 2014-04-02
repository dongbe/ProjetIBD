package accesBD;

import exceptions.ExceptionConnexion;
import modele.Utilisateur;
import modele.Zone;

import java.sql.*;
import java.util.Vector;

import static java.lang.Integer.parseInt;

/**
 * Created by donatien on 02/04/14.
 */
public class BDZone {
    public static Vector<Zone> getZone(Utilisateur user, String zone) throws ExceptionConnexion {
        Vector<Zone> res = new Vector<Zone>();
        String requete;
        ResultSet rs;
        Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());

        try {
            if(zone==null){
                PreparedStatement stmt = conn.prepareStatement("select * from LesCategories where numZ=?");
                stmt.setInt(1, parseInt(zone));
                rs= stmt.executeQuery();
                while (rs.next()) {
                    res.addElement(new Zone(rs.getInt(1), rs.getString(2)));
                }
                BDConnexion.FermerTout(conn, stmt, rs);
            }
            else{
                Statement stmt = conn.createStatement();
                requete = "select * from LesZones order by numZ";
                rs = stmt.executeQuery(requete);
                while (rs.next()) {
                    res.addElement(new Zone(rs.getInt(1), rs.getString(2)));
                }
                BDConnexion.FermerTout(conn, stmt, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public static void setZone(){

    }
}
