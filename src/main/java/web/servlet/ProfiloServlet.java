package web.servlet;

import java.io.IOException;

import java.sql.SQLException;
import java.util.logging.Level;

import web.bean.PagamentoBean;
import web.bean.SystemBean;
import web.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.PagamentoDao;
import laptop.database.UsersDao;
import laptop.model.User;
@WebServlet("/ProfiloServlet")
public class ProfiloServlet extends HttpServlet{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static PagamentoBean pB=new PagamentoBean();
    private static PagamentoDao pD=new PagamentoDao();
    private static String profilo="/profilo.jsp";
    private final UserBean uB=UserBean.getInstance();
    private final SystemBean sB=SystemBean.getInstance();
    private final User u= User.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lista=req.getParameter("genera lista");
        String inserisci=req.getParameter("inserisci");
        String modifica=req.getParameter("modifica");
        String idOgg=req.getParameter("idOgg");
        String cancella=req.getParameter("elimina");
        String annulla=req.getParameter("indietro");
        RequestDispatcher view;
        try {
            if (lista != null && lista.equals("genera lista")) {
                uB.setStringB(UsersDao.getUserList());
                req.setAttribute("beanUb",uB);
                 view=getServletContext().getRequestDispatcher("/profilo.jsp");
                view.forward(req,resp);

            }
            if(inserisci!=null && inserisci.equals("nuovo utente"))
            {
                view=getServletContext().getRequestDispatcher("/inserisciUtente.jsp");
                view.forward(req,resp);
            }
            if(modifica!=null && modifica.equals("modifica utente"))
            {
                int id=Integer.parseInt(idOgg);
                sB.setIdB(id);
                uB.setIdB(sB.getIdB());
                req.setAttribute("beanUb",uB);
                view= getServletContext().getRequestDispatcher("/modificaUtente.jsp");
                view.forward(req,resp);

            }
            if(cancella!=null && cancella.equals("cancella utente"))
            {

                uB.setIdB(Integer.parseInt(idOgg));
                u.setId(uB.getIdB());
                uB.setRuoloB("A");
                if(UsersDao.deleteUser(u))
                {
                    req.setAttribute("beanUb",uB);
                    view= getServletContext().getRequestDispatcher("/profilo.jsp");
                    view.forward(req,resp);
                }

            }
            if(annulla!=null && annulla.equals("indietro"))
            {
                view= getServletContext().getRequestDispatcher("/utenti.jsp");
                view.forward(req,resp);
            }
        }catch (SQLException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post .", e);

        }
        /*
        String dati=req.getParameter("prendiDatiB");
        String modifica=req.getParameter("modificBa");
        String ordini=req.getParameter("ordiniB");
        String cancella=req.getParameter("cancellaB");
        String indietro=req.getParameter("indietroB");


        try {
            if(dati!=null && dati.equals("prendi dati"))
            {
                User.getInstance().setEmail(UserBean.getInstance().getEmailB());
                UsersDao.pickData(User.getInstance());
                UserBean.getInstance().setNomeB(User.getInstance().getNome());
                UserBean.getInstance().setCognomeB(User.getInstance().getCognome());
                UserBean.getInstance().setEmailB(User.getInstance().getEmail());
                UserBean.getInstance().setDataDiNascitaB(UserBean.getInstance().getDataDiNascitaB());
                req.setAttribute("beanUb",UserBean.getInstance());
                RequestDispatcher view = getServletContext().getRequestDispatcher(profilo);
                view.forward(req,resp);
            }
            if(modifica!=null && modifica.equals("modifica"))
            {
                RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaProfilo.jsp");
                view.forward(req,resp);
            }
            if(ordini!=null && ordini.equals("ordini"))
            {
                //prendo pagamento dao> lista pagamento
                User.getInstance().setEmail(UserBean.getInstance().getEmailB());
                pB.setListaPagamentiB(pD.getPagamenti());
                req.setAttribute("bean", User.getInstance());
                req.setAttribute("beanP", pB);
                RequestDispatcher view = getServletContext().getRequestDispatcher(profilo);
                view.forward(req,resp);
            }
            if(cancella!=null && cancella.equals("cancella"))
            {
                User.getInstance().setEmail(UserBean.getInstance().getEmailB());
                if(UsersDao.deleteUser(User.getInstance()))
                {
                    RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp");
                    view.forward(req,resp);
                }
                else {
                    UserBean.getInstance().setMexB(" utente non cancellato... ");
                    req.setAttribute("beanUb",UserBean.getInstance());
                    RequestDispatcher view = getServletContext().getRequestDispatcher(profilo);
                    view.forward(req,resp);
                }

            }
            if(indietro!=null && indietro.equals("indietro"))
            {
                RequestDispatcher view = getServletContext().getRequestDispatcher("/utenti.jsp");
                view.forward(req,resp);
            }
        }catch(SQLException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

        } */

    }




}