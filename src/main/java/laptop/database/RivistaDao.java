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
import laptop.model.raccolta.Raccolta;
import laptop.model.raccolta.Rivista;
import laptop.utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class 	RivistaDao {
	
	private final Factory f;
	
	private  String query ;
	

	private boolean state=false;

	private final ControllerSystemState vis=ControllerSystemState.getInstance();
	private  static final String RIVISTA="rivista";

	private static final String ECCEZIONE="eccezione generata:";


	private static final String RIEPILOGORIVISTE="ReportFinale/riepilogoRivista.txt";
	private final File fd;

	public RivistaDao() throws IOException {
		f = new Factory();
		this.fd=new File(RIEPILOGORIVISTE);

	}
	public Rivista getData(Rivista r) {

		 query ="select * from RIVISTA where idRivista=? or idRivista=?";

		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ= conn.prepareStatement(query))  {

			prepQ.setInt(1,r.getId());
			prepQ.setInt(2,vis.getId());
			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{
				f.createRaccoltaFinale1(RIVISTA, rs.getString(1), null, rs.getString(5), rs.getString(3),rs.getString(4), rs.getString(2));


				f.createRaccoltaFinale2(RIVISTA, 0, rs.getInt(10), rs.getInt(8),rs.getFloat(9),rs.getInt(10));

				r=(Rivista) f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(),null, null);


			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("get data").log(Level.INFO, ECCEZIONE, e);
		}
		return r;

	}

	public ObservableList<Raccolta> getRiviste() {
		ObservableList<Raccolta> catalogo = FXCollections.observableArrayList();

		query = "select * from RIVISTA";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query);
			 ResultSet rs = prepQ.executeQuery()) {
			while (rs.next()) {

				f.createRaccoltaFinale1(RIVISTA, rs.getString(1), null, rs.getString(5), rs.getString(3),rs.getString(4), rs.getString(2));


				f.createRaccoltaFinale2(RIVISTA, 0, rs.getInt(10), rs.getInt(8),rs.getFloat(9),rs.getInt(10));

				catalogo.add(f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(),null, null));




			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("get libri").log(Level.INFO, ECCEZIONE, e);
		}
		return catalogo;
	}

	public ObservableList<Raccolta> getRivisteIdTitoloAutore(Rivista r) {
		ObservableList<Raccolta> catalogo = FXCollections.observableArrayList();

		query = "select * from RIVISTA where idRivista=? or idRivista=? or titolo=? or autore=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ= conn.prepareStatement(query))  {

			prepQ.setInt(1,r.getId());
			prepQ.setInt(2,vis.getId());
			prepQ.setString(3,r.getTitolo());
			prepQ.setString(4,r.getEditore());

			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{
				f.createRaccoltaFinale1(RIVISTA, rs.getString(1), null, rs.getString(5), rs.getString(3),rs.getString(4), rs.getString(2));


				f.createRaccoltaFinale2(RIVISTA, 0, rs.getInt(10), rs.getInt(8),rs.getFloat(9),rs.getInt(10));

				catalogo.add(f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(),null, null));


			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("get data riviste obs").log(Level.INFO, ECCEZIONE, e);
		}
		return catalogo;
	}

	public ObservableList<Rivista> getRivistaIdTitoloAutore(Rivista r) {
		ObservableList<Rivista> catalogo = FXCollections.observableArrayList();

		query = "select * from RIVISTA where idRivista=? or idRivista=? or titolo=? or autore=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ= conn.prepareStatement(query))  {

			prepQ.setInt(1,r.getId());
			prepQ.setInt(2,vis.getId());
			prepQ.setString(3,r.getTitolo());
			prepQ.setString(4,r.getEditore());

			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{
				f.createRaccoltaFinale1(RIVISTA, rs.getString(1), null, rs.getString(5), rs.getString(3),rs.getString(4), rs.getString(2));


				f.createRaccoltaFinale2(RIVISTA, 0, rs.getInt(10), rs.getInt(8),rs.getFloat(9),rs.getInt(10));

				catalogo.add((Rivista) f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(),null, null));


			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("get data rivista obs").log(Level.INFO, ECCEZIONE, e);
		}
		return catalogo;
	}



	public void aggiornaDisponibilita(Rivista r) throws SQLException
	{
		//vedere il segno che cambia
		int d=vis.getQuantita();
		int i=r.getCopieRim();
		int rim=i-d;





		query="update RIVISTA set copieRimanenti=? where  idRivista=?";

		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setInt(2, r.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("aggiorna disp l").log(Level.INFO, ECCEZIONE, e);
		}



	}
	// Creo il Rivista nel terzo caso d'uso per l'aggiunta manuale
	public  boolean creaRivista(Rivista r) throws SQLException  {
		int row;


		query= "INSERT INTO `RIVISTA`"
				+ "(`titolo`,"
				+ "`tipologia`,"
				+ "`autore`,"
				+ "`lingua`,"
				+ "`editore`,"
				+ "`descrizione`,"
				+ "`dataPubblicazione`,"
				+ "`copieRimanenti`,"
				+ "`disp`,"
				+ "`prezzo`)"
				+ "VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?)";
		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{

			prepQ.setString(1,r.getTitolo());
			prepQ.setString(2,r.getTipologia());
			prepQ.setString(3,r.getAutore());
			prepQ.setString(4,r.getLingua());
			prepQ.setString(5,r.getEditore());
			prepQ.setString(6,r.getDescrizione());
			prepQ.setDate(7, java.sql.Date.valueOf(r.getDataPubb().toString()));
			prepQ.setInt(8,r.getCopieRim());
			prepQ.setInt(9, r.getDisp());
			prepQ.setFloat(10, r.getPrezzo());


			row=prepQ.executeUpdate();
			state= row == 1; // true

		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("creazione Rivista").log(Level.INFO, ECCEZIONE, e);
		}


		return state;


	}
	public int cancella(Rivista r) throws SQLException {
		int row;
		query="delete from RIVISTA where idRivista=? or idRivista=?";

		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, r.getId());
			prepQ.setInt(2,vis.getId());
			row=prepQ.executeUpdate();
		}

		java.util.logging.Logger.getLogger("Cancella Rivista").log(Level.INFO,"Rivista cancellato {0}",row);
		return row;

	}
	public  int aggiornaRivista(Rivista r) throws SQLException  {
		int row=0;


		query=" UPDATE `RIVISTA`"
				+ "SET"
				+ "`titolo` =?,"
				+ "`tipologia` = ?,"
				+ "`lingua` = ?,"
				+ "`editore` = ?,"
				+ "`dataPubblicazione` = ?,"
				+ "`copiRim` = ?,"
				+ "`disp` = ?,"
				+ "`prezzo` = ?"
				+ "WHERE `idRivista` = ? or idRivista=?";
		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setString(1,r.getTitolo());
			prepQ.setString(2,r.getTipologia());
			prepQ.setString(3,r.getLingua());
			prepQ.setString(4, r.getEditore());
			prepQ.setString(5,r.getDataPubb().toString());
			prepQ.setInt(6,r.getCopieRim());
			prepQ.setInt(7,r.getDisp());
			prepQ.setFloat(8,r.getPrezzo());
			prepQ.setInt(9, r.getId());
			prepQ.setInt(10, vis.getId());



			row=prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("update r").log(Level.INFO, ECCEZIONE, e);
		}

		java.util.logging.Logger.getLogger("aggiorna r").log(Level.INFO," rows aggiornalte {0}",row);
		return row;

	}
	public void generaReport() throws IOException {

		try {
			if (!fd.exists()) {
				throw new IOException("file not exists");
			}
			if(fd.exists())
			{
				cleanUp(Path.of(RIEPILOGORIVISTE));
					throw new IOException("file deleted -> not exists");

			}
		} catch (IOException e) {
			java.util.logging.Logger.getLogger("Test Eccezione genera report").log(Level.INFO, ECCEZIONE, e);
			if (fd.createNewFile()) {


				try (BufferedWriter b = new BufferedWriter(new FileWriter(RIEPILOGORIVISTE))) {

					query = "select titolo,copieRimanenti,prezzo  from RIVISTA";

					try (Connection conn = ConnToDb.connectionToDB();
						 PreparedStatement prepQ = conn.prepareStatement(query)) {

						ResultSet rs = prepQ.executeQuery();


						while (rs.next()) {

							b.write("Titolo :" + rs.getString("titolo") + "\t" + "Ricavo totale :" + rs.getInt("copieRimanenti") * rs.getFloat("prezzo") + "\n");

							b.flush();

						}

					} catch (SQLException e1) {
						java.util.logging.Logger.getLogger("Test Eccezione sql").log(Level.INFO, ECCEZIONE, e1);
					}
				}
			}

		}
	}

	public void incrementaDisponibilita(Rivista r)
	{
		int d=vis.getQuantita();
		int i=r.getCopieRim();

		int rim=i+d;
		query="update RIVISTA set copieRimanenti= ? where idRivista=?";



		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setInt(2, r.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("Test Eccezione incrementa disp").log(Level.INFO, ECCEZIONE, e);
		}



	}

	public void aggiornaData(Rivista r, Date sqlDate) throws SQLException {
		int row;
		query="update RIVISTA set dataPubblicazione=? where idRivista=? or idRivista=?";
		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setDate(1, sqlDate);
			prepQ.setInt(2, r.getId());
			prepQ.setInt(3, vis.getId());
			row=prepQ.executeUpdate();

		}

		java.util.logging.Logger.getLogger("aggiorna data").log(Level.INFO, "libri aggiornati {0}.",row);

	}


	public void cleanUp(Path path) throws IOException {
		Files.delete(path);
	}


	
		
}

		
