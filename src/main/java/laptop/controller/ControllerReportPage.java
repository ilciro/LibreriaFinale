package laptop.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.database.UsersDao;

public class ControllerReportPage {
	private LibroDao lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	protected String fileLibro = "ReportFinale\\riepilogoLibro.txt";
	protected String fileGiornale ="ReportFinale\\riepilogoGiornali.txt";
	protected String fileRiviste = "ReportFinale\\riepilogoRiviste.txt";
	protected String fileUtenti = "ReportFinale\\riepilogoUtenti.txt";
	private static final String eccezione="eccezione ottenuta :.";
	
	public void generaReportLibri () throws IOException, SQLException
	{
		lD.generaReport();
		
	}
	
	public ControllerReportPage()
	{
		lD=new LibroDao();
		rD=new RivistaDao();
		gD=new GiornaleDao();
	}
	public void getUtenti() throws IOException, SQLException  {
		 UsersDao.getListaUtenti();
	}

	public void generaReportRiviste () throws IOException, SQLException
	{
		rD.generaReport();
		
	}
	public void generaReportGiornali () throws IOException, SQLException 
	{
		gD.generaReport();
		
	}

	public String reportTotale()
	{

		StringBuilder builder=new StringBuilder();
		String line="";






		try (BufferedReader readerU = new BufferedReader(new FileReader(fileUtenti)))
		{


			while( (line = readerU.readLine()) != null)
			{
				builder.append(line);
				builder.append("\n");



			}
			reportLibri();
			builder.append(reportGiornali());
			builder.append(reportRiviste());
		}
		catch(IOException | NullPointerException | SQLException e)
		{
			java.util.logging.Logger.getLogger("report utenti").log(Level.SEVERE,eccezione,e);

		}
        return builder.toString();

	}
	public void  reportLibri() throws IOException, SQLException {

		lD.generaReport();


	}
	public String reportRaccolta() throws IOException {
		String line="";
		String line1="";
		String line2="";
		StringBuilder builder=new StringBuilder();



		try(BufferedReader readerL = new BufferedReader(new FileReader(fileLibro))) {
			while((line=readerL.readLine())!=null)
			{
				builder.append(line);
				builder.append("\n");

			}

		}
		catch(IOException e)
		{

			java.util.logging.Logger.getLogger("report libro").log(Level.SEVERE,"\n eccezione ottenuta .",e);

		}



		try(BufferedReader readerG = new BufferedReader(new FileReader(fileGiornale)))
		{
			while((line1=readerG.readLine())!=null)
			{
				builder.append(line1);
				builder.append("\n");

			}
		}

		try(BufferedReader readerR = new BufferedReader(new FileReader(fileRiviste)))
		{
			while((line2=readerR.readLine())!=null)
			{
				builder.append(line2);
				builder.append("\n");

			}
		}
		return builder.toString();
	}
	public String reportGiornali()
	{
		String line="";
		StringBuilder builder=new StringBuilder();


		try(BufferedReader reader = new BufferedReader(new FileReader(fileGiornale)))
		{
			while((line=reader.readLine())!=null)
			{
				builder.append(line);
				builder.append("\n");

			}
		}catch(IOException e)
		{
			java.util.logging.Logger.getLogger("report giornale").log(Level.SEVERE,"\n eccezione ottenuta .",e);

		}
		return builder.toString();
	}
	public String reportRiviste() throws IOException {
		String line2="";
		StringBuilder builder=new StringBuilder();




		try(BufferedReader readerR = new BufferedReader(new FileReader(fileRiviste)))
		{
			while((line2=readerR.readLine())!=null)
			{
				builder.append(line2);
				builder.append("\n");

			}
		}
		return builder.toString();
	}
	

}
