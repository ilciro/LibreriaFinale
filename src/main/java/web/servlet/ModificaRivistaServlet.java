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
import web.bean.RivistaBean;
import web.bean.SystemBean;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

@WebServlet("/ModificaRivistaServlet")
public class ModificaRivistaServlet extends HttpServlet {
    private final RivistaDao rD=new RivistaDao();
    private final Rivista r=new Rivista();
    private final RivistaBean rB=new RivistaBean();
    private final SystemBean sB=SystemBean.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String lista=req.getParameter("listaB");
        String invia=req.getParameter("buttonI");
        String annulla=req.getParameter("buttonA");
        RequestDispatcher view;
        try{
            if(lista!=null && lista.equals("prendi dati")) {

                rB.setIdB(sB.getIdB());
                r.setId(rB.getIdB());
                rB.setTitoloB(rD.getRivista(r).getTitolo());
                rB.setTipologiaB(rD.retTip(r));
                rB.setAutoreB(rD.getRivista(r).getAutore());
                rB.setLinguaB(rD.getRivista(r).getLingua());
                rB.setEditoreB(rD.getRivista(r).getEditore());
                rB.setDescrizioneB(rD.getRivista(r).getDescrizione());
                rB.setDataB(Date.valueOf(rD.getRivista(r).getDataPubb()));
                rB.setDispB(rD.getRivista(r).getDisp());
                rB.setPrezzoB(rD.getRivista(r).getPrezzo());
                rB.setCopieRimB(rD.getRivista(r).getCopieRim());


                req.setAttribute("beanS",sB);
                req.setAttribute("beanR",rB);
                view= getServletContext().getRequestDispatcher("/modificaRivistaPage.jsp");
                view.forward(req,resp);



            }
            if(invia!=null && invia.equals("aggiorna"))
            {
                rB.setTitoloB(req.getParameter("titoloNR"));
                rB.setTipologiaB(req.getParameter("categoriaNR"));
                rB.setAutoreB(req.getParameter("autoreNR"));
                rB.setLinguaB(req.getParameter("linguaNR"));
                rB.setEditoreB(req.getParameter("editoreNR"));
                rB.setDescrizioneB(req.getParameter("descNR"));
                java.util.Date utilDate;
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                utilDate = format.parse(req.getParameter("dataNR"));
                Date sqlDate = new java.sql.Date(utilDate.getTime());
                rB.setDataB(sqlDate);
                rB.setDispB(Integer.parseInt(req.getParameter("dispNR")));
                rB.setPrezzoB(Float.parseFloat(req.getParameter("prezzoNR")));
                rB.setCopieRimB(Integer.parseInt(req.getParameter("copieNR")));

                r.setTitolo(rB.getTitoloB());
                r.setTipologia(rB.getTipologiaB());
                r.setAutore(rB.getAutoreB());
                r.setLingua(rB.getLinguaB());
                r.setEditore(rB.getEditoreB());
                r.setDescrizione(rB.getDescrizioneB());
                r.setDataPubb(rB.getDataB().toLocalDate());
                r.setDisp(rB.getDispB());
                r.setPrezzo(rB.getPrezzoB());
                r.setCopieRim(rB.getCopieRimB());
                rD.aggiornaRivista(r);

                req.setAttribute("beanR",rB);
                view= getServletContext().getRequestDispatcher("/gestioneOggettoPageRivista.jsp");
                view.forward(req,resp);

            }
            if(annulla!=null && annulla.equals("indietro"))
            {
                view= getServletContext().getRequestDispatcher("/gestioneOggettoPageRivista.jsp");
                view.forward(req,resp);
            }
        }catch (SQLException | ParseException | IdException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

        }
    }
}
