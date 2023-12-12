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

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

@WebServlet("/InserisciGiornaleServlet")

public class InserisciGiornaleServlet extends HttpServlet {

    private final GiornaleDao gD=new GiornaleDao();
    private final GiornaleBean gB=new GiornaleBean();
    private final Giornale g=new Giornale();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String conferma=req.getParameter("confermaB");
       String annulla=req.getParameter("annullaB");
        String dataG=req.getParameter("dataG");


        try{
           if(conferma!=null && conferma.equals("conferma"))
           {
               gB.setTitoloB(req.getParameter("titoloG"));
               gB.setTipologiaB(req.getParameter("tipoG"));
               gB.setLinguaB(req.getParameter("linguaG"));
               gB.setEditoreB(req.getParameter("editoreG"));

               java.util.Date utilDate;
               java.sql.Date sqlDate;
               SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
               utilDate = format.parse(dataG);
               sqlDate = new java.sql.Date(utilDate.getTime());
               gB.setDataB(sqlDate);
               gB.setCopieRimanentiB(Integer.parseInt(req.getParameter("copieG")));
               gB.setDisponibilitaB(Integer.parseInt(req.getParameter("dispG")));
               gB.setPrezzoB(Float.parseFloat(req.getParameter("prezzoG")));

               //setting giornale

               g.setTitolo(gB.getTitoloB());
               g.setTipologia(gB.getTipologiaB());
               g.setLingua(gB.getLinguaB());
               g.setEditore(gB.getEditoreB());

               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
               //convert String to LocalDate
               LocalDate localDate = LocalDate.parse(dataG, formatter);
               g.setDataPubb(localDate);
               g.setCopieRimanenti(gB.getCopieRimanentiB());
               g.setDisponibilita(gB.getDisponibilitaB());
               g.setPrezzo(gB.getPrezzoB());

               if(gD.creaGiornale(g))
               {
                   gD.aggiornaData(g, sqlDate);
                   req.setAttribute("beanG", gB);
                   RequestDispatcher view = getServletContext().getRequestDispatcher("/gestioneOggettoPageGiornale.jsp");
                   view.forward(req, resp);
               }
               else{
                   RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiGiornalePage.jsp");
                   view.forward(req, resp);
               }

           }
           if(annulla!=null && annulla.equals("indietro"))
           {
               RequestDispatcher view= getServletContext().getRequestDispatcher("/gestioneOggettoPagegiornale.jsp");
               view.forward(req,resp);
           }

       }catch (SQLException | ParseException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

        }
    }
}
