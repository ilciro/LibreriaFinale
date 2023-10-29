package laptop.controller;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Raccolta;


public class ControllerRicercaPage {
	
	private LibroDao lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	public ControllerRicercaPage()
	{
		lD = new LibroDao();
		gD = new GiornaleDao();
		rD =new RivistaDao();
		ControllerSystemState.getInstance().setIsSearch(true);
		
	}
	
	public ObservableList<Raccolta> cercaPerTipo (String s) throws SQLException
	{
		ObservableList<Raccolta> r = null;

		switch(ControllerSystemState.getInstance().getType())
		{
			case "libro":
				r= lD.getLibriByName(s);
				break;
			case "giornale":
				r=gD.getGiornaliByName(s);
				break;
			case "rivista":
				r=rD.getRivisteByName(s);
				break;
			default:return r;

		}


		
		return r;
		
	}
	
	public String returnType()
	{
		return ControllerSystemState.getInstance().getType();
	}
	
	
}
