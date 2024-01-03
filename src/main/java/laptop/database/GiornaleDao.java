package laptop.database;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;

import laptop.controller.ControllerSystemState;
import laptop.model.raccolta.Factory;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Raccolta;
import laptop.utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GiornaleDao {
	
	private final Factory f;
	

	private  String query  ;
	

	private boolean state=false;
	private final ControllerSystemState vis=ControllerSystemState.getInstance();
	private static final String GIORNALE = "giornale";
	private static final String ECCEZIONE="eccezione generata:";

	private static final String RIEPILOGOGIORNALI="src/main/resources/Reports/riepilogoGiornali.txt";
	private final File fd;

	public GiornaleDao() {
		f = new Factory();
		this.fd=new File(RIEPILOGOGIORNALI);

	}

	public Giornale getData(Giornale g) {

		 query ="select * from GIORNALE where idGiornale=? or idGiornale=?";

		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ= conn.prepareStatement(query))  {

			prepQ.setInt(1,g.getId());
			prepQ.setInt(2,vis.getId());
			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{
				f.createRaccoltaFinale1(GIORNALE, rs.getString(1), null, rs.getString(4), null,rs.getString(4), rs.getString(3));


				f.createRaccoltaFinale2(GIORNALE, 0, rs.getInt(6), rs.getInt(7),rs.getFloat(8),rs.getInt(9));

				g=(Giornale) f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), rs.getString(6), null);


			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("get data").log(Level.INFO, ECCEZIONE, e);
		}
		return g;

	}

	public ObservableList<Raccolta> getGiornali() {
		ObservableList<Raccolta> catalogo = FXCollections.observableArrayList();

		query = "select * from GIORNALE";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query);
			 ResultSet rs = prepQ.executeQuery()) {
			while (rs.next()) {

				f.createRaccoltaFinale1(GIORNALE, rs.getString(1), null, rs.getString(4), null,rs.getString(4), rs.getString(3));


				f.createRaccoltaFinale2(GIORNALE, 0, rs.getInt(6), rs.getInt(7),rs.getFloat(8),rs.getInt(9));

				catalogo.add(f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), rs.getString(6), null));




			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("get libri").log(Level.INFO, ECCEZIONE, e);
		}
		return catalogo;
	}

	public ObservableList<Raccolta> getGiornaliIdTitoloAutore(Giornale g) {
		ObservableList<Raccolta> catalogo = FXCollections.observableArrayList();

		query = "select * from GIORNALE where idGiornale=? or idGiornale=? or titolo=? or autore=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ= conn.prepareStatement(query))  {

			prepQ.setInt(1,g.getId());
			prepQ.setInt(2,vis.getId());
			prepQ.setString(3,g.getTitolo());
			prepQ.setString(4,g.getEditore());

			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{
				f.createRaccoltaFinale1(GIORNALE, rs.getString(1), null, rs.getString(4), null,rs.getString(4), rs.getString(3));


				f.createRaccoltaFinale2(GIORNALE, 0, rs.getInt(6), rs.getInt(7),rs.getFloat(8),rs.getInt(9));

				catalogo.add(f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), rs.getString(6), null));


			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("get giornale id").log(Level.INFO, ECCEZIONE, e);
		}
		return catalogo;
	}

	public ObservableList<Giornale> getGiornaleIdTitoloAutore(Giornale g) {
		ObservableList<Giornale> catalogo = FXCollections.observableArrayList();

		query = "select * from GIORNALE where idGiornale=? or idGiornale=? or titolo=? or autore=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ= conn.prepareStatement(query))  {

			prepQ.setInt(1,g.getId());
			prepQ.setInt(2,vis.getId());
			prepQ.setString(3,g.getTitolo());
			prepQ.setString(4,g.getEditore());

			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{
				f.createRaccoltaFinale1(GIORNALE, rs.getString(1), null, rs.getString(4), null,rs.getString(4), rs.getString(3));


				f.createRaccoltaFinale2(GIORNALE, 0, rs.getInt(6), rs.getInt(7),rs.getFloat(8),rs.getInt(9));

				catalogo.add((Giornale) f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), rs.getString(6), null));


			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("get giornale titolo id").log(Level.INFO, ECCEZIONE, e);
		}
		return catalogo;
	}



	public void aggiornaDisponibilita(Giornale g) throws SQLException
	{
		//vedere il segno che cambia
		int d=vis.getQuantita();
		int i=g.getCopieRimanenti();
		int rim=i-d;





		query="update GIORNALE set copieRimanenti=? where  idGiornale=?";

		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setInt(2, g.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("aggiorna disp l").log(Level.INFO, ECCEZIONE, e);
		}



	}
	// Creo il Giornale nel terzo caso d'uso per l'aggiunta manuale
	public  boolean creaGiornale(Giornale g) throws SQLException  {
		int row;


		query= "INSERT INTO `GIORNALE`"
				+ "(`titolo`,"
				+ "`tipologia`,"
				+ "`lingua`,"
				+ "`editore`,"
				+ "`dataPubblicazione`,"
				+ "`copieRimanenti`,"
				+ "`disp`,"
				+ "`prezzo`)"
				+ "VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{

			prepQ.setString(1,g.getTitolo());
			prepQ.setString(2,g.getTipologia());
			prepQ.setString(3,g.getLingua());
			prepQ.setString(4,g.getEditore());
			prepQ.setDate(5, java.sql.Date.valueOf(g.getDataPubb().toString()));
			prepQ.setInt(6,g.getCopieRimanenti());
			prepQ.setInt(7, g.getDisponibilita());
			prepQ.setFloat(8, g.getPrezzo());


			row=prepQ.executeUpdate();
			state= row == 1; // true

		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("creazione giornale").log(Level.INFO, ECCEZIONE, e);
		}


		return state;


	}
	public int cancella(Giornale l) throws SQLException {
		int row;
		query="delete from GIORNALE where idGiornale=? or idGiornale=?";

		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, l.getId());
			prepQ.setInt(2,vis.getId());
			row=prepQ.executeUpdate();
		}

		java.util.logging.Logger.getLogger("Cancella Giornale").log(Level.INFO,"Giornale cancellato {0}",row);
		return row;

	}
	public  int aggiornaGiornale(Giornale g) throws SQLException  {
		int row=0;


		query=" UPDATE `GIORNALE`"
				+ "SET"
				+ "`titolo` =?,"
				+ "`tipologia` = ?,"
				+ "`lingua` = ?,"
				+ "`editore` = ?,"
				+ "`dataPubblicazione` = ?,"
				+ "`copiRim` = ?,"
				+ "`disp` = ?,"
				+ "`prezzo` = ?"
				+ "WHERE `id` = ? or id=?";
		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setString(1,g.getTitolo());
			prepQ.setString(2,g.getTipologia());
			prepQ.setString(3,g.getLingua());
			prepQ.setString(4, g.getEditore());
			prepQ.setString(5,g.getDataPubb().toString());
			prepQ.setInt(6,g.getCopieRimanenti());
			prepQ.setInt(7,g.getDisponibilita());
			prepQ.setFloat(8,g.getPrezzo());
			prepQ.setInt(9, g.getId());
			prepQ.setInt(10, vis.getId());



			row=prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("update g").log(Level.INFO, ECCEZIONE, e);
		}

		java.util.logging.Logger.getLogger("aggiorna g").log(Level.INFO," rows aggiornalte {0}",row);
		return row;

	}
	public void generaReport() throws IOException {

		try {
			if (!fd.exists()) {
				throw new IOException("file not exists");
			}
			if(fd.exists())
			{
				cleanUp(Path.of(RIEPILOGOGIORNALI));
					throw new IOException("file deleted -> not exists");

			}
		} catch (IOException e) {
			java.util.logging.Logger.getLogger("Test genera report").log(Level.INFO, ECCEZIONE, e);
			if (fd.createNewFile()) {


				try (BufferedWriter b = new BufferedWriter(new FileWriter(RIEPILOGOGIORNALI))) {

					query = "select titolo,copieRimanenti,prezzo  from GIORNALE";

					try (Connection conn = ConnToDb.connectionToDB();
						 PreparedStatement prepQ = conn.prepareStatement(query)) {

						ResultSet rs = prepQ.executeQuery();


						while (rs.next()) {

							b.write("Titolo :" + rs.getString("titolo") + "\t" + "Ricavo totale :" + rs.getInt("copieRimanenti") * rs.getFloat("prezzo") + "\n");

							b.flush();

						}

					} catch (SQLException e1) {
						java.util.logging.Logger.getLogger("Test Eccezione report").log(Level.INFO, ECCEZIONE, e1);
					}
				}
			}

		}
	}




	public void incrementaDisponibilita(Giornale g)
	{
		int d=vis.getQuantita();
		int i=g.getCopieRimanenti();

		int rim=i+d;
		query="update GIORNALE set copieRimanenti= ? where idGiornale=?";



		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setInt(2, g.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("Test incrementa disp").log(Level.INFO, ECCEZIONE, e);
		}



	}

	public void aggiornaData(Giornale g, Date sqlDate) throws SQLException {
		int row;
		query="update GIORNALE set dataPubblicazione=? where idGiornale=? or idGiornale=?";
		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setDate(1, sqlDate);
			prepQ.setInt(2, g.getId());
			prepQ.setInt(3, vis.getId());
			row=prepQ.executeUpdate();

		}

		java.util.logging.Logger.getLogger("aggiorna data").log(Level.INFO, "libri aggiornati {0}.",row);

	}

	public static void cleanUp(Path path) throws  IOException {
		Files.delete(path);
	}



}

