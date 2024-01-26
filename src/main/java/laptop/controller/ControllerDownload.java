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
	private ControllerSystemState vis=ControllerSystemState.getInstance();
	private ContrassegnoDao cDao;
	private PagamentoDao pDao;
	private LibroDao lD;
	private Giornale g;
	private GiornaleDao gD;
	private RivistaDao rD;
	private Rivista r;	
	private  Libro l;
	

	
	
	

	public void annullaOrdine() throws SQLException {
		/*
		 * MEtodo usato per cancellare pafamento e fatture.. ma con una query di ritardo
		 */
		boolean statusF=false;
		boolean statusP=false;
		String typeP=vis.getMetodoP(); //tipo pagamento
		String typeO=vis.getType(); //tipo oggetto
		
		int idF=cDao.retUltimoOrdineF(); //ultimo elemento (preso con count)
		statusF=cDao.annullaOrdineF(idF);
		
		int idP=pDao.retUltimoOrdinePag();
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
	public void scarica(String type) throws  IOException, URISyntaxException,  DocumentException {
		switch (type)
		{
			case "libro":
			{
				l.setId(vis.getId());
				l.scarica(vis.getId());
				l.leggi(vis.getId());
				break;
			}
			case "giornale":
			{
				g.setId(vis.getId());
				g.scarica(vis.getId());
				g.leggi(vis.getId());
				break;
			}
			case "rivista":
			{
				r.setId(vis.getId());
				r.scarica(vis.getId());
				r.leggi(vis.getId());
				break;
			}
			default:break;
		}
	}


	public ControllerDownload() throws IOException {
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
