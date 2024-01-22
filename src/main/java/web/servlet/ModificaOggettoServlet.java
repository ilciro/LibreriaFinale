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
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

@WebServlet("/ModificaOggettoServlet")
public class ModificaOggettoServlet extends HttpServlet {
    private final ModificaOggettoBean mOB=new ModificaOggettoBean();
    private final SystemBean sB=SystemBean.getInstance();
    private final LibroDao lD=new LibroDao();
    private final Libro l=new Libro();
    private final LibroBean lB=new LibroBean();
    private final GiornaleDao gD=new GiornaleDao();
    private final GiornaleBean gB=new GiornaleBean();
    private final Giornale g=new Giornale();
    private final RivistaDao rD=new RivistaDao();
    private final Rivista r=new Rivista();
    private final RivistaBean rB=new RivistaBean();
    private static final String LIBRO="libro";
    private static final String GIORNALE="giornale";
    private static final String RIVISTA="rivista";

    public ModificaOggettoServlet() throws IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lista=req.getParameter("listaB");
        String invia=req.getParameter("buttonI");
        String annulla=req.getParameter("buttonA");
        RequestDispatcher view;
        try{
            if(lista!=null && lista.equals("prendi dati"))
            {
                switch (sB.getTypeB())
                {
                    case LIBRO-> {
                        lB.setIdB(sB.getIdB());
                        l.setId(lB.getIdB());

                        //passo tutti i valori al bean

                        lB.setTitoloB(lD.getData(l).getTitolo());
                        lB.setNumeroPagineB(lD.getData(l).getNrCopie());
                        lB.setCodIsbnB(lD.getData(l).getCodIsbn());
                        lB.setEditoreB(lD.getData(l).getEditore());
                        lB.setAutoreB(lD.getData(l).getAutore());
                        lB.setLinguaB(lD.getData(l).getLingua());
                        lB.setCategoriaB(lD.getData(l).getCategoria());
                        lB.setDateB(Date.valueOf(lD.getData(l).getDataPubb()));
                        lB.setRecensioneB(lD.getData(l).getRecensione());
                        lB.setDescB(lD.getData(l).getDesc());
                        lB.setDisponibilitaB(lD.getData(l).getDisponibilita());
                        lB.setPrezzoB(lD.getData(l).getPrezzo());
                        lB.setNrCopieB(lD.getData(l).getNrCopie());


                        req.setAttribute("beanS", sB);
                        req.setAttribute("beanL", lB);
                    }
                    case GIORNALE-> {
                        g.setId(sB.getIdB());
                        gB.setTitoloB(gD.getData(g).getTitolo());
                        gB.setTipologiaB("QUOTIDIANO");
                        gB.setLinguaB(gD.getData(g).getLingua());
                        gB.setEditoreB(gD.getData(g).getEditore());
                        gB.setDataB(Date.valueOf(gD.getData(g).getDataPubb()));
                        gB.setCopieRimanentiB(gD.getData(g).getCopieRimanenti());
                        gB.setDisponibilitaB(gD.getData(g).getDisponibilita());
                        gB.setPrezzoB(gD.getData(g).getPrezzo());

                        req.setAttribute("beanS", sB);
                        req.setAttribute("beanG", gB);
                    }
                    case RIVISTA-> {
                        rB.setIdB(sB.getIdB());
                        r.setId(rB.getIdB());
                        rB.setTitoloB(rD.getData(r).getTitolo());
                        rB.setTipologiaB(rD.getData(r).getTipologia());
                        rB.setAutoreB(rD.getData(r).getAutore());
                        rB.setLinguaB(rD.getData(r).getLingua());
                        rB.setEditoreB(rD.getData(r).getEditore());
                        rB.setDescrizioneB(rD.getData(r).getDescrizione());
                        rB.setDataB(Date.valueOf(rD.getData(r).getDataPubb()));
                        rB.setDispB(rD.getData(r).getDisp());
                        rB.setPrezzoB(rD.getData(r).getPrezzo());
                        rB.setCopieRimB(rD.getData(r).getCopieRim());


                        req.setAttribute("beanS", sB);
                        req.setAttribute("beanR", rB);
                    }
                    default->java.util.logging.Logger.getLogger("modif").log(Level.SEVERE, "modif error");

                }
                view= getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
                view.forward(req,resp);


            }
            if(invia!=null && invia.equals("aggiorna"))
            {
                switch (sB.getTypeB())
                {
                    case LIBRO-> {
                        lB.setTitoloB(req.getParameter("titoloNL"));
                        lB.setNumeroPagineB(Integer.parseInt(req.getParameter("pagineNL")));
                        lB.setCodIsbnB(req.getParameter("codiceNL"));
                        lB.setEditoreB(req.getParameter("editoreNL"));
                        lB.setAutoreB(req.getParameter("autoreNL"));
                        lB.setLinguaB(req.getParameter("linguaNL"));
                        lB.setCategoriaB(req.getParameter("categoriaNL"));


                        java.util.Date utilDate;

                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");


                        utilDate = format.parse(req.getParameter("dataNL"));
                        Date sqlDate = new java.sql.Date(utilDate.getTime());


                        lB.setDateB(sqlDate);
                        lB.setRecensioneB(req.getParameter("recNL"));
                        lB.setNrCopieB(Integer.parseInt(req.getParameter("copieNL")));
                        lB.setDescB(req.getParameter("dispNL"));
                        lB.setPrezzoB(Float.parseFloat(req.getParameter("prezzoNL")));

                        l.setTitolo(lB.getTitoloB());
                        l.setNrPagine(lB.getNumeroPagineB());
                        l.setCodIsbn(lB.getCodIsbnB());
                        l.setEditore(lB.getEditoreB());
                        l.setAutore(lB.getAutoreB());
                        l.setLingua(lB.getLinguaB());
                        l.setCategoria(lB.getCategoriaB());


                        l.setDataPubb(lB.getDateB().toLocalDate());
                        l.setRecensione(lB.getRecensioneB());
                        l.setNrCopie(lB.getNrCopieB());
                        l.setDesc(lB.getDescB());
                        l.setPrezzo(lB.getPrezzoB());

                        if (lD.aggiornaLibro(l)) {

                            req.setAttribute("beanL", lB);
                            view = getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                            view.forward(req, resp);
                        } else {
                            req.setAttribute("beanL", lB);
                            view = getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
                            view.forward(req, resp);
                        }
                    }
                    case GIORNALE-> {
                        gB.setTitoloB(req.getParameter("titoloNG"));
                        gB.setTipologiaB(req.getParameter("tipoG"));
                        gB.setLinguaB(req.getParameter("linguaNG"));
                        gB.setEditoreB(req.getParameter("editoreNG"));
                        java.util.Date utilDate1;

                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");


                        utilDate1 = format1.parse(req.getParameter("dataNG"));
                        Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
                        gB.setDataB(sqlDate1);
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

                        if (gD.aggiornaGiornale(g) == 1) {
                            req.setAttribute("beanG", gB);
                            view = getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                            view.forward(req, resp);
                        } else {
                            req.setAttribute("beanG", gB);
                            view = getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
                            view.forward(req, resp);
                        }
                    }
                    case RIVISTA-> {
                        rB.setTitoloB(req.getParameter("titoloNR"));
                        rB.setTipologiaB(req.getParameter("categoriaNR"));
                        rB.setAutoreB(req.getParameter("autoreNR"));
                        rB.setLinguaB(req.getParameter("linguaNR"));
                        rB.setEditoreB(req.getParameter("editoreNR"));
                        rB.setDescrizioneB(req.getParameter("descNR"));
                        java.util.Date utilDate2;
                        SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
                        utilDate2 = format2.parse(req.getParameter("dataNR"));
                        Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
                        rB.setDataB(sqlDate2);
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

                        if (rD.aggiornaRivista(r) == 1) {
                            req.setAttribute("beanR", rB);
                            view = getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                            view.forward(req, resp);
                        } else {
                            req.setAttribute("beanR", rB);
                            view = getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
                            view.forward(req, resp);
                        }
                    }
                    default->java.util.logging.Logger.getLogger("update").log(Level.SEVERE, "update error");

                }
            }
            if(annulla!=null && annulla.equals("indietro"))
            {
                view= getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp");
                view.forward(req,resp);
            }

        }catch (SQLException | ParseException e)
        {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

        }
    }
}
