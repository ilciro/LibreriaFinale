package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import laptop.database.UsersDao;
import laptop.exception.LogoutException;
import laptop.model.TempUser;
import laptop.model.User;
import laptop.utilities.ConnToDb;
import web.bean.SystemBean;
import web.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UtentiServlet")
public class UtentiServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final UserBean uB=UserBean.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String profilo=req.getParameter("buttonProfilo");

        if(profilo!=null && profilo.equals("profilo"))
        {
            req.setAttribute("beanUb",UserBean.getInstance());
            RequestDispatcher view= getServletContext().getRequestDispatcher("/profilo.jsp");
            view.forward(req,resp);
        }
        /*
        String libro=req.getParameter("buttonL");
        String giornale=req.getParameter("buttonG");
        String rivista=req.getParameter("buttonR");
        String logout=req.getParameter("buttonLogout");
        String ricerca=req.getParameter("buttonRic");
        String profilo=req.getParameter("buttonProfilo");
        try {
            if(libro!=null && libro.equals("libri"))
            {
                RequestDispatcher view = getServletContext().getRequestDispatcher("/libri.jsp");
                view.forward(req,resp);
            }
            if(giornale!=null && giornale.equals("giornali"))
            {
                RequestDispatcher view = getServletContext().getRequestDispatcher("/giornali.jsp");
                view.forward(req,resp);
            }
            if(rivista!=null && rivista.equals("riviste"))
            {
                RequestDispatcher view = getServletContext().getRequestDispatcher("/rivista.jsp");
                view.forward(req,resp);
            }
            if(logout!=null && logout.equals("logout"))
            {

                String n = UserBean.getInstance().getNomeB();
                java.util.logging.Logger.getLogger("Test logout").log(Level.INFO, "stai sloggando come {0}" ,n);

                if (n==null)
                {
                    throw new LogoutException("Errore Logout");

                }
                else {
                    UserBean.getInstance().setIdB(-1);
                    UserBean.getInstance().setNomeB(null);
                    UserBean.getInstance().setCognomeB(null);
                    UserBean.getInstance().setDataDiNascitaB(null);
                    UserBean.getInstance().setDescrizioneB(null);
                    UserBean.getInstance().setEmailB("");
                    UserBean.getInstance().setPassB("");


                    java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, "stai sloggando {0}", UserBean.getInstance().getEmailB());
                    SystemBean.getInstance().setLoggedB(false);

                    RequestDispatcher view= getServletContext().getRequestDispatcher("/index.jsp");
                    view.forward(req,resp);
                }

            }

        if(ricerca!=null && ricerca.equals("ricerca"))
            {
                RequestDispatcher view = getServletContext().getRequestDispatcher("/ricerca.jsp");
                view.forward(req,resp);
            }
            if(profilo!=null && profilo.equals("profilo"))
            {
                req.setAttribute("beanUb",UserBean.getInstance());
                RequestDispatcher view = getServletContext().getRequestDispatcher("/profilo.jsp");
                view.forward(req,resp);
            }
        }catch(LogoutException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

        }

         */
    }



    }