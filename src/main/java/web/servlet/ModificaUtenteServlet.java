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
				User.getInstance().setId(SystemBean.getIstance().getIdB());
				UsersDao.getTempUserSingolo(TempUser.getInstance());
				
				
				UserBean.getInstanceB().setIdRuolo(TempUser.getInstance().getIdRuolo());
				UserBean.getInstanceB().setNomeB(TempUser.getInstance().getNomeT());
				UserBean.getInstanceB().setCognomeB(User.getInstance().getCognome());
				UserBean.getInstanceB().setEmailB(User.getInstance().getEmail());
				UserBean.getInstanceB().setPasswordB(TempUser.getInstance().getPasswordT());
				UserBean.getInstanceB().setDescrizioneB(TempUser.getInstance().getDescrizioneT());
				UserBean.getInstanceB().setDataDiNascitaB(TempUser.getInstance().getDataDiNascitaT());
				
				req.setAttribute("beanUb",UserBean.getInstanceB());
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
				
				UserBean.getInstanceB().setIdB(SystemBean.getIstance().getIdB());
				UserBean.getInstanceB().setIdRuolo(ruoloN);
				UserBean.getInstanceB().setNomeB(nomeN);
				UserBean.getInstanceB().setCognomeB(cognomeN);
				UserBean.getInstanceB().setEmailB(emailN);
				UserBean.getInstanceB().setPasswordB(passN);
				UserBean.getInstanceB().setDescrizioneB(descN);
				java.util.Date utilDate;
			     java.sql.Date sqlDate;
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				
			    
		         utilDate = format.parse(dataN);
		        sqlDate = new java.sql.Date(utilDate.getTime());
		        
		        UserBean.getInstanceB().setDataDiNascitaB(sqlDate.toLocalDate());
		        
		        User.getInstance().setId(UserBean.getInstanceB().getIdB());
		        User.getInstance().setId(UserBean.getInstanceB().getIdB());
		        User.getInstance().setNome(UserBean.getInstanceB().getNomeB());
		        User.getInstance().setCognome(UserBean.getInstanceB().getCognomeB());
		        User.getInstance().setEmail(UserBean.getInstanceB().getEmailB());
		        User.getInstance().setPassword(UserBean.getInstanceB().getPasswordB());
		        User.getInstance().setDescrizione(UserBean.getInstanceB().getDescrizioneB());
		        User.getInstance().setDataDiNascita(UserBean.getInstanceB().getDataDiNascitaB());
		        
		        if(UsersDao.aggiornaUtente(User.getInstance())!=null)
		        {
		        	RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneUtente.jsp");
					view.forward(req, resp);
		        }
		        else {
		        	UserBean.getInstanceB().setMexB("Aggiornamento NON andato a buon fine");
		        	req.setAttribute("beanUb", UserBean.getInstanceB());
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
