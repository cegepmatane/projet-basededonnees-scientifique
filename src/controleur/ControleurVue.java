package controleur;

import java.sql.SQLException;


import modele.Bouee;
import modele.Modele;
import vue.VuePrincipale;

public class ControleurVue 
{
	protected static ControleurVue instance;
	private VuePrincipale vuePrincipale = null;
	private Modele modele;
	
	
	
	public static ControleurVue getInstance()
	{
		if(instance == null) instance = new ControleurVue();
		return instance;
	}
	
	public void setVuePrincipale(VuePrincipale vuePrincipale)
	{
		this.vuePrincipale = vuePrincipale;
	}

	public void actionModifierItem(Bouee bouee) 
	{
		this.vuePrincipale.construirePanneauModifierListe(bouee);
	}

	public void actionRetourEnArriere() throws SQLException 
	{
		this.vuePrincipale.construirePanneauListe();
	}

	public void actionAjouterItem() 
	{
		this.vuePrincipale.construirePanneauAjouterItem();
	}
	
	public void actionSupprimerItem()
	{
		this.vuePrincipale.construirePanneauSupprimerItem();
	}
	
	public void actionSauvegarderBouee(Bouee bouee) throws SQLException, ClassNotFoundException
	{
		modele = new Modele();
		modele.sauvegarderBouee(bouee);
	}
	
	
}
