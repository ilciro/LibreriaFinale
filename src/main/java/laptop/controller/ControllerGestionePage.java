package laptop.controller;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Raccolta;
import laptop.model.raccolta.Rivista;



public class ControllerGestionePage {
	private RivistaDao rD;
	private LibroDao lD;
	private GiornaleDao gD;
	private Libro l;
	private Giornale g;
	private Rivista r;
	private static final String LIBRO="libro";
	private static final String GIORNALE="giornale";
	private static final String RIVISTA="rivista";

	public void cancella(int id) throws SQLException {
		if (ControllerSystemState.getInstance().getType().equals(LIBRO)) {
			l.setId(id);
			lD.cancella(l);
		} else if (ControllerSystemState.getInstance().getType().equals(GIORNALE)) {
			g.setId(id);
			gD.cancella(g);
		} else if (ControllerSystemState.getInstance().getType().equals(RIVISTA)) {
			r.setId(id);
			rD.cancella(r);
		}
	}


	public ObservableList<Raccolta> getLista(String type) throws SQLException {
		ObservableList<Raccolta> catalogo = FXCollections.observableArrayList();
		switch (type) {
			case LIBRO:
				catalogo.addAll(lD.getLibroSingolo());
				break;
			case GIORNALE:
				catalogo.addAll( gD.getGiornaleSingolo());
				break;
			case RIVISTA:
				catalogo.addAll( rD.getRivistaSingolo());
				break;
			default:
				return catalogo;

		}
		return catalogo;
	}


	
	public ControllerGestionePage()
	{
		rD=new RivistaDao();
		gD=new GiornaleDao();
		lD=new LibroDao();
		l=new Libro();
		g=new Giornale();
		r=new Rivista();
	}
	public String settaHeader()
	{
		String s=null;
		if(ControllerSystemState.getInstance().getType().equals(LIBRO))
		{
			s="Benvenuto nella schermata dei libri";
		}
		else if(ControllerSystemState.getInstance().getType().equals(GIORNALE))
		{
			s="Benvenuto nella schermata dei giornali";
		}
		else if(ControllerSystemState.getInstance().getType().equals(RIVISTA))
		{
			s="Benvenuto nella schermata dele riviste";
		}
		return s;
	}
}
