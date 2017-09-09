package vue;

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

import java.sql.*;

public class PanneauAjouterItem extends Region {

    private TextField latitudeBouee;
    private TextField longitudeBouee;
    private TextField temperatureEauBouee;
    private TextField temperatureAirBouee;
    private TextField saliniteBouee;
    private TextField vitesseVentBouee;
    private TextField dimensionBouee;
    private TextField pressionAtmospheriqueBouee;

    public PanneauAjouterItem() {
        super();
        ConstruirePanneau();
    }

    private void ConstruirePanneau() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label labelTitreAjouterItem = new Label("Ajouter une bouée :");

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
                ControleurVue.getInstance().actionRetourEnArriere();
            }
        });

        Button BtnActionSauvegardeeModification = new Button("Sauvegarde");
        BtnActionSauvegardeeModification.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO: a faire Sauvegarde;
                int latitude = 0, longitude = 0, temperatureEau = 0, temperatureAir = 0, dimension = 0;
                float salinite = 0, vitesseVent = 0, pressionAtmospherique = 0;
                try {
                    latitude = Integer.parseInt(latitudeBouee.getText());
                    longitude = Integer.parseInt(longitudeBouee.getText());
                    temperatureEau = Integer.parseInt(temperatureEauBouee.getText());
                    temperatureAir = Integer.parseInt(temperatureAirBouee.getText());
                    salinite = Float.parseFloat(saliniteBouee.getText());
                    vitesseVent = Float.parseFloat(vitesseVentBouee.getText());
                    dimension = Integer.parseInt(dimensionBouee.getText());
                    pressionAtmospherique = Float.parseFloat(pressionAtmospheriqueBouee.getText());
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }

                Bouee bouee = new Bouee(latitude, longitude, temperatureEau, temperatureAir, salinite, vitesseVent, dimension, pressionAtmospherique);
                Connection conn = null;
                Statement stmt = null;
                try{
                    //STEP 2: Register JDBC driver
                    Class.forName("com.mysql.jdbc.Driver");

                    //STEP 3: Open a connection
                    System.out.println("Connecting to database...");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cegepmatane","root","");

                    //STEP 4: Execute a query
                    System.out.println("Creating statement...");
                    stmt = conn.createStatement();
                    String sql;
                    sql = "INSERT INTO bouee VALUES("+bouee.getIdBouee()+", "+latitude+", "+longitude+", "+temperatureEau+", "
                            +temperatureAir+", "+salinite+", "+vitesseVent+","+dimension+", "+pressionAtmospherique+");";
                    stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close();
                }catch(SQLException se){
                    //Handle errors for JDBC
                    se.printStackTrace();
                }catch(Exception e){
                    //Handle errors for Class.forName
                    e.printStackTrace();
                }finally{
                    //finally block used to close resources
                    try{
                        if(stmt!=null)
                            stmt.close();
                    }catch(SQLException se2){
                    }// nothing we can do
                    try{
                        if(conn!=null)
                            conn.close();
                    }catch(SQLException se){
                        se.printStackTrace();
                    }//end finally try
                }//end try
                System.out.println("Goodbye!");
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
}
