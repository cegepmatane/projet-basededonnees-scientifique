package vue;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import modele.Bouee;

public class PanneauItemListe extends Region
{
	private HBox itemBoite;
	private Bouee bouee;
	
	public PanneauItemListe(Bouee bouee)
	{
		super();
		this.bouee = bouee;
		ConstruirePanneau();
	}

	private void ConstruirePanneau() 
	{
		itemBoite = new HBox();
		
		Label label = new Label(bouee.getIdBouee()+"");
		itemBoite.getChildren().add(label);
		
		Button btnActionModifier = new Button("Modifier");
		btnActionModifier.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				ControleurVue.getInstance().actionModifierItem();
			}
		});
		itemBoite.getChildren().add(btnActionModifier);
		
		Button btnActionSupprimer = new Button("Supprimer");
		btnActionSupprimer.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				ControleurVue.getInstance().actionSupprimerItem();
			}
		});
		itemBoite.getChildren().add(btnActionSupprimer);
		
		this.getChildren().add(itemBoite);
	}

}
