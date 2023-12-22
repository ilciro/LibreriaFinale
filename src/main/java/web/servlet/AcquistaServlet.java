package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.exception.IdException;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;
import web.bean.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AcquistaServlet")
public class AcquistaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final AcquistaBean aB=new AcquistaBean();
    private final LibroDao lD=new LibroDao();
    private final Libro l=new Libro();
    private final LibroBean lB=new LibroBean();

    private final Rivista r=new Rivista();
    private final RivistaDao rD=new RivistaDao();
    private final RivistaBean rB=new RivistaBean();

    private final   String LIBRO = "libro";
    private final String beanS="beanS";
    private  final String GIORNALE="giornale";
    private  final String RIVISTA="rivista";
    private final Giornale g=new Giornale();
    private final GiornaleDao gD=new GiornaleDao();
    private final GiornaleBean gB=new GiornaleBean();
    private final SystemBean sB=SystemBean.getInstance();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q=req.getParameter("quantita");
        String calcola=req.getParameter("totaleB");
        String metodo=req.getParameter("metodoP");
        String negozio=req.getParameter("negozioB");
        sB.setMetodoPB(metodo);
        String download=req.getParameter("pdfB");
        float costo;
        String type=sB.getTypeB();
        String pagamento=sB.getMetodoPB();
        try {



            if(calcola!=null && calcola.equals("calcola"))
            {

                if (type.equals(LIBRO)) {


                    lB.setIdB(sB.getIdB());
                    l.setId(lB.getIdB());
                    aB.setTitoloB(lD.getTitolo(l));
                    costo = Integer.parseInt(q) * lD.getCosto(l);
                    aB.setPrezzoB(costo);
                    sB.setQuantitaB(Integer.parseInt(q));
                    sB.setSpesaTB(aB.getPrezzoB());
                    sB.setTitoloB(aB.getTitoloB());
                    sB.setIdB(lB.getIdB());

                    req.setAttribute("beanS",sB);
                    req.setAttribute("beanA",aB);
                }
                if(type.equals(GIORNALE))
                {
                    gB.setIdB(sB.getIdB());
                    g.setId(gB.getIdB());
                    aB.setTitoloB(gD.getTitolo(g));
                    costo = Integer.parseInt(q) * gD.getCosto(g);
                    aB.setPrezzoB(costo);
                    sB.setQuantitaB(Integer.parseInt(q));
                    sB.setSpesaTB(aB.getPrezzoB());
                    sB.setTitoloB(aB.getTitoloB());
                    sB.setIdB(gB.getIdB());
                    req.setAttribute("beanS",sB);
                    req.setAttribute("beanA",aB);
                }

                if(type.equals(RIVISTA))
                {
                    rB.setIdB(sB.getIdB());
                    r.setId(rB.getIdB());
                    aB.setTitoloB(rD.getTitolo(r));
                    costo = Integer.parseInt(q) * rD.getCosto(r);
                    aB.setPrezzoB(costo);
                    sB.setQuantitaB(Integer.parseInt(q));
                    sB.setSpesaTB(aB.getPrezzoB());
                    sB.setTitoloB(aB.getTitoloB());
                    sB.setIdB(rB.getIdB());
                    req.setAttribute("beanS",sB);
                    req.setAttribute("beanA",aB);
                }

                RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp");
                view.forward(req,resp);


            }



            if(negozio!=null && negozio.equals("ritiro in negozio"))
            {
                sB.setNegozioSelezionatoB(true);
                switch(pagamento)
                {
                    case "cash":
                    {
                        req.setAttribute(beanS, sB);
                        RequestDispatcher view = getServletContext().getRequestDispatcher("/fattura.jsp");
                        view.forward(req,resp);
                        break;
                    }
                    case "cCredito":
                    {
                        sB.setSpesaTB(aB.getPrezzoB());
                        req.setAttribute(beanS, sB);
                        RequestDispatcher view = getServletContext().getRequestDispatcher("/cartaCredito.jsp");
                        view.forward(req,resp);
                        break;
                    }
                    default:break;
                }

            }
            if(download!=null && download.equals("scarica il pdf"))
            {
                sB.setNegozioSelezionatoB(false);
                switch(pagamento)
                {
                    case "cash":
                    {
                        req.setAttribute(beanS, sB);

                        RequestDispatcher view = getServletContext().getRequestDispatcher("/fattura.jsp");
                        view.forward(req,resp);
                        break;
                    }
                    case "cCredito":
                    {
                        sB.setSpesaTB(aB.getPrezzoB());
                        req.setAttribute(beanS, sB);
                        RequestDispatcher view = getServletContext().getRequestDispatcher("/cartaCredito.jsp");
                        view.forward(req,resp);
                        break;
                    }
                    default:break;
                }
            }


        } catch (NumberFormatException | SQLException e) {
            aB.setMexB(new IdException("quantita eccede la scorta nel magazzino"));
            req.setAttribute("beanA",aB);
            RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp");
            view.forward(req,resp);
        }
    }


}


