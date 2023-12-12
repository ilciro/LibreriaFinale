package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.LibroDao;
import laptop.model.raccolta.Libro;
import web.bean.LibroBean;
import web.bean.ModificaOggettoBean;
import web.bean.SystemBean;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Level;

@WebServlet("/ModificaLibroServlet")

public class ModificaLibroServlet extends HttpServlet {

    private final ModificaOggettoBean mOB=new ModificaOggettoBean();
    private final SystemBean sB=SystemBean.getInstance();
    private final LibroDao lD=new LibroDao();
    private final Libro l=new Libro();
    private final LibroBean lB=new LibroBean();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lista=req.getParameter("listaB");
        String invia=req.getParameter("buttonI");
        String annulla=req.getParameter("buttonA");
        RequestDispatcher view;

        try {
        if(lista!=null && lista.equals("prendi dati"))
        {

                l.setId(sB.getIdB());

                //passo tutti i valori al bean

                lB.setTitoloB(lD.getLibro(l).getTitolo());
                lB.setNumeroPagineB(lD.getLibro(l).getNrCopie());
                lB.setCodIsbnB(lD.getLibro(l).getCodIsbn());
                lB.setEditoreB(lD.getLibro(l).getEditore());
                lB.setAutoreB(lD.getLibro(l).getAutore());
                lB.setLinguaB(lD.getLibro(l).getLingua());
                lB.setCategoriaB(lD.getLibro(l).getCategoria());
                lB.setDateB(Date.valueOf(lD.getLibro(l).getDataPubb()));
                lB.setRecensioneB(lD.getLibro(l).getRecensione());
                lB.setDescB(lD.getLibro(l).getDesc());
                lB.setDisponibilitaB(lD.getLibro(l).getDisponibilita());
                lB.setPrezzoB(lD.getLibro(l).getPrezzo());
                lB.setNrCopieB(lD.getLibro(l).getNrCopie());


                req.setAttribute("beanS",sB);
                req.setAttribute("beanL",lB);
                view= getServletContext().getRequestDispatcher("/modificaLibroPage.jsp");
                view.forward(req,resp);


            }
        if(invia!=null && invia.equals("aggiorna"))
        {
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
            l.setNumeroPagine(lB.getNumeroPagineB());
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

            lD.aggiornaLibro(l);

            req.setAttribute("beanL",lB);
            view= getServletContext().getRequestDispatcher("/gestioneOggettoPageLibro.jsp");
            view.forward(req,resp);

        }
        if(annulla!=null && annulla.equals("indietro"))
        {
            view= getServletContext().getRequestDispatcher("/gestioneOggettoPageLibro.jsp");
            view.forward(req,resp);
        }
        }catch (SQLException | ParseException e) {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());
        }

    }

}
