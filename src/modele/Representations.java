package modele;

import java.util.Date;

/**
 * Created by donatien on 21/03/14.
 */
public class Representations {
    private String nomSpec;
    private Date dateSpec;

    public Representations(String nomSpec, Date dateSpec) {
        this.nomSpec = nomSpec;
        this.dateSpec = dateSpec;
    }

    public String getNomSpec() {
        return nomSpec;
    }

    public void setNomSpec(String nomSpec) {
        this.nomSpec = nomSpec;
    }

    public Date getDateSpec() {
        return dateSpec;
    }

    public void setDateSpec(Date dateSpec) {
        this.dateSpec = dateSpec;
    }
}
