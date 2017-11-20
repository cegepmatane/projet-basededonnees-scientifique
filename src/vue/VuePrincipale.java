package vue;

import java.sql.*;

import controleur.ActionAjouterBouee;
import controleur.ControleurVue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modele.Bouee;
import modele.BoueeDAO;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;

public class VuePrincipale extends Application {
    private PanneauEnTete panneauHeader;
    private PanneauListe panneauListe;
    private PanneauModifierItem panneauModifierItem;
    private BorderPane panneauPrincipale;
    private PanneauAjouterItem panneauAjouterItem;
    private PanneauSupprimerItem panneauSupprimerItem;

    @Override
    public void start(Stage scenePrincipale) throws SQLException, ClassNotFoundException {
        ControleurVue.getInstance().setVuePrincipale(this);

        panneauHeader = new PanneauEnTete();
        panneauListe = new PanneauListe(this.construireListeBouee());

        panneauPrincipale = new BorderPane();

        Scene scene = new Scene(panneauPrincipale, 400, 600);

        panneauHeader.setPrefSize(scene.getWidth(), 30);
        panneauHeader.setStyle("-fx-background-color: #40A497");
        panneauListe.setPrefSize(scene.getWidth(), (scene.getHeight() - 30));
        panneauListe.setStyle("-fx-background-color: #279385");

        panneauPrincipale.setPrefSize(scene.getWidth(), scene.getHeight());
        panneauPrincipale.setTop(panneauHeader);
        panneauPrincipale.setCenter(panneauListe);

        scenePrincipale.setScene(scene);
        scenePrincipale.show();
    }

    public void construirePanneauModifierListe(Bouee bouee) {
        panneauModifierItem = new PanneauModifierItem(bouee);

        panneauPrincipale.setCenter(panneauModifierItem);
    }

    public void construirePanneauListe() throws SQLException {
        panneauListe = new PanneauListe(this.construireListeBouee());

        panneauPrincipale.setCenter(panneauListe);
    }

    public void construirePanneauAjouterItem() {
        panneauAjouterItem = new PanneauAjouterItem();
        ActionAjouterBouee actionAjouterBouee = new ActionAjouterBouee(panneauListe, panneauAjouterItem);
        panneauAjouterItem.setActionAjouterBouee(actionAjouterBouee);
        panneauPrincipale.setCenter(panneauAjouterItem);
    }

    public void construirePanneauSupprimerItem(int id) {
        panneauSupprimerItem = new PanneauSupprimerItem(id);
        panneauPrincipale.setCenter(panneauSupprimerItem);
    }

    public List<Bouee> construireListeBouee() throws SQLException {
        return BoueeDAO.getInstance().recupererListeBouee();
    }

}
