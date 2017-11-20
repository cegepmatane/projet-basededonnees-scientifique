package controleur;

import modele.Bouee;
import modele.BoueeDAO;
import vue.PanneauAjouterItem;
import vue.PanneauListe;

public class ActionAjouterBouee {

    private PanneauListe panneauListe;
    private PanneauAjouterItem panneauAjouterItem;

    public ActionAjouterBouee(PanneauListe panneauListe, PanneauAjouterItem panneauAjouterItem) {
        this.panneauListe = panneauListe;
        this.panneauAjouterItem = panneauAjouterItem;
    }

    public boolean ajouterBouee(){
        int latitude = 0, longitude = 0, temperatureEau = 0, temperatureAir = 0, dimension = 0;
        float salinite = 0, vitesseVent = 0, pressionAtmospherique = 0;
        try {
            latitude = Integer.parseInt(panneauAjouterItem.getLatitudeBouee());
            longitude = Integer.parseInt(panneauAjouterItem.getLongitudeBouee());
            temperatureEau = Integer.parseInt(panneauAjouterItem.getTemperatureEauBouee());
            temperatureAir = Integer.parseInt(panneauAjouterItem.getTemperatureAirBouee());
            salinite = Float.parseFloat(panneauAjouterItem.getSaliniteBouee());
            vitesseVent = Float.parseFloat(panneauAjouterItem.getVitesseVentBouee());
            dimension = Integer.parseInt(panneauAjouterItem.getDimensionBouee());
            pressionAtmospherique = Float.parseFloat(panneauAjouterItem.getPressionAtmospheriqueBouee());
        }catch (NumberFormatException exception){
        	exception.printStackTrace();
            panneauAjouterItem.afficherErreur("Erreur de format", "Vous devez entrer des nombres.");
            return false;
        }

        Bouee bouee = new Bouee(latitude, longitude, temperatureEau, temperatureAir, salinite, vitesseVent, dimension, pressionAtmospherique);
        BoueeDAO.getInstance().ajouterBouee(bouee);
        return true;
    }

}
