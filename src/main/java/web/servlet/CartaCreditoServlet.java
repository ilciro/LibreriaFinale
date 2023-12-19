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
import laptop.exception.IdException;
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
    private final SystemBean sB=SystemBean.getInstance();
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
        String nomeUtente= req.getParameter("utenteL");
        String cartePossedute=req.getParameter("scegliCarta");
        RequestDispatcher view;
        try {
            if(annulla!=null && annulla.equals("annulla"))
            {
                 view = getServletContext().getRequestDispatcher("/acquista.jsp");
                view.forward(req,resp);
            }
            if(invia!=null && invia.equals("paga"))
            {

                //inserire pagamento

                pB.setIdB(0);
                pB.setMetodoB("cCredito");
                pB.setEsitoB(0);
                pB.setNomeUtenteB(n);
                pB.setAmmontareB(sB.getSpesaTB());
                pB.setTipoB(sB.getCategoriaB());
                pB.setIdOggettoB(sB.getIdB());

                Pagamento p=new Pagamento();

                p.setId(pB.getIdB());
                p.setMetodo(pB.getMetodoB());
                p.setEsito(pB.getEsitoB());
                p.setNomeUtente(pB.getNomeUtenteB());
                p.setAmmontare(pB.getAmmontareB());
                p.setTipo(pB.getTipoB());
                p.setIdOggetto(pB.getIdOggettoB());

                pD.inserisciPagamento(p);


                if(SystemBean.getInstance().isNegozioSelezionatoB())
                {
                    req.setAttribute("beanS",sB);
                     view = getServletContext().getRequestDispatcher("/negozi.jsp");
                    view.forward(req,resp);
                }
                else {

                    req.setAttribute("beanS",sB);
                     view = getServletContext().getRequestDispatcher("/download.jsp");
                    view.forward(req,resp);
                }

            }
            if(registra!=null && registra.equals("registra e paga"))
            {
                Date sqlDate = null;
                java.util.Date utilDate;
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");


                utilDate = format.parse(scad);
                sqlDate = new java.sql.Date(utilDate.getTime());


                ccB.setNomeB(n);
                ccB.setCivB(c);
                ccB.setNumeroCCB(numero);
                ccB.setCognomeB(c);
                ccB.setDataScadB(sqlDate);
                ccB.setCivB(civ);
                ccB.setPrezzoTransazioneB(sB.getSpesaTB());



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
                pB.setAmmontareB(sB.getSpesaTB());
                pB.setTipoB(sB.getCategoriaB());
                pB.setIdOggettoB(sB.getIdB());

                Pagamento p=new Pagamento();

                p.setId(pB.getIdB());
                p.setMetodo(pB.getMetodoB());
                p.setEsito(pB.getEsitoB());
                p.setNomeUtente(pB.getNomeUtenteB());
                p.setAmmontare(pB.getAmmontareB());
                p.setTipo(pB.getTipoB());
                p.setIdOggetto(pB.getIdOggettoB());

                pD.inserisciPagamento(p);

                if(pB.getIdB()!=0)
                {
                    view= getServletContext().getRequestDispatcher("/index.jsp");
                    view.forward(req,resp);
                }
                else {
                    req.setAttribute("beanCC",ccB);
                    req.setAttribute("beanS",sB);
                    view= getServletContext().getRequestDispatcher("/cartaCredito.jsp");
                    view.forward(req,resp);
                }


                java.util.logging.Logger.getLogger("post registra ").log(Level.INFO, "da fare");

            }
            if(cartePossedute!=null && cartePossedute.equals("prendi dati carta"))
            {
                String numeroCarta=req.getParameter("numeroCartaL");
                ccB.setNumeroCCB(numeroCarta);
                cc.setNumeroCC(ccB.getNumeroCCB());

                ccB.setNomeB(cCD.popolaDati(cc).getNomeUser());
                ccB.setCognomeB(cCD.popolaDati(cc).getCognomeUser());

                ccB.setDataScadB(cCD.popolaDati(cc).getScadenza());
                ccB.setCivB(cCD.popolaDati(cc).getCiv());

                req.setAttribute("beanS",sB);
                req.setAttribute("beanCC",ccB);

                view= getServletContext().getRequestDispatcher("/cartaCredito.jsp");
                view.forward(req,resp);

            }

            if(generaLista!=null && generaLista.equals("genera lista"))
            {
                ccB.setNomeB(nomeUtente);
                cc.setNomeUser(ccB.getNomeB());
                ccB.setListaCarteCreditoB(cCD.getCarteCredito(ccB.getNomeB()));

                req.setAttribute("beanS",sB);
                req.setAttribute("beanCC",ccB);
                view= getServletContext().getRequestDispatcher("/cartaCredito.jsp");
                view.forward(req,resp);

            }

        } catch ( SQLException | IdException | ParseException e) {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());
        }
    }





}