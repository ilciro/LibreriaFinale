package laptop.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
	protected String fileLibro = "ReportFinale/riepilogoLibro.txt";
	protected String fileGiornale ="ReportFinale/riepilogoGiornali.txt";
	protected String fileRiviste = "ReportFinale/riepilogoRiviste.txt";
	protected String fileUtenti = "ReportFinale/riepilogoUtenti.txt";

	public void generaReportLibri () throws IOException
	{
		lD.generaReport();
		
	}
	public void generaReportUtenti() throws IOException, SQLException  {
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

	public String reportTotale() throws SQLException, IOException {
		generaReportUtenti();
        return leggiLibro() +
				"\n" +
				leggiRivista() +
				"\n" +
				leggiGiornale() +
				"\n" +
				leggiUtenti() +
				"\n";

	}


	/*
	uso queste operazioni er leggere ogni singolo file
	 */
	public String leggiLibro() throws  IOException {
		generaReportLibri();
		StringBuilder builder = new StringBuilder();
		String line = "";
		try (BufferedReader readerU = new BufferedReader(new FileReader(fileLibro))) {
			while ((line = readerU.readLine()) != null) {
				builder.append(line);
				builder.append("\n");
			}

		} catch (IOException e) {
			throw new IOException(e);
		}
		return builder.toString();
	}
	public String leggiGiornale() throws SQLException, IOException {
		generaReportGiornali();
		StringBuilder builder = new StringBuilder();
		String line = "";
		try (BufferedReader readerU = new BufferedReader(new FileReader(fileGiornale))) {
			while ((line = readerU.readLine()) != null) {
				builder.append(line);
				builder.append("\n");
			}

		} catch (IOException e) {
			throw new IOException(e);
		}
		return builder.toString();
	}
	public String leggiRivista() throws SQLException, IOException {
		generaReportRiviste();
		StringBuilder builder = new StringBuilder();
		String line = "";
		try (BufferedReader readerU = new BufferedReader(new FileReader(fileRiviste))) {
			while ((line = readerU.readLine()) != null) {
				builder.append(line);
				builder.append("\n");
			}

		} catch (IOException e) {
			throw new IOException(e);
		}
		return builder.toString();
	}
	public String leggiUtenti() throws SQLException, IOException {
		generaReportUtenti();
		StringBuilder builder = new StringBuilder();
		String line = "";
		try (BufferedReader readerU = new BufferedReader(new FileReader(fileUtenti))) {
			while ((line = readerU.readLine()) != null) {
				builder.append(line);
				builder.append("\n");
			}

		} catch (IOException e) {
			throw new IOException(e);
		}
		return builder.toString();
	}



	public String reportRaccolta() throws IOException, SQLException {
		return leggiLibro()+"\n"+leggiGiornale()+"\n"+leggiRivista()+"\n";
	}
	public ControllerReportPage()
	{
		lD=new LibroDao();
		rD=new RivistaDao();
		gD=new GiornaleDao();
	}



}
