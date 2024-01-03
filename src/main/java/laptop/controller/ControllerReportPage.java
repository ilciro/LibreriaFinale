package laptop.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.database.UsersDao;

public class ControllerReportPage {
	private LibroDao lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	protected String fileLibro = "src/main/resources/Reports/riepilogoLibri.txt";
	protected String fileGiornale ="src/main/resources/Reports/riepilogoGiornali.txt";
	protected String fileRiviste = "src/main/resources/Reports/riepilogoRiviste.txt";
	protected String fileUtenti = "src/main/resources/Reports/riepilogoUtenti.txt";

	public void generaReportLibri () throws IOException
	{
		lD.generaReport();
		
	}
	public void generaReportUtenti() throws IOException  {
		UsersDao.getListaUtenti();
	}
	public void generaReportRiviste () throws IOException
	{
		rD.generaReport();
		
	}
	public void generaReportGiornali () throws IOException
	{
		gD.generaReport();
		
	}

	public String reportTotale() throws  IOException {
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
	public String leggiGiornale() throws  IOException {
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
	public String leggiRivista() throws  IOException {
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
	public String leggiUtenti() throws IOException {
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



	public String reportRaccolta() throws IOException {
		return leggiLibro()+"\n"+leggiGiornale()+"\n"+leggiRivista()+"\n";
	}
	public ControllerReportPage() throws IOException {
		lD=new LibroDao();
		rD=new RivistaDao();
		gD=new GiornaleDao();
	}



}
