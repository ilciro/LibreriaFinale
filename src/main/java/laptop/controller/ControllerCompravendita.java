package laptop.controller;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.exception.IdException;
import laptop.model.User;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Raccolta;
import laptop.model.raccolta.Rivista;



public class ControllerCompravendita {
	//insrt comment
	private LibroDao lD;
	private User u = User.getInstance();
	private GiornaleDao gD;
	private Giornale g;
	private Libro l;
	private Rivista r;
	private RivistaDao rD;
	private boolean status = false;
	private static final String LIBRO = "libro";
	private static final String RIVISTA = "rivista";
	private static final String GIORNALE = "giornale";


	public boolean disponibilita(String type, String i) throws SQLException, IdException {
		switch (type)
		{
			case LIBRO:

				l.setId(Integer.parseInt(i));
				status=lD.checkDisp(l);
				break;
			case GIORNALE:
				g.setId(Integer.parseInt(i));
				status=gD.checkDisp(g);
				break;
			case RIVISTA:
				r.setId(Integer.parseInt(i));
				status=rD.checkDisp(r);
				break;
			default: checkID(Integer.parseInt(i));

		}
		return status;
	}

	public ControllerCompravendita() {
		lD = new LibroDao();
		gD=new GiornaleDao();
		g=new Giornale();
		r=new Rivista();
		l=new Libro();
		rD=new RivistaDao();
	}

	private void checkID(int id) throws IdException {
		if(id<=0)
		{
			throw new IdException("id not correct");
		}
	}

	public ObservableList<Raccolta> getLista(String type) throws SQLException{
		ObservableList<Raccolta> catalogo= FXCollections.observableArrayList();
		switch (type)
		{
			case LIBRO:
				catalogo.add((Raccolta) lD.getLibri());
				break;
			case GIORNALE:
				catalogo.add((Raccolta) gD.getGiornali());
				break;
			case RIVISTA:
				catalogo.add((Raccolta) rD.getRiviste());
				break;
			default:
				return catalogo;

		}
		return catalogo;

	}


	
	/*
	 * Metodo udato per tornare tipo utente in base a se � loggato o no

	 */
	public String retTipoUser()
	{
		return u.getIdRuolo();
	}
	//usato nel caso se non � loggato-> "utente"
	public void setTipoUser(String ruolo)
	{
		u.setIdRuolo(ruolo);	
	}

	public String ritornaMessaggio()
	{
		String s=null;
		if(ControllerSystemState.getInstance().getType().equals(LIBRO))
			s="Benvenuto... ecco la lista dei libri nel nostro catalogo...";
		else if(ControllerSystemState.getInstance().getType().equals(GIORNALE))
			s="Benvenuto... ecco la lista dei giornali nel nostro catalogo...";
		else if(ControllerSystemState.getInstance().getType().equals(RIVISTA))
			s="Benvenuto... ecco la lista dele riviste nel nostro catalogo...";
		return s;
	}
	public String popolaBottoneV()
	{
		String s=null;
		if(ControllerSystemState.getInstance().getType().equals(LIBRO))
			s="Mostra Libro";
		else if(ControllerSystemState.getInstance().getType().equals(GIORNALE))
			s="Mostra Giornale";
		else if(ControllerSystemState.getInstance().getType().equals(RIVISTA))
			s="Mostra Rivista";
		return s;
	}
	public String popolaBottoneA()
	{
		String s=null;
		if(ControllerSystemState.getInstance().getType().equals(LIBRO))
			s="Acquista Libro";
		else if(ControllerSystemState.getInstance().getType().equals(GIORNALE))
			s="Acquista Giornale";
		else if(ControllerSystemState.getInstance().getType().equals(RIVISTA))
			s="Acquista Rivista";
		return s;
	}




	
}
