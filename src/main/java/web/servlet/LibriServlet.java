package web.servlet;

import java.io.IOException;
import java.io.Serial;

import laptop.database.LibroDao;
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

    @Serial
    private static final long serialVersionUID = 1L;
    private final LibroBean lB = new LibroBean();
    private final String libri = "/libri.jsp";
    private final LibroDao lD = new LibroDao();
    private final Libro l = new Libro();
    private final String beanL = "beanL";
    private final SystemBean sB=SystemBean.getInstance();
    private final AcquistaBean aB=new AcquistaBean();

    public LibriServlet() throws IOException {
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String i = req.getParameter("procedi");
        String g = req.getParameter("genera lista");
        String a = req.getParameter("annulla");
        String id = req.getParameter("idOgg");
        RequestDispatcher view;
        if (g != null && g.equals("genera lista")) {

           lB.setElencoLibriB(lD.getLibri());
           req.setAttribute(beanL,lB);
           req.setAttribute("beanS",sB);
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

                lB.setTitoloB(lD.getData(l).getTitolo());
                sB.setTitoloB(lB.getTitoloB());
                sB.setIdB(lB.getIdB());
                //aggiungo categoria
                sB.setCategoriaB(lD.getData(l).getCategoria());
                sB.setTypeB("libro");

                //setto i parametri nel bean acquista
                aB.setTitoloB(lB.getTitoloB());
                aB.setPrezzoB(lD.getData(l).getPrezzo());

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

    }
}