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



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q=req.getParameter("quantita");
        String calcola=req.getParameter("totaleB");
        String metodo=req.getParameter("metodoP");
        String negozio=req.getParameter("negozioB");
        SystemBean.getInstance().setMetodoPB(metodo);
        String download=req.getParameter("pdfB");
        float costo=(float)0.0;
        String type=SystemBean.getInstance().getTypeB();
        String pagamento=SystemBean.getInstance().getMetodoPB();
        try {



            if(calcola!=null && calcola.equals("calcola"))
            {

                if (type.equals(LIBRO)) {


                    lB.setIdB(SystemBean.getInstance().getIdB());
                    l.setId(lB.getIdB());
                    aB.setTitoloB(lD.getTitolo(l));
                    costo = Integer.parseInt(q) * lD.getCosto(l);
                    aB.setPrezzoB(costo);
                    SystemBean.getInstance().setQuantitaB(Integer.parseInt(q));
                    SystemBean.getInstance().setSpesaTB(aB.getPrezzoB());
                    SystemBean.getInstance().setTitoloB(aB.getTitoloB());
                    SystemBean.getInstance().setIdB(lB.getIdB());

                    req.setAttribute("beanS",SystemBean.getInstance());
                    req.setAttribute("beanA",aB);
                }
                if(type.equals(GIORNALE))
                {
                    gB.setIdB(SystemBean.getInstance().getIdB());
                    g.setId(gB.getIdB());
                    aB.setTitoloB(gD.getTitolo(g));
                    costo = Integer.parseInt(q) * gD.getCosto(g);
                    aB.setPrezzoB(costo);
                    SystemBean.getInstance().setQuantitaB(Integer.parseInt(q));
                    SystemBean.getInstance().setSpesaTB(aB.getPrezzoB());
                    SystemBean.getInstance().setTitoloB(aB.getTitoloB());
                    SystemBean.getInstance().setIdB(gB.getIdB());
                    req.setAttribute("beanS",SystemBean.getInstance());
                    req.setAttribute("beanA",aB);
                }

                if(type.equals(RIVISTA))
                {
                    rB.setIdB(SystemBean.getInstance().getIdB());
                    r.setId(rB.getIdB());
                    aB.setTitoloB(rD.getTitolo(r));
                    costo = Integer.parseInt(q) * rD.getCosto(r);
                    aB.setPrezzoB(costo);
                    SystemBean.getInstance().setQuantitaB(Integer.parseInt(q));
                    SystemBean.getInstance().setSpesaTB(aB.getPrezzoB());
                    SystemBean.getInstance().setTitoloB(aB.getTitoloB());
                    SystemBean.getInstance().setIdB(rB.getIdB());
                    req.setAttribute("beanS",SystemBean.getInstance());
                    req.setAttribute("beanA",aB);
                }

                RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp");
                view.forward(req,resp);


            }



            if(negozio!=null && negozio.equals("ritiro in negozio"))
            {
                SystemBean.getInstance().setNegozioSelezionatoB(true);
                switch(pagamento)
                {
                    case "cash":
                    {
                        req.setAttribute(beanS, SystemBean.getInstance());
                        RequestDispatcher view = getServletContext().getRequestDispatcher("/fattura.jsp");
                        view.forward(req,resp);
                        break;
                    }
                    case "cCredito":
                    {
                        SystemBean.getInstance().setSpesaTB(aB.getPrezzoB());
                        req.setAttribute(beanS, SystemBean.getInstance());
                        RequestDispatcher view = getServletContext().getRequestDispatcher("/cartaCredito.jsp");
                        view.forward(req,resp);
                        break;
                    }
                    default:break;
                }

            }
            if(download!=null && download.equals("scarica il pdf"))
            {
                SystemBean.getInstance().setNegozioSelezionatoB(false);
                switch(pagamento)
                {
                    case "cash":
                    {
                        req.setAttribute(beanS, SystemBean.getInstance());

                        RequestDispatcher view = getServletContext().getRequestDispatcher("/fattura.jsp");
                        view.forward(req,resp);
                        break;
                    }
                    case "cCredito":
                    {
                        SystemBean.getInstance().setSpesaTB(aB.getPrezzoB());
                        req.setAttribute(beanS, SystemBean.getInstance());
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
        } catch (IdException e) {
            java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

        }
    }


}


