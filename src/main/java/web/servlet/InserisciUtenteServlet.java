package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import laptop.database.UsersDao;
import web.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.User;

@WebServlet("/InserisciUtenteServlet")
public class InserisciUtenteServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome=req.getParameter("nomeU");
		String cognome=req.getParameter("cognomeU");
		String email=req.getParameter("emailU");
		String pass=req.getParameter("passU");
		String dataN=req.getParameter("dataU");
		String invia=req.getParameter("buttonI");
		String indietro=req.getParameter("buttonA");
		try {
			if(invia!=null && invia.equals("invia"))
			{
				UserBean.getInstance().setNomeB(nome);
				UserBean.getInstance().setCognomeB(cognome);
				UserBean.getInstance().setEmailB(email);
				UserBean.getInstance().setPasswordB(pass);
				java.util.Date utilDate;
			    java.sql.Date sqlDate;
			    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				
			    
		         utilDate = format.parse(dataN);
		        sqlDate = new java.sql.Date(utilDate.getTime());
		        UserBean.getInstance().setDataDiNascitaB(sqlDate.toLocalDate());
		        
		        User.getInstance().setNome(UserBean.getInstance().getNomeB());
		        User.getInstance().setCognome(UserBean.getInstance().getCognomeB());
		        User.getInstance().setEmail(UserBean.getInstance().getEmailB());
		        User.getInstance().setPassword(UserBean.getInstance().getPasswordB());
		        User.getInstance().setDataDiNascita(UserBean.getInstance().getDataDiNascitaB());
		        
		        if(UsersDao.createUser(User.getInstance()))
		        		{
		        			RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneUtente.jsp");
		        			view.forward(req, resp);
		        		}
		        else {
		        	UserBean.getInstance().setMexB("errore nella creazione del nuovo utente");
		        	req.setAttribute("beanUb",UserBean.getInstance());
		        	RequestDispatcher view=getServletContext().getRequestDispatcher("/inserisciUtente.jsp");
        			view.forward(req, resp);
		        }
			}
			if(indietro!=null && indietro.equals("indietro"))
			{
				RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneUtente.jsp");
    			view.forward(req, resp);
			}
		}catch(SQLException | ParseException  e)
		{
			java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

		}
		
	}

}
