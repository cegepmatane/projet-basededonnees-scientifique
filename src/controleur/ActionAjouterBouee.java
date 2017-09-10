package controleur;

import modele.Bouee;
import vue.PanneauAjouterItem;
import vue.PanneauListe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
        }catch (NumberFormatException e){
            e.printStackTrace();
            panneauAjouterItem.afficherErreur("Erreur de format", "Vous devez entrer des nombres.");
            return false;
        }

        Bouee bouee = new Bouee(latitude, longitude, temperatureEau, temperatureAir, salinite, vitesseVent, dimension, pressionAtmospherique);
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/portmatane","root","");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO `bouee` (`idBouee`, `latitude`, `longitude`, `temperatureEau`, `temperatureAir`, `salinite`, `vitesseVent`, `dimension`, `pressionAtmospherique`)" +
                    " VALUES (NULL, '"+latitude+"', '"+longitude+"', '"+temperatureEau+"', '"+temperatureAir+"', '"+salinite+"', '"+vitesseVent+"', '"+dimension+"', '"+pressionAtmospherique+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            return true;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
            return false;
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
        return false;
    }

}
