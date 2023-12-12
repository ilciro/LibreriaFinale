package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.GiornaleDao;
import laptop.model.raccolta.Giornale;
import web.bean.GiornaleBean;
import web.bean.ModificaOggettoBean;
import web.bean.SystemBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

@WebServlet("/GestioneOggettoServletGiornale")
public class GestioneOggettoServletGiornale extends HttpServlet {

    private final ModificaOggettoBean mOB=new ModificaOggettoBean();
    private final GiornaleDao gD=new GiornaleDao();
    private final GiornaleBean gB=new GiornaleBean();
    private final Giornale g=new Giornale();
    private final SystemBean sB=SystemBean.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genera=req.getParameter("buttonGenera");
        String inserisci=req.getParameter("buttonAdd");
        String modifica=req.getParameter("buttonMod");
        String cancella=req.getParameter("buttonCanc");
        String indietro=req.getParameter("buttonI");
        RequestDispatcher view;
        try{

            if(genera!=null && genera.equals("genera lista"))
            {
                mOB.setMiaListaB(gD.getGiornali());
                req.setAttribute("beanMOB",mOB);
                view= getServletContext().getRequestDispatcher("/gestioneOggettoPageGiornale.jsp");
                view.forward(req,resp);
            }
            if(inserisci!=null && inserisci.equals("inserisci"))
            {
                view= getServletContext().getRequestDispatcher("/aggiungiGiornalePage.jsp");
                view.forward(req,resp);
            }
            if(modifica!=null && modifica.equals("modifica"))
            {
                int id=Integer.parseInt(req.getParameter("idL"));
                sB.setIdB(id);
                gB.setIdB(sB.getIdB());
                g.setId(gB.getIdB());

                req.setAttribute("beanS",sB);
                req.setAttribute("beanG",gB);
                view= getServletContext().getRequestDispatcher("/modificaGiornalePage.jsp");
                view.forward(req,resp);

            }
            if(cancella!=null && cancella.equals("cancella"))
            {
                //same scene
                int idL=Integer.parseInt(req.getParameter("idL"));
                sB.setIdB(idL);
                gB.setIdB(sB.getIdB());
                g.setId(sB.getIdB());
                if(gD.cancella(g)==1)
                {
                    view= getServletContext().getRequestDispatcher("/gestioneOggettoPageGiornale.jsp");
                    view.forward(req,resp);
                }
                else {
                    view= getServletContext().getRequestDispatcher("/modificaGiornalePage.jsp");
                    view.forward(req,resp);
                }
            }
            if(indietro!=null && indietro.equals("indietro"))
            {
                view= getServletContext().getRequestDispatcher("/raccolta.jsp");
                view.forward(req,resp);
            }


        }catch(SQLException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

        }
    }
}
