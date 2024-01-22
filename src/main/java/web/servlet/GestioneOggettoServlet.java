package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;
import web.bean.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

@WebServlet("/GestioneOggettoServlet")
public class GestioneOggettoServlet extends HttpServlet {
    private final SystemBean sB=SystemBean.getInstance();
    private final ModificaOggettoBean mOB=new ModificaOggettoBean();

    private final Libro l=new Libro();
    private final LibroDao lD=new LibroDao();
    private final GiornaleDao gD=new GiornaleDao();
    private final Giornale g=new Giornale();
    private final Rivista r=new Rivista();
    private final RivistaDao rD=new RivistaDao();
    private final LibroBean lB=new LibroBean();
    private final RivistaBean rB=new RivistaBean();
    private final GiornaleBean gB=new GiornaleBean();
    private static final String LIBRO="libro";
    private static final String GIORNALE="giornale";
    private static final String RIVISTA="rivista";

    public GestioneOggettoServlet() throws IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genera=req.getParameter("buttonGenera");
        String aggiungi=req.getParameter("buttonAdd");
        String id=req.getParameter("idL");
        String modifica=req.getParameter("buttonMod");
        String cancella=req.getParameter("buttonCanc");
        String indietro=req.getParameter("buttonI");
        int idOgg;
        RequestDispatcher view;
        try{
            if(genera!=null && genera.equals("genera lista"))
            {
                switch (sB.getTypeB()) {
                    case LIBRO -> {
                        mOB.setMiaListaB(lD.getLibri());
                        req.setAttribute("beanS", sB);
                        req.setAttribute("beanL", lB);
                        req.setAttribute("beanMOB", mOB);
                    }
                    case GIORNALE -> {

                    mOB.setMiaListaB(gD.getGiornali());
                    req.setAttribute("beanS", sB);
                    req.setAttribute("beanG", gB);
                    req.setAttribute("beanMOB", mOB);
                    }
                    case RIVISTA-> {
                        mOB.setMiaListaB(rD.getRiviste());
                        req.setAttribute("beanS", sB);
                        req.setAttribute("beanR", rB);
                        req.setAttribute("beanMOB", mOB);
                    }
                    default->	java.util.logging.Logger.getLogger("genera lista").log(Level.SEVERE, "choice not correct");

                }
                req.setAttribute("beanS",sB);
                view= getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                view.forward(req,resp);
            }
            if(aggiungi!=null && aggiungi.equals("inserisci"))
            {
                req.setAttribute("beanS",sB);
                view= getServletContext().getRequestDispatcher("/aggiungiOggettoPage.jsp");
                view.forward(req,resp);
            }
            if(modifica!=null && modifica.equals("modifica"))
            {
                switch (sB.getTypeB())
                {
                    case LIBRO-> {

                        sB.setIdB(lD.getIdMax());
                        lB.setIdB(lD.getIdMax());
                        l.setId(lB.getIdB());

                        req.setAttribute("beanS", sB);
                        req.setAttribute("beanL", lB);
                    }
                    case GIORNALE -> {

                        sB.setIdB(gD.getIdMax());
                        gB.setIdB(gD.getIdMax());
                        g.setId(gB.getIdB());
                        req.setAttribute("beanS", sB);
                        req.setAttribute("beanG", gB);
                    }
                    case RIVISTA -> {
                        sB.setIdB(rD.getIdMax());
                        rB.setIdB(rD.getIdMax());
                        r.setId(sB.getIdB());
                        req.setAttribute("beanS", sB);
                        req.setAttribute("beanR", rB);
                    }
                    default->	java.util.logging.Logger.getLogger("modifica").log(Level.SEVERE, "modif error");

                }
                view= getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
                view.forward(req,resp);


            }
            if(cancella!=null && cancella.equals("cancella"))
            {
                switch (sB.getTypeB())
                {
                    case LIBRO-> {

                        lB.setIdB(sB.getIdB());
                        l.setId(sB.getIdB());
                        if (lD.cancella(l) == 1) {
                            view = getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                            view.forward(req, resp);
                        } else {
                            view = getServletContext().getRequestDispatcher("/modificaLibroPage.jsp");
                            view.forward(req, resp);
                        }
                    }
                    case GIORNALE -> {

                        int idG = Integer.parseInt(id);
                        sB.setIdB(idG);
                        gB.setIdB(sB.getIdB());
                        g.setId(sB.getIdB());
                        if (gD.cancella(g) == 1) {
                            view = getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                            view.forward(req, resp);
                        } else {
                            view = getServletContext().getRequestDispatcher("/modificaLibroPage.jsp");
                            view.forward(req, resp);
                        }
                    }
                    case RIVISTA -> {
                        int idR = Integer.parseInt(id);
                        sB.setIdB(idR);
                        rB.setIdB(sB.getIdB());
                        r.setId(sB.getIdB());
                        if (rD.cancella(r) == 1) {
                            view = getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                            view.forward(req, resp);
                        } else {
                            view = getServletContext().getRequestDispatcher("/modificaLibroPage.jsp");
                            view.forward(req, resp);
                        }
                    }
                    default->java.util.logging.Logger.getLogger("cancella").log(Level.SEVERE, "delete error");

                }


            }
            if(indietro!=null && indietro.equals("indietro"))
            {
                view= getServletContext().getRequestDispatcher("/raccolta.jsp");
                view.forward(req,resp);
            }


        }catch (SQLException  e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());
        }
    }
}
