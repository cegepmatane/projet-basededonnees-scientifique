package vue;

import java.awt.event.MouseEvent;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import modele.Bouee;

public class PanneauItemListe extends Region
{
    private String nomItem;
    private HBox itemBoite;
	private int id;
	private Bouee bouee;
	
	public PanneauItemListe(String nomItem, Bouee bouee)
	{
		super();
		this.bouee = bouee;
		id = bouee.getIdBouee();
		this.nomItem = nomItem;
		
		ConstruirePanneau(bouee);
	}

	private void ConstruirePanneau(Bouee bouee) 
	{
		itemBoite = new HBox();
		
		Label label = new Label(this.nomItem);
		label.setId("noir");
		itemBoite.getChildren().add(label);
		
		Button btnActionModifier = new Button("Modifier");
		btnActionModifier.setOnAction(new EventHandler<ActionEvent>() 
		{
			

			@Override
			public void handle(ActionEvent event)
			{
				ControleurVue.getInstance().actionModifierItem(bouee);
			}
		});
		itemBoite.getChildren().add(btnActionModifier);
		
		Button btnActionSupprimer = new Button("Supprimer");
		btnActionSupprimer.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				ControleurVue.getInstance().actionSupprimerItem(id);
			}
		});
		itemBoite.getChildren().add(btnActionSupprimer);
		
		itemBoite.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				Alert alert = new Alert(AlertType.INFORMATION);
				
				alert.setTitle("bouee " + bouee.getIdBouee());
				
				alert.setHeaderText("Informations de la bouee");
				
				String s =bouee.toString();
				
				alert.setContentText(s);
				
				alert.show();

			}
        });
		
		this.getChildren().add(itemBoite);
	}

}
