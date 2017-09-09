package vue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import controleur.ControleurVue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import modele.Bouee;

public class PanneauListe extends Region
{
	private ListView<PanneauItemListe> panneauListeItem;
	private List<Bouee> listbouee;
	
	
	
	public PanneauListe(List<Bouee> listbouee)
	{	
		super();
		
		this.listbouee = listbouee;
		
		ConstruirePanneau();

	}

	private void ConstruirePanneau() 
	{
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.setPrefSize(400, (600-30));
		
		Button btnActionAjouterItem = new Button("Ajouter");
		btnActionAjouterItem.setPrefSize(200, 15);
		btnActionAjouterItem.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				ControleurVue.getInstance().actionAjouterItem();
			}
		});
		vBox.getChildren().add(btnActionAjouterItem);
		
		panneauListeItem = new ListView<PanneauItemListe>();
		panneauListeItem.setPrefSize(400, 600 - 30);
		construireVueListeItem(listbouee);
		
		
		vBox.getChildren().add(panneauListeItem);
		this.getChildren().add(vBox);
	}
	
	private void construireVueListeItem(List<Bouee> listbouee)
	{
		for(Bouee bouee : listbouee)
		{
			panneauListeItem.getItems().add(new PanneauItemListe(bouee.toString()));
		}
	}
	
	
}
