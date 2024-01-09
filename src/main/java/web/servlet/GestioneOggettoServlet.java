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
                switch (sB.getTypeB())
                {
                    case "libro":
                        mOB.setMiaListaB(lD.getLibri());
                        req.setAttribute("beanMOB",mOB);
                        break;
                    case "giornale":
                        mOB.setMiaListaB(gD.getGiornaliIdTitoloAutore(new Giornale()));
                        req.setAttribute("beanMOB",mOB);
                        break;
                    case "rivista":
                        mOB.setMiaListaB(rD.getRiviste());
                        req.setAttribute("beanMOB",mOB);
                        break;
                    default:break;
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
                    case "libro":
                        idOgg=Integer.parseInt(id);
                        sB.setIdB(idOgg);
                        lB.setIdB(sB.getIdB());
                        l.setId(sB.getIdB());
                        //oggetto libro
                        //li passo al bean
                        req.setAttribute("beanS",sB);
                        req.setAttribute("beanL",lB);
                        break;
                    case "giornale":
                        idOgg=Integer.parseInt(req.getParameter("idL"));
                        sB.setIdB(idOgg);
                        gB.setIdB(sB.getIdB());
                        g.setId(gB.getIdB());
                        req.setAttribute("beanS",sB);
                        req.setAttribute("beanG",gB);
                        break;
                    case "rivista":
                        idOgg=Integer.parseInt(id);
                        sB.setIdB(idOgg);
                        rB.setIdB(sB.getIdB());
                        r.setId(sB.getIdB());
                        req.setAttribute("beanS",sB);
                        req.setAttribute("beanR",rB);
                        break;
                    default:break;



                }
                view= getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
                view.forward(req,resp);


            }
            if(cancella!=null && cancella.equals("cancella"))
            {
                switch (sB.getTypeB())
                {
                    case "libro":
                        int idL=Integer.parseInt(id);
                        sB.setIdB(idL);
                        lB.setIdB(sB.getIdB());
                        l.setId(sB.getIdB());
                        if(lD.cancella(l)==1)
                        {
                            view= getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                            view.forward(req,resp);
                        }
                        else {
                            view= getServletContext().getRequestDispatcher("/modificaLibroPage.jsp");
                            view.forward(req,resp);
                        }
                        break;
                    case "giornale":
                        int idG=Integer.parseInt(id);
                        sB.setIdB(idG);
                        gB.setIdB(sB.getIdB());
                        g.setId(sB.getIdB());
                        if(gD.cancella(g)==1)
                        {
                            view= getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                            view.forward(req,resp);
                        }
                        else {
                            view= getServletContext().getRequestDispatcher("/modificaLibroPage.jsp");
                            view.forward(req,resp);
                        }
                        break;
                    case "rivista":
                        int idR=Integer.parseInt(id);
                        sB.setIdB(idR);
                        rB.setIdB(sB.getIdB());
                        r.setId(sB.getIdB());
                        if(rD.cancella(r)==1)
                        {
                            view= getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                            view.forward(req,resp);
                        }
                        else {
                            view= getServletContext().getRequestDispatcher("/modificaLibroPage.jsp");
                            view.forward(req,resp);
                        }
                }
                //same scene

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
