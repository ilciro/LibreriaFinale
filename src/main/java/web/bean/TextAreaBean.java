package web.bean;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import laptop.model.TempUser;
import laptop.utilities.ConnToDb;

public class TextAreaBean {
	private String scriviB;
	private static String ricavoTotale="Ricavo totale :";
	private static String titolo=" Titolo :";
	private static ResourceBundle rB=ResourceBundle.getBundle("configurations/reportsPath");


	public String getScriviB() {
		return scriviB;
	}

	public void setScriviB(String scriviB) {
		this.scriviB = scriviB;
	}
	
	public String generaReportL() throws SQLException, IOException
	{
		StringBuilder s=new StringBuilder();


		FileWriter w1=null;
		w1=new FileWriter(rB.getString("libri"));
		String query="select titolo,copieVendute,prezzo as totale from libro";
		
		   try (BufferedWriter b1=new BufferedWriter (w1)){
		

			   try(Connection 	conn = ConnToDb.generalConnection();
					   PreparedStatement prepQ=conn.prepareStatement(query);)
			   {
		
			ResultSet rs=prepQ.executeQuery();


			while(rs.next())
			{



				

				b1.write(titolo+rs.getString(1)+"\t"+ricavoTotale +rs.getInt(2)*rs.getFloat(3)+"\n");

				
				s.append(titolo);
				s.append("\t");
				s.append(rs.getString(1));
				s.append("\t");
				s.append(ricavoTotale);
				s.append("\t");
				s.append(rs.getInt(2)*rs.getFloat(3));
				s.append("\n");

				b1.flush();

				
			}

		}catch(SQLException e)
			   {
			java.util.logging.Logger.getLogger("report l").log(Level.INFO, "report libri {0}.",e.toString());
			   }
			   return s.toString();
		   }
		

	}
	public String generaReportR() throws SQLException, IOException
	{
				FileWriter w2;
				StringBuilder s=new StringBuilder();
				String query2="select titolo,editore,copieRimanenti,prezzo as totale  from ispw.rivista";
				w2=new FileWriter(rB.getString("riviste"));
		        
		        
		        try (BufferedWriter bR=new BufferedWriter (w2)){
		        	try(Connection conn=ConnToDb.generalConnection();
		        			PreparedStatement prepQ=conn.prepareStatement(query2);)
		        	{
		        		
		        		ResultSet rsR=prepQ.executeQuery();
				
		           
		            while(rsR.next())
		            {
		        		
		        					
				
		        		bR.write("Rivista :\n"+titolo+rsR.getString(1)+"\t"+"Editore rivista :"+rsR.getString(2)+"\t"+ricavoTotale+ "delle riviste" +rsR.getInt(3)*rsR.getFloat(4)+"\n");




		     			bR.flush();
		     			
		     			s.append(titolo);
						s.append("\t");
						s.append(rsR.getString(1));
						s.append("\t");
						s.append("editore :");
						s.append("\t");
						s.append(rsR.getString(2));
						s.append("\t");
						s.append(ricavoTotale);
						s.append("\t");
						s.append(rsR.getInt(3)*rsR.getFloat(4));
						s.append("\n");


		        			
		        		
		            }
		        	}catch(SQLException e)
		        	{
		    			java.util.logging.Logger.getLogger("report R").log(Level.INFO, "report riviste ",e);
		        	}
		        	return s.toString();
		     
		        }
	}
	public   String generaReportG() throws IOException, SQLException
	{
		FileWriter w3;
		StringBuilder s=new StringBuilder();
		String query3="select titolo,editore,copiRim,prezzo as totale  from ispw.giornale";
		w3=new FileWriter(rB.getString("giornali"));
		   try (BufferedWriter bG=new BufferedWriter (w3)){
			   try(Connection conn=ConnToDb.generalConnection();
					   PreparedStatement prepQ=conn.prepareStatement(query3);)
			   {
				  
				   ResultSet rsG=prepQ.executeQuery();

			while(rsG.next())
			{





				bG.write("Riepigolo Giornale :\n"+titolo+rsG.getString(1)+"\t"+"Editore del giornale :"+rsG.getString(2)+"\t"+ricavoTotale +"dei giornali :" +rsG.getInt(3)*rsG.getFloat(4)+"\n");




				bG.flush();
				
				s.append(titolo);
				s.append("\t");
				s.append(rsG.getString(1));
				s.append("\t");
				s.append("editore :");
				s.append("\t");
				s.append(rsG.getString(2));
				s.append("\t");
				s.append(ricavoTotale);
				s.append("\t");
				s.append(rsG.getInt(3)*rsG.getFloat(4));
				s.append("\n");



			}



			}catch(SQLException e)
			   {
				java.util.logging.Logger.getLogger("report g ").log(Level.INFO, "report giornali eccezione ",e);


			   }


			   return s.toString();

		}}
	
	public static  String getListaUtenti() throws IOException, SQLException  {
		String queryU="select * from ispw.users";
		StringBuilder s=new StringBuilder();
		FileWriter wU;
		wU=new FileWriter(rB.getString("utenti"));

		
		try (BufferedWriter bU=new BufferedWriter (wU)) {

			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(queryU);)
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
				bU.write(""+TempUser.getInstance().getId()+"\t"+TempUser.getInstance().getIdRuolo()+"\t"+TempUser.getInstance().getNomeT()+"\t"+TempUser.getInstance().getCognomeT()+
						"\t"+TempUser.getInstance().getEmailT()+"\t"+TempUser.getInstance().getDescrizioneT()+"\t"+TempUser.getInstance().getDataDiNascitaT().toString()+"\n");
				
				
				
				s.append(TempUser.getInstance().getId());
				s.append("\t");
				s.append(TempUser.getInstance().getIdRuolo());
				s.append("\t");
				s.append(TempUser.getInstance().getNomeT());
				s.append("\t");
				s.append(TempUser.getInstance().getCognomeT());
				s.append("\t");
				s.append(TempUser.getInstance().getEmailT());
				s.append("\t");
				s.append(TempUser.getInstance().getDescrizioneT());
				s.append("\t");
				s.append(TempUser.getInstance().getDataDiNascitaT().toString());
				s.append("\n");
				
			}
		}catch(SQLException  e)
			{
			java.util.logging.Logger.getLogger("lista utenti").log(Level.SEVERE,"\n eccezione ottenuta .",e);

			}
			bU.flush();
			return s.toString();
		
		}
		
	}
	
}
