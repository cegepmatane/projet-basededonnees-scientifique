package vue;

import java.sql.SQLException;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import modele.BoueeDAO;

public class PanneauSupprimerItem extends Region {

    private int id;

    public PanneauSupprimerItem(int id) {
        super();
        this.id = id;
        construirePanneau();
    }

    private void construirePanneau()
    {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label labelTitreModifierItem = new Label("Supprimer");

        Button btnActionRetourEnArriere = new Button("Annuler");
        btnActionRetourEnArriere.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try {
					ControleurVue.getInstance().actionRetourEnArriere();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        Button BtnActionSauvegardeeModification = new Button("Supprimer");
        BtnActionSauvegardeeModification.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                BoueeDAO.getInstance().supprimerBouee(id);
                try {
                    ControleurVue.getInstance().actionRetourEnArriere();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //TODO: a faire Sauvegarde;
            }
        });

        grid.add(labelTitreModifierItem, 0, 0);
        grid.add(btnActionRetourEnArriere, 0, 1);
        grid.add(BtnActionSauvegardeeModification, 2, 1);

        this.getChildren().add(grid);
    }
}
