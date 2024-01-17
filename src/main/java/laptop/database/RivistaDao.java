package laptop.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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


	private static final String RIEPILOGORIVISTE="riepilogoRiviste.txt";
	private static final String RIEPILOGORIVISTEWEB="src/main/webapp/riepilogoRiviste.txt";

	private final File fd;
	private final File fd1;
	private final GenerateDaoReportClass gRC;

	public RivistaDao() throws IOException {
		f = new Factory();
		this.fd=new File(RIEPILOGORIVISTE);
		this.fd1=new File(RIEPILOGORIVISTEWEB);
		gRC=new GenerateDaoReportClass();
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


				f.createRaccoltaFinale2(RIVISTA, 0, rs.getInt(10), rs.getInt(8),rs.getFloat(9),rs.getInt(11));

				r= (Rivista) f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(),null, null);


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
				f.createRaccoltaFinale1(RIVISTA,rs.getString(1),null,rs.getString(5),rs.getString(3),rs.getString(4),rs.getString(2));



				f.createRaccoltaFinale2(RIVISTA, 0, rs.getInt(10), rs.getInt(8),rs.getFloat(9),rs.getInt(11));

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
		String[] info=new String[7];

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
				info[0]=rs.getString("titolo");
				info[2]=rs.getString("editore");
				info[3]=rs.getString("autore");
				info[4]=rs.getString("lingua");
				info[5]=rs.getString("tipologia");
				catalogo.add((Rivista)f.creaRivista(info,rs.getString("descrizione"),rs.getDate("dataPubblicazione").toLocalDate(),rs.getInt("disp"),rs.getFloat("prezzo"),rs.getInt("copieRimanenti"),rs.getInt("idRivista")));

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
				+ "`autore` = ?,"
				+ "`lingua` = ?,"
				+ "`editore` = ?,"
				+ "`descrizione` = ?,"
				+ "`dataPubblicazione` = ?,"
				+ "`disp` = ?,"
				+ "`copieRimanenti` = ?,"
				+ "`prezzo` = ?"
				+ "WHERE `idRivista` = ? or idRivista=?";
		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setString(1,r.getTitolo());
			prepQ.setString(2,r.getTipologia());
			prepQ.setString(3,r.getAutore());
			prepQ.setString(4,r.getLingua());
			prepQ.setString(5,r.getEditore());
			prepQ.setString(6,r.getDescrizione());
			prepQ.setDate(7, Date.valueOf(r.getDataPubb().toString()));
			prepQ.setInt(8,r.getDisp());
			prepQ.setInt(9,r.getCopieRim());
			prepQ.setFloat(10,r.getPrezzo());
			prepQ.setInt(11,r.getId());
			prepQ.setFloat(12,vis.getId());



			row=prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("update r").log(Level.INFO, ECCEZIONE, e);
		}

		return row;

	}
	public void generaReport() throws IOException {
		Path path = Path.of(RIEPILOGORIVISTE);
		Path path1 = Path.of(RIEPILOGORIVISTEWEB);

		try {
			cleanUp(path1);

			if(!fd.exists())
				throw new IOException("file "+ fd.getPath() +" not exists -> creating");
			if(fd.exists())
			{
				cleanUp(path);
				throw new IOException("file "+ fd.getPath()+" -> deleted not exists -> creating");
			}

		} catch (IOException e) {
			java.util.logging.Logger.getLogger("Test Eccezione genera report").log(Level.INFO, ECCEZIONE, e);

			if(fd.createNewFile()) {
				java.util.logging.Logger.getLogger("Test Eccezione genera report").log(Level.INFO, "creating file {0}.", fd.getPath());
				//codice per report non so se mettere in altra classe
				if(!gRC.generateReport("rivista",RIEPILOGORIVISTE))
					throw new IOException(" report not generaterd");
				try {
					if (!fd1.exists())
						throw new IOException("file "+ fd1.getPath()+ "-> not exists");
					if(fd1.exists())
					{
						cleanUp(path1);
						throw new IOException("file "+ fd1.getPath()+" deleted -> not exists -> creating");
					}
				}catch (IOException e1)
				{
					java.util.logging.Logger.getLogger("Test Eccezione genera report").log(Level.INFO, ECCEZIONE, e1);

					if(fd1.createNewFile())
						java.util.logging.Logger.getLogger("Test Eccezione genera report").log(Level.INFO, "creating file {0}.", fd1.getPath());

				}

			}

		}
		java.util.logging.Logger.getLogger("Test Eccezione genera report").log(Level.INFO, "coping file ");

		Files.copy(path,path1, StandardCopyOption.REPLACE_EXISTING);





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


	private void cleanUp(Path path) throws IOException {
		Files.delete(path);
	}


	
		
}

		
