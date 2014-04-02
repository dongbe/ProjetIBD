package modele;

import java.util.Date;

/**
 * Created by donatien on 21/03/14.
 */
public class Representation {
    private int numRep;
    private Date dateRep;
    private String heurRep;

    public Representation(int numRep, Date dateRep, String heurRep) {
        this.numRep = numRep;
        this.dateRep = dateRep;
        this.heurRep = heurRep;
    }

    public int getNumRep() {
        return numRep;
    }

    public void setNumRep(int numRep) {
        this.numRep = numRep;
    }

    public Date getDateRep() {
        return dateRep;
    }

    public void setDateRep(Date dateRep) {
        this.dateRep = dateRep;
    }

    public String getHeurRep() {
        return heurRep;
    }

    public void setHeurRep(String heurRep) {
        this.heurRep = heurRep;
    }
}
