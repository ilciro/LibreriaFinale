package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.RivistaDao;
import laptop.exception.IdException;
import laptop.model.raccolta.Rivista;
import web.bean.RivistaBean;
import web.bean.SystemBean;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/RivisteServlet")

public class RivisteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static RivistaBean rB=new RivistaBean();
    private static String riviste="/riviste.jsp";
    private static Rivista r=new Rivista();
    private static RivistaDao rD=new RivistaDao();
    private final static String beanR="beanR";
    private final SystemBean sB=SystemBean.getInstance();

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

                rB.setListaRivisteB(rD.getRiviste());
                req.setAttribute(beanR,rB);
                req.setAttribute("beanS", SystemBean.getInstance());
                view= getServletContext().getRequestDispatcher("/riviste.jsp");
                view.forward(req,resp);



            }





            if(i!=null && i.equals("procedi"))
            {

                int idO=Integer.parseInt(id);
                sB.setIdB(idO);


                    rB.setIdB(Integer.parseInt(id));
                    r.setId(rB.getIdB());
                    rB.setTitoloB(rD.getTitolo(r));
                    SystemBean.getInstance().setIdB(rB.getIdB());
                    SystemBean.getInstance().setTitoloB(rB.getTitoloB());
                    req.setAttribute("beanR",rB);
                    req.setAttribute("beanS",SystemBean.getInstance());

                     view = getServletContext().getRequestDispatcher("/acquista.jsp");
                    view.forward(req,resp);




            }
            if(a!=null && a.equals("indietro"))
            {
                 view = getServletContext().getRequestDispatcher("/index.jsp");
                view.forward(req,resp);
            }


        } catch ( SQLException  e) {
            rB.setMexB(new IdException("id nullo o eccede lista"));
            req.setAttribute("beanR",rB);
             view = getServletContext().getRequestDispatcher(riviste);
            view.forward(req,resp);
        }
    }

}
