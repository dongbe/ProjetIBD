package modele;


public class Place {

    private int noplace;
	private int norang;
	private int numz;
	//private String nomc;
	//private int prix;

	public Place(int p,int r,int z/*, String nc, int pr*/) {

        this.noplace=p;
        this.norang=r;
        this.numz=z;
       /* this.nomc=nc;
        this.prix=pr;*/
		
	}

	public int getNorang () {
		return this.norang;
	}
	public int getNoplace () {
		return this.noplace;
	}
	public int getNumz(){
		
		return this.numz;
	}
	/*public int getPrix(){
		
		return this.prix;
	}
	public String getNomc(){
		return this.nomc;
		
	}*/
	public void setPlace (int p) {
		this.noplace =p;
	}
	public void setRang (int r) {
		this.norang =r;
	}
	public void setNumz(int z){
		this.numz=z;
		
	}/*
	public void setPrix(int pr){
		this.prix=pr;
		
	}
	public void setNomc(String nc){
		this.nomc=nc;
		
	}*/

}

