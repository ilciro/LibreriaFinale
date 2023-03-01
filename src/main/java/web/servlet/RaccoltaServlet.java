package web.servlet;

import java.io.IOException;
import java.util.logging.Level;

import laptop.exception.LogoutException;
import web.bean.SystemBean;
import web.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RaccoltaServlet")
public class RaccoltaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String gestioneOggetto="/gestioneOggetto.jsp";
	private static String beanS="beanS";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String libro=req.getParameter("buttonL");
		String giornale=req.getParameter("buttonG");
		String rivista=req.getParameter("buttonR");
		String logout=req.getParameter("buttonLog");
		try {
		if(libro!=null && libro.equals("libri"))
			{
				SystemBean.getIstance().setTypeAsBook();
				req.setAttribute(beanS,SystemBean.getIstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher(gestioneOggetto); 
				view.forward(req,resp);
			}
		if(giornale!=null && giornale.equals("giornali") )
		{
			SystemBean.getIstance().setTypeAsDaily();
			req.setAttribute(beanS,SystemBean.getIstance());
			RequestDispatcher view = getServletContext().getRequestDispatcher(gestioneOggetto); 
			view.forward(req,resp);
		}
		if(rivista!=null && rivista.equals("riviste"))
		{
			SystemBean.getIstance().setTypeAsMagazine();
			req.setAttribute(beanS,SystemBean.getIstance());
			RequestDispatcher view = getServletContext().getRequestDispatcher(gestioneOggetto); 
			view.forward(req,resp);
		}
		if(logout!=null && logout.equals("logout"))
		{
			
				logout();
				if(!SystemBean.getIstance().isLoggedB())
				{
					RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
					view.forward(req,resp);
				}
			
		}
		} catch (LogoutException e) {
			java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

		}
			
			
	}
	
	public static boolean logout() throws LogoutException 
	{	
		
		String n = UserBean.getInstanceB().getNomeB();
		java.util.logging.Logger.getLogger("Test logout").log(Level.INFO, "stai sloggando come {0}" ,n);
		
		if (n==null)
		{
			throw new LogoutException("Errore Logout");

		}
		else {
			 UserBean.getInstanceB().setIdB(-1);
			 UserBean.getInstanceB().setNomeB(null);
			 UserBean.getInstanceB().setCognomeB(null);
			 UserBean.getInstanceB().setDataDiNascitaB(null);
			 UserBean.getInstanceB().setDescrizioneB(null);
			 UserBean.getInstanceB().setEmailB(null);
			 UserBean.getInstanceB().setPasswordB(null);
		
		
		java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, "stai sloggando {0}", UserBean.getInstanceB().getEmailB());
			SystemBean.getIstance().setLoggedB(false);
			return true;
		}

	}

}
