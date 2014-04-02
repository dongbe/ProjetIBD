package modele;

public class Zone {
	private int numZ;
    private String nomZone;

    public Zone(int z, String nomZone){
        this.numZ=z;
        this.nomZone=nomZone;
    }

    public int getNumZ() {
        return numZ;
    }

    public void setNumZ(int numZ) {
        this.numZ = numZ;
    }

    public String getNomZone() {
        return nomZone;
    }

    public void setNomZone(String nomZone) {
        this.nomZone = nomZone;
    }
}
