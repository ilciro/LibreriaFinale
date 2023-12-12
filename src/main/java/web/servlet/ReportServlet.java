package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.LibroDao;
import laptop.model.TempUser;
import laptop.utilities.ConnToDb;
import web.bean.TextAreaBean;

import java.io.*;
import java.lang.ref.ReferenceQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;

@WebServlet("/ReportServlet")

public class ReportServlet extends HttpServlet {

    private static final String ECCEZIONE="ECCEZIONE generata:";

    private final TextAreaBean tAB=new TextAreaBean();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String libro=req.getParameter("buttonL");
        String giornale=req.getParameter("buttonG");
        String rivista=req.getParameter("buttonR");
        String raccolta=req.getParameter("raccoltaB");
        String totale=req.getParameter("buttonT");
        String indietro=req.getParameter("buttonI");
        RequestDispatcher view;


        if(libro !=null && libro.equals("libro")) {
            // nou used report , but dicrectly

            tAB.setScriviB(reportLibro());
            req.setAttribute("beanTA",tAB);
             view= getServletContext().getRequestDispatcher("/report.jsp");
            view.forward(req,resp);
        }
        if(giornale!=null && giornale.equals("giornale")) {
            tAB.setScriviB(reportGiornale());
            req.setAttribute("beanTA",tAB);
             view= getServletContext().getRequestDispatcher("/report.jsp");
            view.forward(req,resp);
        }
        if (rivista!=null && rivista.equals("rivista")) {
            tAB.setScriviB(reportRivista());
            req.setAttribute("beanTA",tAB);
             view= getServletContext().getRequestDispatcher("/report.jsp");
            view.forward(req,resp);
        }
        if(raccolta!=null && raccolta.equals("raccolta"))
        {
            String builder = reportLibro() + "\n" +
                    reportGiornale() + "\n" +
                    reportRivista() + "\n";
            tAB.setScriviB(builder);
            req.setAttribute("beanTA",tAB);
             view= getServletContext().getRequestDispatcher("/report.jsp");
            view.forward(req,resp);
        }
        if(totale!=null &&totale.equals("totale"))
        {
            String builder = reportLibro() + "\n" +
                    reportGiornale() + "\n" +
                    reportRivista() + "\n"+
                    reportUtenti()+"\n";
                tAB.setScriviB(builder);
            req.setAttribute("beanTA",tAB);
             view= getServletContext().getRequestDispatcher("/report.jsp");
            view.forward(req,resp);
        }
        if(indietro!=null && indietro.equals("indietro"))
        {
            view= getServletContext().getRequestDispatcher("/admin.jsp");
            view.forward(req,resp);
        }
    }

    private String reportLibro()
    {
        String  query="select titolo,copieVendute,prezzo as totale from LIBRO";

        StringBuilder report=new StringBuilder();
            try(Connection conn = ConnToDb.connectionToDB();
                PreparedStatement prepQ=conn.prepareStatement(query);)
            {
                ResultSet rs=prepQ.executeQuery();
                while(rs.next())
                {
                    report.append("Titolo :").append(rs.getString(1)).append("\t").append("Ricavo totale :").append(rs.getInt(2) * rs.getFloat(3)).append("\n");

                }

            }catch(SQLException e)
            {
                java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, ECCEZIONE, e);
            }
        return report.toString();
    }
    public  String reportGiornale()  {

        String query = "select titolo,editore,copiRim,prezzo as totale  from GIORNALE";
        StringBuilder report = new StringBuilder();
        try (Connection conn = ConnToDb.connectionToDB();
             PreparedStatement prepQ = conn.prepareStatement(query)) {

            ResultSet rs = prepQ.executeQuery();

            while (rs.next()) {


                report.append("Titolo :").append(rs.getString(1)).append("\t").append("Editore :").append(rs.getString(2)).append("\t").append("Ricavo totale :").append(rs.getInt(3) * rs.getFloat(4)).append("\n");


            }


        } catch (SQLException e) {
            java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, ECCEZIONE, e);
        }

        return report.toString();
    }

    private String reportRivista()
    {

        String query="select titolo,editore,copieRimanenti,prezzo as totale ,dataPubblicazione from RIVISTA";

       StringBuilder report=new StringBuilder();
            try(Connection conn=ConnToDb.connectionToDB();
                PreparedStatement prepQ=conn.prepareStatement(query))
            {

                ResultSet rs=prepQ.executeQuery();


                while(rs.next())
                {

                   report.append("Titolo :").append(rs.getString(1)).append("\t").append("Editore :").append(rs.getString(2)).append("\t").append("Ricavo totale :").append(rs.getInt(3) * rs.getFloat(4)).append("\t").append("pubblicato il : ").append(rs.getDate("dataPubblicazione")).append("\n");



                }
            }catch(SQLException e)
            {
                java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, ECCEZIONE, e);
            }
            return report.toString();

        }

    public String   reportUtenti()   {
        String query="select * from USERS";
       StringBuilder report=new StringBuilder();


            try(Connection conn=ConnToDb.connectionToDB();
                PreparedStatement prepQ=conn.prepareStatement(query))
            {

                ResultSet rs=prepQ.executeQuery();



                while(rs.next())
                {

                    TempUser.getInstance().setId(rs.getInt(1));
                    TempUser.getInstance().setIdRuolo(rs.getString(2));
                    TempUser.getInstance().setNomeT(rs.getString(3));
                    TempUser.getInstance().setCognomeT(rs.getString(4));
                    TempUser.getInstance().setEmailT(rs.getString(5));
                    TempUser.getInstance().setDescrizioneT(rs.getString(7));
                    TempUser.getInstance().setDataDiNascitaT(rs.getDate(8).toLocalDate());
                    report.append(TempUser.getInstance().getId()).append("\t").append(TempUser.getInstance().getIdRuolo()).append("\t").append(TempUser.getInstance().getNomeT()).append("\t").append(TempUser.getInstance().getCognomeT()).append("\t").append(TempUser.getInstance().getEmailT()).append("\t").append(TempUser.getInstance().getDescrizioneT()).append("\t").append(TempUser.getInstance().getDataDiNascitaT().toString()).append("\n");

                }
            }catch(SQLException  e)
            {
                java.util.logging.Logger.getLogger("lista utenti").log(Level.SEVERE,ECCEZIONE,e);

            }
            return report.toString();

        }

    }







