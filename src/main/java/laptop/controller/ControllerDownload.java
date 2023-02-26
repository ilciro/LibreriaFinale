package laptop.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.UUID;

import com.itextpdf.text.DocumentException;

import laptop.database.ContrassegnoDao;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.PagamentoDao;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;



public class ControllerDownload {
	private String nrOrdine;
	private ControllerSystemState vis=ControllerSystemState.getIstance();
	private ContrassegnoDao cDao;
	private PagamentoDao pDao;
	private LibroDao lD;
	private Giornale g;
	private GiornaleDao gD;
	private RivistaDao rD;
	private Rivista r;	
	private  Libro l;
	
	public void scaricaLibro() throws DocumentException, IOException, URISyntaxException {
		l.setId(vis.getId());		
		l.scarica();		
		
		l.leggi(vis.getId());
	}
	
	
	

	public void annullaOrdine() throws SQLException {
		/*
		 * MEtodo usato per cancellare pafamento e fatture.. ma con una query di ritardo
		 */
		boolean statusF=false;
		boolean statusP=false;
		String typeP=vis.getMetodoP(); //tipo pagamento
		String typeO=vis.getType(); //tipo oggetto
		
		int idF=cDao.retUltimoOrdine(); //ultimo elemento (preso con count)
		statusF=cDao.annullaOrdineF(idF);
		
		int idP=pDao.retUltimoOrdine();
		statusP=pDao.annullaOrdinePag(idP);
		
		
		if((typeP.equals("cash") &&(statusF && statusP))||(typeP.equals("cCredito") && statusP))
			{
				//aggiorno disponibilita
				
				switch(typeO)
				{
					case "libro":
					{
						incrementaLibri();
						break;
					}
					case "giornale":
					{
						incrementaGiornali();

						break;
					}
					case "rivista":
					{
						incrementaRivista();
						break;
					}
					default :
						break;
				}
				
			
			
			
		}
		// messo su come condizione		
		
		
	}

	public ControllerDownload() {
		this.setNrOrdine(UUID.randomUUID().toString());
		l = new Libro();
		cDao=new ContrassegnoDao();
		pDao=new PagamentoDao();
		lD=new LibroDao();
		g=new Giornale();
		gD=new GiornaleDao();
		r=new Rivista();
		rD=new RivistaDao();
	}

	public void scaricaGiornale() throws IOException, DocumentException {
		g.setId(vis.getId());		
		g.scarica();		
		g.leggi(vis.getId());
		
	}

	public void scaricaRivista() throws DocumentException, IOException {
		r.setId(vis.getId());
		r.scarica();
		r.leggi(vis.getId());
		
	}






	public String getNrOrdine() {
		return nrOrdine;
	}




	public void setNrOrdine(String nrOrdine) {
		this.nrOrdine = nrOrdine;
	}
	
	public void incrementaLibri() throws SQLException
	{
		l.setId(vis.getId());
		lD.incrementaDisponibilita(l);
	}
	public void incrementaGiornali() throws SQLException
	{
		g.setId(vis.getId());
		gD.incrementaDisponibilita(g);
	}
	public void incrementaRivista() throws SQLException
	{
		r.setId(vis.getId());
		rD.incrementaDisponibilita(r);
	}

}
