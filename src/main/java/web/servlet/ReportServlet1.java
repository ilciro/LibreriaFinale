package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.LibroDao;
import web.bean.TextAreaBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;


@WebServlet("/ReportServlet1")
public class ReportServlet1 extends HttpServlet {
    private final TextAreaBean tAB=new TextAreaBean();
    //private String fileLibro = "home/daniele/IdeaProjects/LibreriaFinale/ReportFinale/riepilogoLibri.txt";
    //private String fileLibro1="../../home/daniele/IdeaProjects/LibreriaFinale/ReportFinale/riepilogoLibri.txt";
   // private String fileLibro2="../../ReportFinale/riepilogoLibri.txt";
   private String fileLibro2="~/IdeaProjects/LibreriaFinale/ReportFinale/riepilogoLibri.txt";


    private final LibroDao lD=new LibroDao();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String libro=req.getParameter("buttonL");


        if(libro !=null && libro.equals("libro"))
        {

            System.out.println("sto nell if");
            tAB.setScriviB(leggiLibro());
            req.setAttribute("bean",tAB);

           //tAB.setScriviB(leggiLibro());
            //req.setAttribute("bean",tAB);

            //System.out.println("ddwdd :"+leggiLibro());

        }
        RequestDispatcher view= getServletContext().getRequestDispatcher("/report.jsp");
        view.forward(req,resp);


    }



    private void generaReportL() throws IOException {
        lD.generaReport();
    }

    private String leggiLibro() throws  IOException {

        StringBuilder builder = new StringBuilder();
        String line = "";



        try (BufferedReader readerU = new BufferedReader(new FileReader(fileLibro2))) {
            while ((line = readerU.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}
