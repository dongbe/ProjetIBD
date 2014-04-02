package servlets;


import modele.Representation;
import utils.CaddieClient;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


public class CaddieAfficherServlet extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
    	String numS,dateS,heureS;
        ServletOutputStream out = res.getOutputStream();   

	  res.setContentType("text/html");
  HttpSession session=req.getSession(false);
 CaddieClient addieclient=(CaddieClient) session.getAttribute("represent");  
	  out.println("<HEAD><TITLE> Ajouter une nouvelle reprentation </TITLE></HEAD>");
	  out.println("<BODY bgproperties=\"fixed\" background=\"/Theatre/images/rideau.JPG\">");
	  out.println("<font color=\"#FFFFFF\"><h1> Consulter la liste de toutes les représentations </h1>");
    ArrayList list=addieclient.getReparry();
    
    for(int i=0;i<list.size();i++){
    	Representation representation=(Representation) list.get(i);
    	out.println("<tr>");
    	
    	int nums=representation.getNumRep();
		out.println("<td><font color=\"#ffffff\">"+nums+"</td>");
 	
    	java.util.Date dates=representation.getDateRep();
		out.println("<td><font color=\"#ffffff\">"+dates+"</td>");

		String heures=representation.getHeurRep();
		out.println("<td><font color=\"#ffffff\">"+heures+"</td>");

    	out.println("</tr>");
 	
    }
	  
	  
	  
//	  numS= req.getParameter("numS");
//	  dateS=req.getParameter("dateS");
//	  heureS=req.getParameter("heureS");
//	
//
//	  if (numS == null || dateS == null) {
////          	out.println("<font color=\"#FFFFFF\">Veuillez saisir les informations relatives &agrave; la nouvelle repr&eacute;sentation :");
////          	out.println("<P>");
////          	out.print("<form action=\"");
////          	out.print("ConsulterPlaceServlet\" ");
////          	out.println("method=POST>");
////          	out.println("Num&eacute;ro de spectacle :");
////          	out.println("<input type=text size=20 name=numS>");
////          	out.println("<br>");
////          	out.println("Numero du Zone :");
////          	out.println("<input type=text size=20 name=numZ>");
////          	out.println("<br>");
////          	out.println("Date de la repr&eacute;sentation :");
////          	out.println("<input type=text size=20 name=dateS>");
////          	out.println("<br>");
////          	out.println("<input type=submit>");
////          	out.println("</form>");
//	  } else {
//		
//		  HttpSession session=req.getSession();
//		  int nums=Integer.parseInt(numS);
//		  SimpleDateFormat ff=new SimpleDateFormat("dd-MMM-yy");
//		  java.util.Date date2;
//		try {
//			date2 = ff.parse(dateS);
//			java.sql.Date dates=new java.sql.Date(date2.getTime());
//			Representation representation=new Representation(nums,dates,heureS);
//		   
//		     CaddieClient caddieclient;
//		     if(session.getAttribute("represent")!=null){
//		    	 caddieclient=(CaddieClient)session.getAttribute("represent");
//		    	 caddieclient.setReparry(representation);
//		    	 
//		     }else{
//		    	 caddieclient=new CaddieClient();
//		    	 caddieclient.setReparry(representation);
//		    	 session.setAttribute("represent", caddieclient);
//		    	 
//		     }
//		
//		
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		  
//	  }
	  
		  
		}
   
    public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	  doGet(req, res);
    }

    public String getServletInfo() {
        return "Ajoute une reprentation �une date donn閑 pour un spectacle existant";
    }

}


