package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

public class PanneauEnTete extends Region
{
	public PanneauEnTete()
	{
		super();
		
		ConstruirePanneau();
	}

	private void ConstruirePanneau() 
	{
		HBox hBox = new HBox();
		
		Label labelNomProgramme = new Label();
		labelNomProgramme.setId("labelNomProgramme");
		labelNomProgramme.setText("Liste des bouées");
		labelNomProgramme.setFont(Font.font("27332B_2_0"));
		hBox.getChildren().add(labelNomProgramme);
		
		//TODO: add Logo;
		
		this.getChildren().add(hBox);
	}
}
