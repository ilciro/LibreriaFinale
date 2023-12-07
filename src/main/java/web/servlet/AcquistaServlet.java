package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import laptop.database.LibroDao;
import laptop.exception.IdException;
import laptop.model.raccolta.Libro;
import web.bean.AcquistaBean;
import web.bean.LibroBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AcquistaServlet")
public class AcquistaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static AcquistaBean aB=new AcquistaBean();
    private static LibroDao lD=new LibroDao();
    private static Libro l=new Libro();
    private static LibroBean lB=new LibroBean();

    private static String bean1="bean1";
    private static final String LIBRO="libro";
    private static final String GIORNALE="giornale";
    private static final String RIVISTA="rivista";
/*
    public AcquistaServlet() throws IdException {
        super();
        String type=SystemBean.getInstance().getTypeB();
        if(type.equals(LIBRO))
        {
                lB.setIdB(SystemBean.getInstance().getIdB());
                l.setId(lB.getIdB());
                aB.setTitoloB(lD.getTitolo(l));

        }

    }

 */
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
                    SystemBean.getInstance().setSpesaTB(costo);
                    SystemBean.getInstance().setTitoloB(aB.getTitoloB());
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
                        req.setAttribute(bean1, SystemBean.getInstance());

                        RequestDispatcher view = getServletContext().getRequestDispatcher("/fattura.jsp");
                        view.forward(req,resp);
                        break;
                    }
                    case "cCredito":
                    {
                        req.setAttribute(bean1, SystemBean.getInstance());

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
                        req.setAttribute(bean1, SystemBean.getInstance());

                        RequestDispatcher view = getServletContext().getRequestDispatcher("/fattura.jsp");
                        view.forward(req,resp);
                        break;
                    }
                    case "cCredito":
                    {
                        req.setAttribute(bean1, SystemBean.getInstance());

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
            throw new RuntimeException(e);
        }
    }


}


