package web.servlet;

import java.io.IOException;

import java.io.Serial;
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
    @Serial
    private static final long serialVersionUID = 1L;
    private final PagamentoBean pB=new PagamentoBean();
    private final PagamentoDao pD=new PagamentoDao();
    private final String profilo="/profilo.jsp";
    private final UserBean uB=UserBean.getInstance();
    private final SystemBean sB=SystemBean.getInstance();
    private final User u= User.getInstance() ;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lista=req.getParameter("genera lista");
        String inserisci=req.getParameter("inserisci");
        String modifica=req.getParameter("modifica");
        String idOgg=req.getParameter("idOgg");
        String cancella=req.getParameter("elimina");
        String annulla=req.getParameter("indietro");
        //scrittore gestione
        String dati=req.getParameter("prendiDatiB");
        String ordini=req.getParameter("ordiniB");




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

            if(dati!=null && dati.equals("prendi dati"))
            {
                u.setEmail(uB.getEmailB());
                UsersDao.pickData(u);
                uB.setNomeB(u.getNome());
                uB.setCognomeB(u.getCognome());
                uB.setEmailB(u.getEmail());
                uB.setDataDiNascitaB(u.getDataDiNascita());
                uB.setRuoloB("W");
                req.setAttribute("beanUb",uB);
                 view = getServletContext().getRequestDispatcher(profilo);
                view.forward(req,resp);
            }
            if(ordini!=null && ordini.equals("cronologia ordini"))
            {
                //prendo pagamento dao> lista pagamento
                uB.setRuoloB("W");

                u.setEmail(uB.getEmailB());
                pB.setListaPagamentiB(pD.getPagamenti());
                req.setAttribute("beanUb",uB );
                req.setAttribute("beanP", pB);
                 view = getServletContext().getRequestDispatcher(profilo);
                view.forward(req,resp);
            }
            if(annulla!=null && annulla.equals("indietro"))
            {

                 view = getServletContext().getRequestDispatcher("/index.jsp");
                view.forward(req,resp);
            }

        }catch (SQLException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post .", e);

        }


    }




}