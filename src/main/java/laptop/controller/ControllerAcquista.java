package laptop.controller;

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
	private int rimanenza = 0;//usato per vedee nr copie 
	private static final String LIBRO = "libro";
	private static final String RIVISTA="rivista";
	private static final String GIORNALE="giornale";
	private int disp;

	

	public float totale1 (String type,String titolo,int disp,int quantita) throws SQLException, IdException {
		float x=0f;
		switch (type)
		{
			case LIBRO:
			{
				l.setTitolo(titolo);
				l.setNrCopie(quantita);
				vis.setQuantita(quantita);
				 x = lD.getCosto(l);
				lD.aggiornaDisponibilita(l);
				break;
			}
			case GIORNALE:
			{
				g.setTitolo(titolo);
				g.setId(vis.getId());
				g.setCopieRimanenti(quantita);
				vis.setQuantita(disp);
				x = gD.getCosto(g);
				gD.aggiornaDisponibilita(g);
				break;
			}
			case RIVISTA:
			{
				r.setTitolo(titolo);
				r.setId(vis.getId());
				r.setCopieRim(quantita);
				vis.setQuantita(disp);
				x= rD.getCosto(r);
				rD.aggiornaDisponibilita(r);
				break;
			}
			default :checkID(vis.getId());
		}
		return x;
	}
	private boolean checkID(int id) throws IdException {
		boolean status=false;
		if(id <=0 || id>30)
		{
			throw  new IdException("wrong id");
		}
		status=true;
		return status;
	}



	public ControllerAcquista()    {
		lD = new LibroDao();
		gD = new GiornaleDao();
		rD = new RivistaDao();
		l = new Libro();
		g = new Giornale();
		r = new Rivista();
		
		

	}


	public void inserisciAmmontare(String type,int i) throws SQLException, AcquistaException {
		switch(type)
		{
			case LIBRO:
				l.setId(vis.getId());
				rimanenza=lD.getQuantita(l);
				break;
			case GIORNALE:
				g.setId(vis.getId());
				rimanenza=gD.getQuantita(g);
				break;
			case RIVISTA:
				r.setId(vis.getId());
				rimanenza=rD.getQuantita(r);
				break;
			default: checkRimanenza(rimanenza,i);


		}
	}

	private void checkRimanenza(int quantita,int i) throws AcquistaException {
		if(quantita-i<0)
		{
			throw new AcquistaException("rimanenza <0");
		}
	}

	public String getNomeById() throws SQLException
	{
		
		int id = vis.getId();
		String type =vis.getType();
		if(type.equals(LIBRO))
		{
			l.setId(id);
			name = lD.getNome(l);
		}
		else if(type.equals(GIORNALE)) {
			g.setId(id);
			name = gD.getNome(g);
		}
		else if(type.equals(RIVISTA))
		{
			r.setId(id);
			name = rD.getNome(r);
			
		}
		return name ;
	}

	/*
	 * metodo aggiunto per stampare appena carica la schermata anche il costo di 
	 * ogni singolo elemento(giornale,rivista o lbro)
	 */
	 
	public float 	getCosto() throws SQLException
	{
		String type=vis.getType();
		int id = vis.getId();

		if(type.equalsIgnoreCase(LIBRO))
		{
			l.setId(id);
			costo=lD.getCosto(l);

		}
		 if(type.equalsIgnoreCase(GIORNALE))
		{
			g.setId(id);
			costo=gD.getCosto(g);
			
		}
		 if(type.equalsIgnoreCase(RIVISTA))
		{
			r.setId(id);
			costo=rD.getCosto(r);

		}
		return costo;

		
	}
	public int getDisp(String type) throws SQLException, IdException {

		switch (type)
		{
			case LIBRO:
				l.setId(vis.getId());
				disp=lD.getQuantita(l);
				break;
			case GIORNALE:
				g.setId(vis.getId());
				disp=gD.getQuantita(g);
				break;
			case RIVISTA:
				r.setId(vis.getId());
				disp=rD.getQuantita(r);
				break;
			default:checkID(vis.getId());
				return disp;

		}
		return disp;
	}



}
