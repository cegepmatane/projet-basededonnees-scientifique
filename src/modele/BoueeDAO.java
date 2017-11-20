package modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BoueeDAO {

    static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/portmatane";
    static final String DB_UTILISATEUR = "root";
    static final String DB_MOTDEPASSE = "";

    private static BoueeDAO instance = null;

    public static BoueeDAO getInstance(){
        if (instance == null) try {
            instance = new BoueeDAO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }

    private Connection connection;

    public BoueeDAO() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection(DB_URL, DB_UTILISATEUR, DB_MOTDEPASSE);
    }

    /*
    public List<Bouee> recupererListeBouee() {
        List<Bouee> listeBouees = new ArrayList<>();

        System.out.println("Create statement...");
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM bouee";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int idBouee = rs.getInt("idBouee");
                int latitude = rs.getInt("latitude");
                int longitude = rs.getInt("longitude");
                int temperatureEau = rs.getInt("temperatureEau");
                int temperatureAir = rs.getInt("temperatureAir");
                float salinite = rs.getInt("salinite");
                float vitesseVent = rs.getInt("vitesseVent");
                int dimension = rs.getInt("dimension");
                float pressionAtmospherique = rs.getInt("pressionAtmospherique");

                Bouee bouee = new Bouee(idBouee, latitude, longitude, temperatureEau, temperatureAir, salinite, vitesseVent, dimension, pressionAtmospherique);

                listeBouees.add(bouee);
            }
            return listeBouees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/
    
    public ObservableList<Bouee> recupererListeBouee() {
        ObservableList<Bouee> listeBouees = FXCollections.observableArrayList();

        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT idBouee, latitude, longitude, temperatureEau, temperatureAir, salinite, vitesseVent, dimension, pressionAtmospherique FROM bouee";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int idBouee = rs.getInt("idBouee");
                int latitude = rs.getInt("latitude");
                int longitude = rs.getInt("longitude");
                int temperatureEau = rs.getInt("temperatureEau");
                int temperatureAir = rs.getInt("temperatureAir");
                int salinite = rs.getInt("salinite");
                int vitesseVent = rs.getInt("vitesseVent");
                int dimension = rs.getInt("dimension");
                int pressionAtmospherique = rs.getInt("pressionAtmospherique");

                listeBouees.add(new Bouee(idBouee, latitude, longitude, temperatureEau, temperatureAir, salinite, vitesseVent, dimension, pressionAtmospherique));
            }

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listeBouees;
    }

    public void supprimerBouee(int id) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sqlSupprimer = "DELETE FROM bouee WHERE idBouee = " + id;
            stmt.executeUpdate(sqlSupprimer); //updateQuery
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierBouee(Bouee bouee) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "UPDATE `bouee` " +
                    "SET `idBouee`=" + bouee.getIdBouee() + "," +
                    "`latitude`=" + bouee.getLatitude() + "," +
                    "`longitude`=" + bouee.getLongitude() + "," +
                    "`temperatureEau`=" + bouee.getTemperatureEau() + "," +
                    "`temperatureAir`=" + bouee.getTemperatureAir() + "," +
                    "`salinite`=" + bouee.getSalinite() + "," +
                    "`vitesseVent`=" + bouee.getVitesseVent() + "," +
                    "`dimension`=" + bouee.getDimension() + "," +
                    "`pressionAtmospherique`=" + bouee.getPressionAtmospherique() + " " +
                    "WHERE `idBouee`= " + bouee.getIdBouee();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterBouee(Bouee bouee) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sqlAjouter = "INSERT INTO `bouee`(`idBouee`, `latitude`, `longitude`, `temperatureEau`, `temperatureAir`, `salinite`, `vitesseVent`, `dimension`, `pressionAtmospherique`) " +
                    "VALUES (NULL,"+bouee.getLatitude()+"," +
                    ""+bouee.getLongitude()+"," +
                    ""+bouee.getTemperatureEau()+"," +
                    ""+bouee.getTemperatureAir()+"," +
                    ""+bouee.getSalinite()+"," +
                    ""+bouee.getVitesseVent()+"," +
                    ""+bouee.getDimension()+"," +
                    ""+bouee.getPressionAtmospherique()+")";
            stmt.executeUpdate(sqlAjouter);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
