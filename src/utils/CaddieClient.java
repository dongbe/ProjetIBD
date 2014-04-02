package utils;

import java.util.ArrayList;

import modele.Representation;

public class CaddieClient {
 private  ArrayList<Representation> reparry=new ArrayList<Representation>();
 public void setReparry(Representation representation){

		 reparry.add(representation);
	 
 }
 public ArrayList getReparry(){
	 
	 return reparry;
 }
}
