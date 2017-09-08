package modele;

public class Bouee {
    private String nom;
    private int idBouee;
    private int latitude;
    private int longitude;
    private int temperatureEau;
    private int temperatureAit;
    private float salinite;
    private float vitesseVent;
    private int dimension;
    private float pressionAtmospherique;

    private static int compteurIdBouee;

    public Bouee(String nom, int latitude, int longitude, int temperatureEau, int temperatureAit, float salinite, float vitesseVent, int dimension, float pressionAtmospherique) {
        this.nom = nom;
        idBouee = compteurIdBouee++;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperatureEau = temperatureEau;
        this.temperatureAit = temperatureAit;
        this.salinite = salinite;
        this.vitesseVent = vitesseVent;
        this.dimension = dimension;
        this.pressionAtmospherique = pressionAtmospherique;
    }

    @Override
    public String toString() {
        return "Bouee{" +
                "nom='" + nom + '\'' +
                ", idBouee=" + idBouee +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", temperatureEau=" + temperatureEau +
                ", temperatureAit=" + temperatureAit +
                ", salinite=" + salinite +
                ", vitesseVent=" + vitesseVent +
                ", dimension=" + dimension +
                ", pressionAtmospherique=" + pressionAtmospherique +
                '}';
    }
}