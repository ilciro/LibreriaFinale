package web.servlet;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.database.UsersDao;

import web.bean.TextAreaBean;

import java.io.*;

import java.sql.SQLException;
import java.util.logging.Level;

@WebServlet("/ReportServlet")

public class ReportServlet extends HttpServlet {

    private static final String ECCEZIONE = "ECCEZIONE generata:";

    private final TextAreaBean tAB = new TextAreaBean();

    private final GiornaleDao gD = new GiornaleDao();
    private final LibroDao lD = new LibroDao();
    private final RivistaDao rD = new RivistaDao();

    public ReportServlet() throws IOException {
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String libro = req.getParameter("buttonL");
        String giornale = req.getParameter("buttonG");
        String rivista = req.getParameter("buttonR");
        String raccolta = req.getParameter("raccoltaB");
        String totale = req.getParameter("buttonT");
        String indietro = req.getParameter("buttonI");
        RequestDispatcher view;

        try {

            if (libro != null && libro.equals("libro")) {

                tAB.setScriviB(lD.generaReportWebLibro());

            }
            if (giornale != null && giornale.equals("giornale")) {
                tAB.setScriviB(gD.generaReportWebGiornale());

            }
            if (rivista != null && rivista.equals("rivista")) {
                tAB.setScriviB(rD.generaReportWebRivista());

            }
            if (raccolta != null && raccolta.equals("raccolta")) {
                String builder = lD.generaReportWebLibro() + "\n" +
                        gD.generaReportWebGiornale() + "\n" +
                        rD.generaReportWebRivista() + "\n";
                tAB.setScriviB(builder);

            }
            if (totale != null && totale.equals("totale")) {
                String builder = lD.generaReportWebLibro() + "\n" +
                        gD.generaReportWebGiornale() + "\n" +
                        rD.generaReportWebRivista() + "\n" +
                        UsersDao.generaReportWebUsers();
                tAB.setScriviB(builder);

            }
            if (indietro != null && indietro.equals("indietro")) {
                view = getServletContext().getRequestDispatcher("/admin.jsp");
                view.forward(req, resp);
            }

            req.setAttribute("beanTA", tAB);
            view = getServletContext().getRequestDispatcher("/report.jsp");
            view.forward(req, resp);

        } catch (IOException | SQLException e) {
            java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, ECCEZIONE, e);

        }
    }

    //add comment
    private void prova() {
        System.out.print("ciao");

    }

}





