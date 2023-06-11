package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import laptop.database.RivistaDao;
import web.bean.ModificaOggettoBean;
import web.bean.RivistaBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.raccolta.Rivista;

@WebServlet("/ModificaRivistaServlet")
public class ModificaRivistaServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static RivistaBean rB=new RivistaBean();
	private static Rivista r=new Rivista();
	private static RivistaDao rD=new RivistaDao();
	private static ModificaOggettoBean mOB=new ModificaOggettoBean();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prendiDati=req.getParameter("listaB");
		String aggiorna=req.getParameter("buttonI");
		String annulla=req.getParameter("buttonA");
		
		try {
			rB.setIdB(SystemBean.getInstance().getIdB());
			r.setId(rB.getIdB());
			mOB.setMiaListaB(rD.getRivistaSingolo());
			
			
			if(prendiDati!=null && prendiDati.equals("prendi dati"))
			{
				req.setAttribute("beanMOB", mOB);
				req.setAttribute("beanMOBR",rB);
				RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
				view.forward(req, resp);
			
				
			}
			if(aggiorna!=null && aggiorna.equals("aggiorna"))
			{
				//prendo roba
				String titoloN=req.getParameter("titoloNR");
				String tipologia=req.getParameter("categoriaNR");
				String autoreN=req.getParameter("autoreNR");
				String linguaN=req.getParameter("linguaNR");
				String editoreN=req.getParameter("editoreNR");
				String desc=req.getParameter("descNR");
				String dataN=req.getParameter("dataNR");//fare cast
				String dispN=req.getParameter("dispNR");
				String prezzoN=req.getParameter("prezzoNR");
				String copieN=req.getParameter("copieNR");
				
				rB.setTitoloB(titoloN);
				rB.setTipologiaB(tipologia);
				rB.setAutoreB(autoreN);
				rB.setLinguaB(linguaN);
				rB.setEditoreB(editoreN);
				rB.setDescrizioneB(desc);
			
				java.util.Date utilDate;
			    java.sql.Date sqlDate;
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				
			    
		        utilDate = format.parse(dataN);
		        sqlDate = new java.sql.Date(utilDate.getTime());
		        
		        rB.setDataPubbB(sqlDate.toLocalDate());
		        rB.setDispB(Integer.parseInt(dispN));
		        rB.setPrezzoB(Float.parseFloat(prezzoN));
		        rB.setCopieRimB(Integer.parseInt(copieN));
		        r.setTitolo(rB.getTitoloB());
		        r.setTipologia(rB.getTipologiaB());
		        r.setAutore(rB.getAutoreB());
		        r.setLingua(rB.getLinguaB());
		        r.setEditore(rB.getEditoreB());
		        r.setDescrizione(rB.getDescrizioneB());
		        r.setDataPubb(rB.getDataPubbB());
		        r.setDisp(rB.getDispB());
		        r.setPrezzo(rB.getPrezzoB());
		        r.setCopieRim(rB.getCopieRimB());
		        
				
				rD.aggiornaRivista(r);
				RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneOggetto.jsp");
				view.forward(req, resp);
				
			}
			if(annulla!=null && annulla.equals("indietro"))
			{
				RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneOggetto.jsp");
				view.forward(req, resp);
			}
		
			
		}catch(SQLException | ParseException  e)
		{
			java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

		}
	}
	
	

}
