package laptop.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	
	private  Factory f;
	
	private  String query ;
	

	private boolean state=false;

	private ControllerSystemState vis=ControllerSystemState.getInstance();
	private  final String Rivista="Rivista";

	private static final String ECCEZIONE="eccezione generata:";


	private static final String TXT_FILE_NAME="ReportFinale/riepilogoRivista.txt";
	private File fd;

	public RivistaDao() throws IOException {
		f = new Factory();
		this.fd=new File(TXT_FILE_NAME);
		if (!fd.exists()) {
			fd.createNewFile();
		}
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
				f.createRaccoltaFinale1(Rivista, rs.getString(1), null, rs.getString(5), rs.getString(3),rs.getString(4), rs.getString(2));


				f.createRaccoltaFinale2(Rivista, 0, rs.getInt(10), rs.getInt(8),rs.getFloat(9),rs.getInt(10));

				r=(Rivista) f.createRaccoltaFinaleCompleta(Rivista, rs.getDate(7).toLocalDate(),null, null);


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

				f.createRaccoltaFinale1(Rivista, rs.getString(1), null, rs.getString(5), rs.getString(3),rs.getString(4), rs.getString(2));


				f.createRaccoltaFinale2(Rivista, 0, rs.getInt(10), rs.getInt(8),rs.getFloat(9),rs.getInt(10));

				catalogo.add(f.createRaccoltaFinaleCompleta(Rivista, rs.getDate(7).toLocalDate(),null, null));




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
				f.createRaccoltaFinale1(Rivista, rs.getString(1), null, rs.getString(5), rs.getString(3),rs.getString(4), rs.getString(2));


				f.createRaccoltaFinale2(Rivista, 0, rs.getInt(10), rs.getInt(8),rs.getFloat(9),rs.getInt(10));

				catalogo.add(f.createRaccoltaFinaleCompleta(Rivista, rs.getDate(7).toLocalDate(),null, null));


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
				f.createRaccoltaFinale1(Rivista, rs.getString(1), null, rs.getString(5), rs.getString(3),rs.getString(4), rs.getString(2));


				f.createRaccoltaFinale2(Rivista, 0, rs.getInt(10), rs.getInt(8),rs.getFloat(9),rs.getInt(10));

				catalogo.add((Rivista) f.createRaccoltaFinaleCompleta(Rivista, rs.getDate(7).toLocalDate(),null, null));


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





		query="update Rivista set copieRimanenti=? where  idRivista=?";

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
				+ "`lingua`,"
				+ "`editore`,"
				+ "`dataPubblicazione`,"
				+ "`copiRim`,"
				+ "`disp`,"
				+ "`prezzo`)"
				+ "VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{

			prepQ.setString(1,r.getTitolo());
			prepQ.setString(2,r.getTipologia());
			prepQ.setString(3,r.getLingua());
			prepQ.setString(4,r.getEditore());
			prepQ.setDate(5, java.sql.Date.valueOf(r.getDataPubb().toString()));
			prepQ.setInt(6,r.getCopieRim());
			prepQ.setInt(7, r.getDisp());
			prepQ.setFloat(8, r.getPrezzo());


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
	public void generaReport() throws IOException
	{
		FileWriter w=new FileWriter("ReportFinale/riepilogoRivista.txt");
		query="select titolo,copieVendute,prezzo as totale from Rivista";

		try (BufferedWriter b=new BufferedWriter (w)){


			try(Connection 	conn = ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
			{

				ResultSet rs=prepQ.executeQuery();


				while(rs.next())
				{




					b.write("Titolo :"+rs.getString(1)+"\t"+"Ricavo totale :" +rs.getInt(2)*rs.getFloat(3)+"\n");




					b.flush();


				}

			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, ECCEZIONE, e);
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
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, ECCEZIONE, e);
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


	


	
		
}

		
