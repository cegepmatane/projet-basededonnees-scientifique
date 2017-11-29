package vue;

import controleur.ActionAjouterBouee;
import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;


import java.sql.SQLException;


public class PanneauAjouterItem extends Region {

    private TextField latitudeBouee;
    private TextField longitudeBouee;
    private TextField temperatureEauBouee;
    private TextField temperatureAirBouee;
    private TextField saliniteBouee;
    private TextField vitesseVentBouee;
    private TextField dimensionBouee;
    private TextField pressionAtmospheriqueBouee;

    private ActionAjouterBouee actionAjouterBouee;

    public PanneauAjouterItem() {
        super();
        ConstruirePanneau();
    }

    private void ConstruirePanneau() {
        GridPane grid = new GridPane();
        this.setId("bleuClair");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label labelTitreAjouterItem = new Label("Ajouter une bou�e :");

        /* Création des TextField */
        latitudeBouee = new TextField();
        longitudeBouee = new TextField();
        temperatureEauBouee = new TextField();
        temperatureAirBouee = new TextField();
        saliniteBouee = new TextField();
        vitesseVentBouee = new TextField();
        dimensionBouee = new TextField();
        pressionAtmospheriqueBouee = new TextField();

        Button btnActionRetourEnArriere = new Button("Retour");
        btnActionRetourEnArriere.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
					ControleurVue.getInstance().actionRetourEnArriere();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        Button BtnActionSauvegardeeModification = new Button("Sauvegarde");
        BtnActionSauvegardeeModification.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO: a faire Sauvegarde;
                if(!actionAjouterBouee.ajouterBouee()){
                    afficherErreur("Erreur de format.", "Entrez des nombres.");
                }else{
                    try {
                        ControleurVue.getInstance().actionRetourEnArriere();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        grid.add(labelTitreAjouterItem, 0, 0);

        addTextField(grid, latitudeBouee, "Latitude : ", 0, 1);
        addTextField(grid, longitudeBouee, "Longitude : ", 0, 2);
        addTextField(grid, temperatureEauBouee, "Température de l'eau : ", 0, 3);
        addTextField(grid, temperatureAirBouee, "Température de l'air : ", 0, 4);
        addTextField(grid, saliniteBouee, "Salinité : ", 0, 5);
        addTextField(grid, vitesseVentBouee, "Vitesse : ", 0, 6);
        addTextField(grid, dimensionBouee, "Dimension : ", 0, 7);
        addTextField(grid, pressionAtmospheriqueBouee, "Pression atmosphérique : ", 0, 8);


        grid.add(btnActionRetourEnArriere, 0, 9);
        grid.add(BtnActionSauvegardeeModification, 1, 9);

        this.getChildren().add(grid);
    }

    private void addTextField(GridPane grid, TextField textField,String texteLabel, int colonne, int ligne){
        grid.add(new Label(texteLabel), colonne, ligne);
        grid.add(textField, colonne+1, ligne);
    }

    public void afficherErreur(String titre, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public String getLatitudeBouee() {
        return latitudeBouee.getText();
    }

    public String getLongitudeBouee() {
        return longitudeBouee.getText();
    }

    public String getTemperatureEauBouee() {
        return temperatureEauBouee.getText();
    }

    public String getTemperatureAirBouee() {
        return temperatureAirBouee.getText();
    }

    public String getSaliniteBouee() {
        return saliniteBouee.getText();
    }

    public String getVitesseVentBouee() {
        return vitesseVentBouee.getText();
    }

    public String getDimensionBouee() {
        return dimensionBouee.getText();
    }

    public String getPressionAtmospheriqueBouee() {
        return pressionAtmospheriqueBouee.getText();
    }

    public void setActionAjouterBouee(ActionAjouterBouee actionAjouterBouee) {
        this.actionAjouterBouee = actionAjouterBouee;
    }
}
