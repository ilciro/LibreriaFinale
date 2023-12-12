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
import web.bean.SystemBean;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

@WebServlet("/ModificaGiornaleServlet")
public class ModificaGiornaleServlet extends HttpServlet {
    private final GiornaleDao gD=new GiornaleDao();
    private final GiornaleBean gB=new GiornaleBean();
    private final Giornale g=new Giornale();
    private final SystemBean sB=SystemBean.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lista=req.getParameter("buttonG");
        String aggiorna=req.getParameter("confermaB");
        String annulla=req.getParameter("annullaB");
        RequestDispatcher view;
        try{
            if(lista!=null && lista.equals("prendi dati"))
            {

                g.setId(sB.getIdB());
                gB.setTitoloB(gD.getGiornale(g).getTitolo());
                gB.setTipologiaB("QUOTIDIANO");
                gB.setLinguaB(gD.getGiornale(g).getLingua());
                gB.setEditoreB(gD.getGiornale(g).getEditore());
                gB.setDataB(Date.valueOf(gD.getGiornale(g).getDataPubb()));
                gB.setCopieRimanentiB(gD.getGiornale(g).getCopieRimanenti());
                gB.setDisponibilitaB(gD.getGiornale(g).getDisponibilita());
                gB.setPrezzoB(gD.getCosto(g));

                req.setAttribute("beanS",sB);
                req.setAttribute("beanG",gB);

                view=getServletContext().getRequestDispatcher("/modificaGiornalePage.jsp");
                view.forward(req,resp);
            }
            if(aggiorna!=null && aggiorna.equals("conferma"))
            {
                gB.setTitoloB(req.getParameter("titoloNG"));
                gB.setTipologiaB(req.getParameter("tipoG"));
                gB.setLinguaB(req.getParameter("linguaNG"));
                gB.setEditoreB(req.getParameter("editoreNG"));
                java.util.Date utilDate;

                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");


                utilDate = format.parse(req.getParameter("dataNG"));
                Date sqlDate = new java.sql.Date(utilDate.getTime());
                gB.setDataB(sqlDate);
                gB.setCopieRimanentiB(Integer.parseInt(req.getParameter("copieNG")));
                gB.setDisponibilitaB(Integer.parseInt(req.getParameter("dispNG")));
                gB.setPrezzoB(Float.parseFloat(req.getParameter("prezzoNG")));

                g.setTitolo(gB.getTitoloB());
                g.setTipologia(gB.getTipologiaB());
                g.setLingua(gB.getLinguaB());
                g.setEditore(gB.getEditoreB());
                g.setDataPubb(gB.getDataB().toLocalDate());
                g.setCopieRimanenti(gB.getCopieRimanentiB());
                g.setDisponibilita(gB.getDisponibilitaB());
                g.setPrezzo(gB.getPrezzoB());

                if(gD.aggiornaGiornale(g)==1)
                {
                    req.setAttribute("beanG",gB);
                    view= getServletContext().getRequestDispatcher("/gestioneOggettoPageGiornale.jsp");
                    view.forward(req,resp);
                }
                else{

                    view= getServletContext().getRequestDispatcher("/modificaGiornalePage.jsp");
                    view.forward(req,resp);
                }


            }
            if(annulla!=null && annulla.equals("indietro"))
            {
                view= getServletContext().getRequestDispatcher("/gestioneOggettoPageGiornale.jsp");
                view.forward(req,resp);
            }

        }catch(SQLException | ParseException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

        }
    }
}
