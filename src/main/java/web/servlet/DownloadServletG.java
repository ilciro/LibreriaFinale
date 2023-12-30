package web.servlet;

import com.itextpdf.text.DocumentException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.ContrassegnoDao;
import laptop.database.GiornaleDao;
import laptop.database.PagamentoDao;
import laptop.model.raccolta.Giornale;
import web.bean.DownloadBean;
import web.bean.SystemBean;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/DownloadServletG")

public class DownloadServletG extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DownloadBean dB=new DownloadBean();
    private final SystemBean sB=SystemBean.getInstance();
    private final Giornale g=new Giornale();
    private final PagamentoDao pD=new PagamentoDao();
    private final GiornaleDao gD=new GiornaleDao();
    private final ContrassegnoDao fDao=new ContrassegnoDao();
    private final String index="/index.jsp";

    public DownloadServletG() throws IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String invia = request.getParameter("downloadB");
        String annulla = request.getParameter("annullaB");
        RequestDispatcher view;

        try {
            if (invia != null && invia.equals("scarica e leggi")) {

                dB.setIdB(sB.getIdB());
                dB.setTitoloB(sB.getTitoloB());
                g.setId(sB.getIdB());
                g.scarica();
                g.leggi(g.getId());

                request.setAttribute("beanD", dB);
                view = getServletContext().getRequestDispatcher(index);
                view.forward(request, response);
            }
            if (annulla != null && annulla.equals("annulla")) {

                //split
                boolean statusF = false;
                boolean statusP = false;

                String metodoP = sB.getMetodoPB();

                int idF = fDao.retUltimoOrdineF(); //ultimo elemento (preso con count)
                statusF = fDao.annullaOrdineF(idF);

                int idP = pD.retUltimoOrdinePag();
                statusP = pD.annullaOrdinePag(idP);


                if (statusF && statusP && metodoP.equals("cash")) {

                    g.setId(sB.getIdB());
                    gD.aggiornaDisponibilita(g);


                    request.setAttribute("bean", dB);
                    view = getServletContext().getRequestDispatcher(index);
                    view.forward(request, response);


                }
                if (statusP && metodoP.equals("cCredito")) {

                    g.setId(sB.getIdB());
                    gD.aggiornaDisponibilita(g);
                    request.setAttribute("bean", dB);
                    view = getServletContext().getRequestDispatcher(index);
                    view.forward(request, response);


                }


            }


        } catch (SQLException | DocumentException | IOException e) {
            request.setAttribute("bean", dB);
            view = getServletContext().getRequestDispatcher(index);
            view.forward(request, response);

        }
    }
}
