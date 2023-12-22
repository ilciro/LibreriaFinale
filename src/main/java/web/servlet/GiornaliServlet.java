package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import laptop.database.GiornaleDao;
import laptop.exception.IdException;
import web.bean.AcquistaBean;
import web.bean.GiornaleBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.raccolta.Giornale;


@WebServlet("/GiornaliServlet")
public class GiornaliServlet extends HttpServlet{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static String giornali="/giornali.jsp";
    private static GiornaleBean gB=new GiornaleBean();
    private static GiornaleDao gD=new GiornaleDao();
    private static Giornale giornale=new Giornale();
    private final SystemBean sB=SystemBean.getInstance();
    private final AcquistaBean aB=new AcquistaBean();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String i=req.getParameter("procedi");
        String g=req.getParameter("genera lista");
        String a=req.getParameter("annulla");
        String id=req.getParameter("idOgg");
        RequestDispatcher view;

        try {
            if(g!=null && g.equals("genera lista"))
            {


                gB.setListaGiornaliB(gD.getGiornali());

                req.setAttribute("beanG",gB);
                 view = getServletContext().getRequestDispatcher(giornali);
                view.forward(req,resp);


            }
            if(i!=null && i.equals("procedi"))
            {

                int idOgg=Integer.parseInt(id);
                sB.setIdB(idOgg);

                gB.setIdB(idOgg);
                giornale.setId(gB.getIdB());

                gB.setTitoloB(gD.getTitolo(giornale));
                sB.setTitoloB(gB.getTitoloB());
                sB.setIdB(gB.getIdB());
                sB.setTypeB("giornale");

                //aggiungo categoria
                sB.setCategoriaB(gD.retTip(giornale));

                //setto i parametri nel bean acquista
                aB.setTitoloB(gB.getTitoloB());
                aB.setPrezzoB(gD.getCosto(giornale));

                req.setAttribute("beanS", sB);
                req.setAttribute("beanG", gB);
                req.setAttribute("beanA",aB);
                view = getServletContext().getRequestDispatcher("/acquista.jsp");
                view.forward(req, resp);
                }



            if(a!=null && a.equals("indietro"))
            {
                 view = getServletContext().getRequestDispatcher("/index.jsp");
                view.forward(req,resp);
            }


        } catch (SQLException e) {
            gB.setMexB(new IdException("id nullo o eccede lista"));
            req.setAttribute("beanG",gB);
             view = getServletContext().getRequestDispatcher(giornali);
            view.forward(req,resp);
        }
    }






}