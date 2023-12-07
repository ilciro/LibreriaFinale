package web.servlet;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.sql.SQLException;
import java.util.Objects;

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

@WebServlet("/LibriServlet")
public class LibriServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static LibroBean lB = new LibroBean();
    private static String libri = "/libri.jsp";
    private int dimensione = 0;
    private static LibroDao lD = new LibroDao();
    private static Libro l = new Libro();
    private final String beanL = "beanL";
    private final SystemBean sB=SystemBean.getInstance();
    private final AcquistaBean aB=new AcquistaBean();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String i = req.getParameter("procedi");
        String g = req.getParameter("genera lista");
        String a = req.getParameter("annulla");
        String id = req.getParameter("idOgg");
        RequestDispatcher view;
        try {
            if (g != null && g.equals("genera lista")) {

               lB.setElencoLibriB(lD.getLibri());
               req.setAttribute(beanL,lB);
               req.setAttribute("beanS",SystemBean.getInstance());
                view= getServletContext().getRequestDispatcher(libri);
               view.forward(req,resp);

            }
            if(i!=null && i.equals("procedi"))
            {
                //cast
                int idOgg=Integer.parseInt(id);
                sB.setIdB(idOgg);

                    lB.setIdB(idOgg);
                    l.setId(lB.getIdB());

                    lB.setTitoloB(lD.getTitolo(l));
                    sB.setTitoloB(lB.getTitoloB());
                    sB.setIdB(lB.getIdB());
                    //aggiungo categoria
                    sB.setCategoriaB(lD.retTip(l));

                    //setto i parametri nel bean acquista
                    aB.setTitoloB(lB.getTitoloB());
                    aB.setPrezzoB(lD.getCosto(l));

                    req.setAttribute("beanS", sB);
                    req.setAttribute(beanL, lB);
                    req.setAttribute("beanA",aB);
                    view = getServletContext().getRequestDispatcher("/acquista.jsp");
                    view.forward(req, resp);


            }
            if(a!=null && a.equals("home"))
            {
                 view= getServletContext().getRequestDispatcher("/index.jsp");
                view.forward(req,resp);
            }
        } catch (SQLException | IdException e) {
            throw new RuntimeException(e);
        }

    }
}