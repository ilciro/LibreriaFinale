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
		String report = req.getParameter("reportB");
		String raccolta = req.getParameter("raccoltaB");
		String utente = req.getParameter("utentiB");
		String logout = req.getParameter("logoutB");
		try {
			if (report != null && report.equals("report")) {
				RequestDispatcher view = getServletContext().getRequestDispatcher("/report.jsp");
				view.forward(req, resp);
			}
			if (raccolta != null && raccolta.equals("raccolta")) {
				RequestDispatcher view = getServletContext().getRequestDispatcher("/raccolta.jsp");
				view.forward(req, resp);
			}
			if (utente != null && utente.equals("utenti")) {
				RequestDispatcher view = getServletContext().getRequestDispatcher("/utenti.jsp");
				view.forward(req, resp);
			}
			if (logout != null && logout.equals("logout")) {
				String email = UserBean.getInstance().getEmailB();


				if (email == null) {
					RequestDispatcher view = getServletContext().getRequestDispatcher("/admin.jsp");
					view.forward(req, resp);
					throw new LogoutException("Errore Logout");



				} else {

							UserBean.getInstance().setIdB(-1);
							UserBean.getInstance().setNomeB(null);
							UserBean.getInstance().setCognomeB(null);
							UserBean.getInstance().setDataDiNascitaB(null);
							UserBean.getInstance().setDescrizioneB(null);
							UserBean.getInstance().setEmailB(null);
							UserBean.getInstance().setPasswordB(null);


							java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, "stai sloggando {0}", UserBean.getInstance().getEmailB());
							SystemBean.getInstance().setLoggedB(false);

					RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp");
					view.forward(req, resp);



				}

				}


		} catch (LogoutException | ServletException | IOException e) {
			java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post .", e);

		}


	}

}
