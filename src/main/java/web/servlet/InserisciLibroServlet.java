package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.LibroDao;
import laptop.model.raccolta.Libro;
import org.apache.ibatis.jdbc.SQL;
import web.bean.LibroBean;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/InserisciLibroServlet")

public class InserisciLibroServlet extends HttpServlet {
    private final Libro l=new Libro();
    private final LibroBean lB=new LibroBean();
    private final LibroDao lD=new LibroDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String conferma=req.getParameter("confermaB");
        String annulla=req.getParameter("annullaB");
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

        try {

            if (conferma != null && conferma.equals("conferma")) {
                java.util.Date utilDate;
                java.sql.Date sqlDate;

                lB.setTitoloB(titolo);
                lB.setCodIsbnB(codice);
                lB.setNumeroPagineB(Integer.parseInt(pagine));
                lB.setEditoreB(editore);
                lB.setAutoreB(autore);
                lB.setLinguaB(lingua);
                lB.setCategoriaB(cat);

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
                    req.setAttribute("bean", lB);
                    RequestDispatcher view = getServletContext().getRequestDispatcher("/gestioneOggettoPageLibro.jsp");
                    view.forward(req, resp);

                } else {
                    RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiLibroPage.jsp");
                    view.forward(req, resp);
                }


            }
        }catch (SQLException | ParseException e) {throw new RuntimeException();}

    }
}
