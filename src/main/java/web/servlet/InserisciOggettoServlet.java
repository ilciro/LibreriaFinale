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
import web.bean.GiornaleBean;
import web.bean.LibroBean;
import web.bean.RivistaBean;
import web.bean.SystemBean;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

@WebServlet("/InserisciOggettoSevlet")

public class InserisciOggettoServlet extends HttpServlet {
    private final Libro l=new Libro();
    private final LibroBean lB=new LibroBean();
    private final LibroDao lD=new LibroDao();
    private final SystemBean sB=SystemBean.getInstance();
    private final GiornaleDao gD=new GiornaleDao();
    private final GiornaleBean gB=new GiornaleBean();
    private final Giornale g=new Giornale();
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
                switch (sB.getTypeB())
                {
                    case "libro":
                        String titolo=req.getParameter("titoloL");
                        String pagine=req.getParameter("nrPagL");
                        String codice=req.getParameter("codL");
                        String autore=req.getParameter("autoreL");
                        String editore=req.getParameter("editoreL");
                        String lingua=req.getParameter("linguaL");
                        String cat=req.getParameter("catS");
                        String dataL=req.getParameter("dataL");
                        String recensione=req.getParameter("recensioneL");
                        String desc=req.getParameter("descL");
                        String disp=req.getParameter("checkL");
                        String prezzo=req.getParameter("prezzoL");
                        String copie=req.getParameter("copieL");


                        lB.setTitoloB(titolo);
                        lB.setCodIsbnB(codice);
                        lB.setNumeroPagineB(Integer.parseInt(pagine));
                        lB.setEditoreB(editore);
                        lB.setAutoreB(autore);
                        lB.setLinguaB(lingua);
                        lB.setCategoriaB(cat);

                        java.util.Date utilDate;
                        java.sql.Date sqlDate;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                        utilDate = format.parse(dataL);

                        sqlDate = new java.sql.Date(utilDate.getTime());
                        lB.setDateB(sqlDate);


                        lB.setRecensioneB(recensione);
                        lB.setDescB(desc);
                        lB.setDisponibilitaB(0);
                        if (disp != null) {
                            lB.setDisponibilitaB(1);
                        }

                        lB.setPrezzoB(Float.parseFloat(prezzo));
                        lB.setNrCopieB(Integer.parseInt(copie));

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

                        //convert String to LocalDate
                        LocalDate localDate = LocalDate.parse(dataL, formatter);


                        l.setTitolo(lB.getTitoloB());
                        l.setNumeroPagine(lB.getNumeroPagineB());
                        l.setCodIsbn(lB.getCodIsbnB());
                        l.setEditore(lB.getEditoreB());
                        l.setAutore(lB.getAutoreB());
                        l.setLingua(lB.getLinguaB());
                        l.setCategoria(lB.getCategoriaB());
                        l.setDataPubb(localDate);
                        l.setRecensione(lB.getRecensioneB());
                        l.setDesc(lB.getDescB());
                        l.setDisponibilita(lB.getDisponibilitaB());
                        l.setPrezzo(lB.getPrezzoB());
                        l.setNrCopie(lB.getNrCopieB());


                        if (lD.creaLibrio(l)) {
                            lD.aggiornaData(l, sqlDate);
                            req.setAttribute("beanL", lB);
                            RequestDispatcher view = getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                            view.forward(req, resp);

                        } else {
                            RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiOggettoPage.jsp");
                            view.forward(req, resp);
                        }
                        break;
                    case "giornale":
                        String dataG=req.getParameter("dataG");

                        gB.setTitoloB(req.getParameter("titoloG"));
                        gB.setTipologiaB(req.getParameter("tipoG"));
                        gB.setLinguaB(req.getParameter("linguaG"));
                        gB.setEditoreB(req.getParameter("editoreG"));

                        java.util.Date utilDate1;
                        java.sql.Date sqlDate1;
                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
                        utilDate1 = format1.parse(dataG);
                        sqlDate1 = new java.sql.Date(utilDate1.getTime());
                        gB.setDataB(sqlDate1);
                        gB.setCopieRimanentiB(Integer.parseInt(req.getParameter("copieG")));
                        gB.setDisponibilitaB(Integer.parseInt(req.getParameter("dispG")));
                        gB.setPrezzoB(Float.parseFloat(req.getParameter("prezzoG")));

                        //setting giornale

                        g.setTitolo(gB.getTitoloB());
                        g.setTipologia(gB.getTipologiaB());
                        g.setLingua(gB.getLinguaB());
                        g.setEditore(gB.getEditoreB());

                        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        //convert String to LocalDate
                        LocalDate localDate1 = LocalDate.parse(dataG, formatter1);
                        g.setDataPubb(localDate1);
                        g.setCopieRimanenti(gB.getCopieRimanentiB());
                        g.setDisponibilita(gB.getDisponibilitaB());
                        g.setPrezzo(gB.getPrezzoB());

                        if(gD.creaGiornale(g))
                        {
                            gD.aggiornaData(g, sqlDate1);
                            req.setAttribute("beanG", gB);
                            RequestDispatcher view = getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                            view.forward(req, resp);
                        }
                        else{
                            RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiOggettoPage.jsp");
                            view.forward(req, resp);
                        }
                        break;
                    case "rivista":
                        rB.setTitoloB(req.getParameter("titoloL"));
                        rB.setTipologiaB(req.getParameter("catS"));
                        rB.setAutoreB(req.getParameter("autL"));
                        rB.setLinguaB(req.getParameter("linguaL"));
                        rB.setEditoreB(req.getParameter("editoreL"));
                        rB.setDescrizioneB(req.getParameter("descL"));

                        java.util.Date utilDate2;
                        java.sql.Date sqlDate2;
                        SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
                        utilDate2 = format2.parse(req.getParameter("dataL"));

                        sqlDate2 = new java.sql.Date(utilDate2.getTime());

                        rB.setDataB(sqlDate2);

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
                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");

                        //convert String to LocalDate
                        LocalDate localDate2 = LocalDate.parse(req.getParameter("dataL"), formatter2);
                        r.setDataPubb(localDate2);
                        r.setDisp(rB.getDispB());
                        r.setPrezzo(rB.getPrezzoB());
                        r.setCopieRim(rB.getCopieRimB());

                        if (rD.creaRivista(r)) {
                            rD.aggiornaData(r, sqlDate2);
                            req.setAttribute("beanR", rB);
                            RequestDispatcher view = getServletContext().getRequestDispatcher("/gestioneOggettoPageRivista.jsp");
                            view.forward(req, resp);

                        } else {
                            RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiRivistaPage.jsp");
                            view.forward(req, resp);
                        }
                        break;
                    default:break;


                }
            }
            if(annulla!=null && annulla.equals("indietro"))
            {
                RequestDispatcher view= getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                view.forward(req,resp);
            }
        }catch (SQLException | ParseException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

        }
    }
}
