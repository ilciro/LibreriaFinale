package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Rivista;
import web.bean.RivistaBean;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

@WebServlet("/InserisciRivistaServlet")

public class InserisciRivistaServlet extends HttpServlet {
    private final RivistaBean rB=new RivistaBean();
    private final Rivista r=new Rivista();
    private final RivistaDao rD=new RivistaDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String conferma=req.getParameter("confermaB");
        String annulla=req.getParameter("annullaB");
        try{
            if(conferma!=null && conferma.equals("conferma"))
            {
                rB.setTitoloB(req.getParameter("titoloL"));
                rB.setTipologiaB(req.getParameter("catS"));
                rB.setAutoreB(req.getParameter("autL"));
                rB.setLinguaB(req.getParameter("linguaL"));
                rB.setEditoreB(req.getParameter("editoreL"));
                rB.setDescrizioneB(req.getParameter("descL"));

                java.util.Date utilDate;
                java.sql.Date sqlDate;
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                utilDate = format.parse(req.getParameter("dataL"));

                sqlDate = new java.sql.Date(utilDate.getTime());

                rB.setDataB(sqlDate);

                if(req.getParameter("checkL")!=null)
                    rB.setDispB(1);
                else
                    rB.setDispB(0);
                rB.setPrezzoB(Float.parseFloat(req.getParameter("prezzoL")));
                rB.setCopieRimB(Integer.parseInt(req.getParameter("copieL")));

                r.setTitolo(rB.getTitoloB());
                r.setTipologia(rB.getTipologiaB());
                r.setAutore(rB.getAutoreB());
                r.setLingua(rB.getLinguaB());
                r.setEditore(rB.getEditoreB());
                r.setDescrizione(rB.getDescrizioneB());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

                //convert String to LocalDate
                LocalDate localDate = LocalDate.parse(req.getParameter("dataL"), formatter);
                r.setDataPubb(localDate);
                r.setDisp(rB.getDispB());
                r.setPrezzo(rB.getPrezzoB());
                r.setCopieRim(rB.getCopieRimB());

                if (rD.creaRivista(r)) {
                    rD.aggiornaData(r, sqlDate);
                    req.setAttribute("beanR", rB);
                    RequestDispatcher view = getServletContext().getRequestDispatcher("/gestioneOggettoPageRivista.jsp");
                    view.forward(req, resp);

                } else {
                    RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiRivistaPage.jsp");
                    view.forward(req, resp);
                }

            }
            if(annulla!=null && annulla.equals("indietro"))
            {
                RequestDispatcher view= getServletContext().getRequestDispatcher("/gestioneOggettoPageRivista.jsp");
                view.forward(req,resp);
            }

        }catch (SQLException  | ParseException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

        }


    }
}
