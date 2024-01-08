package laptop.controller;

import java.io.IOException;
import java.sql.SQLException;



import laptop.database.GiornaleDao;
import laptop.database.LibroDao;

import laptop.database.RivistaDao;
import laptop.exception.AcquistaException;
import laptop.exception.IdException;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;



public class ControllerAcquista {
	
	// Levatoil agamento in quanto lo faccio dopo a seconda del tipo
	 

	private LibroDao lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	private Libro l;
	private Giornale g;
	private Rivista r;
	private static ControllerSystemState vis = ControllerSystemState.getInstance() ;
	private String name;

	private float costo;//aggiunto per costo (vedere metodo in fondo ((getCosto()))

	private static final String LIBRO = "libro";
	private static final String RIVISTA="rivista";
	private static final String GIORNALE="giornale";
	private int disp;

	

	public float totale1 (String type,String titolo,int disp,int quantita) throws SQLException, IdException {
		float x;
		switch (type)
		{
			case LIBRO:
			{
				checkID(vis.getId());
				l.setTitolo(titolo);
				l.setNrCopie(quantita);
				vis.setQuantita(quantita);
				 x = lD.getData(l).getPrezzo();
				lD.aggiornaDisponibilita(l);
				break;
			}
			case GIORNALE:
			{
				checkID(vis.getId());
				g.setTitolo(titolo);
				g.setId(vis.getId());
				g.setCopieRimanenti(quantita);
				vis.setQuantita(disp);
				x = gD.getData(g).getPrezzo();
				gD.aggiornaDisponibilita(g);
				break;
			}
			case RIVISTA:
			{
				checkID(vis.getId());
				r.setTitolo(titolo);
				r.setId(vis.getId());
				r.setCopieRim(quantita);
				vis.setQuantita(disp);
				x= rD.getData(r).getPrezzo();
				rD.aggiornaDisponibilita(r);
				break;
			}
			default : throw new IdException("id incorrect");

		}
		return x;
	}
	private void checkID(int id) throws IdException {

		if(id <=0 || id>30)
		{
			throw  new IdException("wrong id");
		}

	}



	public ControllerAcquista() throws IOException {
		lD = new LibroDao();
		gD = new GiornaleDao();
		rD = new RivistaDao();
		l = new Libro();
		g = new Giornale();
		r = new Rivista();
		
		

	}


	public void inserisciAmmontare(String type,int i) throws AcquistaException, IdException {
		int rimanenza;
		vis.setId(i);
		switch(type)
		{
			case LIBRO:

				l.setId(vis.getId());
				rimanenza=lD.getData(l).getNrCopie();
				checkRimanenza(rimanenza);
				break;
			case GIORNALE:

				g.setId(vis.getId());
				rimanenza=gD.getData(g).getCopieRimanenti();
				checkRimanenza(rimanenza);
				break;
			case RIVISTA:

				r.setId(vis.getId());
				rimanenza=rD.getData(r).getCopieRim();
				checkRimanenza(rimanenza);
				break;
			default: throw new IdException("incorrect id");


		}
	}

	private void checkRimanenza(int quantita) throws AcquistaException {
		if(quantita<0)
		{
			throw new AcquistaException("rimanenza <0");
		}
	}

	public String getNomeById() throws SQLException
	{
		
		int id = vis.getId();
		String type =vis.getType();
        switch (type) {
            case LIBRO -> {
                l.setId(id);
                name = lD.getData(l).getTitolo();
            }
            case GIORNALE -> {
                g.setId(id);
                name = gD.getData(g).getTitolo();
            }
            case RIVISTA -> {
                r.setId(id);
                name = rD.getData(r).getTitolo();
            }
        }
		return name ;
	}

	/*
	 * metodo aggiunto per stampare appena carica la schermata anche il costo di 
	 * ogni singolo elemento(giornale,rivista o lbro)
	 */
	 
	public float getCosto(String type) throws SQLException, IdException {

		int id = vis.getId();
		checkID(id);
		switch (type)
		{
			case LIBRO :
				l.setId(id);
				costo=lD.getData(l).getPrezzo();
				break;
			case GIORNALE:
				g.setId(id);
				costo=gD.getData(g).getPrezzo();
				break;
			case RIVISTA:
				r.setId(id);
				costo=rD.getData(r).getPrezzo();
				break;
			default: return costo;
		}



		return costo;

		
	}
	public int getDisp(String type) throws SQLException, IdException {

		switch (type)
		{
			case LIBRO:
				l.setId(vis.getId());
				disp=lD.getData(l).getDisponibilita();
				break;
			case GIORNALE:
				g.setId(vis.getId());
				disp=gD.getData(g).getDisponibilita();
				break;
			case RIVISTA:
				r.setId(vis.getId());
				disp=rD.getData(r).getDisp();
				break;
			default:checkID(vis.getId());
				return disp;

		}
		return disp;
	}



}
