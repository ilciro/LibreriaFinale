package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Rivista;
import web.bean.RivistaBean;
import web.bean.SystemBean;

import java.io.IOException;
@WebServlet("/RivisteServlet")

public class RivisteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final RivistaBean rB=new RivistaBean();
    private final String riviste="/riviste.jsp";
    private final Rivista r=new Rivista();
    private final RivistaDao rD=new RivistaDao();
    private final static String beanR="beanR";
    private final SystemBean sB=SystemBean.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String i=req.getParameter("procedi");
        String g=req.getParameter("genera lista");
        String a=req.getParameter("annulla");
        String id=req.getParameter("idOgg");
        RequestDispatcher view;


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
            sB.setTypeB("rivista");



            rB.setIdB(Integer.parseInt(id));
                r.setId(rB.getIdB());
                rB.setTitoloB(rD.getData(r).getTitolo());
                sB.setIdB(rB.getIdB());
                sB.setTitoloB(rB.getTitoloB());
                req.setAttribute("beanR",rB);
                req.setAttribute("beanS",sB);

                 view = getServletContext().getRequestDispatcher("/acquista.jsp");
                view.forward(req,resp);




        }
        if(a!=null && a.equals("indietro"))
        {
             view = getServletContext().getRequestDispatcher("/index.jsp");
            view.forward(req,resp);
        }


    }

}
