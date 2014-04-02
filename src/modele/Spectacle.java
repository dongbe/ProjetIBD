package modele;

/**
 * Created by donatien on 30/03/14.
 */
public class Spectacle {
    private int numS;
    private String nomSpec;

    public Spectacle(int numS,String string) {
        this.numS=numS;
        this.nomSpec=string;
    }

    public int getNumS() {
        return numS;
    }

    public void setNumS(int numS) {
        this.numS = numS;
    }

    public String getNomSpec() {
        return nomSpec;
    }
    public void setNomSpec(String s) {
        this.nomSpec = s;
    }
}
