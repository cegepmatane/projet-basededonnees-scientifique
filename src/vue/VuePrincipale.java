package vue;

import java.sql.*;

import controleur.ActionAjouterBouee;
import controleur.ControleurVue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modele.Bouee;
import modele.BoueeDAO;

import java.util.List;

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

        Font.loadFont(VuePrincipale.class.getResource("police/policeTitre.ttf").toExternalForm(), 10);
        Font.loadFont(VuePrincipale.class.getResource("police/policeTexte.ttf").toExternalForm(), 10);

        panneauHeader = new PanneauEnTete();
        panneauListe = new PanneauListe(this.construireListeBouee());

        panneauPrincipale = new BorderPane();

        Scene scene = new Scene(panneauPrincipale, 400, 600);

        scene.getStylesheets().add("style.css");

        panneauHeader.setPrefSize(scene.getWidth(), 30);
        panneauListe.setPrefSize(scene.getWidth(), (scene.getHeight() - 30));

        panneauPrincipale.setPrefSize(scene.getWidth(), scene.getHeight());
        panneauPrincipale.setTop(panneauHeader);
        panneauPrincipale.setCenter(panneauListe);

        panneauHeader.setId("bleuFonce");
        panneauListe.setId("bleuClair");

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
