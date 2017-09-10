package vue;

import java.sql.*;

import controleur.ActionAjouterBouee;
import controleur.ControleurVue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modele.Bouee;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;

public class VuePrincipale extends Application
{
	private PanneauHeader panneauHeader;
	private PanneauListe panneauListe;
	private PanneauModifierItem panneauModifierItem;
	private BorderPane panneauPrincipale;
	private PanneauAjouterItem panneauAjouterItem;
	private PanneauSupprimerItem panneauSupprimerItem;
	private Connection conn;
	
	static final String DB_URL = "jdbc:mysql://localhost/portmatane";
	
	static final String USER = "root";
	static final String PASS = "";

	@Override
	public void start(Stage scenePrincipale) throws SQLException, ClassNotFoundException
	{
		ControleurVue.getInstance().setVuePrincipale(this);
		
		conn = null;
		   Statement stmt = null;
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		
		panneauHeader = new PanneauHeader();
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
	
	public void construirePanneauModifierListe()
	{
		panneauModifierItem = new PanneauModifierItem();
		
		panneauPrincipale.setCenter(panneauModifierItem);
	}

	public void construirePanneauListe() throws SQLException
	{
		panneauListe = new PanneauListe(this.construireListeBouee());
		
		panneauPrincipale.setCenter(panneauListe);
	}

	public void construirePanneauAjouterItem() 
	{
		panneauAjouterItem = new PanneauAjouterItem();
        ActionAjouterBouee actionAjouterBouee = new ActionAjouterBouee(panneauListe, panneauAjouterItem);
        panneauAjouterItem.setActionAjouterBouee(actionAjouterBouee);
		panneauPrincipale.setCenter(panneauAjouterItem);
	}
	
	public void construirePanneauSupprimerItem()
	{
		panneauSupprimerItem = new PanneauSupprimerItem();
		panneauPrincipale.setCenter(panneauSupprimerItem);
	}
	
	public List<Bouee> construireListeBouee() throws SQLException
	{
		
		System.out.println("Creating statement...");
	      Statement stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT * FROM bouee";
	      ResultSet rs = stmt.executeQuery(sql);
	      List<Bouee> listBouee = new ArrayList<Bouee>();

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	    	  
	    	 Bouee bouee = new Bouee(0, 0, 0, 0, 0, 0, 0, 0);
	         //Retrieve by column name
	         int idBouee  = rs.getInt("idBouee");
	         int latitude = rs.getInt("latitude");
	         int longitude  = rs.getInt("longitude");
	         int temperatureEau = rs.getInt("temperatureEau");
	         int temperatureAir  = rs.getInt("temperatureAir");
	         int salinite = rs.getInt("salinite");
	         int vitesseVent  = rs.getInt("vitesseVent");
	         int dimension = rs.getInt("dimension");
	         int pressionAtmospherique = (int) rs.getFloat("pressionAtmospherique");

	         bouee.setIdBouee(idBouee);
	         bouee.setLatitude(latitude);
	         bouee.setLongitude(longitude);
	         bouee.setTemperatureEau(temperatureEau);
	         bouee.setTemperatureAir(temperatureAir);
	         bouee.setSalinite(salinite);
	         bouee.setVitesseVent(vitesseVent);
	         bouee.setDimension(dimension);
	         bouee.setPressionAtmospherique(pressionAtmospherique);
	         
	         listBouee.add(bouee);
	      }
		
		return listBouee;
		
	}

}
