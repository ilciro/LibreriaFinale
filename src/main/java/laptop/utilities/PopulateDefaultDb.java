package laptop.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

import org.apache.ibatis.jdbc.ScriptRunner;

public class PopulateDefaultDb {

	
	private static Connection conn;
	private static ScriptRunner sr;
	private static boolean state=false;

	private PopulateDefaultDb()
	{

	}
	
	public static boolean populateDefaultDb() throws FileNotFoundException, SQLException
	{
			createLibri();
			 createGiornale() ;
			  createRivista();
			   createUser(); 
			   createNegozio();
			   daiPrivilegi();
		
			state=true;
		
		return state;
	}

	public static boolean createLibri() throws FileNotFoundException
	{
		java.util.logging.Logger.getLogger("Test create libri").log(Level.INFO,"---------Chiamo stored insLibri---------\n\n");
		
			conn=ConnToDb.generalConnection();
			sr = new ScriptRunner(conn);
			sr.setSendFullScript(true);
			Reader reader = new BufferedReader(new FileReader("FileSql/storedInsLibri.sql"));
			//Running the script
			sr.runScript(reader);
			state=true;

			
		
		return state;
	}

	public static boolean createGiornale() throws FileNotFoundException
	{
		java.util.logging.Logger.getLogger("Test create giornali").log(Level.INFO,"---------Chiamo stored insGiornali---------\n\n");

		
			conn=ConnToDb.generalConnection();
			sr = new ScriptRunner(conn);
			sr.setSendFullScript(true);
			Reader reader = new BufferedReader(new FileReader("FileSql/stroredInsGiornali.sql"));
			//Running the script
			sr.runScript(reader);
			state= true;
		
		return state;
	}

	public static boolean createRivista() throws FileNotFoundException
	{
		java.util.logging.Logger.getLogger("Test create riviste").log(Level.INFO,"---------Chiamo stored insRiviste---------\n\n");

		conn=ConnToDb.generalConnection();
			sr = new ScriptRunner(conn);
			sr.setSendFullScript(true);
			Reader reader = new BufferedReader(new FileReader("FileSql/storedInsRiviste.sql"));
			//Running the script
			sr.runScript(reader);
				state=true;
		
		
		return state;
	}

	public static boolean createUser() throws FileNotFoundException
	{
		java.util.logging.Logger.getLogger("Test create users").log(Level.INFO,"---------Chiamo stored insUsers---------\n\n");

		
			conn=ConnToDb.generalConnection();
			sr = new ScriptRunner(conn);
			sr.setSendFullScript(true);
			Reader 	reader = new BufferedReader(new FileReader("FileSql/storedInsUtenti.sql"));
			sr.runScript(reader);
			state=true;
		
		return state;
	}

	public static boolean createNegozio() throws FileNotFoundException
	{
		java.util.logging.Logger.getLogger("Test create negozio").log(Level.INFO,"---------Chiamo stored insNegozi---------\n\n");

		
			conn=ConnToDb.generalConnection();
			sr = new ScriptRunner(conn);
			sr.setSendFullScript(true);
			Reader reader = new BufferedReader(new FileReader("FileSql/storedInsNegozio.sql"));
			sr.runScript(reader);
			state=true;
	return state;


	}
	public static void daiPrivilegi() throws SQLException
	{
		String query="set sql_safe_updates=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1,0);
			prepQ.executeUpdate();

		}catch(SQLException e)
		{

			java.util.logging.Logger.getLogger("dai privilegi").log(Level.INFO, "eccezione ottenuta nel dare i provilegi", e);
		}

		
		java.util.logging.Logger.getLogger("Populate default db").log(Level.INFO, "Dai privilegi generali");
		

	}
	

}
