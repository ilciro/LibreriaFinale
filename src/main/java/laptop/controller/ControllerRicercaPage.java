package laptop.controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Raccolta;
import laptop.model.raccolta.Rivista;


public class ControllerRicercaPage {
	
	private LibroDao lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	private Libro l;
	private Giornale g;
	private Rivista riv;
	public ControllerRicercaPage() throws IOException {
		lD = new LibroDao();
		gD = new GiornaleDao();
		rD =new RivistaDao();
		l=new Libro();
		g=new Giornale();
		riv=new Rivista();

		ControllerSystemState.getInstance().setIsSearch(true);
		
	}
	
	public ObservableList<Raccolta> cercaPerTipo (String s) throws SQLException
	{
		ObservableList<Raccolta> r = null;

		switch(returnType())
		{
			case "libro":
				l.setTitolo(s);
				l.setAutore(s);
				r= lD.getLibriIdTitoloAutore(l);
				break;
			case "giornale":
				g.setEditore(s);
				g.setTitolo(s);
				r=gD.getGiornaliIdTitoloAutore(g);
				break;
			case "rivista":
				riv.setTitolo(s);
				riv.setAutore(s);
				r=rD.getRivisteIdTitoloAutore(riv);
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
