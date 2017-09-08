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

public class PanneauModifierItem extends Region
{
	
	private TextField idBouee;
    private TextField latitudeBouee;
    private TextField longitudeBouee;
    private TextField temperatureEauBouee;
    private TextField temperatureAirBouee;
    private TextField saliniteBouee;
    private TextField vitesseVentBouee;
    private TextField dimensionBouee;
    private TextField pressionAtmospheriqueBouee;
	
	public PanneauModifierItem()
	{
		super();
		
		ConstruirePanneau();
	}

	private void ConstruirePanneau() 
	{
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Label labelTitreModifierItem = new Label("Modifier");
		
		idBouee = new TextField();
        latitudeBouee = new TextField();
        longitudeBouee = new TextField();
        temperatureEauBouee = new TextField();
        temperatureAirBouee = new TextField();
        saliniteBouee = new TextField();
        vitesseVentBouee = new TextField();
        dimensionBouee = new TextField();
        pressionAtmospheriqueBouee = new TextField();
		
		Button btnActionRetourEnArriere = new Button("Retour");
		btnActionRetourEnArriere.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				ControleurVue.getInstance().actionRetourEnArriere();
			}
		});
		
		Button BtnActionSauvegardeeModification = new Button("Sauvegarde");
		BtnActionSauvegardeeModification.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				//TODO: a faire Sauvegarde;
			}
		});
		
		grid.add(labelTitreModifierItem, 0, 0);
		
		addTextField(grid, idBouee, "Id de la bouée : ", 0, 1);
        addTextField(grid, latitudeBouee, "Latitude : ", 0, 2);
        addTextField(grid, longitudeBouee, "Longitude : ", 0, 3);
        addTextField(grid, temperatureEauBouee, "Température de l'eau : ", 0, 4);
        addTextField(grid, temperatureAirBouee, "Température de l'air : ", 0, 5);
        addTextField(grid, saliniteBouee, "Salinité : ", 0, 6);
        addTextField(grid, vitesseVentBouee, "Vitesse : ", 0, 7);
        addTextField(grid, dimensionBouee, "Dimension : ", 0, 8);
        addTextField(grid, pressionAtmospheriqueBouee, "Pression atmosphérique : ", 0, 9);
		
		grid.add(btnActionRetourEnArriere, 0, 10);
		grid.add(BtnActionSauvegardeeModification, 1, 10);
		
		this.getChildren().add(grid);
	}
	
	private void addTextField(GridPane grid, TextField textField,String texteLabel, int colonne, int ligne)
	{
        grid.add(new Label(texteLabel), colonne, ligne);
        grid.add(textField, colonne+1, ligne);
    }

}
