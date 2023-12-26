package laptop.database;

import java.io.BufferedWriter;
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
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Raccolta;
import laptop.utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LibroDao {

	private Factory f;
	private String query;



	private boolean state = false;
	private ControllerSystemState vis = ControllerSystemState.getInstance();

	private static final String LIBRO = "libro";
	private static final String ECCEZIONE = "ECCEZIONE generata:";

	public LibroDao()
	{
		f=new Factory();
	}



	public Libro getData(Libro l) {

		String query ="select * from LIBRO where idLibro=? or idLibro=?";

		try (Connection conn = ConnToDb.connectionToDB();
			PreparedStatement prepQ= conn.prepareStatement(query))  {

			prepQ.setInt(1,l.getId());
			prepQ.setInt(2,vis.getId());
			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{
				f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getString(7));


				f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getInt(10), rs.getInt(12),rs.getFloat(13),rs.getInt(14));

				l=(Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11));


			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("get data").log(Level.INFO, ECCEZIONE, e);
		}
	return l;

	}

	public ObservableList<Raccolta> getLibri() {
		ObservableList<Raccolta> catalogo = FXCollections.observableArrayList();

		query = "select * from LIBRO";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query);
			 ResultSet rs = prepQ.executeQuery()) {
			while (rs.next()) {

				f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getString(7));


				f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getInt(10), rs.getInt(12),rs.getFloat(13),rs.getInt(14));



					catalogo.add(f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11)));



			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("get libri").log(Level.INFO, ECCEZIONE, e);
		}
		return catalogo;
	}

	public ObservableList<Raccolta> getLibriIdTitoloAutore(Libro l) {
		ObservableList<Raccolta> catalogo = FXCollections.observableArrayList();

		query = "select * from LIBRO where idLibro=? or idLibro=? or titolo=? or autore=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ= conn.prepareStatement(query))  {

			prepQ.setInt(1,l.getId());
			prepQ.setInt(2,vis.getId());
			prepQ.setString(3,l.getTitolo());
			prepQ.setString(4,l.getAutore());

			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{
				f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getString(7));


				f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getInt(10), rs.getInt(12),rs.getFloat(13),rs.getInt(14));



				catalogo.add(f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11)));


			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("get data").log(Level.INFO, ECCEZIONE, e);
		}
		return catalogo;
	}

	public ObservableList<Libro> getLibroIdTitoloAutore(Libro l) {
		ObservableList<Libro> catalogo = FXCollections.observableArrayList();

		query = "select * from LIBRO where idLibro=? or idLibro=? or titolo=? or autore=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ= conn.prepareStatement(query))  {

			prepQ.setInt(1,l.getId());
			prepQ.setInt(2,vis.getId());
			prepQ.setString(3,l.getTitolo());
			prepQ.setString(4,l.getAutore());

			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{
				f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getString(7));


				f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getInt(10), rs.getInt(12),rs.getFloat(13),rs.getInt(14));



				catalogo.add((Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11)));


			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("get data").log(Level.INFO, ECCEZIONE, e);
		}
		return catalogo;
	}





	public void aggiornaDisponibilita(Libro l) throws SQLException
	{
		//vedere il segno che cambia
		int d=vis.getQuantita();
		int i=l.getNrCopie();
		int rim=i-d;





		query="update LIBRO set copieRimanenti=? where  idLibro=?";

		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setInt(2, l.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("aggiorna disp l").log(Level.INFO, ECCEZIONE, e);
		}



	}
	// Creo il libro nel terzo caso d'uso per l'aggiunta manuale
	public boolean creaLibrio(Libro l) throws SQLException
	{

		query= "INSERT INTO `LIBRO`"
				+ "(`titolo`,"
				+ "`numeroPagine`,"
				+ "`Cod_isbn`,"
				+ "`editore`,"
				+ "`autore`,"
				+ "`lingua`,"
				+ "`categoria`,"
				+ "`dataPubblicazione`,"
				+ "`recensione`,"
				+ " copieVendute,"
				+ "`breveDescrizione`,"
				+ "`disp`,"
				+ "`prezzo`,"
				+ "`copieRimanenti`)"
				+ "VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setString(1,l.getTitolo());
			prepQ.setInt(2,l.getNrPagine());
			prepQ.setString(3,l.getCodIsbn());
			prepQ.setString(4,l.getEditore());
			prepQ.setString(5,l.getAutore());
			prepQ.setString(6,l.getLingua());
			prepQ.setString(7,l.getCategoria());
			prepQ.setDate(8, java.sql.Date.valueOf(l.getDataPubb().toString()));
			prepQ.setString(9, l.getRecensione());
			//copie vendute
			prepQ.setInt(10,l.getNrCopie());
			prepQ.setString(11, l.getDesc());
			prepQ.setInt(12, l.getDisponibilita());
			prepQ.setFloat(13, l.getPrezzo());
			prepQ.setInt(14,l.getNrCopie());
			int row= prepQ.executeUpdate();
			if(row==1) {
				state = true; // true

			}
		}catch(SQLException e) {

			state = false;
			java.util.logging.Logger.getLogger("crea libro").log(Level.INFO, ECCEZIONE, e);
		}

		return state;

	}

	public int cancella(Libro l) throws SQLException {
		int row;
		query="delete from LIBRO where idLibro=? or Cod_isbn=?";

		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, l.getId());
			prepQ.setString(2,l.getCodIsbn());
			row=prepQ.executeUpdate();
		}

		java.util.logging.Logger.getLogger("Cancella libro").log(Level.INFO,"libro cancellato {0}",row);
		return row;

	}
	public boolean aggiornaLibro(Libro l) throws NullPointerException
	{


		//int rowAffected=0;
		boolean status=false;


		System.out.println("libro id nel dao:"+ l.getId());
		System.out.println("libro titolo nel dao:"+ l.getTitolo());
		System.out.println("libro data nel dao:"+ l.getDataPubb());


		query=" UPDATE LIBRO SET  `titolo` =?, `numeroPagine` = ?, `Cod_isbn` = ?, `editore` = ?, `autore` = ?, `lingua` = ?, `categoria` = ?, `dataPubblicazione` = ?, `recensione` = ?, `copieVendute` = ?, `breveDescrizione` =?, `disp` = ?,`prezzo` = ?,`copieRimanenti` =? WHERE `idLibro`= ? ";
		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{

			prepQ.setString(1,l.getTitolo());
			prepQ.setInt(2,l.getNrPagine());
			prepQ.setString(3,l.getCodIsbn());
			prepQ.setString(4,l.getEditore());
			prepQ.setString(5,l.getAutore());
			prepQ.setString(6,l.getLingua());
			prepQ.setString(7,l.getCategoria());
			prepQ.setString(8, l.getDataPubb().toString());
			prepQ.setString(9,l.getRecensione());
			prepQ.setInt(10,l.getNrCopie());
			prepQ.setString(11,l.getDesc());
			prepQ.setInt(12,l.getDisponibilita());
			prepQ.setFloat(13,l.getPrezzo());
			prepQ.setInt(14,l.getNrCopie());
			prepQ.setInt(15, l.getId());



			System.out.println("rows aff"+ prepQ.executeUpdate());

			if(prepQ.executeUpdate()==1)
			{
				status=true;
			}




		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("Aggiornamento libro").log(Level.INFO, ECCEZIONE,e);

		}




		//java.util.logging.Logger.getLogger("Aggiornamento libro").log(Level.INFO, "row affected {0}",rowAffected);
		return status;

	}
	public void generaReport() throws IOException
	{
		FileWriter w=new FileWriter("ReportFinale/riepilogoLibro.txt");
		query="select titolo,copieVendute,prezzo as totale from LIBRO";

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
	public void incrementaDisponibilita(Libro l)
	{
		int d=vis.getQuantita();
		int i=l.getNrCopie();

		int rim=i+d;
		query="update LIBRO set copieRimanenti= ? where Cod_isbn=? or idLibro=?";



		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setString(2, l.getCodIsbn());
			prepQ.setInt(3, l.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, ECCEZIONE, e);
		}



	}

	public void aggiornaData(Libro l, Date sqlDate) throws SQLException {
		int row;
		query="update LIBRO set dataPubblicazione=? where idLibro=? or idLibro=?";
		try(Connection conn=ConnToDb.connectionToDB();
			PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setDate(1, sqlDate);
			prepQ.setInt(2, l.getId());
			prepQ.setInt(3, vis.getId());
			row=prepQ.executeUpdate();

		}

		java.util.logging.Logger.getLogger("aggiorna data").log(Level.INFO, "libri aggiornati {0}.",row);

	}

}

