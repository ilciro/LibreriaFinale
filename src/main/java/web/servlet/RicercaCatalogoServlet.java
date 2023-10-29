package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import web.bean.RicercaBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RicercaCatalogoServlet")
public class RicercaCatalogoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static RicercaBean rB=new RicercaBean();
	private static LibroDao lD=new LibroDao();
	private static RivistaDao rD=new RivistaDao();
	private static GiornaleDao gD=new GiornaleDao();
	private static final String BEANRICERCA="beanRicerca";
	private static final String LIBRO="libro";
	private static final String GIORNALE= "giornale";
	private static final String RIVISTA="rivista";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String titolo=req.getParameter("cercaL");
		String cercaB=req.getParameter("cercaB");
		String visualizza=req.getParameter("visualizzaB");
		String indietro=req.getParameter("buttonI");
		String type=SystemBean.getInstance().getTypeB();
		
		try {
		if(cercaB!=null && cercaB.equals("cerca"))
		{
			switch(type)
			{
				case LIBRO:
				rB.setListaB(lD.getLibriByName(titolo));
				req.setAttribute(BEANRICERCA,rB);
				break;
				case GIORNALE:
				rB.setListaB(gD.getGiornaliByName(titolo));
				req.setAttribute(BEANRICERCA,rB);
				break;
				case RIVISTA:
				rB.setListaB(rD.getRivistaSingolo());
				req.setAttribute(BEANRICERCA, rB);
				break;
				default:break;
				
			}
			RequestDispatcher view=getServletContext().getRequestDispatcher("/ricercaInCatalogo.jsp");
			view.forward(req, resp);
		}
		if(indietro!=null && indietro.equals("indietro"))
		{
			RequestDispatcher view=getServletContext().getRequestDispatcher("/ricerca.jsp");
			view.forward(req, resp);
		}
		if(visualizza!=null && visualizza.equals("visualizza"))
		{
			RequestDispatcher view;
			switch(type)
			{
				case LIBRO:
				 view=getServletContext().getRequestDispatcher("/libri.jsp");
				view.forward(req, resp);
				break;
				case GIORNALE:
				 view=getServletContext().getRequestDispatcher("/giornali.jsp");
				view.forward(req, resp);	
				break;
				case RIVISTA:
				 view=getServletContext().getRequestDispatcher("/riviste.jsp");
				view.forward(req, resp);
				break;
				default:break;
				
			}
			}
		
		
		
	}catch(SQLException e)
		{
		java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

		}
	}
	

}
