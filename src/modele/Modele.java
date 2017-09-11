package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modele 
{
	
	private Connection conn;
	static final String DB_URL = "jdbc:mysql://localhost/portmatane";
	
	static final String USER = "root";
	static final String PASS = "";
	
	
	public Modele() throws ClassNotFoundException, SQLException
	{
		conn = null;
		Statement stmt = null;
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      
	      System.out.println("modele creer");
	}
	
	public void sauvegarderBouee(Bouee bouee) throws SQLException
	{
		//System.out.println("modele: sauvegarde, " + bouee.getIdBouee() + " longitude:" + bouee.getLongitude());
		
		
	      Statement stmt = conn.createStatement();
	      String sql;
	      sql = "UPDATE bouee SET latitude= " + bouee.getLatitude() +  ", longitude= " + bouee.getLongitude()  + ", temperatureEau= " + bouee.getTemperatureEau() + ", temperatureAir= " + bouee.getTemperatureAir() + ", salinite= " + bouee.getSalinite() + ", vitesseVent= " + bouee.getVitesseVent() + ", dimension= " + bouee.getDimension() + ", pressionAtmospherique= " + bouee.getPressionAtmospherique() + " WHERE idBouee = " + bouee.getIdBouee();
	      System.out.println(sql);
	      stmt.executeUpdate(sql);
	}
}
