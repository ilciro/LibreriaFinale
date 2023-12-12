package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.LibroDao;
import laptop.exception.IdException;
import laptop.model.raccolta.Libro;
import web.bean.LibroBean;
import web.bean.ModificaOggettoBean;
import web.bean.SystemBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

@WebServlet("/GestioneOggettoServletLibro")

public class GestioneOggettoServletLibro extends HttpServlet {
    private final ModificaOggettoBean mOB=new ModificaOggettoBean();
    private final SystemBean sB=SystemBean.getInstance();
    private final LibroBean lB=new LibroBean();
    private final Libro l=new Libro();
    private final LibroDao lD=new LibroDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genera=req.getParameter("buttonGenera");
        String aggiungi=req.getParameter("buttonAdd");
        String id=req.getParameter("idL");
        String modifica=req.getParameter("buttonMod");
        String cancella=req.getParameter("buttonCanc");
        String indietro=req.getParameter("buttonI");
        RequestDispatcher view;

        try{
        if(genera!=null && genera.equals("genera lista"))
        {
            mOB.setMiaListaB(lD.getLibri());
            req.setAttribute("beanMOB",mOB);
            view= getServletContext().getRequestDispatcher("/gestioneOggettoPageLibro.jsp");
            view.forward(req,resp);
        }
        if(aggiungi!=null && aggiungi.equals("inserisci"))
        {
            view= getServletContext().getRequestDispatcher("/aggiungiLibroPage.jsp");
            view.forward(req,resp);
        }
        if(modifica!=null && modifica.equals("modifica"))
        {
            int idL=Integer.parseInt(id);
            sB.setIdB(idL);
            lB.setIdB(sB.getIdB());
            l.setId(sB.getIdB());
            //oggetto libro
            //li passo al bean


            req.setAttribute("beanS",sB);
            req.setAttribute("beanL",lB);
            view= getServletContext().getRequestDispatcher("/modificaLibroPage.jsp");
            view.forward(req,resp);

        }
        if(cancella!=null && cancella.equals("cancella"))
        {
            //same scene
            int idL=Integer.parseInt(id);
            sB.setIdB(idL);
            lB.setIdB(sB.getIdB());
            l.setId(sB.getIdB());
            if(lD.cancella(l)==1)
            {
                view= getServletContext().getRequestDispatcher("/gestioneOggettoPageLibro.jsp");
                view.forward(req,resp);
            }
            else {
                view= getServletContext().getRequestDispatcher("/modificaLibroPage.jsp");
                view.forward(req,resp);
            }
        }
        if(indietro!=null && indietro.equals("indietro"))
        {
            view= getServletContext().getRequestDispatcher("/raccolta.jsp");
            view.forward(req,resp);
        }
    }catch (SQLException |IdException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());
        }
    }
}
