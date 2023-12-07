package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import laptop.database.CartaCreditoDao;
import web.bean.CartaCreditoBean;
import web.bean.LibroBean;
import web.bean.PagamentoBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.PagamentoDao;
import laptop.model.CartaDiCredito;
import laptop. model.Pagamento;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;


@WebServlet("/CartaCreditoServlet")
public class CartaCreditoServlet extends HttpServlet {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static CartaCreditoBean ccB=new CartaCreditoBean();
    private static CartaDiCredito cc=new CartaDiCredito();
    private static Libro l=new Libro();
    private static LibroBean lB=new LibroBean();
    private static Giornale g=new Giornale();

    private final PagamentoBean pB=new PagamentoBean();

    private static PagamentoDao pD=new PagamentoDao();

    private final CartaCreditoDao cCD=new CartaCreditoDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String n=req.getParameter("nomeL");
        String c=req.getParameter("cognomeL");
        String numero=req.getParameter("cartaL");
        String scad=req.getParameter("scadL");
        String civ=req.getParameter("passL");
        String invia=req.getParameter("buttonI");
        String annulla=req.getParameter("buttonA");
        String registra=req.getParameter("regB");
        String generaLista=req.getParameter("prendiDB");
        try {
            if(annulla!=null && annulla.equals("annulla"))
            {
                RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp");
                view.forward(req,resp);
            }
            if(invia!=null && invia.equals("paga"))
            {
                ccB.setNomeB(n);
                ccB.setCivB(c);
                ccB.setNumeroCCB(numero);
                ccB.setCognomeB(c);
                ccB.setDataScadB(new SimpleDateFormat("yyyy/mm/dd").parse(scad));
                ccB.setCivB(civ);
                ccB.setPrezzoTransazioneB(SystemBean.getInstance().getSpesaTB());

                Date sqlDate = null;
                java.util.Date utilDate;
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");


                utilDate = format.parse(scad);
                sqlDate = new java.sql.Date(utilDate.getTime());

                //inserire cc

                cc.setNomeUser(ccB.getNomeB());
                cc.setCognomeUser(ccB.getCognomeB());
                cc.setNumeroCC(ccB.getNumeroCCB());
                cc.setScadenza(sqlDate);
                cc.setCiv(ccB.getCivB());
                cc.setAmmontare(ccB.getPrezzoTransazioneB());
                cCD.insCC(cc);


                //inserire pagamento

                pB.setIdB(0);
                pB.setMetodoB("cCredito");
                pB.setEsitoB(0);
                pB.setNomeUtenteB(n);
                pB.setAmmontareB(SystemBean.getInstance().getSpesaTB());
                pB.setTipoB(SystemBean.getInstance().getCategoriaB());
                pB.setIdOggettoB(SystemBean.getInstance().getIdB());

                Pagamento p=new Pagamento();

                p.setId(pB.getIdB());
                p.setMetodo(pB.getMetodoB());
                p.setEsito(pB.getEsitoB());
                p.setNomeUtente(pB.getNomeUtenteB());
                p.setAmmontare(pB.getAmmontareB());
                p.setTipo(pB.getTipoB());
                p.setIdOggetto(pB.getIdOggettoB());



                if(SystemBean.getInstance().isNegozioSelezionatoB())
                {
                    req.setAttribute("beanS",SystemBean.getInstance());

                    RequestDispatcher view = getServletContext().getRequestDispatcher("/negozi.jsp");
                    view.forward(req,resp);
                }
                else {
                    pD.inserisciPagamento(p);

                    req.setAttribute("beanS",SystemBean.getInstance());
                    RequestDispatcher view = getServletContext().getRequestDispatcher("/download.jsp");
                    view.forward(req,resp);
                }

            }
            if(registra!=null && registra.equals("registra e paga"))
            {
                java.util.logging.Logger.getLogger("post registra ").log(Level.INFO, "da fare");

            }
            if(generaLista!=null && generaLista.equals("generaLista"))
            {
                java.util.logging.Logger.getLogger("post genera").log(Level.INFO, "da fare");

            }

        } catch (ParseException   e) {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}