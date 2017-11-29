package vue;

import java.sql.SQLException;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import modele.Bouee;

public class PanneauModifierItem extends Region {

    private TextField txtidBouee;
    private TextField txtlatitudeBouee;
    private TextField txtlongitudeBouee;
    private TextField txttemperatureEauBouee;
    private TextField txttemperatureAirBouee;
    private TextField txtsaliniteBouee;
    private TextField txtvitesseVentBouee;
    private TextField txtdimensionBouee;
    private TextField txtpressionAtmospheriqueBouee;

    int idBouee;
    int latitude;
    int longitude;
    int temperatureEau;
    int temperatureAir;
    float salinite;
    float vitesseVent;
    int dimension;
    float pressionAtmospherique;

    String sidBouee;
    String slatitude;
    String slongitude;
    String stemperatureEau;
    String stemperatureAir;
    String ssalinite;
    String svitesseVent;
    String sdimension;
    String spressionAtmospherique;
    
    public PanneauModifierItem(Bouee bouee) {
        super();

        ConstruirePanneau(bouee);
    }

    private void ConstruirePanneau(Bouee bouee) {

        this.setId("bleuClair");
        this.sidBouee = String.valueOf(bouee.getIdBouee());
        this.slatitude = String.valueOf(bouee.getLatitude());
        this.slongitude = String.valueOf(bouee.getLongitude());
        this.stemperatureEau = String.valueOf(bouee.getTemperatureEau());
        this.stemperatureAir = String.valueOf(bouee.getTemperatureAir());
        this.ssalinite = String.valueOf(bouee.getSalinite());
        this.svitesseVent = String.valueOf(bouee.getVitesseVent());
        this.sdimension = String.valueOf(bouee.getDimension());
        this.spressionAtmospherique = String.valueOf(bouee.getPressionAtmospherique());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label labelTitreModifierItem = new Label("Modifier");

        txtidBouee = new TextField();
        txtidBouee.setText(this.sidBouee);
        txtidBouee.setEditable(false);

        txtlatitudeBouee = new TextField();
        txtlatitudeBouee.setText(slatitude);

        txtlongitudeBouee = new TextField();
        txtlongitudeBouee.setText(slongitude);

        txttemperatureEauBouee = new TextField();
        txttemperatureEauBouee.setText(stemperatureEau);

        txttemperatureAirBouee = new TextField();
        txttemperatureAirBouee.setText(stemperatureAir);

        txtsaliniteBouee = new TextField();
        txtsaliniteBouee.setText(ssalinite);

        txtvitesseVentBouee = new TextField();
        txtvitesseVentBouee.setText(svitesseVent);

        txtdimensionBouee = new TextField();
        txtdimensionBouee.setText(sdimension);

        txtpressionAtmospheriqueBouee = new TextField();
        txtpressionAtmospheriqueBouee.setText(spressionAtmospherique);

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
                idBouee = Integer.parseInt(txtidBouee.getText());
                latitude = Integer.parseInt(txtlatitudeBouee.getText());
                longitude = Integer.parseInt(txtlongitudeBouee.getText());
                temperatureAir = Integer.parseInt(txttemperatureAirBouee.getText());
                temperatureEau = Integer.parseInt(txttemperatureEauBouee.getText());
                salinite = Float.parseFloat(txtsaliniteBouee.getText());
                vitesseVent = Float.parseFloat(txtvitesseVentBouee.getText());
                dimension = Integer.parseInt(txtdimensionBouee.getText());
                pressionAtmospherique = Float.parseFloat(txtpressionAtmospheriqueBouee.getText());

                bouee.setIdBouee(idBouee);
                bouee.setLatitude(latitude);
                bouee.setLongitude(longitude);
                bouee.setTemperatureAir(temperatureAir);
                bouee.setTemperatureEau(temperatureEau);
                bouee.setSalinite(salinite);
                bouee.setVitesseVent(vitesseVent);
                bouee.setDimension(dimension);
                bouee.setPressionAtmospherique(pressionAtmospherique);


                try {
                    ControleurVue.getInstance().actionSauvegarderBouee(bouee);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        grid.add(labelTitreModifierItem, 0, 0);

        addTextField(grid, txtidBouee, "Id de la bou�e : ", 0, 1);
        addTextField(grid, txtlatitudeBouee, "Latitude : ", 0, 2);
        addTextField(grid, txtlongitudeBouee, "Longitude : ", 0, 3);
        addTextField(grid, txttemperatureEauBouee, "Temp�rature de l'eau : ", 0, 4);
        addTextField(grid, txttemperatureAirBouee, "Temp�rature de l'air : ", 0, 5);
        addTextField(grid, txtsaliniteBouee, "Salinit� : ", 0, 6);
        addTextField(grid, txtvitesseVentBouee, "Vitesse : ", 0, 7);
        addTextField(grid, txtdimensionBouee, "Dimension : ", 0, 8);
        addTextField(grid, txtpressionAtmospheriqueBouee, "Pression atmosph�rique : ", 0, 9);

        grid.add(btnActionRetourEnArriere, 0, 10);
        grid.add(BtnActionSauvegardeeModification, 1, 10);

        this.getChildren().add(grid);
    }

    private void addTextField(GridPane grid, TextField textField, String texteLabel, int colonne, int ligne) {
        grid.add(new Label(texteLabel), colonne, ligne);
        grid.add(textField, colonne + 1, ligne);
    }

}
