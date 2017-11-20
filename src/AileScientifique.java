import controleur.ControleurVue;
import vue.Test;
import vue.VuePrincipale;

public class AileScientifique 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/portmatane";
	
	static final String USER = "root";
	static final String PASS = "";
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) 
	{
		
		//VuePrincipale vuePrincipale = new VuePrincipale();
		//vuePrincipale.launch(VuePrincipale.class, args);
		
		Test test = new Test();								 
		Test.launch(Test.class, args);
		
		//ControleurVue controleur = new ControleurVue();
		//controleur.setVuePrincipale(vuePrincipale);
	}
}
