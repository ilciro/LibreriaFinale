package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.controller.ControllerSystemState;
import web.bean.SystemBean;

import java.io.IOException;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    private final SystemBean sB=SystemBean.getInstance();
    private final ControllerSystemState vis=ControllerSystemState.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String libro=req.getParameter("buttonL");
        String giornale=req.getParameter("buttonR");
        String rivista=req.getParameter("buttonG");
        String login=req.getParameter("buttonLogin");
        String ricerca=req.getParameter("buttonRic");
        RequestDispatcher view;
        /*
        TODO
            impementare login e ricerca
         */
        if(libro!=null && libro.equals("libri"))
        {
            sB.setTypeAsBook();
            vis.setTypeAsBook();
            req.setAttribute("beanS",sB);

             view= getServletContext().getRequestDispatcher("/libri.jsp");
            view.forward(req,resp);
        }
        if(giornale!=null && giornale.equals("giornali"))
        {
            sB.setTypeAsDaily();
            vis.setTypeAsDaily();
            req.setAttribute("beanS",sB);
            req.setAttribute("visB",vis);
             view= getServletContext().getRequestDispatcher("/giornali.jsp");
            view.forward(req,resp);
        }
        if(rivista!=null && rivista.equals("riviste"))
        {
            sB.setTypeAsMagazine();
            vis.setTypeAsMagazine();
            req.setAttribute("beanS",sB);
            req.setAttribute("visB",vis);
             view= getServletContext().getRequestDispatcher("/riviste.jsp");
            view.forward(req,resp);
        }
    }
}
