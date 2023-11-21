package laptop.utilities;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;


public class ConnToDb 
{
	
	protected static Connection conn = null;

	
	private static final String connessione="Tentativo di conessione al server..........\\\\n";


	private static final String errore="Errore in mysql..........\\n";


	private static final ResourceBundle rB=ResourceBundle.getBundle("configurations/configDb");

	private static boolean statusConnSys;
	private static boolean statusConnDB;

	public static boolean getStatusConnSys() {
		return statusConnSys;
	}

	public static void setStatusConnSys(boolean statusConnSys) {
		ConnToDb.statusConnSys = statusConnSys;
	}

	public static boolean getStatusConnDB() {
		return statusConnDB;
	}

	public static void setStatusConnDB(boolean statusConnDB) {
		ConnToDb.statusConnDB = statusConnDB;
	}

	public static Connection generalConnection()
	{


		String driver=rB.getString("driver");
		String user=rB.getString("user");
		String pwd=rB.getString("pwdLinux");
		String url=rB.getString("url");
		try
		{
			Class.forName(driver);
			java.util.logging.Logger.getLogger("Test General connection").log(Level.INFO, connessione);
			conn = DriverManager.getConnection(url, user,pwd);
			java.util.logging.Logger.getLogger("Test General connection standard").log(Level.INFO, "Connesso standard a sys........\n");
			setStatusConnSys(true);
		}
		catch (SQLException | ClassNotFoundException e1)
		{
			java.util.logging.Logger.getLogger("Test general connection error").log(Level.INFO, errore, e1);
			setStatusConnSys(false);

		}

		return conn;

	}
	public static Connection ConnectionToDB()
	{


		String driver=rB.getString("driver");
		String user=rB.getString("user");
		String pwd=rB.getString("pwdLinux");
		String url=rB.getString("urlDb");
		try
		{
			Class.forName(driver);
			java.util.logging.Logger.getLogger("Test connection to my db").log(Level.INFO, connessione);
			conn = DriverManager.getConnection(url, user,pwd);
			java.util.logging.Logger.getLogger("Test connection to my db").log(Level.INFO, "Connesso standard a ISPW........\n");
			setStatusConnDB(true);
		}
		catch (SQLException | ClassNotFoundException e1)
		{
			java.util.logging.Logger.getLogger("TTest connection to my db error").log(Level.INFO, errore, e1);
			setStatusConnDB(false);

		}

		return conn;

	}



	public static void creaPopolaDb() throws FileNotFoundException {
		ConnToDb.generalConnection();
		ConnToDb.ConnectionToDB();
		boolean statusSys=ConnToDb.getStatusConnSys();
		boolean statusDb=ConnToDb.getStatusConnDB();
		boolean status=statusSys&&statusDb;
		if(Boolean.FALSE.equals(status)) {
			Reader reader = new BufferedReader(new FileReader("FileSql/export-db.sql"));
			ScriptRunner sr = new ScriptRunner(conn);
			sr.setSendFullScript(false);
			sr.runScript(reader);
		}
		else {
			java.util.logging.Logger.getLogger("creaPopolaDB").log(Level.INFO, "entrambi popolati");

		}
	}


/*

	public static  boolean initailConnection()
	{

		ResourceBundle rB=ResourceBundle.getBundle("configurations/configInitial");
		
		
		String driver=rB.getString(driv);
		String user=rB.getString("user");
		String pwd=rB.getString("pwdLinux");
		String url=rB.getString("url");
		
		
		
		
		try
		{
			Class.forName(driver);

			conn = DriverManager.getConnection(url,user,pwd);
			java.util.logging.Logger.getLogger("Test InitialConnection").log(Level.INFO, "Connesso initial a sys.....\n");

			status= true;

		} 
		catch (SQLException | ClassNotFoundException  e1)
		{
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, "errore in mysql", e1);

		} 
		
		return status;
	}

	public static boolean connection() throws SQLException {
		
		boolean status=false;
		ResourceBundle rB=ResourceBundle.getBundle("configurations/configDb");
		String driver=rB.getString(driv);
		String user=rB.getString("user");
		String pwd=rB.getString("pwdLinux");
		String url=rB.getString("url");

		try 
		{
			if(initailConnection()) 
			{
				//actuac DB project

				 Class.forName(driver);
				 java.util.logging.Logger.getLogger("Test connection").log(Level.INFO, connessione);
				
				

				conn = DriverManager.getConnection(url, user,pwd);

				java.util.logging.Logger.getLogger("Test connection standard").log(Level.INFO, "Connesso standard ........\n");
				

				status= true;
			}
			
		} 
		catch (SQLException | ClassNotFoundException  e1) 
		{
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, errore, e1);

		} 
		

		return status;
	}
	
	public static Connection generalConnection()
	{

		ResourceBundle rB=ResourceBundle.getBundle("configurations/configDb");
		String driver=rB.getString(driv);
		String user=rB.getString("user");
		String pwd=rB.getString("pwdLinux");
		String url=rB.getString("url");
		try
		{
			Class.forName(driver);
			java.util.logging.Logger.getLogger("Test General connection").log(Level.INFO, connessione);
			conn = DriverManager.getConnection(url, user,pwd);
			java.util.logging.Logger.getLogger("Test General connection standard").log(Level.INFO, "Connesso standard ........\n");

		} 
		catch (SQLException  | ClassNotFoundException e1)
		{
			java.util.logging.Logger.getLogger("Test general connection error").log(Level.INFO, errore, e1);


		} 
		
		return conn;
	
	}
	private ConnToDb(){
		
	}



 */


}

