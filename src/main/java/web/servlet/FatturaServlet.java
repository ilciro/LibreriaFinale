package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import web.bean.FatturaBean;
import web.bean.LibroBean;
import web.bean.PagamentoBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.ContrassegnoDao;
import laptop.database.PagamentoDao;
import laptop.model.Fattura;
import laptop.model.Pagamento;
@WebServlet("/FatturaServlet")
public class FatturaServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FatturaBean fB=new FatturaBean();
	private static  Fattura f=new Fattura();
	
	private static LibroBean lB=new LibroBean();
	private static PagamentoDao pD=new PagamentoDao();
	private static ContrassegnoDao fD=new ContrassegnoDao();
	private static Pagamento p=new Pagamento();
	private static PagamentoBean pB=new PagamentoBean();
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome=req.getParameter("nomeL");
		String cognome=req.getParameter("cognomeL");
		String indirizzo=req.getParameter("indirizzoL");
		String com=req.getParameter("com");
		String invia=req.getParameter("buttonC");
		String annullaO=req.getParameter("annulla");
		
		if(checkData(fB.getNomeB(),fB.getCognomeB(),fB.getIndirizzoB()) && (invia!=null )&& invia.equals("procedi"))
		{
			
			fB.setNomeB(nome);
			fB.setCognomeB(cognome);
			fB.setIndirizzoB(indirizzo);
			fB.setComunicazioniB(com);
			
			f.setNome(fB.getNomeB());
			f.setCognome(fB.getCognomeB());
			f.setVia(fB.getIndirizzoB());
			f.setCom(fB.getComunicazioniB());
			f.setAmmontare(SystemBean.getIstance().getSpesaTB());
			
			pB.setIdB(0);
			pB.setMetodoB(SystemBean.getIstance().getMetodoPB());
			pB.setAmmontareB(SystemBean.getIstance().getSpesaTB());
			pB.setEsitoB(0);
			pB.setNomeUtenteB(fB.getNomeB());
			pB.setTipoB(lB.getcategoriaB());
		
			p.setId(pB.getIdB());
			p.setMetodo(pB.getMetodoB());
			p.setAmmontare(pB.getAmmontareB());
			p.setEsito(pB.getEsitoB());
			p.setNomeUtente(pB.getNomeUtenteB());
			p.setTipo(pB.getTipoB());
			
			try {
				fD.inserisciFattura(f);
				pD.inserisciPagamento(p);
			} catch (SQLException e) {
				java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());
			}
		
			if(SystemBean.getIstance().isNegozioSelezionatoB())
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/negozi.jsp"); 
				view.forward(req,resp);
			}
			else {
				req.setAttribute("bean1",SystemBean.getIstance());
			RequestDispatcher view = getServletContext().getRequestDispatcher("/download.jsp"); 
			view.forward(req,resp);
			}
		}
		
		
		if(annullaO!=null && annullaO.equals("annulla"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp"); 
			view.forward(req,resp);
		}
	
		
		}
	
		private  boolean checkData(String nome,String cognome ,String indirizzo)
		{
			boolean status=false;
			if(!"".equals(nome) && !"".equals(cognome) && !"".equals(indirizzo))
				status=true;
			return status;
		}
	
	

}
