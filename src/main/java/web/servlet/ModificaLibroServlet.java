package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import laptop.database.LibroDao;
import web.bean.LibroBean;
import web.bean.ModificaOggettoBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.raccolta.Libro;

@WebServlet("/ModificaLibroServlet")
public class ModificaLibroServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ModificaOggettoBean mOB=new ModificaOggettoBean(); 
	private static LibroDao lD=new LibroDao();
	private static Libro l=new Libro();
	private static LibroBean lB=new LibroBean();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String buttonLista=req.getParameter("listaB");
		String buttonCateg=req.getParameter("buttonCat");
		String aggiorna=req.getParameter("buttonI");
		String annulla=req.getParameter("buttonA");
		try {
			
		lB.setIdB(SystemBean.getIstance().getIdB());
		l.setId(lB.getIdB());
		
		if(buttonLista!=null && buttonLista.equals("prendi dati"))
		{
		
			
			req.setAttribute("beanMOB", mOB);
			RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
			view.forward(req, resp);
		}
		if(buttonCateg!=null && buttonCateg.equals("elenco categorie"))
		{
			req.setAttribute("beanMOBL",lB);
			RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
			view.forward(req, resp);
		}
		if(aggiorna!=null && aggiorna.equals("aggiorna"))
		{
			//prendo roba
			String titoloN=req.getParameter("titoloNL");
			String pagineN=req.getParameter("pagineNL");
			String codiceN=req.getParameter("codiceNL");
			String editoreN=req.getParameter("editoreNL");
			String autoreN=req.getParameter("autoreNL");
			String linguaN=req.getParameter("linguaNL");
			String categoriaN=req.getParameter("categoriaNL");
			String dataN=req.getParameter("dataNL");//fare cast
			String recN=req.getParameter("recNL");
			String copieN=req.getParameter("copieNL");
			String descN=req.getParameter("descNL");
			String dispN=req.getParameter("dispNL");
			String prezzoN=req.getParameter("prezzoNL");
			
			lB.setTitoloB(titoloN);
			lB.setNumeroPagineB(Integer.parseInt(pagineN));
			lB.setCodIsbnB(codiceN);
			lB.setEditoreB(editoreN);
			lB.setAutoreB(autoreN);
			lB.setLinguaB(linguaN);
			lB.setcategoriaB(categoriaN);
			java.util.Date utilDate;
		     java.sql.Date sqlDate;
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			
		    
	         utilDate = format.parse(dataN);
	        sqlDate = new java.sql.Date(utilDate.getTime());
	        lB.setDataPubbB(sqlDate.toLocalDate());
	        lB.setRecensioneB(recN);
	        lB.setNrCopieB(Integer.parseInt(copieN));
	        lB.setDescB(descN);
	        lB.setDisponibilitaB(Integer.parseInt(dispN));
	        lB.setPrezzoB(Float.parseFloat(prezzoN));
	        
			l.setTitolo(lB.getTitoloB());
			l.setNumeroPagine(lB.getNumeroPagineB());
			l.setCodIsbn(lB.getCodIsbnB());
			l.setEditore(lB.getEditoreB());
			l.setAutore(lB.getAutoreB());
			l.setLingua(lB.getLinguaB());
			l.setCategoria(categoriaN);
			l.setDataPubb(lB.getDataPubbB());
			l.setRecensione(lB.getRecensioneB());
			l.setNrCopie(lB.getNrCopieB());
			l.setDesc(lB.getDescB());
			l.setDisponibilita(lB.getDisponibilitaB());
			l.setPrezzo(lB.getPrezzoB());
			
			lD.aggiornaLibro(l);
			RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneOggetto.jsp");
			view.forward(req, resp);
			
		}
		if(annulla!=null && annulla.equals("indietro"))
		{
			RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneOggetto.jsp");
			view.forward(req, resp);
		}
		}catch(SQLException | ParseException e)
		{
			java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

		
		
	}
	}
	
	

}
