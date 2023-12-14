package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.RivistaDao;
import laptop.exception.IdException;
import laptop.model.raccolta.Rivista;
import web.bean.ModificaOggettoBean;
import web.bean.RivistaBean;
import web.bean.SystemBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

@WebServlet("/GestioneOggettoServletRivista")

public class GestioneOggettoServletRivista extends HttpServlet{
    private final ModificaOggettoBean mOB=new ModificaOggettoBean();
    private final RivistaDao rD=new RivistaDao();
    private final SystemBean sB=SystemBean.getInstance();
    private final RivistaBean rB=new RivistaBean();
    private final Rivista r=new Rivista();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lista=req.getParameter("buttonGenera");
        String inserisci=req.getParameter("buttonAdd");
        String modifica=req.getParameter("buttonMod");
        String cancella=req.getParameter("buttonCanc");
        String indietro=req.getParameter("buttonI");
        String id=req.getParameter("idL");

        RequestDispatcher view;
        try {
            if(lista!=null && lista.equals("genera lista"))
            {
                mOB.setMiaListaB(rD.getRiviste());
                req.setAttribute("beanMOB",mOB);
                view= getServletContext().getRequestDispatcher("/gestioneOggettoPageRivista.jsp");
                view.forward(req,resp);
            }
            if(inserisci!=null && inserisci.equals("inserisci"))
            {
                view= getServletContext().getRequestDispatcher("/aggiungiRivistaPage.jsp");
                view.forward(req,resp);
            }
            if(modifica!=null && modifica.equals("modifica"))
            {
                int idL=Integer.parseInt(id);
                sB.setIdB(idL);
                rB.setIdB(sB.getIdB());
                r.setId(sB.getIdB());
                req.setAttribute("beanS",sB);
                req.setAttribute("beanR",rB);
                view= getServletContext().getRequestDispatcher("/modificaRivistaPage.jsp");
                view.forward(req,resp);
            }
            if(cancella!=null && cancella.equals("cancella"))
            {
                //same scene
                int idL=Integer.parseInt(id);
                sB.setIdB(idL);
                rB.setIdB(sB.getIdB());
                r.setId(sB.getIdB());
                if(rD.cancella(r)==1)
                {
                    view= getServletContext().getRequestDispatcher("/gestioneOggettoPageRivista.jsp");
                    view.forward(req,resp);
                }
                else {
                    view= getServletContext().getRequestDispatcher("/modificaRivistaPage.jsp");
                    view.forward(req,resp);
                }
            }
            if(indietro!=null && indietro.equals("indietro"))
            {
                view= getServletContext().getRequestDispatcher("/raccolta.jsp");
                view.forward(req,resp);
            }

        }catch (SQLException | IdException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

        }
    }
}
