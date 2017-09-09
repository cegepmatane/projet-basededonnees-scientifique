package vue;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import modele.Bouee;

import java.sql.*;

public class PanneauListe extends Region {
    private ListView<PanneauItemListe> panneauListeItem;
    private VBox vBox;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/cegepmatane";

    static final String USER = "root";
    static final String PASS = "";

    public PanneauListe() {
        super();
        ConstruirePanneau();
    }

    private void ConstruirePanneau() {
        vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setPrefSize(400, (600 - 30));

        Button btnActionAjouterItem = new Button("Ajouter");
        btnActionAjouterItem.setPrefSize(200, 15);
        btnActionAjouterItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControleurVue.getInstance().actionAjouterItem();
            }
        });
        vBox.getChildren().add(btnActionAjouterItem);

        panneauListeItem = new ListView<PanneauItemListe>();
        panneauListeItem.setPrefSize(400, 600 - 30);
        construireVueListeItem();


        vBox.getChildren().add(panneauListeItem);
        this.getChildren().add(vBox);
    }

    private void construireVueListeItem() {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT idBouee FROM bouee";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int idBouee = rs.getInt("idBouee");


                panneauListeItem.getItems().add(new PanneauItemListe(new Bouee(idBouee, 15, 15, 51 ,51 ,51 ,51 ,51 ,51)));
                //Display values
                System.out.println("ID: " + idBouee);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void ajouterBoueeVueListe(int idBouee) {
        System.out.println("clicked on " + panneauListeItem.getSelectionModel().getSelectedItem());
    }
}
