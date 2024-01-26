package web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.net.URISyntaxException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

import web.bean.DownloadBean;

import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.ContrassegnoDao;
import laptop.database.LibroDao;
import laptop.database.PagamentoDao;
import laptop.model.raccolta.Libro;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet{

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;
    private final DownloadBean dB=new DownloadBean();
    private final SystemBean sB=SystemBean.getInstance();
    private final Libro l=new Libro();
    private final PagamentoDao pD=new PagamentoDao();
    private final LibroDao lD=new LibroDao();
    private final ContrassegnoDao fDao=new ContrassegnoDao();



    private static final String index="/index.jsp";

    public DownloadServlet() throws IOException {
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String invia=req.getParameter("downloadB");
        String annulla=req.getParameter("annullaB");


        try {
            if(invia!=null && invia.equals("scarica e leggi") )

            {

                dB.setIdB(sB.getIdB());
                dB.setTitoloB(sB.getTitoloB());
                l.setId(dB.getIdB());

                l.scarica(sB.getIdB());
                l.leggi(l.getId());


                req.setAttribute("bean",dB);
                req.setAttribute("beanS",sB);
                RequestDispatcher view = getServletContext().getRequestDispatcher(index);
                view.forward(req,resp);
            }
            if(annulla!=null && annulla.equals("annulla"))
            {

                //split
                boolean statusF;
                boolean statusP;

                String metodoP=sB.getMetodoPB();

                int idF=fDao.retUltimoOrdineF(); //ultimo elemento (preso con count)
                statusF=fDao.annullaOrdineF(idF);

                int idP=pD.retUltimoOrdinePag();
                statusP=pD.annullaOrdinePag(idP);


                if(statusF && statusP && metodoP.equals("cash"))
                {

                    l.setId(sB.getIdB());
                    lD.aggiornaDisponibilita(l);


                    req.setAttribute("bean",dB);
                    RequestDispatcher view = getServletContext().getRequestDispatcher(index);
                    view.forward(req,resp);


                }
                if( statusP && metodoP.equals("cCredito"))
                {

                    l.setId(sB.getIdB());
                    lD.aggiornaDisponibilita(l);
                    req.setAttribute("bean",dB);
                    RequestDispatcher view = getServletContext().getRequestDispatcher(index);
                    view.forward(req,resp);




                }




            }


        }catch(SQLException |DocumentException|URISyntaxException e) {
            req.setAttribute("bean", dB);
            RequestDispatcher view = getServletContext().getRequestDispatcher(index);
            view.forward(req, resp);


        }
    }


}







