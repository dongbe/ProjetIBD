package modele;

import java.sql.Date;

public class Programme {
    private int nums;
	private String categorie;
	private Date date;
	private String heure;
	
//	
//	public Programme (String c, float p) {
//		this.categorie = c;
//		this.prix = p;
//	}

	public Programme(int n, String c, Date d, String h) {
		// TODO Auto-generated constructor stub
		this.nums=n;
		this.categorie=c;
		this.date=d;
		this.heure=h;
		
	}

	public String getCategorie () {
		return this.categorie;
	}
	public Date getDate(){
		return this.date;
		
	}
	public String getHeure(){
		return this.heure;
		
	}
	public int getNum () {
		return this.nums;
	}
	
	public void setCategorie (String c) {
		this.categorie = c;
	}
	
	public void setNum (int n) {
		this.nums =n;
	}
	public void setDate(Date d){
		this.date=d;
	}
	public void setHeure(String h){
		this.heure=h;
	}
}
