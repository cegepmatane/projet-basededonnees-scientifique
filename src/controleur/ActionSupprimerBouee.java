package controleur;

import vue.PanneauAjouterItem;
import vue.PanneauListe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ActionSupprimerBouee {

    private PanneauListe panneauListe;
    private PanneauAjouterItem panneauAjouterItem;

    public ActionSupprimerBouee(PanneauListe panneauListe, PanneauAjouterItem panneauAjouterItem) {
        this.panneauListe = panneauListe;
        this.panneauAjouterItem = panneauAjouterItem;
    }

    public boolean supprimerBouee(int id){
    	
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
            sql = "DELETE FROM `bouee` WHERE idBouee='"+ id +"'";
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
