/*package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.utilities.CreateDefaultDB;
import web.bean.TextAreaBean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

@WebServlet("/InitializeServlet")
public class InitializeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String popola=req.getParameter("buttonGenera");
        TextAreaBean tAB=new TextAreaBean();
        if(popola!=null && popola.equals("genera"))
        {
            try {

                CreateDefaultDB.createDefaultDB();


            } catch (FileNotFoundException | SQLException  eFile) {
                java.util.logging.Logger.getLogger("crwa db").log(Level.SEVERE," eccezione ottenuta .",eFile);
                tAB.setScriviB(eFile.toString());
            }
            tAB.setScriviB("fcewfc");
            req.setAttribute("beanT",tAB);
            RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp");
            view.forward(req,resp);
        }
    }
}
*/