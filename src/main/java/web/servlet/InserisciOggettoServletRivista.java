package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

import web.bean.RivistaBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Rivista;

@WebServlet("/InserisciOggettoServletRivista")
public class InserisciOggettoServletRivista extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static RivistaBean rB=new RivistaBean();
	private static Rivista r=new Rivista();
	private static RivistaDao rD=new RivistaDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
         try {
         	String titolo=req.getParameter("titoloL");
     		String cat=req.getParameter("catS");
     		String autore=req.getParameter("autL");
     		String lingua=req.getParameter("linguaL");
     		String editore=req.getParameter("editoreL");
     		String desc=req.getParameter("descL");
     		String data=req.getParameter("dataL");
     		String checkL=req.getParameter("checkL");
     		String prezzo=req.getParameter("prezzoL");
     		String copie=req.getParameter("copieL");
     		String generaB=req.getParameter("generaL");
     		String buttonC=req.getParameter("confermaB");
     		String buttonA=req.getParameter("annullaB");
     		String s="";
     		if(generaB!=null && generaB.equals("prendi lista"))
     		{
     			s+="SETTIMANALE"+"\n";
     			s+="BISETTIMANALE"+"\n";
     			s+="MENSILE"+"\n";
     			s+="BIMESTRALE"+"\n";
     			s+="TRIMESTRALE"+"\n";
     			s+="ANNUALE"+"\n";
     			s+="ESTIVO"+"\n";
     			s+="INVERNALE"+"\n";
     			s+="SPORTIVO"+"\n";
     			s+="CINEMATOGRAFICA"+"\n";
     			s+="GOSSIP"+"\n";
     			s+="TELEVISIVO"+"\n";
     			s+="MILITARE"+"\n";
     			s+="INFORMATICA"+"\n";
     			
     			rB.setListaCategorieB(s);
     			req.setAttribute("bean",rB);
     			RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiOggettoPage.jsp"); 
     			view.forward(req,resp);

     		}
     		if(buttonC!=null && buttonC.equals("conferma") && (data!=null) )
     			{
     				rB.setTitoloB(titolo);
     				rB.setTipologiaB(cat);
     				rB.setAutoreB(autore);
     				rB.setLinguaB(lingua);
     				rB.setEditoreB(editore);
     				rB.setDescrizioneB(desc);
     			
     				
     				java.util.Date utilDate;
     		         java.sql.Date sqlDate;
     				
     				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
     	
     			    
     			         utilDate = format.parse(data);
     			        sqlDate = new java.sql.Date(utilDate.getTime());
     			        rB.setDataB(sqlDate);
     			   
     				rB.setPrezzoB(Float.parseFloat(prezzo));
     				rB.setCopieRimB(Integer.parseInt(copie));
     			
     			    if(checkL!=null && checkL.equals("on"))
     			    {
     					rB.setDispB(1);

     			    }
     			    
     				
     				
     				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
     				  String date = data;
     	
     				  //convert String to LocalDate
     				  LocalDate localDate = LocalDate.parse(date, formatter);
     			
     			r.setTitolo(rB.getTitoloB());
     			r.setTipologia(rB.getTipologiaB());
     			r.setAutore(rB.getAutoreB());
     			r.setLingua(rB.getLinguaB());
     			r.setEditore(rB.getEditoreB());
     			r.setDescrizione(rB.getDescrizioneB());
     			r.setDataPubb(localDate);
     			r.setDisp(rB.getDispB());
     			r.setPrezzo(rB.getPrezzoB());
     			r.setCopieRim(rB.getCopieRimB());
     			
     			  
     			
     			
     				if(Boolean.TRUE.equals(rD.creaRivista(r)))
     				{
     					rD.aggiornaData(r, sqlDate);
     					RequestDispatcher view = getServletContext().getRequestDispatcher("/gestioneOggetto.jsp"); 
     					view.forward(req,resp); 
     	
     				}
     				else {
     					rB.setMexB(new SQLException(" data non corretta"));
     					RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiOggettoPage.jsp"); 
     					view.forward(req,resp); 
     				}
     			}
     			
     		
     		if(buttonA!=null && buttonA.equals("indietro"))
     		{
     			RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaRivista.jsp"); 
     			view.forward(req,resp); 
     		}
     	}catch(SQLException|ParseException|ServletException e)
              {
    		java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post .",e);
              }

         }


	
}
