package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import laptop.database.UsersDao;
import web.bean.SystemBean;
import web.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.TempUser;
import laptop.model.User;

@WebServlet("/ModificaUtenteServlet")
public class ModificaUtenteServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String genera=req.getParameter("generaB");
		String aggiorna=req.getParameter("buttonI");
		String indietro=req.getParameter("buttonA");
		try {
			if(genera!=null &&genera.equals("prendi valori"))
			{
				User.getInstance().setId(SystemBean.getInstance().getIdB());
				UsersDao.getTempUserSingolo(TempUser.getInstance());
				
				
				UserBean.getInstance().setIdRuolo(TempUser.getInstance().getIdRuolo());
				UserBean.getInstance().setNomeB(TempUser.getInstance().getNomeT());
				UserBean.getInstance().setCognomeB(User.getInstance().getCognome());
				UserBean.getInstance().setEmailB(User.getInstance().getEmail());
				UserBean.getInstance().setPasswordB(TempUser.getInstance().getPasswordT());
				UserBean.getInstance().setDescrizioneB(TempUser.getInstance().getDescrizioneT());
				UserBean.getInstance().setDataDiNascitaB(TempUser.getInstance().getDataDiNascitaT());
				
				req.setAttribute("beanUb",UserBean.getInstance());
				RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaUtente.jsp");
				view.forward(req, resp);
				
			}
			if(aggiorna!=null && aggiorna.equals("aggiorna"))
			{
				String ruoloN=req.getParameter("ruoloNL");
				String nomeN=req.getParameter("nomeNL");
				String cognomeN=req.getParameter("cognomeNL");
				String emailN=req.getParameter("emailNL");
				String passN=req.getParameter("passNL");
				String descN=req.getParameter("descNL");
				String dataN=req.getParameter("dataNL");//cast
				
				UserBean.getInstance().setIdB(SystemBean.getInstance().getIdB());
				UserBean.getInstance().setIdRuolo(ruoloN);
				UserBean.getInstance().setNomeB(nomeN);
				UserBean.getInstance().setCognomeB(cognomeN);
				UserBean.getInstance().setEmailB(emailN);
				UserBean.getInstance().setPasswordB(passN);
				UserBean.getInstance().setDescrizioneB(descN);
				java.util.Date utilDate;
			     java.sql.Date sqlDate;
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				
			    
		         utilDate = format.parse(dataN);
		        sqlDate = new java.sql.Date(utilDate.getTime());
		        
		        UserBean.getInstance().setDataDiNascitaB(sqlDate.toLocalDate());
		        
		        User.getInstance().setId(UserBean.getInstance().getIdB());
		        User.getInstance().setId(UserBean.getInstance().getIdB());
		        User.getInstance().setNome(UserBean.getInstance().getNomeB());
		        User.getInstance().setCognome(UserBean.getInstance().getCognomeB());
		        User.getInstance().setEmail(UserBean.getInstance().getEmailB());
		        User.getInstance().setPassword(UserBean.getInstance().getPasswordB());
		        User.getInstance().setDescrizione(UserBean.getInstance().getDescrizioneB());
		        User.getInstance().setDataDiNascita(UserBean.getInstance().getDataDiNascitaB());
		        
		        if(UsersDao.aggiornaUtente(User.getInstance())!=null)
		        {
		        	RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneUtente.jsp");
					view.forward(req, resp);
		        }
		        else {
		        	UserBean.getInstance().setMexB("Aggiornamento NON andato a buon fine");
		        	req.setAttribute("beanUb", UserBean.getInstance());
		        	RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaUtente.jsp");
					view.forward(req, resp);
		        }
		        
				
			}
			if(indietro!=null && indietro.equals("indietro"))
			{
				RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneUtente.jsp");
				view.forward(req, resp);
			}
		}catch(SQLException | ParseException e)
		{
			java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

		}
	}

}
