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

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String report=req.getParameter("reportB");
		String raccolta=req.getParameter("raccoltaB");
		String utente=req.getParameter("utentiB");
		String logout=req.getParameter("logoutB");
		try {
		if(report!=null && report.equals("report"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/report.jsp"); 
			view.forward(req,resp);
		}
		if(raccolta!=null && raccolta.equals("raccolta"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/raccolta.jsp"); 
			view.forward(req,resp);
		}
		if(utente!=null && utente.equals("utenti"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/utenti.jsp"); 
			view.forward(req,resp);
		}
		if(logout!=null &&logout.equals("logout"))
		{
			logout();
				if(!SystemBean.getIstance().isLoggedB())
				{
					RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
					view.forward(req,resp);
				}
			
		}
	} catch (LogoutException | ServletException | IOException e) {
		java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post .",e);

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
