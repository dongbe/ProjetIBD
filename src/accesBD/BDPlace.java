package accesBD;

import exceptions.ExceptionConnexion;
import modele.Place;
import modele.Utilisateur;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class BDPlace {

public BDPlace () {
		
	}
	/**
	 * retourne la liste des catégories définies dans la bd
	 * @param user
	 * @return Vector<Programme>
	 * @throws exceptions.ExceptionPlace
	 * @throws ExceptionConnexion
	 */
	
public static Vector<Place> getPlace(Utilisateur user, String numS, String dateS,String HeureS)
		throws ParseException, ExceptionConnexion{
	  Vector<Place> res=new Vector<Place>();
	  PreparedStatement stmt=null;
	  ResultSet rs=null;
	  Connection conn =BDConnexion.getConnexion(user.getLogin(), user.getmdp());
	  
	  try {
		  	stmt = conn.prepareStatement("select * from LesPlaces");
		    //stmt.setString(1,numS+dateS+HeureS);
		    rs=stmt.executeQuery();
		while(rs.next()){		
			res.addElement(new Place (rs.getInt(1),rs.getInt(2),rs.getInt(3)));
		}		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	  BDConnexion.FermerTout(conn, stmt, rs);
	  return res;
}



public static Vector<Place> ConsulterPlaceAvecZone (Utilisateur user,String numZ,String numS,String dateS)
		throws ParseException, ExceptionConnexion{
	  Vector<Place> ress=new Vector<Place>();
	  String requete;
	  Statement stmt = null;
	  ResultSet rs = null;
	  int nums =Integer.parseInt(numS);
	  int numz=Integer.parseInt(numZ);
	  
	  System.out.println(dateS);
	  Connection conn =BDConnexion.getConnexion(user.getLogin(), user.getmdp());
	  
	  try {
	
		  SimpleDateFormat ff=new SimpleDateFormat("dd-MMM-yy");
		
		   java.util.Date date2=ff.parse(dateS);
		   String d=ff.format(date2);
		   java.util.Date date3=ff.parse(d);
		   java.sql.Date dates=new java.sql.Date(date3.getTime());
		   requete="select pla.NUMZ,pla.NOPLACE,pla.NORANG,cat.NOMC,cat.PRIX from LESPLACES pla left outer JOIN LESZONES on pla.NUMZ = LESZONES.NUMZ LEFT OUTER JOIN LESCATEGORIES cat on LESZONES.NOMC = cat.NOMC where pla.NUMZ="+numz+" and not EXISTS(select tic.NORANG,tic.NOPLACE from LESTICKETS tic where pla.NOPLACE=tic.NOPLACE and pla.NORANG=tic.NORANG and tic.nums="+nums+" and tic.DATEREP between ? and ?) ORDER BY pla.NORANG,pla.NOPLACE";		    
		   PreparedStatement pstmt = conn.prepareStatement(requete);
		    pstmt.setDate(1,dates);
		    pstmt.setDate(2,dates);   
		    rs=pstmt.executeQuery();
		while(rs.next()){		
			ress.addElement(new Place (rs.getInt(1),rs.getInt(2),rs.getInt(3)));
		}		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  BDConnexion.FermerTout(conn, stmt, rs);
	  return ress;		
}
public static Vector<Place> ReserverPlace (Utilisateur user,String numZ,String numS,String dateS, String noplacE, String ranG)
		throws ParseException, ExceptionConnexion{
	  Vector<Place> ress=new Vector<Place>();
	  String requete;
	  Statement stmt = null;
	  ResultSet rs = null;
	  int nums =Integer.parseInt(numS);
	  int numz=Integer.parseInt(numZ);
	  int noplace=Integer.parseInt(noplacE);
	  int norang=Integer.parseInt(ranG);
	  System.out.println(dateS);
	  Connection conn =BDConnexion.getConnexion(user.getLogin(), user.getmdp());
	  
	  try {
	
		  SimpleDateFormat ff=new SimpleDateFormat("dd-MMM-yy");	 
		   java.util.Date date2=ff.parse(dateS);	  
		   java.sql.Date dates=new java.sql.Date(date2.getTime());
			  requete="select res.numz,res.noplace,res.norang,cat.nomc,cat.prix from(select * from LESPLACES pla where not EXISTS" +
			  		"(select tic.NORANG,tic.NOPLACE from LESTICKETS tic " +
			  		"where pla.NOPLACE=tic.NOPLACE and pla.NORANG=tic.NORANG and tic.nums="+nums+" "+"and tic.DATEREP between ? and ?) and pla.numz="+numz+")res join leszones zon on res.numz=zon.numz join lescategories cat on zon.nomc=cat.nomc where res.noplace="+noplace+" and res.norang="+norang+"";
		    PreparedStatement pstmt = conn.prepareStatement(requete);
		    pstmt.setDate(1,dates);
		    pstmt.setDate(2,dates);	   
		    rs=pstmt.executeQuery();
		while(rs.next()){		
			ress.addElement(new Place (rs.getInt(1),rs.getInt(2),rs.getInt(3)));
		}		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  BDConnexion.FermerTout(conn, stmt, rs);
	  return ress;		
}

}