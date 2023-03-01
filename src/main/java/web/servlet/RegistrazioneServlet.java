package web.servlet;

import java.io.IOException;
import java.sql.Date;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.regex.Pattern;

import web.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.UsersDao;
import laptop.model.User;

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean state=false;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome=req.getParameter("nomeL");
		String cognome=req.getParameter("cognomeL");
		String email=req.getParameter("emailL");
		String pass=req.getParameter("passL");
		String confermaPass=req.getParameter("confermaPassL");
		String data=req.getParameter("dataL");
		String invia=req.getParameter("inviaB");
		String indietro=req.getParameter("indietro");
		try {
		if(invia!=null && invia.equals("registrati") && checkData(nome,cognome,email,pass,confermaPass) )
			{
				UserBean.getInstanceB().setNomeB(nome);
				UserBean.getInstanceB().setCognomeB(cognome);
				UserBean.getInstanceB().setEmailB(email);
				UserBean.getInstanceB().setPasswordB(pass);
				Date sqlDate = null;
				java.util.Date utilDate;
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

			  
			         
						utilDate = format.parse(data);
						sqlDate = new java.sql.Date(utilDate.getTime());

					
				UserBean.getInstanceB().setDataDiNascitaB(sqlDate.toLocalDate());
				
				User.getInstance().setNome(UserBean.getInstanceB().getNomeB());
				User.getInstance().setCognome(UserBean.getInstanceB().getCognomeB());
				User.getInstance().setEmail(UserBean.getInstanceB().getEmailB());
				User.getInstance().setPassword(UserBean.getInstanceB().getPasswordB());
				User.getInstance().setDataDiNascita(UserBean.getInstanceB().getDataDiNascitaB());




				
					if(UsersDao.checkUser(User.getInstance())==1)
							{
								//utente già trovato
						UserBean.getInstanceB().setMexB("utente gia registrato nel sistema !!!");
						req.setAttribute("beanUb", UserBean.getInstanceB());
							RequestDispatcher view = getServletContext().getRequestDispatcher("/registrazione.jsp"); 
							view.forward(req,resp);
							}
					else {
						RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
						view.forward(req,resp);
					}
			
		}
		if(indietro!=null && indietro.equals("indietro"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
			view.forward(req,resp);
		}
	}catch(SQLException | ParseException|NullPointerException e)
		{
		java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

		}
	}
	
	private boolean checkData (String n, String c, String email, String pwd, String pwdC)
	// controll  all data
	{
		if(checkEmail(email) && checkPassword(pwd,pwdC) && checkNomeCognome(n,c))
		{
			state=true;
		}
		return state;
	}
	
	private boolean checkEmail(String email)
	{
		 Pattern pattern;

		String emailRegex;
		emailRegex= "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; 
                  
		pattern = Pattern.compile(emailRegex); 
		if (email == null) 
			return false; 
		return pattern.matcher(email).matches();
	}
    
	private boolean checkPassword(String pwd, String pwdC )
	{
		if(pwd.length()>=8 && pwdC.length()>=8 && pwd.equals(pwdC)) {
			state= true;
		}
		return state;
	}
	
	private boolean checkNomeCognome(String n, String c)
	{
		if (n != null && c != null)
		{
			state= true;
		}
		return state;
	}
	
	

}
