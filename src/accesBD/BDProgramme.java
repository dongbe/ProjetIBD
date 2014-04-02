package accesBD;

import exceptions.ExceptionSpectacle;
import exceptions.ExceptionConnexion;
import modele.Programme;
import modele.Utilisateur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class BDProgramme {

	public BDProgramme () {
		
	}
	/**
	 * retourne la liste des catégories définies dans la bd
	 * @param user
	 * @return Vector<Programme>
	 * @throws exceptions.ExceptionSpectacle
	 * @throws ExceptionConnexion
	 */
	public static Vector<Programme> getCategorie (Utilisateur user)
	throws ExceptionSpectacle, ExceptionConnexion {
		Vector<Programme> ress = new Vector<Programme>();
		String requete ;
		Statement stmt ;
		ResultSet rs ;
		Connection conn = BDConnexion.getConnexion(user.getLogin(), user.getmdp());
		System.out.println(user.getLogin()+user.getmdp());
		requete = "select rep.NUMS,spe.NOMS,rep.DATEREP,rep.HEUREDEB from LESREPRESENTATIONS rep left OUTER JOIN LESSPECTACLES spe ON spe.NUMS = rep.NUMS";
		//requete="insert into lesspectacles values('102','sda')";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(requete);
		
			while (rs.next()) {
				ress.addElement(new Programme (rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			throw new ExceptionSpectacle(" Probleme dans l'interrogation des categories.."
					+ "Code Oracle " + e.getErrorCode()
					+ "Message " + e.getMessage());
		}
		BDConnexion.FermerTout(conn, stmt, rs);
	//	System.out.println("sdadasda");
		//System.out.println("sadadas"+res.elementAt(0).getPrix());
		for (int i = 0; i < ress.size(); i++) {
			System.out.println(ress.elementAt(i).getNum()+" "+ress.elementAt(i).getCategorie() + "  "
					+ ress.elementAt(i).getDate()+" "+ress.elementAt(i).getHeure());
		}
		return ress;
	}
}
